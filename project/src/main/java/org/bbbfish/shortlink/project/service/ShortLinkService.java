package org.bbbfish.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.bbbfish.shortlink.project.dao.entity.ShortLinkDO;
import org.bbbfish.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.bbbfish.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.bbbfish.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.bbbfish.shortlink.project.dto.resp.ShortLinkPageRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParm);
}
