package club.bannerstudio.oauth2sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: Ben
 * @Date: 2021/12/30 11:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("user")
@ApiModel(value = "用户实体")
public class User {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @TableField("userName")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$",message = "4到16位（字母，数字，下划线，减号）")
    private String userName;

    /**
     * 密码
     */
    @Pattern(regexp = "(^[a-zA-Z]\\w{5,17}$)", message = "密码不符合要求(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)")
    @NotNull(message = "密码不能为空")
    @TableField("password")
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "邮箱不符合规范")
    @NotNull(message = "邮箱不能为空")
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "手机号不符合格式")
    @NotNull(message = "手机号不能为空")
    @TableField("phone")
    private String phone;

    /**
     * 用户权限
     */
    @TableField("role")
    private String role;

    @TableField("creatTime")
    private String creatTime;
}
