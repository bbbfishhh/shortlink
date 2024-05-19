package org.bbbfish.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.common.convention.result.Results;
import org.bbbfish.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;
import org.bbbfish.shortlink.admin.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("api/short-link/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username){
        return Results.success(userService.hasUserName(username));
    }

    @PostMapping("api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        userService.register(requestParam);
        return Results.success();
    }
}
