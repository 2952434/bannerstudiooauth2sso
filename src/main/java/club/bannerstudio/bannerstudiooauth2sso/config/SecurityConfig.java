package club.bannerstudio.bannerstudiooauth2sso.config;

import club.bannerstudio.bannerstudiooauth2sso.handler.AuthAccessDeniedHandler;
//import club.bannerstudio.bannerstudiooauth2sso.handler.AuthAuthenticationFailureHandler;
//import club.bannerstudio.bannerstudiooauth2sso.handler.AuthAuthenticationSuccessHandler;
import club.bannerstudio.bannerstudiooauth2sso.service.impl.UserAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Ben
 * @Date: 2021/12/29 4:16
 */
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    protected AuthAuthenticationFailureHandler authAuthenticationFailureHandler;
//
//    @Autowired
//    protected AuthAuthenticationSuccessHandler authAuthenticationSuccessHandler;


    @Autowired
    protected UserAuthServiceImpl userAuthService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login.html", "/css/**", "/js/**", "/images/**","/favicon.ico","/register");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/login")
                .antMatchers("/register")
                .antMatchers("/oauth/authorize")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
//                .successHandler(authAuthenticationSuccessHandler)
//                .failureHandler(authAuthenticationFailureHandler)
                .permitAll()
                .and()
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .rememberMe()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new AuthAccessDeniedHandler());
//                .authenticationEntryPoint(new AuthAuthenticationEntryPoint());
    }
}
