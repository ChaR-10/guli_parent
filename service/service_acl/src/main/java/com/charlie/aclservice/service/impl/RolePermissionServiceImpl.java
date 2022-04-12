package com.charlie.aclservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charlie.aclservice.entity.RolePermission;
import com.charlie.aclservice.mapper.RolePermissionMapper;
import com.charlie.aclservice.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<String> selectUsernameByRoleId(String role) {
        List<String> usernameList = baseMapper.selectUsernameByRoleId(role);
        return usernameList;
    }
}
