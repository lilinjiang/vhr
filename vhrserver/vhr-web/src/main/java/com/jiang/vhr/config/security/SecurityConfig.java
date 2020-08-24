package com.jiang.vhr.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.vhr.model.Hr;
import com.jiang.vhr.service.hr.HrService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

/**
 * @author lilinjiang
 * @date 2020-02-05  0:38
 * Spring Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HrService hrService;

    /**
     * 安全数据源调用过滤器
     * 根据请求url分析出访问资源所需要的角色
     */
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource metadataSource;

    /**
     * 鉴权 访问决策管理器
     */
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    /**
     * 密码解密策略
     *
     * @return 解密组件
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置用户查找策略
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/druid/**", "/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证(防止跨站请求伪造攻击)
        http.csrf().disable();

        // url权限认证处理
        http.authorizeRequests()
//                //其他任何请求
//                .anyRequest()
//                //都需要身份认证
//                .authenticated()
                //注入自定义鉴权
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        //动态获取url权限配置
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        //权限判断
                        object.setSecurityMetadataSource(metadataSource);
                        return object;
                    }
                });
        // 未登录时：返回状态码401
        http.exceptionHandling()
                //未认证时的回调
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setStatus(401);
                    PrintWriter out = httpServletResponse.getWriter();
                    String s = new ObjectMapper().writeValueAsString(RespUtil.error("尚未登录，请登录!"));
                    out.write(s);
                    out.flush();
                    out.close();
                });
        // 开启自动配置的登录功能
        http.formLogin()
                //自定义登录请求路径(post)
                .loginProcessingUrl("/doLogin")
                //自定义登录用户名密码属性名,默认为username和password
                .usernameParameter("username")
                .passwordParameter("password")
//                .loginPage("/login")
                //登录成功的回调
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    //将登录用户的信息返回去
                    Hr hr = (Hr) authentication.getPrincipal();
                    //去掉密码
                    hr.setPassword(null);
                    RespUtil ok = RespUtil.ok("登录成功!", hr);
                    String s = new ObjectMapper().writeValueAsString(ok);
                    out.write(s);
                    out.flush();
                    out.close();
                })
                //登录失败的回调
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    RespUtil respBean = RespUtil.error("登录失败!");
                    if (e instanceof LockedException) {
                        respBean.setMsg("账户被锁定，请联系管理员!");
                    } else if (e instanceof CredentialsExpiredException) {
                        respBean.setMsg("密码过期，请联系管理员!");
                    } else if (e instanceof AccountExpiredException) {
                        respBean.setMsg("账户过期，请联系管理员!");
                    } else if (e instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，请联系管理员!");
                    } else if (e instanceof BadCredentialsException) {
                        respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                //与登录相关的接口都能直接访问和响应，无须鉴权
                .permitAll();
        // 开启自动配置的注销功能
        http.logout()
                //自定义注销请求路径
                .logoutUrl("/logout")
                //退出成功的回调
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(RespUtil.ok("注销成功!")));
                    out.flush();
                    out.close();
                });
    }
}
