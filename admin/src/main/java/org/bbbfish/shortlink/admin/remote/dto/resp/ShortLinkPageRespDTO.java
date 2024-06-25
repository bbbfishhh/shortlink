package org.bbbfish.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkPageRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * domain
     */
    private String domain;

    /**
     * short_uri
     */
    private String shortUri;

    /**
     * full_short_url
     */
    private String fullShortUrl;

    /**
     * origin_url
     */
    private String originUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * valid_date_type
     */
    private Integer validDateType;

    /**
     * create_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm::ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * valid_date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm::ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * describe
     */
    private String describe;

    /**
     * 网站图标
     */
    private String favicon;
}
