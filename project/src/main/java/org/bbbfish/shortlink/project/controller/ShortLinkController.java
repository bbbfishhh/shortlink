package org.bbbfish.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.project.common.convention.result.Result;
import org.bbbfish.shortlink.project.common.convention.result.Results;
import org.bbbfish.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.bbbfish.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.bbbfish.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        shortLinkService.createShortLink(requestParam);
        return Results.success(null);
    }
}
