package com.maqh.comm;

/**
 * @author: maqh
 * @since: 2020-06-17
 */
public class StringUtils {

    public static String getTableName(String tableName) {
        String substring = tableName.substring(2, tableName.length());
        String temp = String.valueOf(substring.charAt(0));
        return temp.toUpperCase() + tableName.substring(3, tableName.length());
    }
}
