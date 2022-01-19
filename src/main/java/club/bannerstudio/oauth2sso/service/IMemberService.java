package club.bannerstudio.oauth2sso.service;


import club.bannerstudio.oauth2sso.entity.Member;
import club.bannerstudio.oauth2sso.utils.RespBean;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:34
 */
public interface IMemberService {

    /**
     * 增加成员数据
     * @param member
     * @return RespBean
     */
    RespBean insertMember(Member member);

    /**
     * 根据BannerID删除成员数据
     * @param bannerId
     * @return RespBean
     */
    RespBean deleteMember(Integer bannerId);

    /**
     *更新成员数据
     * @param member
     * @return RespBean
     */
    RespBean updateMember(Member member);

    /**
     * 根据BannerID查询成员数据
     * @param bannerId
     * @return RespBean
     */
    RespBean selectMemberByBannerId(Integer bannerId);

    /**
     * 分页查询所有的成员数据
     * @param pageNumber
     * @param pageSize
     * @return RespBean
     */
    RespBean selectMemberListByPage(Integer pageNumber, Integer pageSize);

    /**
     * 根据用户名查询用户头像
     * @param userName
     * @return RespBean
     */
    RespBean selectHeadUrlByUserName(String userName);
}
