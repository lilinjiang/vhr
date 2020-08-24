package com.jiang.vhr.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.vhr.mapper.department.DepartmentMapper;
import com.jiang.vhr.mapper.employee.EmployeeMapper;
import com.jiang.vhr.model.Department;
import com.jiang.vhr.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-10  13:09
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取全部的部门（递归方式嵌套）
     *
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    /**
     * 新增一个部门
     *
     * @param dep
     * @return
     */
    @Override
    @Transactional
    public Boolean addDep(Department dep) {
        try {
            //根据父级id 查询父级部门对象
            Department parentDepartment = departmentMapper.selectById(dep.getParentId());
            dep.setEnabled(true);
            dep.setParent(false);
            //新部门
            departmentMapper.insert(dep);
            //生成部门路径
            dep.setDepPath(parentDepartment.getDepPath() + "." + dep.getId());
            //修改部门路径
            departmentMapper.updateById(dep);
            //如果此时父级部门表示自己不是父级则该为父级
            if (!parentDepartment.getParent()) {
                parentDepartment.setParent(true);
                //确认修改父级对象
                departmentMapper.updateById(parentDepartment);
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 根据 id删除部门
     * <p>
     * 如果需要删除的部门下有子部门则不能删除
     * 如果删除的部门下有员工则不能删除
     * <p>
     * 删除部门后需要判断该部门的父部门是否还存在员工如果不存在则将父部门的isParent该为false 0
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Integer deleteDep(Integer id) {

        //查出要删除的部门对象
        Department deleteDepartment = departmentMapper.selectById(id);
        if (deleteDepartment == null) {
            //不存在此部门
            return -3;
        }
        //判断需要删除的部门对象是否存在子部门，如果存在则返回-2 删除失败
        if (deleteDepartment.getParent()) {
            return -2;
        }
        //查询需要删除的部门下是否存在员工
        QueryWrapper<Employee> wrapper = new QueryWrapper();
        wrapper.eq("departmentId", id);
        Integer integer = employeeMapper.selectCount(wrapper);
        if (integer > 0) {
            return -1;
        }
        //删除部门
        int deleteResult = departmentMapper.deleteById(id);
        if (deleteResult > 0) {
            //查询父级部门下是否存在子部门
            QueryWrapper<Department> depWrapper = new QueryWrapper();
            depWrapper.eq("parentId", deleteDepartment.getParentId());
            Integer parentDepChildrenCount = departmentMapper.selectCount(depWrapper);
            //如果被删除的部门的父部门下没有子部门则将父部门的isParent该为false 0
            if (parentDepChildrenCount == 0) {
                Department parentDep = new Department();
                parentDep.setId(deleteDepartment.getParentId());
                parentDep.setParent(false);
                departmentMapper.updateById(parentDep);
            }
        }
        return 1;
    }

    /**
     * 获取全部部门（平行查询获取所有行，非嵌套子级形式）
     *
     * @return
     */
    @Override
    public List<Department> getAllDepartmentsWithNotChildren() {
        return departmentMapper.selectList(null);
    }
}
