package com.jiang.vhr.service.salary;

import com.jiang.vhr.mapper.salary.SalaryMapper;
import com.jiang.vhr.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-03-02  21:01
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    /**
     * 获取所有工资账套
     *
     * @return
     */
    @Override
    public List<Salary> getAllSalaries() {
        return salaryMapper.selectList(null);
    }

    /**
     * 新增一个工资账套
     *
     * @param salary
     * @return
     */
    @Override
    public Integer addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insert(salary);
    }

    /**
     * 根据工资账套 id删除工资账套
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteSalaryById(Integer id) {
        return salaryMapper.deleteById(id);
    }

    /**
     * 更新工资账套
     *
     * @param salary
     * @return
     */
    @Override
    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateById(salary);
    }
}
