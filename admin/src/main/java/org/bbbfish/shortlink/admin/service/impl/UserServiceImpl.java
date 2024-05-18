package org.bbbfish.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bbbfish.shortlink.admin.common.convention.exception.ClientException;
import org.bbbfish.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.bbbfish.shortlink.admin.dao.entity.UserDo;
import org.bbbfish.shortlink.admin.dao.mapper.UserMapper;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;
import org.bbbfish.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDo> queryWrapper = Wrappers.lambdaQuery(UserDo.class).eq(UserDo::getUsername, username);
        UserDo userDo = baseMapper.selectOne(queryWrapper);

        if(userDo == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }

        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDo, result);
        return result;
    }
}
