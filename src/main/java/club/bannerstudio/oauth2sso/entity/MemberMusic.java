package club.bannerstudio.oauth2sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Ben
 * @Date: 2021/12/30 13:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("memberMusic")
public class MemberMusic {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 音乐id
     */
    @NotNull(message = "音乐Id不能为空")
    @TableField("music_id")
    private Integer musicId;

    /**
     *bannerId
     */
    @NotNull(message = "bannerId不能为空")
    @TableField("banner_id")
    private Integer bannerId;

    public MemberMusic(Integer musicId, Integer bannerId) {
        this.musicId = musicId;
        this.bannerId = bannerId;
    }
}
