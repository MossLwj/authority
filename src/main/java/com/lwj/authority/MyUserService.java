package com.lwj.authority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created By lwj
 * 2018/2/3 0003 19:09
 */
@Component
public class MyUserService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //这里具体写数据库交互的用户验证逻辑
        return null;
    }
}
