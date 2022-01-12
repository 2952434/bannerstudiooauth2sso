package club.bannerstudio.bannerstudiooauth2sso.service.impl;


import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.manager.SendMail;
import club.bannerstudio.bannerstudiooauth2sso.mapper.UserMapper;
import club.bannerstudio.bannerstudiooauth2sso.service.IUserService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private SendMail sendMail;
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    /**
     * 根据邮箱发送验证码
     * @param email
     * @return RespBean
     */
    @Override
    public RespBean sendCodeByEmail(String email) {
        String code = sendMail.sendSimpleMail(email);
        if (code!=null){
            redisTemplate.opsForValue().set(email,code,180,TimeUnit.SECONDS);
            logger.info("验证码发送成功");
            return RespBean.ok("验证码发送成功");
        }
        logger.error("验证码发送失败");
        return RespBean.error("验证码发送失败");
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @return RespBean
     */
    @Override
    public RespBean insertUser(User user, String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("userName", user.getUserName());
        List<User> list = userMapper.selectList(queryWrapper);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (list.size() == 0) {
            String redisCode = redisTemplate.opsForValue().get(user.getEmail());
            if (!code.equals(redisCode)){
                logger.error("验证码错误");
                return RespBean.error("验证码错误");
            }
            if (userMapper.insert(user) == 1){
                logger.info("注册成功");
                return RespBean.ok("注册成功");
            }
            logger.error("系统异常，插入失败");
            return  RespBean.error("系统异常，插入失败");
        }
        logger.error("您输入的数据已经存在，插入失败");
        return  RespBean.error("您输入的数据已经存在，插入失败");
    }

    /**
     *管理员增加用户
     * @param user
     * @return RespBean
     */
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

    /**
     * 根据id删除用户
     * @param id
     * @return RespBean
     */
    @Override
    public RespBean deleteUser(Integer id) {
        if (userMapper.deleteById(id) != 0){
            logger.info("删除成功");
            return RespBean.ok("删除成功");
        }
        logger.error("系统异常，删除失败");
        return RespBean.error("系统异常，删除失败");
    }

    /**
     * 更改User
     * @param user
     * @return RespBean
     */
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

    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return RespBean
     */
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

    /**
     * 分页查询用户数据
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    @Override
    public RespBean selectUserListByPage(Integer pageNumber, Integer pageSize) {
        Page<User> page = new Page<>(pageNumber, pageSize);
        IPage<User> userIPage= userMapper.selectPage(page, null);
        return RespBean.ok("查询成功",userIPage);
    }

    /**
     * 根据email发送验证码
     * @param userName
     * @param email
     * @return
     */
    @Override
    public RespBean sendCodeByEmail(String userName,String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userName);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size()==0){
            logger.error("你输入的用户名未注册");
            return RespBean.error("你输入的用户名未注册");
        }else {
            if (email.equals(users.get(0).getEmail())){
                String code = sendMail.sendSimpleMail(email);
                System.out.println(code);
                System.out.println(email);
                redisTemplate.opsForValue().set(email,code,180, TimeUnit.SECONDS);
                logger.info("验证码发送成功");
                return RespBean.ok("验证码发送成功");
            }else {
                logger.error("您输入的邮箱和注册时不匹配");
                return RespBean.error("您输入的邮箱和注册时不匹配");
            }
        }
    }

    /**
     * 忘记密码
     * @param userName
     * @param email
     * @param newPassword
     * @param rPassword
     * @param code
     * @return RespBean
     */
    @Override
    public RespBean forgetPassword(String userName, String email, String newPassword, String rPassword, String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userName);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size()==0){
            logger.error("该用户不存在");
            return RespBean.error("该用户不存在");
        }else {
            if (email.equals(users.get(0).getEmail())){
                if (newPassword.equals(rPassword)){
                    String redisCode = redisTemplate.opsForValue().get(email);
                    if (code.equals(redisCode)){
                        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("userName",userName).set("password",passwordEncoder.encode(newPassword));
                        logger.info("修改密码成功"+newPassword);
                        return RespBean.ok("修改密码成功,新密码为："+newPassword);
                    }else {
                        logger.error("输入的验证码错误");
                        return RespBean.error("您输入的验证码错误");
                    }
                }else {
                    logger.error("两次输入密码不匹配");
                    return RespBean.error("两次输入密码不匹配");
                }
            }else {
                logger.error("邮箱和注册时不匹配");
                return RespBean.error("邮箱和注册时不匹配");
            }
        }
    }
}
