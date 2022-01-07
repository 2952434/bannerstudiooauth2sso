package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ben
 * @Date: 2022/1/7 18:08
 */
@RestController
public class TestController {


    @GetMapping("/admin/test")
    public RespBean testController(){
        System.out.println("测试成功");
        return RespBean.ok("测试成功");
    }
}
