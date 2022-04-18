package club.bannerstudio.oauth2sso.service.impl;

import club.bannerstudio.oauth2sso.entity.AuthUser;
import club.bannerstudio.oauth2sso.entity.Member;
import club.bannerstudio.oauth2sso.entity.User;
import club.bannerstudio.oauth2sso.mapper.MemberMapper;
import club.bannerstudio.oauth2sso.mapper.UserMapper;
import club.bannerstudio.oauth2sso.service.ISelectInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ljx
 * @Date: 2022/3/5 8:51
 * @role:
 */
@Service
public class SelectInformationImpl implements ISelectInformation {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberMapper memberMapper;


    /**
     * 根据用户名查询用户所有信息
     * @param userName
     */
    @Override
    public AuthUser selectAuthUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user!=null){
            QueryWrapper<Member> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("userId",user.getId());
            Member member = memberMapper.selectOne(queryWrapper1);
            if (member!=null){
                return new AuthUser(user, member);
            }
        }
        return null;
    }
}
