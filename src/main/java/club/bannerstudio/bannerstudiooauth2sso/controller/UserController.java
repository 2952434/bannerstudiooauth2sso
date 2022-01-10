package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.service.IUserService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
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
 * @Date: 2021/12/29 4:50
 */

@RestController
@Api(tags = "用户信息管理", value = "UserController")
public class UserController {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected IUserService iUserService;

    @PostMapping("/user/code")
    @ApiOperation(value = "用户注册时发送验证码")
    @ApiImplicitParam(type = "query",name = "email",
            value = "用户邮箱",required = true,dataTypeClass = String.class)
    public RespBean sendCodeByEmail(String email) {
        String judge = "(^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$)";
        if (!email.matches(judge)) {
            logger.error("邮箱不符合要求");
            return RespBean.error("邮箱不符合要求");
        }
        return iUserService.sendCodeByEmail(email);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id",
                    value = "主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "userName",
                    value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "password",
                    value = "用户密码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "email",
                    value = "用户邮箱", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "phone",
                    value = "用户手机号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "role",
                    value = "用户权限", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "code",
                    value = "验证码",required = true,dataTypeClass = String.class)
    })
    public RespBean registerUser(@Valid User user,@RequestParam String code, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("注册失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        user.setRole("ROLE_User");
        return iUserService.insertUser(user,code);
    }


    @PostMapping("/admin/user")
    @ApiOperation(value = "管理员增加用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id",
                    value = "主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "userName",
                    value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "password",
                    value = "用户密码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "email",
                    value = "用户邮箱", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "phone",
                    value = "用户手机号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "role",
                    value = "用户权限", required = true, dataTypeClass = String.class)
    })
    public RespBean insertIntranetUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("注册失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        if ("IntranetUser".equals(user.getRole())) {
            user.setRole("ROLE_IntranetUser");
            return iUserService.insertUser(user);
        } else if ("InterViewUser".equals(user.getRole())) {
            user.setRole("ROLE_InterViewUser");
            return iUserService.insertUser(user);
        } else if ("AdminUser".equals(user.getRole())) {
            user.setRole("ROLE_AdminUser");
            return iUserService.insertUser(user);
        } else if ("User".equals(user.getRole())) {
            user.setRole("ROLE_User");
            return iUserService.insertUser(user);
        } else {
            logger.error("您插入的用户权限有误，请重新输入");
            return RespBean.error("您插入的用户权限有误，请重新输入");
        }
    }


    @DeleteMapping("/admin/user/{id}")
    @ApiOperation(value = "根据id删除用户信息")
    @ApiImplicitParam(type = "query", name = "id",
            value = "主键id", required = true, dataTypeClass = String.class)
    public RespBean deleteUser(@PathVariable("id") String id) {
        if (id == null) {
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        System.out.println(id);
        Integer returnId = null;
        try {
            returnId = Integer.parseInt(id);
        } catch (Exception exception) {
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
        return iUserService.deleteUser(returnId);
    }

    @PutMapping("/admin/user")
    @ApiOperation(value = "更改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id",
                    value = "主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "userName",
                    value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "password",
                    value = "用户密码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "email",
                    value = "用户邮箱", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "phone",
                    value = "用户手机号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "role",
                    value = "用户权限", required = true, dataTypeClass = String.class)
    })
    public RespBean updateUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("注册失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        if ("IntranetUser".equals(user.getRole())) {
            user.setRole("ROLE_IntranetUser");
            return iUserService.updateUser(user);
        } else if ("InterViewUser".equals(user.getRole())) {
            user.setRole("ROLE_InterViewUser");
            return iUserService.updateUser(user);
        } else if ("AdminUser".equals(user.getRole())) {
            user.setRole("ROLE_AdminUser");
            return iUserService.updateUser(user);
        } else {
            logger.error("您插入的用户权限有误，请重新输入");
            return RespBean.error("您插入的用户权限有误，请重新输入");
        }
    }


    @GetMapping("/admin/user/{userName}")
    @ApiOperation(value = "根据用户名查询用户信息")
    @ApiImplicitParam(type = "query", name = "userName",
            value = "用户名", required = true, dataTypeClass = String.class)
    public RespBean selectUserByUserName(@PathVariable("userName") String userName) {
        return iUserService.selectUserByUserName(userName);
    }

    @GetMapping("/admin/user")
    @ApiOperation(value = "分页查询所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNumber",
                    value = "第几页", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "pageSize",
                    value = "每页几条信息", required = true, dataTypeClass = Integer.class)
    })
    public RespBean selectUserListByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return iUserService.selectUserListByPage(pageNumber, pageSize);
    }

    @PostMapping("/send/code")
    @ApiOperation(value = "通过邮箱发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "userName",
                    value = "用户名",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "email",
                    value = "该用户名注册时邮箱",required = true,dataTypeClass = String.class)
    })
    public RespBean sendCodeByEmail(@RequestParam String userName,@RequestParam String email) {
        String judge = "(^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$)";
        if (!email.matches(judge)) {
            logger.error("邮箱不符合要求");
            return RespBean.error("邮箱不符合要求");
        }else {
            return iUserService.sendCodeByEmail(userName,email);
        }
    }

    @PostMapping("/forget/password")
    @ApiOperation(value = "忘记密码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "userName",
                    value = "用户名",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "email",
                    value = "该用户名注册时邮箱",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "newPassword",
                    value = "新密码",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "rPassword",
                    value = "再次输入新密码",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(type = "query",name = "code",
                    value = "验证码(有效时长3分钟)",required = true,dataTypeClass = String.class)
    })
    public RespBean forgetPassword(String userName, String email, String newPassword, String rPassword, String code) {
        String judge = "(^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$)";
        if (!email.matches(judge)) {
            logger.error("邮箱不符合要求");
            return RespBean.error("邮箱不符合要求");
        }
        String judge1 = "(^[a-zA-Z]\\w{5,17}$)";
        if (!newPassword.matches(judge1)&&!rPassword.matches(judge1)) {
            logger.error("密码不符合要求(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)");
            return RespBean.error("密码不符合要求(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)");
        }
        return iUserService.forgetPassword(userName, email, newPassword, rPassword, code);
    }
}