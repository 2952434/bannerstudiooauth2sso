package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ben
 * @Date: 2022/1/7 18:08
 */
@RestController
@Api(tags = "测试",value = "TestController")
public class TestController {


    @GetMapping("/admin/test")
    @ApiOperation(value = "测试连接")
    public RespBean testController(){
        System.out.println("测试成功");
        return RespBean.ok("测试成功");
    }
}
