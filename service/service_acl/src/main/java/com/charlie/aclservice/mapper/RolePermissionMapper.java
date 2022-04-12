package com.charlie.aclservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charlie.aclservice.entity.RolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<String> selectUsernameByRoleId(String roleId);
}
