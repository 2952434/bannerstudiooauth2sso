package club.bannerstudio.oauth2sso.mapper;

import club.bannerstudio.oauth2sso.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:29
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {


    /**
     * 查询所有方向
     * @return List<Map<String,String>>
     *
     */
    @Select("select me.direction from member me group by me.direction having me.direction != '全方向'")
    List<Map<String,String>> selectDirectionGroupBy();

    /**
     * 查询年级
     * @return List<Map<String,String>>
     */
    @Select("select me.grade from member me group by me.grade order by me.grade desc")
    List<Map<String,String>> selectGradeGroupBy();

    /**
     * 根据年级和方向查询成员
     * @param direction 方向
     * @param grade 年级
     * @return List<Map<String,String>>
     */
    @Select("select me.userId,me.memberName from member me where me.direction=#{direction} and me.grade = #{grade}")
    List<Map<String,String>> selectUserIdAndMemberName(@Param("direction") String direction,@Param("grade") String grade);
}
