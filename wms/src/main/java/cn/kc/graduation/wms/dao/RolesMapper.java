package cn.kc.graduation.wms.dao;

/**
 */
public interface RolesMapper {

    /**
     * 获取角色对应的ID
     * @param roleName 角色名
     * @return 返回角色的ID
     */
    Long getRoleID(String roleName);

}
