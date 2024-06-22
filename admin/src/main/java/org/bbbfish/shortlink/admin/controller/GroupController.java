package org.bbbfish.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.bbbfish.shortlink.admin.common.convention.result.Result;
import org.bbbfish.shortlink.admin.common.convention.result.Results;
import org.bbbfish.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.bbbfish.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.bbbfish.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.bbbfish.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.bbbfish.shortlink.admin.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam){
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam){
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid){
        groupService.deleteGroup(gid);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam){
        groupService.sortGroup(requestParam);
        return Results.success();
    }

}
