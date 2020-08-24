package com.jiang.vhr.controller.config;

import com.jiang.vhr.model.Menu;
import com.jiang.vhr.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lilinjiang
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    /**
     * 根据hr id获取该hr可访问的菜单 （hrid在service层获取）
     *
     * @return
     */
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId();
    }


}