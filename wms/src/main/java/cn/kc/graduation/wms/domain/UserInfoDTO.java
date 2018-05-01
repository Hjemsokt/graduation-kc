package cn.kc.graduation.wms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户账户信息（数据传输对象）
 */
public class UserInfoDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long userID;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码（已加密）
     */
    private String password;

    /**
     * 是否为初次登陆
     */
    private boolean firstLogin;

    /**
     * 用户登陆的IP
     */
    private String accessIP;

    /**
     * 用户角色
     */
    private List<String> role = new ArrayList<>();

    /**
     * 用户指派的仓库
     */
    private Long repositoryBelong;

    /**
     * 用户账户属性的 getter 以及 setter
     */

    public String getUserName() {
        return userName;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<String> getRole() {
        return role;
    }

    public Long getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public Long getRepositoryBelong() {
        return repositoryBelong;
    }

    public void setRepositoryBelong(Long repositoryBelong) {
        this.repositoryBelong = repositoryBelong;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                ", role=" + role +
                '}';
    }
}
