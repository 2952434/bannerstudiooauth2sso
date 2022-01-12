package club.bannerstudio.bannerstudiooauth2sso.service.impl;

import club.bannerstudio.bannerstudiooauth2sso.entity.AuthUser;
import club.bannerstudio.bannerstudiooauth2sso.entity.Member;
import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MemberMapper;
import club.bannerstudio.bannerstudiooauth2sso.mapper.UserMapper;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @Author: Ben
 * @Date: 2022/1/6 1:00
 */
@Service
public class UserAuthServiceImpl implements UserDetailsService {

    protected static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
    @Autowired
   protected UserMapper userMapper;

    @Autowired
    protected MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("用户名："+userName);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("userName", userName);
        User user= userMapper.selectOne(queryWrapper);
        if (user==null){
            logger.error("用户名不存在,请输入正确的用户名");
              throw new UsernameNotFoundException("用户名不存在,请输入正确的用户名");
        }
        QueryWrapper<Member> queryWrapperMember = new QueryWrapper<>();
        queryWrapperMember = queryWrapperMember.eq("userId", user.getId());
        Member member= memberMapper.selectOne(queryWrapperMember);
        if (member!=null){
            logger.info("该用户是工作室内网用户");
            return new AuthUser(user,member.getBannerId());
        }
        logger.info("该用户不是工作室内网用户");
        return new AuthUser(user,0);
    }
}
