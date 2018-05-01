package cn.kc.graduation.wms.exception;

/**
 * 系统操作日志 Service 异常
 *

 * @since 2017/4/7.
 */
public class SystemLogServiceException extends BusinessException {

    public SystemLogServiceException(){
        super();
    }

    public SystemLogServiceException(Exception e){
        super(e);
    }

    public SystemLogServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public SystemLogServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}