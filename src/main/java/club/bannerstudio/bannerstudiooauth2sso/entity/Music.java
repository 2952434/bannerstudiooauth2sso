package club.bannerstudio.bannerstudiooauth2sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class Music {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
   private Integer id;

    /**
     * 音乐名称
     */
    @NotNull(message = "音乐名称不能为空")
    private String musicName;


    /**
     * 音乐地址
     */
    @NotNull(message = "音乐地址不能为空")
    private String musicUrl;

    /**
     * 歌手姓名
     */
    @NotNull(message = "歌手姓名不能为空")
    private String singerName;
}
