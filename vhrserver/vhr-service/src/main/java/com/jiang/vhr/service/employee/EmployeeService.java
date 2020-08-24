package com.jiang.vhr.service.employee;

import com.github.pagehelper.PageInfo;
import com.jiang.vhr.model.Employee;

import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-15  21:42
 */
public interface EmployeeService {

    /**
     * 分页获取员工信息
     *
     * @param page
     * @param size
     * @return
     */
    PageInfo getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope);


    /**
     * 分页获取员工信息（带有工资账套）
     *
     * @param page
     * @param size
     * @return
     */
    PageInfo getEmployeeByPageWithSalary(Integer page, Integer size);

    /**
     * 新增一个员工
     *
     * @param employee
     * @return
     */
    Integer addEmp(Employee employee);

    /**
     * 获取最大工号
     *
     * @return
     */
    Integer maxWorkId();

    /**
     * 根据emp id删除 员工
     *
     * @param id
     * @return
     */
    Integer deleteEmpByEid(Integer id);

    /**
     * 修改员工根据id
     *
     * @param employee
     * @return
     */
    Integer updateEmp(Employee employee);

    /**
     * 获取全部的员工
     *
     * @return
     */
    List<Employee> getAllEmployees();


    /**
     * 批量添加员工
     *
     * @param list
     * @return
     */
    Integer addEmps(List<Employee> list);

    /**
     * 修改员工的所属工资套
     *
     * @param eid
     * @param sid
     * @return
     */
    Integer updateEmployeeSalaryById(Integer eid, Integer sid);
}
