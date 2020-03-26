package com.security.example.demo.config;

import com.security.example.demo.handler.MyAuthenticationAccessDeniedHandler;
import com.security.example.demo.handler.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyAuthenticationSucessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.httpBasic() // HTTP Basic
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                // http.httpBasic() // HTTP Basic
                .and()
                // 授权配置
                .authorizeRequests()
                // 无需认证的请求路径
                .antMatchers("/login.html", "/code/image", "/code/sms", "/session/invalid")
                .permitAll()
                // 所有请求 都需要认证
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler)
                .and()
                // 添加 Session管理器
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                // Session并发控制可以控制一个账号同一时刻最多能登录多少个
                .maximumSessions(1)
                .expiredSessionStrategy(sessionExpiredStrategy)
        ;
        //    在实际开发中，发现Session并发控制只对Spring Security默认的登录方式——账号密码登录有效，而像短信验证码登录，社交账号登录并不生效

//        Session集群处理
//        把Session信息存储在第三方容器里（如Redis集群），而不是各自的服务器
//        1.在pom.xml 中引入 spring-session、spring-boot-starter-data-redis 两个坐标
//        2.配置文件中 spring.session.store-type = redis
    }
}
