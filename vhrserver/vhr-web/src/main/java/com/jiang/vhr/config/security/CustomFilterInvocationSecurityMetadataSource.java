package com.jiang.vhr.config.security;

import com.jiang.vhr.model.Menu;
import com.jiang.vhr.model.Role;
import com.jiang.vhr.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author lilinjiang
 * 根据请求url分析出访问资源所需要的角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //拿到请求路径
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //获取到所有的菜单（包含角色）
        List<Menu> menus = menuService.getAllMenusWithRole();
        //循环菜单
        for (Menu menu : menus) {
            //依次用请求路径匹配菜单路径
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                //如果匹配到对应菜单，则取出该菜单所需要的所有角色
                List<Role> roles = menu.getRoles();
                //创建一个用于存放角色name的数组
                String[] str = new String[roles.size()];
                //依次将角色name存放到数组
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                //将数组转成一个security认识的角色配置集合
                return SecurityConfig.createList(str);
            }
        }
        //如果该路径与所有菜单都没有匹配上则代表该路径登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}