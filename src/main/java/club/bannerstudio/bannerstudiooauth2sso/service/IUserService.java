package club.bannerstudio.bannerstudiooauth2sso.service;


import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:35
 */
public interface IUserService {

    /**
     * 根据邮箱发送验证码
     * @param email
     * @return RespBean
     */
    RespBean sendCodeByEmail(String email);
    /**
     * 用户注册
     * @param user
     * @param code
     * @return RespBean
     */
    RespBean insertUser(User user, String code);

    /**
     *管理员增加用户
     * @param user
     * @return RespBean
     */
    RespBean insertUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return RespBean
     */
    RespBean deleteUser(Integer id);


    /**
     * 更改User
     * @param user
     * @return RespBean
     */
    RespBean updateUser(User user);

    /**
     * 根据邮箱查询用户数据
     * @param userName
     * @return RespBean
     */
    RespBean selectUserByUserName(String userName);

    /**
     * 分页查询用户数据
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    RespBean selectUserListByPage(Integer pageNumber, Integer pageSize);

    /**
     * 根据email发送验证码
     * @param userName
     * @param email
     * @return
     */
    RespBean sendCodeByEmail(String userName, String email);
    /**
     * 忘记密码
     * @param userName
     * @param email
     * @param newPassword
     * @param rPassword
     * @param code
     * @return RespBean
     */
    RespBean forgetPassword(String userName,String email,String newPassword,String rPassword,String code);
}
