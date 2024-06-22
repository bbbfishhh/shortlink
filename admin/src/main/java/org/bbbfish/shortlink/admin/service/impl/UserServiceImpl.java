package org.bbbfish.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.admin.common.convention.exception.ClientException;
import org.bbbfish.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.bbbfish.shortlink.admin.dao.entity.UserDo;
import org.bbbfish.shortlink.admin.dao.mapper.UserMapper;
import org.bbbfish.shortlink.admin.dto.req.UserLoginReqDTO;
import org.bbbfish.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.bbbfish.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;
import org.bbbfish.shortlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.bbbfish.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static org.bbbfish.shortlink.admin.common.constant.RedisCacheConstant.USER_LOGIN_KEY;
import static org.bbbfish.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static org.bbbfish.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
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
    @Override
    public Boolean hasUserName(String username){
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(!hasUserName(requestParam.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try{
            if(lock.tryLock()){
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDo.class));
                if(inserted < 1){
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证当前用户是否为登陆用户 需要网关
        LambdaUpdateWrapper<UserDo> updateWrapper = Wrappers.lambdaUpdate(UserDo.class)
                .eq(UserDo::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDo.class), updateWrapper);

    }
    public UserLoginRespDTO login(UserLoginReqDTO requestParam){
        LambdaQueryWrapper<UserDo> queryWrapper = Wrappers.lambdaQuery(UserDo.class)
                .eq(UserDo::getUsername, requestParam.getUsername())
                .eq(UserDo::getPassword, requestParam.getPassword())
                .eq(UserDo::getDelFlag, 0);
        UserDo userDo = baseMapper.selectOne(queryWrapper);
        if(userDo == null){
            throw new ClientException("用户不存在");
        }

        Map<Object, Object> hasLoginMap = stringRedisTemplate.opsForHash().entries(USER_LOGIN_KEY +  requestParam.getUsername());
        if(CollUtil.isNotEmpty(hasLoginMap)){
            stringRedisTemplate.expire(USER_LOGIN_KEY + requestParam.getUsername(), 30L, TimeUnit.MINUTES);
            String token = hasLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException("用户登录错误"));
            return new UserLoginRespDTO(token);
        }

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put(USER_LOGIN_KEY + requestParam.getUsername(), uuid, JSON.toJSONString(userDo));
        stringRedisTemplate.expire(USER_LOGIN_KEY + requestParam.getUsername(), 30L, TimeUnit.MINUTES);

        UserLoginRespDTO result = new UserLoginRespDTO();
        result.setToken(uuid);
        return result;
    }

    public Boolean checkLogin(String username, String token){
        return stringRedisTemplate.opsForHash().get(USER_LOGIN_KEY + username, token) != null;
    }
    @Override
    public void logout(String username, String token) {
        if(checkLogin(username, token)){
            stringRedisTemplate.delete(USER_LOGIN_KEY + username);
            return;
        }
        throw new ClientException("用户token不存在或者未登陆");
    }
}
