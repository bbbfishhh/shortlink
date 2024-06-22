package org.bbbfish.shortlink.project.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
@Data
public class BaseDO {
    /**
     * create_time
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * update_time
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * del_flag
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
