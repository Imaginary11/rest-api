package cn.org.imaginary.web.restapi.common.exception;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:14
 * @see :
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String msg) {
        super(msg);
    }
}
