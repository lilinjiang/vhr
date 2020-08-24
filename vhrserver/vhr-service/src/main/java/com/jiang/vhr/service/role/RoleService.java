package com.jiang.vhr.service.role;

import com.jiang.vhr.model.Role;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-05  1:26
 */
public interface RoleService {

    /**
     * 获取所有的角色
     *
     * @return
     */
    List<Role> getAllRoles();

    /**
     * 添加一个角色
     *
     * @param role
     * @return
     */
    Integer addRole(Role role);

    /**
     * 根据角色id删除一个角色
     *
     * @param rid
     * @return
     */
    Integer deleteRoleById(Integer rid);
}
