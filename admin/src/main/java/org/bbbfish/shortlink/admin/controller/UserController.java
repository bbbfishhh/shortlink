package org.bbbfish.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.common.convention.result.Results;
import org.bbbfish.shortlink.admin.dto.resp.UserRespDTO;
import org.bbbfish.shortlink.admin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }
}
