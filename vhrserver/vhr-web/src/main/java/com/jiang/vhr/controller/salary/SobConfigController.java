package com.jiang.vhr.controller.salary;

import com.github.pagehelper.PageInfo;
import com.jiang.vhr.model.Salary;
import com.jiang.vhr.service.employee.EmployeeService;
import com.jiang.vhr.service.salary.SalaryService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-03-04  18:27
 * 员工工资账套设置
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    /**
     * 分页获取员工（带有工资套）
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public PageInfo getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    /**
     * 获取全部工资账套
     *
     * @return
     */
    @GetMapping("/salaries")
    public List<Salary> getAllSalarys() {
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespUtil updateEmployeeSalaryById(Integer eid, Integer sid) {
        Integer result = employeeService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2) {
            return RespUtil.ok("更新成功");
        }
        return RespUtil.error("更新失败");
    }
}
