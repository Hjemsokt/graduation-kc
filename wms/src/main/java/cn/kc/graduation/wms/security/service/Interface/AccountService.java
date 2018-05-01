package cn.kc.graduation.wms.security.service.Interface;

import cn.kc.graduation.wms.exception.UserAccountServiceException;

import java.util.Map;

/**
 * 账号相关服务
 */
public interface AccountService {

	/**
	 * 密码更改
	 * @param userID 用户ID
	 * @param passwordInfo 更改的密码信息
	 */
	public void passwordModify(Long userID, Map<String, Object> passwordInfo) throws
		UserAccountServiceException;
}
