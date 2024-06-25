package org.bbbfish.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.remote.dto.ShortLinkRemoteService;
import org.bbbfish.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.bbbfish.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.bbbfish.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.bbbfish.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortLinkController {

    // TODO: 重构为 SpringCloud
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkRemoteService.createShortLink(requestParam);
    }
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParm){
        return shortLinkRemoteService.pageShortLink(requestParm);
    }
}
