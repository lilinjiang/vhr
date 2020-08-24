package com.jiang.vhr.mapper.hr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.vhr.model.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-04  23:58
 */
public interface HrMapper extends BaseMapper<Hr> {

    /**
     * 根据username（账号） 查询一个Hr并包含账号拥有角色
     *
     * @param username
     * @return
     */
    Hr loadUserByUsername(String username);

    /**
     * 获取除了当前登录hr 以外的所有hr
     *
     * @param hrId
     * @return
     */
    List<Hr> getAllHrs(@Param("hrId") Integer hrId, @Param("keywords") String keywords);

    /**
     * 获取除了当前登录的所有hr
     *
     * @param id
     * @return
     */
    List<Hr> getAllHrsExceptCurrentHr(Integer id);
}
