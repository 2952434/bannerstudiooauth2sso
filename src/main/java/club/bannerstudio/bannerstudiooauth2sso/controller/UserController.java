package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.service.IUserService;
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
 * @Date: 2021/12/29 4:50
 */

@RestController
public class UserController {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected IUserService iUserService;


    /**
     * 用户注册接口
     * @param user
     * @param bindingResult
     * @return RespBean
     */
    @PostMapping("/register")
   public  RespBean registerUser(@Valid User user, BindingResult bindingResult) {
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
      return  iUserService.insertUser(user);
    }


    /**
     * 管理员增加用户接口
     * @param user
     * @param bindingResult
     * @return RespBean
     */
    @PostMapping("/admin/user")
    public  RespBean insertIntranetUser(@Valid User user, BindingResult bindingResult) {
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
        if ("IntranetUser".equals(user.getRole())){
            user.setRole("ROLE_IntranetUser");
            return  iUserService.insertUser(user);
        }else if ("InterViewUser".equals(user.getRole())){
            user.setRole("ROLE_InterViewUser");
            return  iUserService.insertUser(user);
        }else if ("AdminUser".equals(user.getRole())){
            user.setRole("ROLE_AdminUser");
            return  iUserService.insertUser(user);
        }else if ("User".equals(user.getRole())){
            user.setRole("ROLE_User");
            return  iUserService.insertUser(user);
        }else {
            logger.error("您插入的用户权限有误，请重新输入");
            return RespBean.error("您插入的用户权限有误，请重新输入");
        }
    }


    /**
     * 根据id删除用户信息
     * @param id
     * @return RespBean
     */
    @DeleteMapping("/admin/user/{id}")
    public RespBean deleteUser( @PathVariable("id") String id){
        if (id==null){
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        System.out.println(id);
        Integer returnId=null;
        try {
           returnId=Integer.parseInt(id);
        }catch (Exception exception){
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
       return iUserService.deleteUser(returnId);
    }

    /**
     * 更改用户信息
     * @param user
     * @param bindingResult
     * @return RespBean
     */
    @PutMapping("/admin/user")
    public  RespBean updateUser(@Valid User user, BindingResult bindingResult){
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
        if ("IntranetUser".equals(user.getRole())){
            user.setRole("ROLE_IntranetUser");
            return  iUserService.updateUser(user);
        }else if ("InterViewUser".equals(user.getRole())){
            user.setRole("ROLE_InterViewUser");
            return  iUserService.updateUser(user);
        }else if ("AdminUser".equals(user.getRole())){
            user.setRole("ROLE_AdminUser");
            return  iUserService.updateUser(user);
        }else{
            logger.error("您插入的用户权限有误，请重新输入");
            return RespBean.error("您插入的用户权限有误，请重新输入");
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return RespBean
     */
    @GetMapping("/admin/user/{userName}")
    public  RespBean selectUserByUserName(@PathVariable("userName") String userName){
     return   iUserService.selectUserByUserName(userName);
    }

    /**
     * 分页查询所有用户信息
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    @GetMapping("/admin/user")
    public  RespBean selectUserListByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return iUserService.selectUserListByPage(pageNumber,pageSize);
    }
    }