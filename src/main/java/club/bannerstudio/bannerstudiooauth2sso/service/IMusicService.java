package club.bannerstudio.bannerstudiooauth2sso.service;


import club.bannerstudio.bannerstudiooauth2sso.entity.Music;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:35
 */
public interface IMusicService {


    /**
     * 增加音乐数据
     * @param music
     * @param bannerId
     * @return RespBean
     */
    RespBean insertMusic(Music music, Integer bannerId);

    /**
     * 根据删除音乐
     * @param musicId
     * @return RespBean
     */
    RespBean deleteMusic(Integer musicId);

    /**
     *更新音乐数据
     * @param music
     * @return RespBean
     */
    RespBean updateMusic(Music music);

    /**
     * 根据BannerID查询音乐数据
     * @param bannerId
     * @return RespBean
     */
    RespBean selectMusicListByBannerId(Integer bannerId);

    /**
     * 分页查询所有的成音乐数据
     * @param pageNumber
     * @param pageSize
     * @param bannerId
     * @return RespBean
     */
    RespBean selectMusicListByPage(Integer pageNumber, Integer pageSize,Integer bannerId);

}
