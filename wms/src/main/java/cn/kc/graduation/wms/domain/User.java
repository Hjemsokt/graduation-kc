package cn.kc.graduation.wms.domain;

import java.io.Serializable;

/**
 * 系统的使用用户
 */
public class User implements Serializable {

	private Long id;// 用户ID
	private String userName;// 用户名
	private String password;// 用户密码

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
