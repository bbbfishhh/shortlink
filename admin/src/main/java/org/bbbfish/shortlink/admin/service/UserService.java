package org.bbbfish.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.bbbfish.shortlink.admin.dao.entity.UserDo;
import org.bbbfish.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDo> {
    UserRespDTO getUserByUsername(String username);

    Boolean hasUserName(String username);

    void register(UserRegisterReqDTO requestParam);
}
