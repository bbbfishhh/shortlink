package org.bbbfish.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.remote.dto.ShortLinkRemoteService;
import org.bbbfish.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.bbbfish.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortLinkController {
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParm){

        ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
        };
        return shortLinkRemoteService.pageShortLink(requestParm);
    }
}
