package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.entity.AuthUser;
import club.bannerstudio.oauth2sso.service.ISelectInformation;
import club.bannerstudio.oauth2sso.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    @ApiOperation(value = "资源服务器根据用户名访问用户信息",httpMethod = "GET")
    @ApiImplicitParam(type = "query", name = "userName",
            value = "用户名", required = true, dataTypeClass = String.class)
    public AuthUser selectInformationByUserName(@PathVariable("userName") String userName){
        return iSelectInformation.selectAuthUserByUserName(userName);
    }

    @GetMapping("/admin/selectGradeGroupBy")
    @ApiOperation(value = "资源服务器查询所有用户年级",httpMethod = "GET")
    public RespBean selectGradeGroupBy(){
        return RespBean.ok("查询成功",iSelectInformation.selectGradeGroupBy());
    }

    @GetMapping("/admin/selectDirectionGroupBy/{grade}")
    @ApiOperation(value = "资源服务器根据年级查询所有用户方向",httpMethod = "GET")
    @ApiImplicitParam(type = "query", name = "grade",
            value = "年级", required = true, dataTypeClass = String.class)
    public RespBean selectDirectionGroupBy(@PathVariable String grade){
        return RespBean.ok("查询成功",iSelectInformation.selectDirectionGroupBy(grade));
    }

    @GetMapping("/admin/selectUserIdAndMemberName/{direction}/{grade}")
    @ApiOperation(value = "资源服务器根据年级和方向查询用户id和用户名",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "direction",
                    value = "方向", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "grade",
                    value = "年级", required = true, dataTypeClass = String.class)
    })
    public RespBean selectUserIdAndMemberName(@PathVariable String direction,@PathVariable String grade){
        return RespBean.ok("查询成功",iSelectInformation.selectUserIdAndMemberName(direction,grade));
    }

    @PutMapping("/updateAllUserInformation")
    @ApiOperation(value = "更新用户信息",httpMethod = "PUT")
    public RespBean updateAllUserInformation(AuthUser authUser){
        return iSelectInformation.updateAllUserInformation(authUser);
    }


}
