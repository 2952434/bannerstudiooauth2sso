package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.Member;
import club.bannerstudio.bannerstudiooauth2sso.service.IMemberService;
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
 * @Date: 2021/12/30 14:48
 */
@RestController
public class MemberController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected IMemberService iMemberService;

    /**
     * 增加成员信息
     * @param member
     * @param bindingResult
     * @return RespBean
     */
    @PostMapping("/admin/member")
    public RespBean insertMember(@Valid Member member, BindingResult bindingResult){
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
      return  iMemberService.insertMember(member);
    }

    /**
     * 根据BannerId删除成员信息
     * @param bannerId
     * @return RespBean
     */
    @DeleteMapping("/admin/member/{bannerId}")
    public  RespBean deleteMember(@PathVariable("bannerId") String bannerId){
        if (bannerId==null){
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        Integer returnBannerId=null;
        try {
            returnBannerId=Integer.parseInt(bannerId);
        }catch (Exception exception){
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
       return iMemberService.deleteMember(returnBannerId);
    }


    /**
     * 更改成员信息
     * @param member
     * @param bindingResult
     * @return RespBean
     */
    @PutMapping("/admin/member")
    public RespBean updateMember(@Valid Member member, BindingResult bindingResult){
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
        return  iMemberService.updateMember(member);
    }


    /**
     * 根据BannerId查询成员信息
     * @param bannerId
     * @return RespBean
     */
    @GetMapping("/admin/member/{bannerId}")
    public RespBean selectMemberByBannerId(@PathVariable("bannerId") String bannerId){
        if (bannerId==null){
            logger.error("您输入的数据为空，请重新输入");
            return RespBean.error("您输入的数据为空，请重新输入");
        }
        Integer returnBannerId=null;
        try {
            returnBannerId=Integer.parseInt(bannerId);
        }catch (Exception exception){
            logger.error("您传入的id不合法，请重新输入");
            return RespBean.error("您传入的id不合法，请重新输入");
        }
        return iMemberService.selectMemberByBannerId(returnBannerId);
    }


    /**
     * 分页查询成员信息
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    @GetMapping("/admin/member")
    public RespBean selectMemberListByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return iMemberService.selectMemberListByPage(pageNumber,pageSize);
    }
}
