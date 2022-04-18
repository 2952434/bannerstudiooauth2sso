package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.entity.AuthUser;
import club.bannerstudio.oauth2sso.service.ISelectInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ljx
 * @Date: 2022/3/5 8:58
 * @role:
 */
@RestController
@Api(tags = "资源服务器获取用户信息接口", value = "SelectInformationController")
public class SelectInformationController {

    @Autowired
    private ISelectInformation iSelectInformation;

    @GetMapping("/admin/getInformation/{userName}")
    @ApiOperation(value = "资源服务器根据用户名访问用户信息")
    @ApiImplicitParam(type = "query", name = "userName",
            value = "用户名", required = true, dataTypeClass = String.class)
    public AuthUser selectInformationByUserName(@PathVariable("userName") String userName){
        return iSelectInformation.selectAuthUserByUserName(userName);
    }

}
