package club.bannerstudio.oauth2sso.handler;

import club.bannerstudio.oauth2sso.utils.RespBean;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Ben
 * @Date: 2022/1/8 0:40
 */
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    protected static final Logger logger = LoggerFactory.getLogger(AuthAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        logger.error("权限不足，请联系管理员！！！");
        try {
            response.getWriter().write(JSON.toJSONString(new RespBean(403,"权限不足，请联系管理员！！！","权限不足，请联系管理员！！！").toString()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
