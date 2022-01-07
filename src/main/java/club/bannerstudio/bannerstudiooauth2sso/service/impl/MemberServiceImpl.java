package club.bannerstudio.bannerstudiooauth2sso.service.impl;

import club.bannerstudio.bannerstudiooauth2sso.entity.Member;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MemberMapper;
import club.bannerstudio.bannerstudiooauth2sso.service.IMemberService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
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

    @Override
    public RespBean selectMemberListByPage(Integer pageNumber, Integer pageSize) {
        Page<Member> page = new Page<>(pageNumber, pageSize);
        IPage<Member> memberIPage =memberMapper.selectPage(page, null);
        return RespBean.ok("查询成功",memberIPage);
    }
}
