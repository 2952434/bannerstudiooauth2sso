package club.bannerstudio.oauth2sso.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/5 21:29
 */
public class BannerJwtConfig extends JwtAccessTokenConverter {

    protected static final Logger logger = LoggerFactory.getLogger(BannerJwtConfig.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        Map<String, Object> additionalInformation = new LinkedHashMap<>();
//        Map<String, Object> info = new LinkedHashMap<>();
        Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();
        Authentication userAuthentication = authentication.getUserAuthentication();
        logger.info("用户权限:"+userAuthentication.getAuthorities());
        logger.info("用户名"+userAuthentication.getName());
        logger.info("用户"+userAuthentication.getDetails());
        logger.info("用户身份"+userAuthentication.getPrincipal());
        Map<String,Object> userInfo=new LinkedHashMap<>();
        userInfo.put("requestParameters",requestParameters);
        userInfo.put("userAuthorities",userAuthentication.getAuthorities());
        userInfo.put("userName",userAuthentication.getName());
        userInfo.put("userDetails",userAuthentication.getDetails());
        userInfo.put("userPrincipal",userAuthentication.getPrincipal());
//        info.put("author", "江南一点雨");
//        info.put("email", "wangsong0210@gmail.com");
//        info.put("site", "www.javaboy.org");
//        info.put("weixin", "a_java_boy2");
//        info.put("WeChat Official Accounts", "江南一点雨");
//        info.put("GitHub", "https://github.com/lenve");
//        info.put("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        additionalInformation.put("info", info);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userInfo);
        return super.enhance(accessToken, authentication);
    }
}
