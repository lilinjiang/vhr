package com.jiang.vhr.controller.system.basic;

import com.jiang.vhr.model.Department;
import com.jiang.vhr.service.department.DepartmentService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-10  13:07
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取全部部门
     *
     * @return
     */
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * 新增部门
     *
     * @param dep
     * @return
     */
    @PostMapping("/")
    public RespUtil addDep(@RequestBody Department dep) {
        System.out.println("dep = " + dep);
        if (departmentService.addDep(dep)) {
            return RespUtil.ok("添加成功！", dep);
        }
        return RespUtil.error("添加失败！");
    }

    /**
     * 根据id删除部门
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespUtil deleteDep(@PathVariable Integer id) {
        int result = departmentService.deleteDep(id);
        if (result == 1) {
            return RespUtil.ok("删除成功！");
        } else if (result == -2) {
            return RespUtil.error("该部门下有子部门, 删除失败 !");
        } else if (result == -1) {
            return RespUtil.error("该部门下有关联员工, 删除失败 !");
        } else if (result == -3) {
            return RespUtil.error("该部门不存在, 删除失败 !");
        }
        return RespUtil.error("删除失败 !");
    }
}
