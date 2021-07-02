package com.maqh.comm;

/**
 * @author: maqh
 * @since: 2020-06-17
 */
public class StringUtils {

    private final static String UNDERLINE = "_";

    public static String getTableName(String tableName) {
        String substring = tableName.substring(2, tableName.length());
        String temp = String.valueOf(substring.charAt(0));
        return temp.toUpperCase() + tableName.substring(3, tableName.length());
    }

    public static String getColumnName(String para){
        StringBuilder result = new StringBuilder();
        String a[] = para.split(UNDERLINE);
        for (String s : a) {
            if (!para.contains(UNDERLINE)) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
