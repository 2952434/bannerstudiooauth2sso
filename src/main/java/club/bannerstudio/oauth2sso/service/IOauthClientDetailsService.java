package club.bannerstudio.oauth2sso.service;

import club.bannerstudio.oauth2sso.entity.OauthClientDetails;
import club.bannerstudio.oauth2sso.utils.RespBean;

/**
 * @Author: Ben
 * @Date: 2022/1/6 19:33
 */
public interface IOauthClientDetailsService {


    /**
     * 增加授权信息
     * @param oauthClientDetails
     * @return RespBean
     */
    RespBean insertOauthClientDetails(OauthClientDetails oauthClientDetails);


    /**
     * 根据客户端id删除授权信息
     * @param clientId
     * @return RespBean
     */
    RespBean deleteOauthClientDetails(String clientId);

    /**
     * 更改授权信息
     * @param oauthClientDetails
     * @return RespBean
     */
    RespBean updateOauthClientDetails(OauthClientDetails oauthClientDetails);

    /**
     * 根据客户端id查询授权信息
     * @param clientId
     * @return RespBean
     */
    RespBean selectOauthClientDetailsByClientId(String clientId);

    /**
     * 分页查询客户端授权信息
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    RespBean selectOauthClientDetailsByPage(Integer pageNumber, Integer pageSize);
}
