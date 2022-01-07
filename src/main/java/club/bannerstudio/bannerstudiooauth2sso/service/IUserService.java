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
     *增加用户
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
}
