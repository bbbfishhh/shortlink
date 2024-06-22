package org.bbbfish.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bbbfish.shortlink.admin.common.database.BaseDO;

@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * short-link-group-flag
     */
    private String gid;

    /**
     * name
     */
    private String name;

    /**
     * username
     */
    private String username;

    /**
     * group_order
     */
    private Integer sortOrder;

}
