package com.jiang.vhr.mapper.hr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.HrRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author lilinjiang
 * @create 2020-02-13  21:58
 */
public interface HrRoleMapper extends BaseMapper<HrRole> {

    /**
     * 批量hr id 与 role id 批量添加 hr与role的映射关系
     *
     * @param hrId
     * @param rids
     * @return
     */
    Integer addHrRole(@Param("hrId") Integer hrId, @Param("rids") Integer[] rids);
}
