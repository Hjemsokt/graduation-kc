package cn.kc.graduation.wms.exception;

/**
 * SupplierManageService异常
 *

 * @since 2017/3/8.
 */
public class SupplierManageServiceException extends BusinessException {

    SupplierManageServiceException(){
        super();
    }

    public SupplierManageServiceException(Exception e){
        super(e);
    }

    SupplierManageServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

}