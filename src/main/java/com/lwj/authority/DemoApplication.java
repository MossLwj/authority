package com.lwj.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By lwj
 * 2018/2/1 0001 18:52
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "hello spring boot";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    /**
     * 注意第一个注解中的规则名称必须由ROLE_开头，在roleVoter类中定义了前缀
     * PreAuthorize：方法调用前权限检查
     * PostAuthorize：方法调用后权限检查
     * PreFilter,PostFilter：对返回值或参数进行校验
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @PreFilter("")
    @PostFilter("")
    @RequestMapping("/roleAuth")
    public String role() {
        return "admin auth";
    }
}
