package cn.wolfcode.shop.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应给界面的对象
 */
@Setter
@Getter
public class WSResponseVo {
    /**
     * 成功状态码
     */
    public static final int CODE_IN_SUCCESS = 0;
    /**
     * 错误状态码
     */
    public static final int CODE_IN_ERROR = 5000;
    /**
     * 参数错误状态码
     */
    public static final int CODE_IN_PARAM_ERROR = 4000;


    private int code;//状态码

    private String msg; //描述信息

    private Object data; //需要返回的数据

    public WSResponseVo() {
    }

    public WSResponseVo(int code) {
        this(code, null);
    }

    public WSResponseVo(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public WSResponseVo(int code, String msg) {
        this(code, msg, null);
    }

    public WSResponseVo(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public void mark(String msg) {
        this.msg = msg;
        this.code = -1;
    }
}
