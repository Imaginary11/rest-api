package cn.org.imaginary.web.restapi.util;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 21:14
 * @see : 字符串工具类
 */
public final class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (null == str || "".equals(str));
    }

    /**
     * 判断多个字符串是否同时为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String... str) {
        boolean isEmpty = false;
        for (String strElement : str) {
            if (isEmpty(strElement)) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

}
