package club.bannerstudio.bannerstudiooauth2sso.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2021/12/29 18:25
 */
@Component
public class CustomAdditionalInformationConfig implements TokenEnhancer {

    protected static final Logger logger = LoggerFactory.getLogger(CustomAdditionalInformationConfig.class);
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = accessToken.getAdditionalInformation();
        info.put("BannerStudio", "BannerStudio欢迎您");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
