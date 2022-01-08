//package club.bannerstudio.bannerstudiooauth2sso.handler;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author: Ben
// * @Date: 2022/1/8 16:42
// */
//@Component
//public class AuthAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//    protected static final Logger logger = LoggerFactory.getLogger(AuthAuthenticationSuccessHandler.class);
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        logger.info("登录成功");
//        //返回json处理 默认也是json处理
//        response.setContentType("application/json;charset=UTF-8");
//        logger.info("认证信息:" + JSON.toJSONString(authentication));
//        response.getWriter().write(JSON.toJSONString(authentication));
//
//    }
//}
