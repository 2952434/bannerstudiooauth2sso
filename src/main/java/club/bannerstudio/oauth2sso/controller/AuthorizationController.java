package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.utils.JsonUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author: Ben
 * @Date: 2022/1/7 21:58
 */
@RestController
@SessionAttributes("authorizationRequest")
@Api(value = "AuthorizationController")
public class AuthorizationController {
    protected static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);


    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Enumeration<?> enumeration = session.getAttributeNames();
        logger.info("直接访问的session信息");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            Object value = session.getAttribute(name);
            logger.info("this is :" + session.toString() + "--> ");
            logger.info(name + " : " + value);
        }
        String SPRING_SECURITY_CONTEXT = JSON.toJSONString(session.getAttribute("SPRING_SECURITY_CONTEXT"));
        Map<String, Object> stringObjectMap = JsonUtils.toMap(SPRING_SECURITY_CONTEXT, String.class, Object.class);
        logger.info("map:" + stringObjectMap);
        logger.info("value:" + stringObjectMap.get("authentication"));
        String jsonAuthentication = JSON.toJSONString(stringObjectMap.get("authentication"));
        logger.info("json转化后的jsonAuthentication：" + jsonAuthentication);
        Map<String, Object> objectMap = JsonUtils.toMap(jsonAuthentication, String.class, Object.class);
        String userName = objectMap.get("name").toString();
        String jsonAuthorities = JSON.toJSONString(objectMap.get("authorities"));
        logger.info("jsonAuthorities" + jsonAuthorities);
        logger.info("jsonAuthorities的class对象" + jsonAuthorities.getClass());
        Object[] objects = JsonUtils.toBean(jsonAuthorities, Object[].class);
        logger.info(objects[0].toString());
        String jsonString = JSON.toJSONString(objects[0]);
        logger.info(jsonString);
        Map<String, String> stringStringMap = JsonUtils.toMap(jsonString, String.class, String.class);
        String authority = stringStringMap.get("authority").toString();
        logger.info(stringStringMap.get("authority").toString());
        ModelAndView modelAndView = new ModelAndView();
        if (authority.equals("ROLE_User")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("authority", "用户");
        } else if (authority.equals("ROLE_IntranetUser")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("authority", "工作室内部成员");
        } else if (authority.equals("ROLE_InterViewUser")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("authority", "工作室面试官成员");
        } else if (authority.equals("ROLE_AdminUser")) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("authority", "工作室管理员");
        }
        logger.info("session信息：" + SPRING_SECURITY_CONTEXT);
        logger.info("model信息:" + model.toString());
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        modelAndView.setViewName("oauthcenter");
        stringObjectMap.put("scopes", authorizationRequest.getScope());
        modelAndView.addObject("clientId", authorizationRequest.getClientId());
        modelAndView.addObject("scopes", authorizationRequest.getScope());
        logger.info("请求地址：" + request.getRequestURL());
        return modelAndView;
    }
}
