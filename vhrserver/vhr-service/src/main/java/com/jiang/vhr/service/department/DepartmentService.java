package com.jiang.vhr.service.department;

import com.jiang.vhr.model.Department;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-10  13:09
 */
public interface DepartmentService {


    /**
     * 获取全部的部门信息（递归方式）
     *
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加一个新的部门
     *
     * @param dep
     * @return
     */
    Boolean addDep(Department dep);

    /**
     * 根据 id删除部门
     *
     * @param id
     * @return
     */
    Integer deleteDep(Integer id);

    /**
     * 获取全部部门（平行查询获取所有行，非嵌套子级形式）
     *
     * @return
     */
    List<Department> getAllDepartmentsWithNotChildren();
}
