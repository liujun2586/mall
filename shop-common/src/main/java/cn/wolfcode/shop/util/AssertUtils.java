package cn.wolfcode.shop.util;

import cn.wolfcode.shop.exception.WSException;

/**
 * 检验前端用户传入的数据
 */
abstract  public class AssertUtils {
    /**
     * 校验数据是否为空
     * @param obj
     * @param msg
     */
    public static void isNull(Object obj, String msg) {
        boolean flag = false;
        if (obj == null) {
            flag = true;
        }

        if (obj instanceof String && obj != null) {
            if ("".equals(((String) obj).trim())) {
                flag = true;
            }
        }

        handlerCheck(flag, msg);
    }

    public static void handlerCheck(boolean flag, String msg) {
        if (flag) {
            throw new WSException(msg);
        }
    }
}
