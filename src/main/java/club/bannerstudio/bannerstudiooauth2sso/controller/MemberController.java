package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.Member;
import club.bannerstudio.bannerstudiooauth2sso.service.IMemberService;
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
 * @Date: 2021/12/30 14:48
 */
@RestController
@Api(tags = "成员信息管理接口", value = "MemberController")
public class MemberController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected IMemberService iMemberService;

    @PostMapping("/admin/member")
    @ApiOperation(value = "增加成员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id",
                    value = "成员主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "userId",
                    value = "用户id", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "bannerId",
                    value = "bannerId", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "memberName",
                    value = "成员姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "sex",
                    value = "成员性别", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "direction",
                    value = "成员方向", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "grade",
                    value = "成员年级", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "birthday",
                    value = "成员生日", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "headPortraitUrl",
                    value = "成员头像", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "blogUrl",
                    value = "成员博客地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "gitUrl",
                    value = "成员Git地址(Gitee或者GitHub)", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "personalizedSignature",
                    value = "成员个性签名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberQQ",
                    value = "成员QQ号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberWeChat",
                    value = "成员微信号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberCompany",
                    value = "成员公司", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberWork",
                    value = "成员的工作", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberAddress",
                    value = "成员工作地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberPay",
                    value = "成员薪资", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "personalIntroduction",
                    value = "成员自我介绍", required = true, dataTypeClass = String.class)
    })
    public RespBean insertMember(@Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return iMemberService.insertMember(member);
    }


    @DeleteMapping("/admin/member/{bannerId}")
    @ApiOperation(value = "根据BannerId删除成员信息")
    @ApiImplicitParam(type = "query", name = "bannerId",
            value = "bannerId", required = true, dataTypeClass = String.class)
    public RespBean deleteMember(@PathVariable("bannerId") String bannerId) {
        if (bannerId == null) {
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        Integer returnBannerId = null;
        try {
            returnBannerId = Integer.parseInt(bannerId);
        } catch (Exception exception) {
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
        return iMemberService.deleteMember(returnBannerId);
    }


    @PutMapping("/admin/member")
    @ApiOperation(value = "更改成员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id",
                    value = "成员主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "userId",
                    value = "用户id", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "bannerId",
                    value = "bannerId", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "memberName",
                    value = "成员姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "sex",
                    value = "成员性别", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "direction",
                    value = "成员方向", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "grade",
                    value = "成员年级", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "birthday",
                    value = "成员生日", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "headPortraitUrl",
                    value = "成员头像", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "blogUrl",
                    value = "成员博客地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "gitUrl",
                    value = "成员Git地址(Gitee或者GitHub)", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "personalizedSignature",
                    value = "成员个性签名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberQQ",
                    value = "成员QQ号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberWeChat",
                    value = "成员微信号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberCompany",
                    value = "成员公司", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberWork",
                    value = "成员的工作", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberAddress",
                    value = "成员工作地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "memberPay",
                    value = "成员薪资", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "personalIntroduction",
                    value = "成员自我介绍", required = true, dataTypeClass = String.class)
    })
    public RespBean updateMember(@Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return iMemberService.updateMember(member);
    }


    @GetMapping("/admin/member/{bannerId}")
    @ApiOperation(value = "根据BannerId查询成员信息")
    @ApiImplicitParam(type = "query", name = "bannerId",
            value = "bannerId", required = true, dataTypeClass = String.class)
    public RespBean selectMemberByBannerId(@PathVariable("bannerId") String bannerId) {
        if (bannerId == null) {
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        Integer returnBannerId = null;
        try {
            returnBannerId = Integer.parseInt(bannerId);
        } catch (Exception exception) {
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
        return iMemberService.selectMemberByBannerId(returnBannerId);
    }


    @GetMapping("/admin/member")
    @ApiOperation(value = "分页查询成员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNumber",
                    value = "第几页", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "pageSize",
                    value = "每页几条信息", required = true, dataTypeClass = Integer.class)
    })
    public RespBean selectMemberListByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return iMemberService.selectMemberListByPage(pageNumber, pageSize);
    }


    @GetMapping("/user/head/{userName}")
    @ApiOperation(value = "根据用户名查询用户头像")
    @ApiImplicitParam(type = "query",name = "userName",
            value = "用户名",required = true,dataTypeClass = String.class)
    public RespBean selectHeadUrlByUserName(@PathVariable("userName") String userName) {
        return iMemberService.selectHeadUrlByUserName(userName);
    }
}
