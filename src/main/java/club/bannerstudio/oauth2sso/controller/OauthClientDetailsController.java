package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.entity.OauthClientDetails;
import club.bannerstudio.oauth2sso.service.IOauthClientDetailsService;
import club.bannerstudio.oauth2sso.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Oauth客户端操作", value = "OauthClientDetailsController")
public class OauthClientDetailsController {

    protected static final Logger logger = LoggerFactory.getLogger(OauthClientDetailsController.class);

    @Autowired
    protected IOauthClientDetailsService iOauthClientDetailsService;


    @PostMapping("/user/oauthClientDetails")
    @ApiOperation(value = "增加授权信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "clientId",
                    value = "客户端id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "resourceIds",
                    value = "资源id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "clientSecret",
                    value = "客户端秘钥", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "scope",
                    value = "授权范围", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "authorizedGrantTypes",
                    value = "授权支持的模式", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "webServerRedirectUri",
                    value = "回调地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "authorities",
                    value = "权限", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "accessTokenValidity",
                    value = "accessToken有效时间", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "refreshTokenValidity",
                    value = "refreshToken有效时间", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "additionalInformation",
                    value = "预留字段json格式", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "autoapprove",
                    value = "autoapprove", required = false, dataTypeClass = String.class),
    })
    public RespBean insertOauthClientDetails(@Valid OauthClientDetails oauthClientDetails, BindingResult bindingResult) {
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
        return iOauthClientDetailsService.insertOauthClientDetails(oauthClientDetails);
    }

    @DeleteMapping("/oauthClientDetails")
    @ApiOperation(value = "根据客户端id删除授权信息")
    @ApiImplicitParam(type = "query", name = "clientId",
            value = "客户端id", required = true, dataTypeClass = String.class)
    public RespBean deleteOauthClientDetails(@RequestParam String clientId) {
        return iOauthClientDetailsService.deleteOauthClientDetails(clientId);
    }

    @PutMapping("/admin/oauthClientDetails")
    @ApiOperation(value = "更新授权信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "clientId",
                    value = "客户端id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "resourceIds",
                    value = "资源id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "clientSecret",
                    value = "客户端秘钥", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "scope",
                    value = "授权范围", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "authorizedGrantTypes",
                    value = "授权支持的模式", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "webServerRedirectUri",
                    value = "回调地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "authorities",
                    value = "权限", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "accessTokenValidity",
                    value = "accessToken有效时间", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "refreshTokenValidity",
                    value = "refreshToken有效时间", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "additionalInformation",
                    value = "预留字段json格式", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "autoapprove",
                    value = "autoapprove", required = false, dataTypeClass = String.class),
    })
    public RespBean updateOauthClientDetails(@Valid OauthClientDetails oauthClientDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员修改失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return iOauthClientDetailsService.updateOauthClientDetails(oauthClientDetails);
    }

    @GetMapping("/admin/oauthClientDetails/{clientId}")
    @ApiOperation(value = "根据客户端id查询客户端信息")
    @ApiImplicitParam(type = "query", name = "clientId",
            value = "客户端id", required = true, dataTypeClass = String.class)
    public RespBean selectOauthClientDetails(@PathVariable("clientId") String clientId) {
        if (clientId == null) {
            logger.error("您输入的数据为空");
            return RespBean.error("您输入的数据为空");
        }
        return iOauthClientDetailsService.selectOauthClientDetailsByClientId(clientId);
    }

    @GetMapping("/admin/oauthClientDetails")
    @ApiOperation(value = "分页查询客户端信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNumber",
                    value = "第几页", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "pageSize",
                    value = "每页几条信息", required = true, dataTypeClass = Integer.class)
    })
    public RespBean selectOauthClientDetailsByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return iOauthClientDetailsService.selectOauthClientDetailsByPage(pageNumber, pageSize);
    }
}
