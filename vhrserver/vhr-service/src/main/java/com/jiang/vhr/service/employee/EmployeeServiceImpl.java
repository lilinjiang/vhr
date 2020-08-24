package com.jiang.vhr.service.employee;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.vhr.mapper.employee.EmployeeMapper;
import com.jiang.vhr.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-15  21:42
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Autowired
    RabbitTemplate rabbitTemplate;

    public final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    /**
     * 分页获取员工信息
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        Page<Object> pageObject = PageHelper.startPage(page, size);
        List<Employee> emps = employeeMapper.getEmployeeByPage(employee, beginDateScope);
        PageInfo<Employee> info = new PageInfo<>(emps);
        return info;
    }


    /**
     * 分页获取员工信息（带有工资账套）
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo getEmployeeByPageWithSalary(Integer page, Integer size) {
        Page<Object> pageObject = PageHelper.startPage(page, size);
        List<Employee> emps = employeeMapper.getEmployeeByPageWithSalary(page, size);
        System.out.println("emps.get(0).getSalary() = " + emps.get(0).getSalary());
        PageInfo<Employee> info = new PageInfo<>(emps);
        return info;
    }

    /**
     * 新增一个员工
     *
     * @param employee
     * @return
     */
    @Override
    public Integer addEmp(Employee employee) {
        // region 用于计算合同年限字段的值
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        // endregion
        int insert = employeeMapper.insert(employee);
        if (insert == 1) {
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            logger.info(emp.toString());
            rabbitTemplate.convertAndSend("vhr.mail.welcome", emp);
        }
        return insert;
    }

    /**
     * 获取最大工号
     *
     * @return
     */
    @Override
    public Integer maxWorkId() {
        return employeeMapper.maxWorkId();
    }

    /**
     * 根据emp id删除 员工
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteEmpByEid(Integer id) {
        return employeeMapper.deleteById(id);
    }

    /**
     * 修改员工根据id
     *
     * @param employee
     * @return
     */
    @Override
    public Integer updateEmp(Employee employee) {
        // region 用于计算合同年限字段的值
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        // endregion
        return employeeMapper.updateById(employee);
    }

    /**
     * 获取全部的员工
     *
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.getEmployeeByPage(null, null);
    }

    /**
     * 批量添加员工
     *
     * @param list
     * @return
     */
    @Override
    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }

    /**
     * 修改员工的所属工资套
     *
     * @param eid
     * @param sid
     * @return
     */
    @Override
    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }
}
