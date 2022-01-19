package club.bannerstudio.oauth2sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author: Ben
 * @Date: 2021/12/29 4:22
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("BannerStudio").tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("IntranetUser","InterViewUser","AdminUser")
                .antMatchers("/druid/**").hasRole("AdminUser")
                .anyRequest().authenticated()
                .and().csrf().disable()
                //把session设置为无状态，意思是使用了token，那么session不再做数据的记录
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}