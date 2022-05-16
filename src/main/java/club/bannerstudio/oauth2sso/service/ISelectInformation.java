package club.bannerstudio.oauth2sso.service;

import club.bannerstudio.oauth2sso.entity.AuthUser;
import club.bannerstudio.oauth2sso.utils.RespBean;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据年级查询所有方向
     * @param grade 年级
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> selectDirectionGroupBy(String grade);

    /**
     * 查询年级
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> selectGradeGroupBy();

    /**
     * 根据年级和方向查询成员
     * @param direction 方向
     * @param grade 年级
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> selectUserIdAndMemberName(String direction,String grade);


    /**
     * 更新所有用户信息
     * @param authUser
     * @return
     */
    RespBean updateAllUserInformation(AuthUser authUser);
}
