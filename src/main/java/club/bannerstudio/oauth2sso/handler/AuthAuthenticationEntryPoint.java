//package club.bannerstudio.bannerstudiooauth2sso.handler;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author: Ben
// * @Date: 2022/1/8 0:41
// */
//public class AuthAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    protected static final Logger logger = LoggerFactory.getLogger(AuthAuthenticationEntryPoint.class);
//
//
//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException authException) throws IOException, ServletException {
//
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        logger.error("未登录");
//        response.getWriter().write("请登录!!");
//    }
//}
