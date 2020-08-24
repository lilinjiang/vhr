package com.jiang.vhr.controller.system.basic;

import com.jiang.vhr.model.Menu;
import com.jiang.vhr.model.Role;
import com.jiang.vhr.service.menu.MenuService;
import com.jiang.vhr.service.role.RoleService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lilinjiang
 * @create 2020-02-09  1:07
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取所有的角色
     *
     * @return
     */
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 获取所有的菜单（树形）
     *
     * @return
     */
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    /**
     * 根据角色id获取该角色拥有的菜单id
     *
     * @param rid
     * @return
     */
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    /**
     * 修改角色拥有的权限
     *
     * @param map
     * @return
     */
    @PutMapping("/")
    public RespUtil updateMenuRole(@RequestBody Map<String, Object> map) {
        int rid = (int) map.get("rid");
        //另一种list转数组的转换方式
//        List<Integer> listMids = ((List<Integer>) map.get("mids"));
//        Integer [] mids = new Integer[listMids.size()];
//        listMids.toArray(mids);

        Integer[] mids = ((List<Integer>) map.get("mids")).toArray(new Integer[]{});
        System.out.println("rid = " + rid);
        for (Integer mid : mids) {
            System.out.println("mids = " + mid);
        }
        if (menuService.updateMenuRole(rid, mids)) {
            return RespUtil.ok("更新成功！");
        }
        return RespUtil.error("更新失败！");
    }

    /**
     * 新增一个角色
     *
     * @param role
     * @return
     */
    @PostMapping("/role")
    public RespUtil addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespUtil.ok("添加成功！");
        }
        return RespUtil.error("添加失败！");
    }

    @DeleteMapping("/role/{rid}")
    public RespUtil deleteRoleById(@PathVariable Integer rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }
}
