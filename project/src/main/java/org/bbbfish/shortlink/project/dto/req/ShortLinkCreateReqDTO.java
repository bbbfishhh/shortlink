package org.bbbfish.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkCreateReqDTO {
    /**
     * domain
     */
    private String domain;

    /**
     * origin_url
     */
    private String originUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * created_type
     */
    private Integer createdType;

    /**
     * valid_date_type
     */
    private Integer validDateType;

    /**
     * valid_date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm::ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * create_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm::ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * describe
     */
    private String describe;

}
