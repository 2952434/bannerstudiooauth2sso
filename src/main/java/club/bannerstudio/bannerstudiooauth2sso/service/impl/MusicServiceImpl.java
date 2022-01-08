package club.bannerstudio.bannerstudiooauth2sso.service.impl;

import club.bannerstudio.bannerstudiooauth2sso.entity.MemberMusic;
import club.bannerstudio.bannerstudiooauth2sso.entity.Music;
import club.bannerstudio.bannerstudiooauth2sso.entity.User;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MemberMusicMapper;
import club.bannerstudio.bannerstudiooauth2sso.mapper.MusicMapper;
import club.bannerstudio.bannerstudiooauth2sso.service.IMusicService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        queryWrapperMusic.eq("musicName", music.getMusicName());
        List<Music> listMusic = musicMapper.selectList(queryWrapperMusic);
         if (listMusic.size()==0){
             if (musicMapper.insert(music)==1){
                 logger.info("音乐插入成功");
                 List<Music> returnListMusic = musicMapper.selectList(queryWrapperMusic);
                 Music returnMusic=returnListMusic.get(0);
                 if (memberMusicMapper.insert(new MemberMusic(returnMusic.getMusicId(),bannerId))== 1){
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
        queryWrapperMemberMusic.eq("banner_id", bannerId);
        List<MemberMusic> listMemberMusic = memberMusicMapper.selectList(queryWrapperMemberMusic);
        if (listMemberMusic.size()==0){
          if (memberMusicMapper.insert(new MemberMusic(returnMusic.getMusicId(),bannerId))==1){
              logger.info("音乐插入成功");
              return RespBean.ok("音乐插入成功");
          }
            logger.error("系统异常,音乐插入失败");
            return RespBean.error("系统异常,音乐插入失败");
        }
        for (MemberMusic returnMemberMusic:listMemberMusic) {
            if (returnMemberMusic.getMusicId().equals(returnMusic.getMusicId())){
                logger.error("该音乐已经存在，插入失败");
                return RespBean.error("该音乐已经存在，插入失败");
            }
        }
        if (memberMusicMapper.insert(new MemberMusic(returnMusic.getMusicId(),bannerId))== 1){
            logger.info("音乐插入成功");
            return RespBean.ok("音乐插入成功");
        }   logger.error("系统异常,音乐插入失败");
        return RespBean.error("系统异常,音乐插入失败");
    }

    @Override
    public RespBean deleteMusic(Integer bannerId, Integer musicId) {
        UpdateWrapper<MemberMusic> updateWrapperMusic = new UpdateWrapper<>();
        updateWrapperMusic.eq("music_id",musicId);
        int delete = memberMusicMapper.delete(updateWrapperMusic);
        int delete1 = musicMapper.deleteById(musicId);
        if (delete==1&&delete1==1){
            logger.info("删除音乐成功！！！");
            return RespBean.ok("删除音乐成功！！！");
        }else {
            logger.error("删除音乐失败！！！");
            return RespBean.error("删除音乐失败！！！");
        }
    }

    @Override
    public RespBean updateMusic(Music music) {
        int updateById = musicMapper.updateById(music);
        if (updateById == 1){
            logger.info("更新成功");
            return RespBean.ok("更新成功");
        }else {
            logger.error("更新失败");
            return RespBean.error("更新失败");
        }
    }

    @Override
    public RespBean selectMusicListByBannerId(Integer bannerId) {
        QueryWrapper<MemberMusic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("banner_id",bannerId);
        List<MemberMusic> memberMusics = memberMusicMapper.selectList(queryWrapper);
        List<Music> musicList = new ArrayList<>();
        for (MemberMusic memberMusic : memberMusics) {
            QueryWrapper<Music> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("music_id",memberMusic.getMusicId());
            Music music = musicMapper.selectOne(queryWrapper1);
            musicList.add(music);
        }
        if (musicList.size()==0){
            logger.error("查询失败！！！");
            return RespBean.error("查询失败！！！");
        }else {
            logger.info("查询成功！！！");
            return RespBean.ok(musicList);
        }
    }

    @Override
    public RespBean selectMusicListByPage(Integer pageNumber, Integer pageSize, Integer bannerId) {
        Page<MemberMusic> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<MemberMusic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("banner_id",bannerId);
        Page<MemberMusic> memberMusicPage = memberMusicMapper.selectPage(page, queryWrapper);
        List<Music> musicList = new ArrayList<>();
        for (MemberMusic record : memberMusicPage.getRecords()) {
            QueryWrapper<Music> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("music_id",record.getMusicId());
            Music music = musicMapper.selectOne(queryWrapper1);
            musicList.add(music);
        }
        if (musicList.size() == 0){
            logger.error("分页查询失败！！！");
            return RespBean.error("分页查询失败！！！");
        }else {
            logger.info("分页查询成功");
            return RespBean.ok(musicList);
        }
    }
}
