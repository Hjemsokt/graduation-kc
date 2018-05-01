package cn.kc.graduation.wms.domain;

import java.io.Serializable;

/**
 * 系统使用的角色信息
 */
public class RoleDO implements Serializable {

	private Long id;// 角色ID
	private String roleName;// 角色名
	private String roleDesc;// 角色描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

    @Override
    public String toString() {
        return "RoleDO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
