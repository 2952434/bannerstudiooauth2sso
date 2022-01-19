package club.bannerstudio.oauth2sso.service.impl;

import club.bannerstudio.oauth2sso.entity.Member;
import club.bannerstudio.oauth2sso.entity.User;
import club.bannerstudio.oauth2sso.mapper.MemberMapper;
import club.bannerstudio.oauth2sso.mapper.UserMapper;
import club.bannerstudio.oauth2sso.service.IMemberService;
import club.bannerstudio.oauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:38
 */
@Service
public class MemberServiceImpl implements IMemberService {
    protected static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    protected MemberMapper memberMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 增加成员数据
     * @param member
     * @return RespBean
     */
    @Override
    public RespBean insertMember(Member member) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("bannerId", member.getBannerId());
        List<Member> list = memberMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            if (memberMapper.insert(member)== 1){
                logger.info("插入成功");
                return  RespBean.ok("插入成功");
            }
            logger.error("系统异常,插入失败");
            return  RespBean.error("系统异常,插入失败");
        }
        logger.error("您输入的数据已经存在，插入失败");
        return RespBean.error("您输入的数据已经存在，插入失败");
    }

    /**
     * 根据BannerID删除成员数据
     * @param bannerId
     * @return RespBean
     */
    @Override
    public RespBean deleteMember(Integer bannerId) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("bannerId", bannerId);
        List<Member> list = memberMapper.selectList(queryWrapper);
        if (list != null) {
            if (memberMapper.delete(queryWrapper)!= 0){
                logger.info("删除成功");
                return  RespBean.ok("删除成功");
            }
            logger.error("系统异常，删除失败");
            return RespBean.error("系统异常，删除失败");
        }
        logger.info("您要删除的数据不存在，删除失败");
        return RespBean.error("您要删除的数据不存在，删除失败");
    }

    /**
     *更新成员数据
     * @param member
     * @return RespBean
     */
    @Override
    public RespBean updateMember(Member member) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("bannerId", member.getBannerId());
        List<Member> list = memberMapper.selectList(queryWrapper);
        if (list != null) {
            if (memberMapper.update(member, queryWrapper)!= 0){
                logger.info("更新成功");
                return RespBean.ok("更新成功");
            }
              logger.error("系统异常，更新失败");
        }
        logger.error("您要更新的数据不存在，更新失败");
        return RespBean.error("您要更新的数据不存在，更新失败");
    }

    /**
     * 根据BannerID查询成员数据
     * @param bannerId
     * @return RespBean
     */
    @Override
    public RespBean selectMemberByBannerId(Integer bannerId) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("bannerId", bannerId);
        List<Member> list = memberMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功",list);
        }
        logger.error("查询失败");
        return RespBean.error("查询失败");
    }

    /**
     * 分页查询所有的成员数据
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    @Override
    public RespBean selectMemberListByPage(Integer pageNumber, Integer pageSize) {
        Page<Member> page = new Page<>(pageNumber, pageSize);
        IPage<Member> memberIPage =memberMapper.selectPage(page, null);
        return RespBean.ok("查询成功",memberIPage);
    }

    @Override
    public RespBean selectHeadUrlByUserName(String userName) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userName",userName);
        List<User> users = userMapper.selectList(userQueryWrapper);
        if (users.size()==0){
            logger.error("该用户不存在");
            return RespBean.error("该用户不存在");
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("userId",users.get(0).getId());
        List<Member> members = memberMapper.selectList(memberQueryWrapper);
        if (members.size()==0){
            logger.error("该成员不存在");
            return RespBean.error("该成员不存在");
        }
        logger.info("该成员头像地址为："+members.get(0).getHeadPortraitUrl());
        return RespBean.ok("该成员头像地址为：",members.get(0).getHeadPortraitUrl());
    }
}
