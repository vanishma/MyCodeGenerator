package com.maqh.generator;

import com.maqh.comm.StringUtils;
import com.maqh.config.Config;
import com.maqh.config.GlobalConfig;
import com.maqh.config.StrategyConfig;
import com.maqh.domain.MessageEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;


/**
 * @author: maqh
 * @since: 2020-06-16
 */
public class Generator {
    private static final Logger log = LoggerFactory.getLogger(Generator.class);

    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;

    /**
     * 数据库 配置信息
     */
    private Config config;

    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;

    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    Writer out = null;

    public void runGener(String tableName) throws SQLException {
        log.debug("---------------------------代码生成开始---------------------------");
        GetDataBase getDataBase = new GetDataBase();
        log.debug("---------------------------配置参数---------------------------");
        getDataBase.setConfig(config);
        //表备注
        log.debug("---------------------------获取表备注---------------------------");
        String tableComment = getDataBase.parse(getDataBase.getTableName(tableName));
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setTableComment(tableComment);
        messageEntity.setTableName(tableName);
        log.debug("---------------------------获取数据类型---------------------------");
        messageEntity.setTypeName(getDataBase.getColumnTypes(tableName, globalConfig, messageEntity));
        log.debug("---------------------------获取字段---------------------------");
        messageEntity.setName(getDataBase.getColumnNames(tableName));
        log.debug("---------------------------获取字段备注---------------------------");
        messageEntity.setComment(getDataBase.getColumnComments(tableName));
        log.debug("---------------------------获取是否是lombok---------------------------");
        messageEntity.setLombok(strategyConfig.isEntityLombokModel());
        log.debug("---------------------------开始生成文件---------------------------");
        log.debug("---------------------------开始生成Entity---------------------------");
        generateEntityFile(messageEntity);
        log.debug("---------------------------生成Entity 结束---------------------------");
        generateControllerFile(messageEntity);
        log.debug("---------------------------代码生成结束---------------------------");
    }

    /**
     * 初始化设置信息
     */
    public void init(MessageEntity messageEntity) {
        messageEntity.setLombok(strategyConfig.isEntityLombokModel());

    }

    /**
     * 生成实体
     *
     * @param messageEntity 表信息
     */
    public void generateEntityFile(MessageEntity messageEntity) {
        try {
            //拼接文件名称
            messageEntity.setFileName(StringUtils.getTableName(messageEntity.getTableName()) + "Entity");
            messageEntity.setClassPath(globalConfig.getPackDir());
            configuration.setDirectoryForTemplateLoading(new File(globalConfig.getTemplatePath()));
            Template template = configuration.getTemplate("Entity.ftl");
            File file = new File(globalConfig.getPackDirPath() + "\\" + messageEntity.getFileName() + ".java");
            configuration(messageEntity, template, file);
        } catch (IOException e) {
            log.error("读取模板异常");
            e.printStackTrace();
        }
    }

    /**
     * 生成Controller
     *
     * @param messageEntity 表信息
     */
    private void generateControllerFile(MessageEntity messageEntity) {
        try {
            messageEntity.setFileName(StringUtils.getTableName(messageEntity.getTableName()) + "Controller");
            messageEntity.setClassPath(globalConfig.getPackDir());
            configuration.setDirectoryForTemplateLoading(new File(globalConfig.getTemplatePath()));
            Template template = configuration.getTemplate("Controller.ftl");
            File file = new File(globalConfig.getPackDirPath() + "\\" + messageEntity.getFileName() + ".java");
            configuration(messageEntity, template, file);
        } catch (IOException e) {
            log.error("读取模板异常");
            e.printStackTrace();
        }
    }

    /**
     * 生成文件前配置
     *
     * @param messageEntity 模板数据
     * @param template      路径
     */
    public void configuration(MessageEntity messageEntity, Template template, File file) {
        try {
            if (file.exists()) {
                if (globalConfig.isFileOverride()) {
                    generateFile(messageEntity, out, template, file);
                }
            } else {
                //目录不存在创建目录
                new File(globalConfig.getPackDirPath()).mkdirs();
                generateFile(messageEntity, out, template, file);
            }
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成文件
     *
     * @param messageEntity 模版数据
     * @param out           Writer
     * @param template      Template
     * @param docFile       File
     * @throws IOException
     * @throws TemplateException
     */
    public void generateFile(MessageEntity messageEntity, Writer out, Template template, File docFile) throws IOException, TemplateException {
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        template.process(messageEntity, out);
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }
}
