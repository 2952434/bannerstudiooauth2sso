package club.bannerstudio.bannerstudiooauth2sso.handler;

import club.bannerstudio.bannerstudiooauth2sso.utils.JsonUtils;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/8 16:42
 */
@Component
public class AuthAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    protected static final Logger logger = LoggerFactory.getLogger(AuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        try {
            logger.info("登录成功");
            response.setContentType("application/json;charset=UTF-8");
            String userName = authentication.getName();
            String principal = authentication.getPrincipal() + "";
            logger.info("用户名：" + userName);
            logger.info("授权信息：" + principal);
            logger.info("角色信息：" + JSON.toJSONString(authentication.getAuthorities()));
            Object[] objects = JsonUtils.toBean(JSON.toJSONString(authentication.getAuthorities()), Object[].class);
            logger.info(objects[0].toString());
            String jsonString = JSON.toJSONString(objects[0]);
            logger.info(jsonString);
            HttpSession httpSession = request.getSession();
            String url = httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST") + "";
            String substringUrl = url.substring(21, 149);
            logger.info("substringUrl：" + substringUrl);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("url", substringUrl);
            logger.info("认证信息:" + JSON.toJSONString(authentication));
            stringObjectMap.put("message", "登录成功");
            Enumeration<?> enumeration = httpSession.getAttributeNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement().toString();
                Object value = httpSession.getAttribute(name);
                logger.info("this is :" + httpSession.toString() + "--> ");
                logger.info(name + " : " + value);
            }
            logger.info("url信息：" + JSON.toJSONString(url));
            stringObjectMap.put("url", substringUrl);
            logger.info("session信息" + httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST"));
            ;
            logger.info("--------结束------");
            response.getWriter().write(JSON.toJSONString(RespBean.ok(stringObjectMap)));
        }catch (Exception e){
            Map<String, Object> stringObjectMap = new HashMap<>();
            Map<String, String> stringMap = new HashMap<>();
            stringObjectMap.put("message", "您传入的登录地址有误，请返回正确的登录地址");
            stringObjectMap.put("userMap", stringMap);
            stringObjectMap.put("url", "/");
            response.getWriter().write(JSON.toJSONString(RespBean.ok(stringObjectMap)));
        }
        }
}
