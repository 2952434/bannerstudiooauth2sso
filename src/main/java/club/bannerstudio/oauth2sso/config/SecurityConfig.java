package club.bannerstudio.oauth2sso.config;

import club.bannerstudio.oauth2sso.handler.AuthAccessDeniedHandler;
import club.bannerstudio.oauth2sso.handler.AuthAuthenticationFailureHandler;
import club.bannerstudio.oauth2sso.handler.AuthAuthenticationSuccessHandler;
import club.bannerstudio.oauth2sso.service.impl.UserAuthServiceImpl;
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

    @Autowired
    protected AuthAuthenticationFailureHandler authAuthenticationFailureHandler;

    @Autowired
    protected AuthAuthenticationSuccessHandler authAuthenticationSuccessHandler;


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
        web.ignoring().antMatchers("/login.html", "/css/**", "/js/**", "/img/**","/favicon.ico","/user/**","/agreement.html");
//        web.ignoring().antMatchers("/login.html", "/css/**", "/js/**", "/images/**","/favicon.ico","/register");
//        放行swagger和knife4j
//        web.ignoring().antMatchers("/swagger/**")
//                .antMatchers("/swagger-ui.html")
//                .antMatchers("/webjars/**")
//                .antMatchers("/v2/**")
//                .antMatchers("/v2/api-docs-ext/**")
//                .antMatchers("/swagger-resources/**")
//                .antMatchers("/doc.html");
//        web.ignoring().antMatchers("/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/login")
                .antMatchers("/oauth/authorize")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(authAuthenticationSuccessHandler)
                .failureHandler(authAuthenticationFailureHandler)
                .permitAll()
                .and()
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .rememberMe()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new AuthAccessDeniedHandler())
                .and().cors();
    }
}
