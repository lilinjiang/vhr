package com.jiang.vhr.controller.system;

import com.jiang.vhr.model.Hr;
import com.jiang.vhr.model.Role;
import com.jiang.vhr.service.hr.HrService;
import com.jiang.vhr.service.role.RoleService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lilinjiang
 * @create 2020-02-11  19:20
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    private HrService hrService;
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有hr
     *
     * @param keywords
     * @return
     */
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        System.out.println("keywords = " + keywords);
        return hrService.getAllHrs(keywords);
    }

    /**
     * 更新hr
     *
     * @param hr
     * @return
     */
    @PutMapping("/")
    public RespUtil updateHr(@RequestBody Hr hr) {
        System.out.println("hr = " + hr);
        if (hrService.updateHr(hr) == 1) {
            return RespUtil.ok("更新成功！");
        }
        return RespUtil.error("更新失败！");
    }

    @DeleteMapping("/{hrId}")
    public RespUtil deleteHrById(@PathVariable Integer hrId) {
        if (hrService.deleteHrById(hrId) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败!");
    }

    /**
     * 获取全部的role
     *
     * @return
     */
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 修改hr 拥有的角色
     *
     * @param map
     * @return
     */
    @PutMapping("/role")
    public RespUtil updateHrRole(@RequestBody Map<String, Object> map) {
        Integer hrId = (Integer) map.get("hrId");
        Integer[] rids = ((List<Integer>) map.get("rids")).toArray(new Integer[]{});
        if (hrService.updateHrRole(hrId, rids)) {
            return RespUtil.ok("修改成功！");
        }
        return RespUtil.error("修改失败！");
    }
}