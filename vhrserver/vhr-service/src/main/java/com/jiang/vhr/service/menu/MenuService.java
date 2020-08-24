package com.jiang.vhr.service.menu;

import com.jiang.vhr.model.Menu;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-06  2:25
 */
public interface MenuService {

    /**
     * 根据登录Hr的ID获取到该HR可以查看的菜单
     *
     * @return
     */
    List<Menu> getMenusByHrId();

    /**
     * 获取所有菜单并包含着菜单中的角色
     *
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有菜单（树形结构）
     *
     * @return
     */
    List<Menu> getAllMenus();

    /**
     * 根据角色id获取该角色拥有的菜单id
     *
     * @param rid
     * @return
     */
    List<Integer> getMidsByRid(Integer rid);

    /**
     * 更新角色与菜单的对应关系
     *
     * @param rid
     * @param mid
     * @return
     */
    Boolean updateMenuRole(Integer rid, Integer[] mids);
}
