package club.bannerstudio.bannerstudiooauth2sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Ben
 * @Date: 2022/1/6 19:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("oauth_client_details")
@ApiModel(value = "客户端实体")
public class OauthClientDetails {


    /**
     * 客户端id
     */
    @TableId(value = "client_id", type = IdType.NONE)
    @NotNull(message = "客户端id为空")
    private String clientId;

    /**
     * 资源id
     */
    @TableField("resource_ids")
    @NotNull(message = "资源id为空")
    private String resourceIds;

    /**
     * 客户端密钥
     */
    @TableField("client_secret")
    @NotNull(message = "客户端密钥为空")
    private String clientSecret;

    /**
     * 授权范围
     */
    @NotNull(message = "授权范围为空")
    private String scope;


    /**
     * 授权码支持的模式
     */
    @TableField("authorized_grant_types")
    @NotNull(message = "授权码支持的模式为空")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @TableField("web_server_redirect_uri")
    @NotNull(message = "回调地址为空")
    private String webServerRedirectUri;

    /**
     * 权限
     */
    private String authorities;

    /**
     * access_token有效时间
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     * refresh_token有效时间
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 预留字段 若设置必须为json
     */
    @TableField("additional_information")
    private String additionalInformation;

    /**
     * autoapprove
     */
    private String autoapprove;
}
