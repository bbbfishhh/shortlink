package org.bbbfish.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.common.convention.result.Results;
import org.bbbfish.shortlink.admin.dto.req.UserLoginReqDTO;
import org.bbbfish.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.bbbfish.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;
import org.bbbfish.shortlink.admin.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username){
        return Results.success(userService.hasUserName(username));
    }

    @PostMapping("api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        userService.register(requestParam);
        return Results.success();
    }

    @PutMapping("api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam){
        userService.update(requestParam);
        return Results.success();
    }

    @PostMapping("api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam){
        return Results.success(userService.login(requestParam));
    }

    @GetMapping("api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token){
        return Results.success(userService.checkLogin(username, token));
    }

    @DeleteMapping("api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token){
        userService.logout(username, token);
        return Results.success();
    }
}
