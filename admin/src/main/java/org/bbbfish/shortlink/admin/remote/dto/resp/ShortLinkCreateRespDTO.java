package org.bbbfish.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkCreateRespDTO {
    private String gid;

    /**
     * origin_url
     */
    private String originUrl;

    /**
     * gid
     */
    private String fullShortUrl;


}
