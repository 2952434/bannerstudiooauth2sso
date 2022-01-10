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
 * @Date: 2021/12/30 11:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("member")
@ApiModel(value = "成员信息实体")
public class Member {

    /**
     * 成员id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @TableField("userId")
    private Integer userId;

    /**
     * BannerId
     */
    @NotNull(message = "BannerId不能为空")
    @TableField("bannerId")
    private Integer bannerId;

    /**
     * 成员姓名
     */
    @NotNull(message = "成员姓名不能为空")
    @TableField("memberName")
    private String memberName;

    /**
     * 成员性别
     */
    @NotNull(message = "成员性别不能为空")
    @TableField("sex")
    private String sex;

    /**
     * 成员方向
     */
    @NotNull(message = "成员方向不能为空")
    @TableField("direction")
    private String direction;

    /**
     * 成员年级
     */
    @NotNull(message = "成员年级不能为空")
    @TableField("grade")
    private String grade;

    /**
     * 成员生日
     */
    @NotNull(message = "成员生日不能为空")
    @TableField("birthday")
    private String birthday;

    /**
     * 成员头像
     */
    @NotNull(message = "成员头像不能为空")
    @TableField("headPortraitUrl")
    private String headPortraitUrl;


    /**
     * 成员博客地址
     */
    @NotNull(message = "成员博客地址不能为空")
    @TableField("blogUrl")
    private String blogUrl;

    /**
     * 成员Git地址(Gitee或者GitHub)
     */
    @TableField("gitUrl")
    private String gitUrl;

    /**
     * 成员个性签名
     */
    @NotNull(message = "成员个性签名不能为空")
    @TableField("personalizedSignature")
    private String personalizedSignature;

    /**
     * 成员QQ号
     */
    @NotNull(message = "成员QQ号不能为空")
    @TableField(value = "usersQQ")
    private String memberQQ;
    /**
     * 成员微信号
     */
    @NotNull(message = "成员微信号不能为空")
    @TableField(value = "usersWeChat")
    private String memberWeChat;
    /**
     * 成员公司
     */
    @NotNull(message = "成员公司不能为空")
    @TableField(value = "usersCompany")
    private String memberCompany;
    /**
     * 成员的工作
     */
    @NotNull(message = "成员的工作不能为空")
    @TableField(value = "usersWork")
    private String memberWork;
    /**
     * 用户工作地址
     */
    @NotNull(message = "成员工作地址不能为空")
    @TableField(value = "usersAddress")
    private String memberAddress;
    /**
     * 成员薪资
     */
    @NotNull(message = "成员薪资不能为空")
    @TableField(value = "usersPay")
    private Integer memberPay;

    /**
     * 成员自我介绍
     */
    @NotNull(message = "自我介绍不能为空")
    @TableField(value = "personalIntroduction")
    private String personalIntroduction;

}
