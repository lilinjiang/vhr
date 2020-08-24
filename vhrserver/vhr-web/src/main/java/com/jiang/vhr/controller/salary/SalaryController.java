package com.jiang.vhr.controller.salary;

import com.jiang.vhr.model.Salary;
import com.jiang.vhr.service.salary.SalaryService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-03-02  20:57
 * 工资账套
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * 获取全部的工资账套
     *
     * @return
     */
    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }


    @PostMapping("/")
    public RespUtil addSalary(@RequestBody Salary salary) {
        if (salaryService.addSalary(salary) == 1) {
            return RespUtil.ok("添加成功！");
        }
        return RespUtil.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespUtil deleteSalaryById(@PathVariable Integer id) {
        if (salaryService.deleteSalaryById(id) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }

    @PutMapping("/")
    public RespUtil updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateSalary(salary) == 1) {
            return RespUtil.ok("修改成功！");
        }
        return RespUtil.error("修改失败！");
    }
}
