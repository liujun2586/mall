package cn.wolfcode.shop.exception;

/**
 * 自定义异常类
 */
public class WSException extends RuntimeException {

    public WSException() {
        super();
    }

    public WSException(String message) {
        super(message);
    }

    public WSException(String message, Throwable cause) {
        super(message, cause);
    }
}
