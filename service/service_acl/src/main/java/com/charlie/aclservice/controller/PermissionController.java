package com.charlie.aclservice.controller;



import com.charlie.aclservice.entity.Permission;
import com.charlie.aclservice.service.PermissionService;
import com.charlie.commonutils.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public R indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        System.out.println(list);
        return R.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id")
    })
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return R.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id"),
            @ApiImplicitParam(name = "permissionId", value = "菜单id数组", allowMultiple = true)
    })
    @PostMapping("/doAssign")
    public R doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return R.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id")
    })
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return R.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public R save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public R updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

    //============================自主练习===============================
    //自我练习获取全部菜单
//    @ApiOperation(value = "自我练习获取全部菜单")
//    @GetMapping("my")
//    public R getAllPermission() {
//        List<Permission> list =  permissionService.getAllPermission();
//        return R.ok().data("children",list);
//    }
//
//    // 自我练习递归删除指定菜单
//    @ApiOperation(value = "自我练习递归删除指定菜单")
//    @DeleteMapping("my/{id}")
//    public R deletePermissionById(@PathVariable String id) {
//        permissionService.deletePermissionById(id);
//        return R.ok();
//    }

}

