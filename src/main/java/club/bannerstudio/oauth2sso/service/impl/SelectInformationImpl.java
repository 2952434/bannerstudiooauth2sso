package club.bannerstudio.oauth2sso.service.impl;

import club.bannerstudio.oauth2sso.entity.AuthUser;
import club.bannerstudio.oauth2sso.entity.Member;
import club.bannerstudio.oauth2sso.entity.User;
import club.bannerstudio.oauth2sso.mapper.MemberMapper;
import club.bannerstudio.oauth2sso.mapper.UserMapper;
import club.bannerstudio.oauth2sso.service.ISelectInformation;
import club.bannerstudio.oauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: Ljx
 * @Date: 2022/3/5 8:51
 * @role:
 */
@Service
public class SelectInformationImpl implements ISelectInformation {

    private static final Logger logger = LoggerFactory.getLogger(SelectInformationImpl.class);
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

    /**
     * 根据年级查询所有方向
     * @param grade 年级
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> selectDirectionGroupBy(String grade) {
        List<Map<String, String>> maps = memberMapper.selectDirectionGroupBy(grade);
        if (maps.size()==0){
            logger.error("数据查询失败");
            return maps;
        }
        logger.info("查询成功");
        return maps;
    }


    /**
     * 查询年级
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> selectGradeGroupBy() {
        List<Map<String, String>> maps = memberMapper.selectGradeGroupBy();
        if (maps.size()==0){
            logger.error("数据查询失败");
            return maps;
        }
        logger.info("查询成功");
        return maps;
    }


    /**
     * 根据年级和方向查询成员
     * @param direction 方向
     * @param grade 年级
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> selectUserIdAndMemberName(String direction, String grade) {
        List<Map<String, String>> maps = memberMapper.selectUserIdAndMemberName(direction, grade);
        if (maps.size()==0){
            logger.error("数据查询失败");
            return maps;
        }
        logger.info("查询成功");
        return maps;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespBean updateAllUserInformation(AuthUser authUser) {
        Member member = new Member(authUser);
        User user = new User(authUser);
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",authUser.getId());
        int update = memberMapper.update(member, updateWrapper);
        int updateById = userMapper.updateById(user);
        if (update==1&&updateById==1){
            logger.info("更新成功");
            return RespBean.ok("更新成功",true);
        }
        logger.error("更新失败");
        return RespBean.error("更新失败",false);
    }


}
