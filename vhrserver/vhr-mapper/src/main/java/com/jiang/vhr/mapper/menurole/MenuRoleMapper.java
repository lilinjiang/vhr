package com.jiang.vhr.mapper.menurole;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author lilinjiang
 * @create 2020-02-09  20:07
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {


    /**
     * 新增角色与菜单对应关系
     *
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
