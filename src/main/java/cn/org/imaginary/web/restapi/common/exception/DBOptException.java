package cn.org.imaginary.web.restapi.common.exception;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:13
 * @see : 数据库异常类
 */
public class DBOptException extends BaseException {
    private static final long serialVersionUID = 1L;

    public DBOptException(Throwable cause) {
        super(cause);
    }

    public DBOptException(String msg) {
        super(msg);
    }
}
