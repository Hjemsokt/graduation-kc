package cn.kc.graduation.wms.dao;

import cn.kc.graduation.wms.domain.RolePermissionDO;

import java.util.List;

/**
 * 角色权限信息 Mapper
 */
public interface RolePermissionMapper {

    List<RolePermissionDO> selectAll();
}
