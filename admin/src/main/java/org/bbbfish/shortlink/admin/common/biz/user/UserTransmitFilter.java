package org.bbbfish.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.bbbfish.shortlink.admin.common.convention.exception.ClientException;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.bbbfish.shortlink.admin.common.constant.RedisCacheConstant.USER_LOGIN_KEY;
import static org.bbbfish.shortlink.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;
    private final static List<String> IGNORE_URL = Lists.newArrayList(
            "/api/short-link/admin/v1/user/has-username",
            "/api/short-link/admin/v1/user/login"
    );
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURL = httpServletRequest.getRequestURI();

        if(!IGNORE_URL.contains(requestURL)){
            String method = httpServletRequest.getMethod();
            if(!(Objects.equals(requestURL, "api/short-link/admin/v1/user")&& Objects.equals(method, "POST"))){
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if(!StrUtil.isAllNotBlank(username, token)){
                    throw new ClientException(USER_TOKEN_FAIL);
                }
                Object userInfoJsonStr;
                try{
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get( USER_LOGIN_KEY + username, token);
                    if(userInfoJsonStr == null){
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                }catch (Exception ex){
                    throw new ClientException(USER_TOKEN_FAIL);
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.removeUser();
        }
    }
}