package club.bannerstudio.bannerstudiooauth2sso.handler;

import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import club.bannerstudio.bannerstudiooauth2sso.utils.UrlPattern;
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
        logger.info("登录成功");
        //返回json处理 默认也是json处理
        response.setContentType("application/json;charset=UTF-8");
        logger.info("认证信息:" + JSON.toJSONString(authentication));
        HttpSession httpSession=request.getSession();
        Map<String,Object> stringObjectMap=new HashMap<>();
        stringObjectMap.put("message","登录成功");
        stringObjectMap.put("session",httpSession);
        Enumeration<?> enumeration = httpSession.getAttributeNames();
// 遍历enumeration
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = httpSession.getAttribute(name);
            // 打印结果
            logger.info("this is :" + httpSession.toString() + "--> ");
            logger.info(name + " : " + value);
        }
        UrlPattern urlPattern=new UrlPattern();
        String url=(String)httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        String client_id=urlPattern.getParamByUrl(url,"client_id");
        String scope=urlPattern.getParamByUrl(url,"scope");
        logger.info("客户端id:"+client_id);
        logger.info("scope:"+scope);
        logger.info("session信息"+httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST"));;
        logger.info("--------结束------");
        response.getWriter().write(JSON.toJSONString(RespBean.ok(stringObjectMap)));
//        response.getWriter().write(JSON.toJSONString(authentication));

    }
}
