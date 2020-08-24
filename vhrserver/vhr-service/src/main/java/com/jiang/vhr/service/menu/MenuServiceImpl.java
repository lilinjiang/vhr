package com.jiang.vhr.service.menu;

import com.jiang.vhr.mapper.menu.MenuMapper;
import com.jiang.vhr.mapper.menurole.MenuRoleMapper;
import com.jiang.vhr.model.Hr;
import com.jiang.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lilinjiang
 * @create 2020-02-06  2:25
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 根据登录Hr的ID获取到该HR可以查看的菜单
     * 这里之所以不用前端传来的数据是因为不能信任前端的数据
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByHrId() {
        //(Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ===当前登录人
        //此方法的参数就是获取当前登录人的id
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * 获取所有菜单并包含着菜单中的角色
     *
     * @return
     */
    @Cacheable(cacheNames = "menu")
    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    /**
     * 获取所有菜单（树形结构）
     *
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    /**
     * 根据角色id获取该角色拥有的菜单id
     *
     * @param rid
     * @return
     */
    @Override
    public List<Integer> getMidsByRid(Integer rid) {

        return menuMapper.getMidsByRid(rid);
    }

    /**
     * 更新角色与菜单的对应关系
     *
     * @param rid
     * @param mids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateMenuRole(Integer rid, Integer[] mids) {
        //用于封装删除条件 多条件之间是and关系
        Map<String, Object> map = new HashMap<>(16);
        map.put("rid", rid);
        menuRoleMapper.deleteByMap(map);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result == mids.length;
    }
}
