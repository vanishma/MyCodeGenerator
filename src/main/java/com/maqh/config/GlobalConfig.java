package com.maqh.config;

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

    private String defaultDir = "\\src\\main\\java\\";

    private String packDir = "com.maqh.user";

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
}
