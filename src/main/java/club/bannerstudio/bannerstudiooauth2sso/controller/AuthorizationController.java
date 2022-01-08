package club.bannerstudio.bannerstudiooauth2sso.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/7 21:58
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizationController {


    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base-grant");
        modelAndView.addObject("clientId", authorizationRequest.getClientId());
        modelAndView.addObject("scopes",authorizationRequest.getScope());
        return modelAndView;
    }
}
