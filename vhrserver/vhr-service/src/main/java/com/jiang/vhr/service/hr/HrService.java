package com.jiang.vhr.service.hr;

import com.jiang.vhr.model.Hr;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-04  23:51
 */
public interface HrService extends UserDetailsService {

    /**
     * 查询全部的hr
     *
     * @return
     */
    List<Hr> getAllHrs(String keywords);

    /**
     * 修改hr 根据id
     *
     * @param hr
     * @return
     */
    Integer updateHr(Hr hr);

    /**
     * 更新用户角色
     *
     * @param hrId
     * @param rids
     * @return
     */
    Boolean updateHrRole(Integer hrId, Integer[] rids);

    /**
     * 根据 hr id删除hr
     *
     * @param hrId
     * @return
     */
    Integer deleteHrById(Integer hrId);

    /**
     * 获取所有hr 排除当前登录hr
     *
     * @return
     */
    List<Hr> getAllHrsExceptCurrentHr();
}
