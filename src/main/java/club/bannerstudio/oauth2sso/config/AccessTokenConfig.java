package club.bannerstudio.oauth2sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: Ben
 * @Date: 2021/12/29 4:17
 */
@Configuration
public class AccessTokenConfig {

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new BannerJwtConfig();
        String signKey = "BannerStudio";
        converter.setSigningKey(signKey);
        return converter;
    }
}
