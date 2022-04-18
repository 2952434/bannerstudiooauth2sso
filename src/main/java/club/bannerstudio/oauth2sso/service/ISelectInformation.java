package club.bannerstudio.oauth2sso.service;

import club.bannerstudio.oauth2sso.entity.AuthUser;

/**
 * @Author: Ljx
 * @Date: 2022/3/5 8:43
 * @role: 封装用户所有信息接口
 */
public interface ISelectInformation {

    /**
     * 根据userName查询用户所有信息
     * @param userName
     * @return
     */
    AuthUser selectAuthUserByUserName(String userName);
}
