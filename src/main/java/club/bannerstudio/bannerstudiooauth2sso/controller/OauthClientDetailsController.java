package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.OauthClientDetails;
import club.bannerstudio.bannerstudiooauth2sso.service.IOauthClientDetailsService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/6 22:23
 */
@RestController
public class OauthClientDetailsController {

    protected static final Logger logger = LoggerFactory.getLogger(OauthClientDetailsController.class);

    @Autowired
    protected IOauthClientDetailsService iOauthClientDetailsService;
    @PostMapping("/oauthClientDetails")
    public RespBean insertOauthClientDetails(@Valid OauthClientDetails oauthClientDetails, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return  iOauthClientDetailsService.insertOauthClientDetails(oauthClientDetails);
    }

    @DeleteMapping("/oauthClientDetails")
    public  RespBean deleteOauthClientDetails( @RequestParam String clientId){
        return iOauthClientDetailsService.deleteOauthClientDetails(clientId);
    }
    @PutMapping("/oauthClientDetails")
    public RespBean updateOauthClientDetails(@Valid OauthClientDetails oauthClientDetails, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return  iOauthClientDetailsService.updateOauthClientDetails(oauthClientDetails);
    }

    @GetMapping("/oauthClientDetails/{clientId}")
    public RespBean selectOauthClientDetails(@PathVariable("clientId") String clientId){
        if (clientId==null){
            logger.error("您输入的数据为空");
            return RespBean.error("您输入的数据为空");
        }
        return iOauthClientDetailsService.selectOauthClientDetailsByClientId(clientId);
    }

    @GetMapping("/oauthClientDetails")
    public  RespBean  selectOauthClientDetailsByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return iOauthClientDetailsService.selectOauthClientDetailsByPage(pageNumber, pageSize);
    }
}
