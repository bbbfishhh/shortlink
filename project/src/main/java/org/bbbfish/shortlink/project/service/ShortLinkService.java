package org.bbbfish.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.bbbfish.shortlink.project.dao.entity.ShortLinkDO;
import org.bbbfish.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.bbbfish.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
