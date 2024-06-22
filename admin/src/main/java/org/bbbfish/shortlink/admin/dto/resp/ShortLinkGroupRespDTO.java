package org.bbbfish.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class ShortLinkGroupRespDTO {

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
