package com.jiang.vhr.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 鉴权 访问决策管理器
 *
 * @author DEEL
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication   当前登录的用户信息
     * @param object           object
     * @param configAttributes 通过当前资源验证的所需要的的角色集合
     * @throws AccessDeniedException               未登录
     * @throws InsufficientAuthenticationException 无权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //循环请求资源所需要的角色集合
        for (ConfigAttribute configAttribute : configAttributes) {
            //获取当前循环到的角色名
            String needRole = configAttribute.getAttribute();
            //如果等于我们标记的ROLE_LOGIN则代表该资源登录即可访问
            if ("ROLE_LOGIN".equals(needRole)) {
                //判断当前登录的类型是否等于 AnonymousAuthenticationToken，如果是则代表未登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //抛出异常
                    throw new AccessDeniedException("尚未登录，请登录!");
                } else {
                    //放行
                    return;
                }
            }
            //如果走到这里代表访问资源需要规定的角色 此时根据登录用户对象取出该用户所拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //循环用户拥有的角色
            for (GrantedAuthority authority : authorities) {
                //循环当前用户拥有的角色去匹配当前循环到的该资源所需的角色
                //只要该用户所拥有的角色匹配到该资源所需要的角色则通过验证
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        //如果该请求资源既不是登录即可访问，并且登录用户所拥有的角色也不符合该请求资源所需的角色则代表权限不足
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}