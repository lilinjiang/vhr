package com.jiang.vhr.mapper.menu;


import com.jiang.vhr.model.Menu;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-06  2:30
 */
public interface MenuMapper {

    /**
     * 根据Hr ID获取该HR能够访问的菜单
     *
     * @param hrId
     * @return
     */
    List<Menu> getMenusByHrId(Integer hrId);

    /**
     * 获取所有菜单 及每个菜单访问所需角色
     *
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有的菜单 （树形结构）
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
}
