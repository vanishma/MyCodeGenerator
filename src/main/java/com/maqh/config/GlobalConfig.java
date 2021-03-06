package com.maqh.config;

import com.maqh.rules.DateType;

/**
 * @author: maqh
 * @since: 2020-06-16
 */
public class GlobalConfig {

    /**
     * 文件生成目录 默认为项目跟目录
     */
    private String outPutDir = System.getProperty("user.dir");

    /**
     * 模版路径
     */
    private String templatePath = outPutDir + "\\src\\main\\resources\\templates";

    /**
     * 是否覆盖文件
     */
    private boolean fileOverride = true;

    /**
     * 基础包
     */
    private String defaultDir = "\\src\\main\\java\\";

    /**
     * 包路径
     */
    private String packDir = "com.maqh.user";

    /**
     * 时间类型对应策略
     */
    private DateType dateType = DateType.TIME_PACK;

    public String getDefaultDir() {
        return defaultDir;
    }

    public void setDefaultDir(String defaultDir) {
        this.defaultDir = defaultDir;
    }

    public String getPackDir() {
        return packDir;
    }

    public String getPackDirPath() {
        return outPutDir + defaultDir + packDir.replace(".", "\\");
    }

    public void setPackDir(String packDir) {
        this.packDir = packDir;
    }

    public String getOutPutDir() {
        return outPutDir;
    }

    public void setOutPutDir(String outPutDir) {
        this.outPutDir = outPutDir;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }
}
