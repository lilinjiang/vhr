package com.jiang.vhr.service.role;

import com.jiang.vhr.mapper.role.RoleMapper;
import com.jiang.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-05  1:27
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 获取所有的角色
     *
     * @return
     */
    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectList(null);
    }

    /**
     * 添加一个角色
     *
     * @param role
     * @return
     */
    @Override
    public Integer addRole(Role role) {
        String prefix = "ROLE_";
        if (!role.getName().startsWith(prefix)) {
            role.setName(prefix + role.getName());
        }
        return roleMapper.insert(role);
    }

    /**
     * 根据角色id删除一个角色
     *
     * @param rid
     * @return
     */
    @Override
    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteById(rid);
    }
}
