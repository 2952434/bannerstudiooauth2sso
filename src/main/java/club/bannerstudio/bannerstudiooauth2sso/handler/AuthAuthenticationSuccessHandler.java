package club.bannerstudio.bannerstudiooauth2sso.handler;

import club.bannerstudio.bannerstudiooauth2sso.utils.JsonUtils;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import club.bannerstudio.bannerstudiooauth2sso.utils.UrlPattern;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

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
          String userName=authentication.getName();
         String principal=authentication.getPrincipal()+"";
         logger.info("用户名："+userName);
         logger.info("授权信息："+principal);
        logger.info("角色信息："+JSON.toJSONString(authentication.getAuthorities()));

        Object[] objects = JsonUtils.toBean(JSON.toJSONString(authentication.getAuthorities()),Object[].class);
        logger.info(objects[0].toString());
        String jsonString=JSON.toJSONString(objects[0]);
        logger.info(jsonString);
        Map<String,String> stringStringMap=JsonUtils.toMap(jsonString,String.class,String.class);
        String authority=stringStringMap.get("authority").toString();
        logger.info(stringStringMap.get("authority").toString());
        HttpSession httpSession=request.getSession();
        String url=httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST")+"";
        String substringUrl = url.substring(21, 149);
        logger.info("substringUrl："+substringUrl);
        Map<String,String> stringMap=new HashMap<>();
        Map<String,Object> stringObjectMap=new HashMap<>();
        stringObjectMap.put("url",substringUrl);
//        if (authority.equals("ROLE_User")){
//            substringUrl+="&username="+userName+"&authority="+"用户";
//            stringObjectMap.put("url",substringUrl);
////            stringMap.put("userName",userName);
////            stringMap.put("authority","用户");
//        }else if (authority.equals("ROLE_IntranetUser")){
//            substringUrl+="&username="+userName+"&authority="+"工作室内部成员";
//            stringObjectMap.put("url",substringUrl);
//        }else if (authority.equals("ROLE_InterViewUser")){
//            substringUrl+="&username="+userName+"&authority="+"工作室面试官成员";
//            stringObjectMap.put("url",substringUrl);
//        }else if(authority.equals("ROLE_AdminUser")){
//            substringUrl+="&username="+userName+"&authority="+"工作室管理员";
//            stringObjectMap.put("url",substringUrl);
//        }


        logger.info("认证信息:" + JSON.toJSONString(authentication));


        stringObjectMap.put("message","登录成功");
//        stringObjectMap.put("session",httpSession);
        stringObjectMap.put("userMap",stringMap);
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

//        String client_id=urlPattern.getParamByUrl(url,"client_id");
//        String scope=urlPattern.getParamByUrl(url,"scope");
//        String redirect_uri=urlPattern.getParamByUrl(url,"redirect_uri");
//        logger.info(redirect_uri);

//        String response_type=urlPattern.getParamByUrl(url,"response_type");
//        String returnUrl="http://localhost:8081/oauth/authorize?"+"client_id"+client_id+"&"+"response_type"+response_type+"&"+"scope"+scope+"&"+"redirect_uri"+redirect_uri;
//        logger.info("returnUrl:"+returnUrl);
//        logger.info("客户端id:"+client_id);
//        logger.info("scope:"+scope);
//        stringObjectMap.put("client_id",client_id);
//        stringObjectMap.put("scope",scope);
        logger.info("url信息："+JSON.toJSONString(url));
        stringObjectMap.put("url",substringUrl);
        logger.info("session信息"+httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST"));;
        logger.info("--------结束------");
        response.getWriter().write(JSON.toJSONString(RespBean.ok(stringObjectMap)));
//        response.getWriter().write(JSON.toJSONString(authentication));

    }
}
