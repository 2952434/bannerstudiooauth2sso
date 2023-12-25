package club.bannerstudio.oauth2sso.controller;

import club.bannerstudio.oauth2sso.entity.Music;
import club.bannerstudio.oauth2sso.service.IMusicService;
import club.bannerstudio.oauth2sso.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户音乐操作接口", value = "MusicController")
@CrossOrigin
public class MusicController {
    protected static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    protected IMusicService iMusicService;

    @PostMapping("/admin/music")
    @ApiOperation(value = "增加音乐数据")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "musicId",
                    value = "音乐主键id", required = false, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "musicName",
                    value = "音乐名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "musicUrl",
                    value = "音乐地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "singerName",
                    value = "歌手姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "bannerId",
                    value = "bannerId", required = true, dataTypeClass = Integer.class)
    })
    public RespBean insertMusic(@Valid Music music, BindingResult bindingResult, @RequestParam Integer bannerId) {
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
        return iMusicService.insertMusic(music, bannerId);
    }

    @DeleteMapping("/admin/music")
    @ApiOperation(value = "根据音乐id删除音乐")
    @ApiImplicitParam(type = "query", name = "musicId",
            value = "音乐id", required = true, dataTypeClass = Integer.class)
    public RespBean deleteMusic(@RequestParam Integer musicId) {
        return iMusicService.deleteMusic(musicId);
    }

    @PutMapping("/admin/music")
    @ApiOperation(value = "更新音乐数据")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "musicId",
                    value = "音乐主键id", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "musicName",
                    value = "音乐名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "musicUrl",
                    value = "音乐地址", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(type = "query", name = "singerName",
                    value = "歌手姓名", required = true, dataTypeClass = String.class)
    })
    public RespBean updateMusic(@Valid Music music, BindingResult bindingResult) {
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
        return iMusicService.updateMusic(music);
    }


    @GetMapping("/admin/music")
    @ApiOperation(value = "根据BannerID查询音乐数据")
    @ApiImplicitParam(type = "query", name = "bannerId",
            value = "bannerId", required = true, dataTypeClass = Integer.class)
    public RespBean selectMusicByBannerId(@RequestParam Integer bannerId) {
        return iMusicService.selectMusicListByBannerId(bannerId);
    }

    @GetMapping("/admin/music/{bannerId}")
    @ApiOperation(value = "分页查询bannerId的所有的成音乐数据")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNumber",
                    value = "第几页", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "pageSize",
                    value = "每页几条信息", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(type = "query", name = "bannerId",
                    value = "bannerId", required = true, dataTypeClass = Integer.class)
    })
    public RespBean selectMusicListByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @PathVariable("bannerId") Integer bannerId) {
        if (bannerId == null) {
            logger.error("bannerId不能为空");
            return RespBean.error("bannerId不能为空！！！");
        }
        return iMusicService.selectMusicListByPage(pageNumber, pageSize, bannerId);
    }
}
