package com.lwj.authority;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created By lwj
 * 2018/2/1 0001 19:31
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置哪些请求将被拦截；配置请求的模式
     *  项目的主路径是允许访问的，其他路径都需要验证，项目的退出也是允许访问的，允许form表单
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();//关闭csrf认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //将js，css，images加入权限拦截
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
}
