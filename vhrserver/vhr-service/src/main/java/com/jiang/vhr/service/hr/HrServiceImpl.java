package com.jiang.vhr.service.hr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.vhr.mapper.hr.HrMapper;
import com.jiang.vhr.mapper.hr.HrRoleMapper;
import com.jiang.vhr.mapper.role.RoleMapper;
import com.jiang.vhr.model.Hr;
import com.jiang.vhr.model.HrRole;
import com.jiang.vhr.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author lilinjiang
 * @create 2020-02-04  23:51
 */
@Service
public class HrServiceImpl implements HrService {

    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private HrRoleMapper hrRoleMapper;

    /**
     * 根据username账号 查找一个用户(包含角色)
     *
     * @param username
     * @return hr对象
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        hr.setRoles(roleMapper.getHrRolesByHrId(hr.getId()));
        System.out.println("hr = " + hr);
        return hr;
    }

    /**
     * 查询全部的hr
     *
     * @return
     */
    @Override
    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(), keywords);
    }

    /**
     * 修改hr 根据id
     *
     * @param hr
     * @return
     */
    @Override
    public Integer updateHr(Hr hr) {
        return hrMapper.updateById(hr);
    }

    /**
     * 更新用户角色
     * <p>
     * 首先根据hr id删除该用户所有角色
     * 然后根据role id 重新给hr 赋予角色
     *
     * @param hrId
     * @param rids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateHrRole(Integer hrId, Integer[] rids) {
        HrRole hrRole = new HrRole();
        hrRole.setHrid(hrId);
        QueryWrapper<HrRole> wrapper = new QueryWrapper<>(hrRole);
        hrRoleMapper.delete(wrapper);
        if (rids == null || rids.length == 0) {
            return true;
        }
        int addResult = hrRoleMapper.addHrRole(hrId, rids);
        return addResult == rids.length;
    }

    /**
     * 根据 hr id删除hr
     *
     * @param hrId
     * @return
     */
    @Override
    public Integer deleteHrById(Integer hrId) {
        return hrMapper.deleteById(hrId);
    }

    /**
     * 获取所有hr 排除当前登录hr
     *
     * @return
     */
    @Override
    public List<Hr> getAllHrsExceptCurrentHr() {
        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
    }
}
