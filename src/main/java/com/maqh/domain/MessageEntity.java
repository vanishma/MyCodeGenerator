package com.maqh.domain;

import java.util.List;

/**
 * @author: maqh
 * @since: 2020-06-16
 */
public class MessageEntity {

    /**
     * 文件路径
     */
    private String classPath;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段类型
     */
    private List<String> typeName;

    /**
     * 字段名称
     */
    private List<String> name;

    /**
     * 字段备注
     */
    private List<String> comment;

    /**
     * 是否是lombok
     */
    private boolean lombok;

    public boolean isLombok() {
        return lombok;
    }

    public void setLombok(boolean lombok) {
        this.lombok = lombok;
    }

    /**
     * 需要导入的包的集合
     */
    private List<String> importPackages;

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(List<String> importPackages) {
        this.importPackages = importPackages;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getTypeName() {
        return typeName;
    }

    public void setTypeName(List<String> typeName) {
        this.typeName = typeName;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
