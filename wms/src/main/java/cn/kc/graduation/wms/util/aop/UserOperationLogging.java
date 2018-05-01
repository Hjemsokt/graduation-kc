package cn.kc.graduation.wms.util.aop;

import cn.kc.graduation.wms.exception.SystemLogServiceException;
import cn.kc.graduation.wms.common.service.Interface.SystemLogService;
import cn.kc.graduation.wms.domain.UserInfoDTO;
import cn.kc.graduation.wms.exception.SystemLogServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户操作日志记录

 */
public class UserOperationLogging {

    @Autowired
    private SystemLogService systemLogService;

    /**
     * 记录用户操作
     *
     * @param joinPoint 切入点信息
     */
    public void loggingUserOperation(JoinPoint joinPoint, Object returnValue, UserOperation userOperation) {

        if (userOperation != null) {
            // 获取 annotation 的值
            String userOperationValue = userOperation.value();

            // 获取调用的方法名
            String methodName = joinPoint.getSignature().getName();

            // 获取除 import* export* 外的方法的返回值
            String invokedResult = "-";
            if (!methodName.matches("^import\\w*") && !methodName.matches("^export\\w*")) {
                if (returnValue instanceof Boolean) {
                    boolean result = (boolean) returnValue;
                    invokedResult = result ? "成功" : "失败";
                }
            }

            // 获取用户信息
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
            Long userID = userInfo.getUserID();
            String userName = userInfo.getUserName();

            // 插入记录
            try {
                systemLogService.insertUserOperationRecord(userID, userName, userOperationValue, invokedResult);
            } catch (SystemLogServiceException e) {
                // do log
            }
        }
    }
}
