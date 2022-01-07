package club.bannerstudio.bannerstudiooauth2sso.controller;

import club.bannerstudio.bannerstudiooauth2sso.entity.Music;
import club.bannerstudio.bannerstudiooauth2sso.service.IMusicService;
import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2021/12/30 14:49
 */
@RestController
public class MusicController {
    protected static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    protected IMusicService iMusicService;

    @PostMapping("/admin/music")
    public RespBean insertMusic(@Valid Music music, BindingResult bindingResult,@RequestParam Integer bannerId){
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return  iMusicService.insertMusic(music, bannerId);
    }

    @DeleteMapping("/admin/music")
    public RespBean deleteMusic(@RequestParam Integer bannerId, @RequestParam Integer musicId){
        return iMusicService.deleteMusic(bannerId, musicId);
    }

    @PutMapping("/admin/music")
    public RespBean updateMusic(@Valid Music music, BindingResult bindingResult,@RequestParam Integer bannerId){
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            logger.error("成员添加失败！");
            for (FieldError fieldError : errors) {
                logger.error("错误的字段名：" + fieldError.getField());
                logger.error("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RespBean.error(map);
        }
        return  iMusicService.updateMusic(music, bannerId);
    }
//    @GetMapping("/admin/music")
//public RespBean selectMusicByBannerId(){
//
//    }

}
