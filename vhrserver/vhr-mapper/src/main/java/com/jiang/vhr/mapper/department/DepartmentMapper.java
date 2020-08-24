package com.jiang.vhr.mapper.department;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.Department;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-10  13:10
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据父级id查询部门
     *
     * @param parentId
     * @return
     */
    List<Department> getAllDepartmentsByParentId(Integer parentId);


}
