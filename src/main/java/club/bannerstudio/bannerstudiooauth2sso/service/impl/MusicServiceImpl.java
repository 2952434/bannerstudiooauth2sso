package club.bannerstudio.bannerstudiooauth2sso.service.impl;

import club.bannerstudio.bannerstudiooauth2sso.entity.MemberMusic;
import club.bannerstudio.bannerstudiooauth2sso.entity.Music;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MemberMusicMapper;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MusicMapper;
import club.bannerstudio.bannerstudiooauth2sso.service.IMusicService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:39
 */
@Service
public class MusicServiceImpl implements IMusicService {



    protected static final Logger logger = LoggerFactory.getLogger(MusicServiceImpl.class);
    @Autowired
    protected MusicMapper musicMapper;
    @Autowired
    protected MemberMusicMapper memberMusicMapper;

    @Override
    public RespBean insertMusic(Music music, Integer bannerId) {
        QueryWrapper<Music> queryWrapperMusic = new QueryWrapper<>();
        queryWrapperMusic = queryWrapperMusic.eq("musicName", music.getMusicName());
        List<Music> listMusic = musicMapper.selectList(queryWrapperMusic);
         if (listMusic.size()==0){
             if (musicMapper.insert(music)==1){
                 logger.info("音乐插入成功");
                 List<Music> returnListMusic = musicMapper.selectList(queryWrapperMusic);
                 Music returnMusic=returnListMusic.get(0);
                 if (memberMusicMapper.insert(new MemberMusic(returnMusic.getId(),bannerId))== 1){
                     logger.info("该音乐和该用户关联成功");
                     return RespBean.ok("音乐插入成功");
                 }
                 logger.error("系统异常,音乐插入失败");
                 return RespBean.error("系统异常,音乐插入失败");
             }
             logger.error("系统异常,音乐插入失败");
             return RespBean.error("系统异常,音乐插入失败");
         }
        Music returnMusic=listMusic.get(0);
        QueryWrapper<MemberMusic> queryWrapperMemberMusic = new QueryWrapper<>();
        queryWrapperMemberMusic = queryWrapperMemberMusic.eq("bannerId", bannerId);
        List<MemberMusic> listMemberMusic = memberMusicMapper.selectList(queryWrapperMemberMusic);
        if (listMemberMusic.size()==0){
          if (memberMusicMapper.insert(new MemberMusic(returnMusic.getId(),bannerId))==1){
              logger.info("音乐插入成功");
              return RespBean.ok("音乐插入成功");
          }
            logger.error("系统异常,音乐插入失败");
            return RespBean.error("系统异常,音乐插入失败");
        }
        for (MemberMusic returnMemberMusic:listMemberMusic) {
            if (returnMemberMusic.getMusicId().equals(returnMusic.getId())){
                logger.error("该音乐已经存在，插入失败");
                return RespBean.error("该音乐已经存在，插入失败");
            }
        }
        if (memberMusicMapper.insert(new MemberMusic(returnMusic.getId(),bannerId))== 1){
            logger.info("音乐插入成功");
            return RespBean.ok("音乐插入成功");
        }   logger.error("系统异常,音乐插入失败");
        return RespBean.error("系统异常,音乐插入失败");
    }

    @Override
    public RespBean deleteMusic(Integer bannerId, Integer musicId) {
        QueryWrapper<Music> queryWrapperMusic = new QueryWrapper<>();
        queryWrapperMusic = queryWrapperMusic.eq("musicId",musicId);
        List<Music> listMusic = musicMapper.selectList(queryWrapperMusic);
        return null;
    }

    @Override
    public RespBean updateMusic(Music music, Integer bannerId) {
        return null;
    }

    @Override
    public RespBean selectMusicListByBannerId(Integer bannerId) {
        return null;
    }

    @Override
    public RespBean selectMusicListByPage(Integer pageNumber, Integer pageSize, Integer bannerId) {
        return null;
    }
}
