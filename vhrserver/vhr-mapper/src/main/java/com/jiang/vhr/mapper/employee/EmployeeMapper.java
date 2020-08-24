package com.jiang.vhr.mapper.employee;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-11  15:00
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 分页查询员工信息
     *
     * @return
     */
    List<Employee> getEmployeeByPage(@Param("employee") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    /**
     * 获取最大工号
     *
     * @return
     */
    Integer maxWorkId();

    /**
     * 批量添加员工
     *
     * @param list
     * @return
     */
    Integer addEmps(@Param("list") List<Employee> list);

    /**
     * 根据id获取一个员工
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 分页获取员工（带工资套）
     *
     * @param page
     * @param size
     * @return
     */
    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);

    /**
     * 更新员工工资套关系表如果存在就更新，不存在就新增
     *
     * @param eid
     * @param sid
     * @return
     */
    Integer updateEmployeeSalaryById(@Param("eid") Integer eid, @Param("sid") Integer sid);
}
