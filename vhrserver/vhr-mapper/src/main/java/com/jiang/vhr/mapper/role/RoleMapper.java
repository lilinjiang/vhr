package com.jiang.vhr.mapper.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.Role;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-05  1:24
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据hrId查询 该hr拥有的角色列表
     *
     * @param hrId
     * @return List<Role>
     */
    List<Role> getHrRolesByHrId(Integer hrId);
}
