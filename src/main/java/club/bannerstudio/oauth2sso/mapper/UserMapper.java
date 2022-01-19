package club.bannerstudio.oauth2sso.mapper;

import club.bannerstudio.oauth2sso.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Ben
 * @Date: 2021/12/30 13:49
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
