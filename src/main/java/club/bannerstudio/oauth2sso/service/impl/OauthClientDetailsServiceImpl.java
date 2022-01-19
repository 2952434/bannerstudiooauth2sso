package club.bannerstudio.oauth2sso.service.impl;

import club.bannerstudio.oauth2sso.entity.OauthClientDetails;
import club.bannerstudio.oauth2sso.mapper.OauthClientDetailsMapper;
import club.bannerstudio.oauth2sso.service.IOauthClientDetailsService;
import club.bannerstudio.oauth2sso.utils.RespBean;
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
 * @Date: 2022/1/6 19:34
 */
@Service
public class OauthClientDetailsServiceImpl implements IOauthClientDetailsService {

    protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected OauthClientDetailsMapper oauthClientDetailsMapper;

    /**
     * 增加授权信息
     * @param oauthClientDetails
     * @return RespBean
     */
    @Override
    public RespBean insertOauthClientDetails(OauthClientDetails oauthClientDetails) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("client_id", oauthClientDetails.getClientId());
        List<OauthClientDetails> list = oauthClientDetailsMapper.selectList(queryWrapper);
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        if (list.size() == 0) {
            if (oauthClientDetailsMapper.insert(oauthClientDetails) == 1){
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
     * 根据客户端id删除授权信息
     * @param clientId
     * @return RespBean
     */
    @Override
    public RespBean deleteOauthClientDetails(String clientId) {
            if (oauthClientDetailsMapper.deleteById(clientId) != 0){
                logger.info("删除成功");
                return RespBean.ok("删除成功");
            }
            logger.error("您要删除的数据不存在，删除失败");
            return RespBean.error("您要删除的数据不存在，删除失败");

    }

    /**
     * 更改授权信息
     * @param oauthClientDetails
     * @return RespBean
     */
    @Override
    public RespBean updateOauthClientDetails(OauthClientDetails oauthClientDetails) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("client_id", oauthClientDetails.getClientId());
        List<OauthClientDetails> list = oauthClientDetailsMapper.selectList(queryWrapper);
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        if (list != null) {
            if (oauthClientDetailsMapper.update(oauthClientDetails, queryWrapper) != 0){
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
     * 根据客户端id查询授权信息
     * @param clientId
     * @return RespBean
     */
    @Override
    public RespBean selectOauthClientDetailsByClientId(String clientId) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("client_id", clientId);
        List<OauthClientDetails> list = oauthClientDetailsMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功",list);
        }
        logger.info("查询失败");
        return RespBean.error("查询失败");
    }

    /**
     * 分页查询客户端授权信息
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    @Override
    public RespBean selectOauthClientDetailsByPage(Integer pageNumber, Integer pageSize) {
        Page<OauthClientDetails> page = new Page<>(pageNumber, pageSize);
        IPage<OauthClientDetails> oauthClientDetailsIPage= oauthClientDetailsMapper.selectPage(page, null);
        return RespBean.ok("查询成功",oauthClientDetailsIPage);
    }
}
