package club.bannerstudio.bannerstudiooauth2sso.service.impl;


import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.mapper.UserMapper;
import club.bannerstudio.bannerstudiooauth2sso.service.IUserService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:37
 */
@Service
public class UserServiceImpl implements IUserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected UserMapper userMapper;

    @Override
    public RespBean insertUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("userName", user.getUserName());
        List<User> list = userMapper.selectList(queryWrapper);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (list.size() == 0) {
            if (userMapper.insert(user) == 1){
                logger.info("插入成功");
                return RespBean.ok("插入成功");
            }
            logger.error("系统异常，插入失败");
            return  RespBean.error("系统异常，插入失败");
        }
        logger.info("您输入的数据已经存在，插入失败");
        return  RespBean.error("您输入的数据已经存在，插入失败");
    }

    @Override
    public RespBean deleteUser(Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("id", id);
        List<User> list = userMapper.selectList(queryWrapper);
        if (list != null) {
            if (userMapper.deleteById(queryWrapper) != 0){
                logger.info("删除成功");
                return RespBean.ok("删除成功");
            }
            logger.error("系统异常，删除失败");
            return RespBean.error("系统异常，删除失败");
        }
        logger.info("您要删除的数据不存在，删除失败");
        return RespBean.error("您要删除的数据不存在，删除失败");
    }

    @Override
    public RespBean updateUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("userName", user.getUserName());
        List<User> list = userMapper.selectList(queryWrapper);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (list != null) {
            if (userMapper.update(user, queryWrapper) != 0){
                logger.info("更新成功");
                return RespBean.ok("更新成功");
            }
            logger.error("系统异常，更新失败");
            return RespBean.error("系统异常，更新失败");
        }
        logger.info("您要更新的数据不存在，更新失败");
        return RespBean.error("您要更新的数据不存在，更新失败");
    }

    @Override
    public RespBean selectUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("userName", userName);
        List<User> list = userMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功",list);
        }
        logger.info("查询失败");
        return RespBean.error("查询失败");
    }

    @Override
    public RespBean selectUserListByPage(Integer pageNumber, Integer pageSize) {
        Page<User> page = new Page<>(pageNumber, pageSize);
        IPage<User> userIPage= userMapper.selectPage(page, null);
        return RespBean.ok("查询成功",userIPage);
    }
}
