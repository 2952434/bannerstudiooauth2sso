package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/7 21:58
 */
@RestController
@SessionAttributes("authorizationRequest")
public class AuthorizationController {
    protected static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);


    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
// 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
// 遍历enumeration
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            logger.info("this is :" + session.toString() + "--> ");
            logger.info(name + " : " + value);
        }
        logger.info("model信息:"+model.toString());
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base-grant");
//        Map<String,Object> stringObjectMap=new HashMap<>();
//        stringObjectMap.put("clientId",authorizationRequest.getClientId());
//        stringObjectMap.put("scopes",authorizationRequest.getScope());
        modelAndView.addObject("clientId", authorizationRequest.getClientId());
        modelAndView.addObject("scopes",authorizationRequest.getScope());
        return modelAndView;
    }
    @RequestMapping("/oauth/getJson")
    public RespBean getAccessJson(Map<String, Object> model, HttpServletRequest request) throws Exception {
        logger.info("session信息:"+request.getSession());
        logger.info("json转换的session信息:"+ request.getSession().toString());
        HttpSession session = request.getSession();
// 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
// 遍历enumeration
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            logger.info("this is :" + session.toString() + "--> ");
            logger.info(name + " : " + value);
        }
        logger.info("model信息:"+model.toString());
//        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("base-grant");
        Map<String,Object> stringObjectMap=new HashMap<>();
//        stringObjectMap.put("clientId",authorizationRequest.getClientId());
//        stringObjectMap.put("scopes",authorizationRequest.getScope());
//        modelAndView.addObject("clientId", authorizationRequest.getClientId());
//        modelAndView.addObject("scopes",authorizationRequest.getScope());
        return RespBean.ok(stringObjectMap);
    }
}
