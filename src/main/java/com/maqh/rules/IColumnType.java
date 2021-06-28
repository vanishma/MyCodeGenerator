package com.maqh.rules;

/**
 * 获取
 * @author maqh
 * @since 1.0
 */
public interface IColumnType {

    /**
     * 获取字段类型
     * @return 字段类型
     */
    String getType();

    /**
     * 获取字段类型完整名
     * @return 字段类型完整名
     */
    String getPkg();

}
