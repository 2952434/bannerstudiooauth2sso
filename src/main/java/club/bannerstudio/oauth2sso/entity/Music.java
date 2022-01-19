package club.bannerstudio.oauth2sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Ben
 * @Date: 2021/12/30 12:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("music")
@ApiModel(value = "音乐实体")
public class Music {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer musicId;

    /**
     * 音乐名称
     */
    @NotNull(message = "音乐名称不能为空")
    @TableField("musicName")
    private String musicName;


    /**
     * 音乐地址
     */
    @NotNull(message = "音乐地址不能为空")
    @TableField("musicUrl")
    private String musicUrl;

    /**
     * 歌手姓名
     */
    @NotNull(message = "歌手姓名不能为空")
    @TableField("singerName")
    private String singerName;
}
