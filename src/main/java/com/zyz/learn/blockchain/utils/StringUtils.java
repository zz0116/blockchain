package com.zyz.learn.blockchain.utils;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/2
 */
public final class StringUtils {

    public static boolean isNoneBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    private static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
