package com.lwj.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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


    @Autowired
    private MyUserService myUserService;


    /**
     * 通过缓存的方式做登陆验证，控制权限
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //写死的方式，使用内存验证权限管理
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("demo").password("123456").roles("USER");
        auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());
        
        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());
    }

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
