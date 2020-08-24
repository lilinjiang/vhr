package com.jiang.vhr.service.salary;

import com.jiang.vhr.model.Salary;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-03-02  21:00
 * 工资账套接口
 */
public interface SalaryService {

    /**
     * 获取所有工资账套
     *
     * @return
     */
    List<Salary> getAllSalaries();

    /**
     * 新增一个工资账套
     *
     * @param salary
     * @return
     */
    Integer addSalary(Salary salary);


    /**
     * 根据工资账套 id删除工资账套
     *
     * @param id
     * @return
     */
    Integer deleteSalaryById(Integer id);

    /**
     * 更新工资账套
     *
     * @param salary
     * @return
     */
    Integer updateSalary(Salary salary);
}
