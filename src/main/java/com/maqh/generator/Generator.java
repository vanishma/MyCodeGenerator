package com.maqh.generator;

import com.maqh.config.Config;
import com.maqh.domain.TempEntity;
import com.maqh.config.GlobalConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: maqh
 * @since: 2020-06-16
 */
public class Generator {

    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    Writer out = null;

    public void runGener(Config config, GlobalConfig globalConfig) throws SQLException {
        GetDataBase getDataBase = new GetDataBase();
        getDataBase.setConfig(config);
        //表备注
        String tableComment = getDataBase.parse(getDataBase.getTableName("t_menu"));

    }

    /**
     * 生成文件
     *
     * @param database 模版数据
     * @param out      Writer
     * @param template Template
     * @param docFile  File
     * @throws IOException
     * @throws TemplateException
     */
    public void generateFile(Map<String, Object> database, Writer out, Template template, File docFile) throws IOException, TemplateException {
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        template.process(database, out);
    }

    public void generateEntityFile(GlobalConfig globalConfig, ResultSetMetaData md) throws SQLException, IOException, TemplateException {
        configuration.setDirectoryForTemplateLoading(new File(globalConfig.getTemplatePath()));
        Map<String, Object> database = new HashMap<>();
        database.put("classPath", globalConfig.getPackDir());
        database.put("fileName", "Menu");
        List<TempEntity> type = new ArrayList<>();

        for (int i = 1; i < md.getColumnCount(); i++) {
            TempEntity typeName = new TempEntity();
            String columnTypeName = md.getColumnTypeName(i);
            if (columnTypeName.equals("INT")) {
                typeName.setType("Integer");
            }else if (columnTypeName.equals("VARCHAR")){
                typeName.setType("String");
            }
            typeName.setName(md.getColumnName(i));
            type.add(typeName);
        }
        database.put("type", type);

        Template template = configuration.getTemplate("Entity.ftl");
        File docFile = new File(globalConfig.getPackDirPath() + "\\Menu.java");

        if (docFile.exists()) {
            if (globalConfig.isFileOverride()) {
                generateFile(database,out,template,docFile);
            }
        } else {
            //目录不存在创建目录
            new File(globalConfig.getPackDirPath()).mkdirs();
            generateFile(database,out,template,docFile);
        }
    }
}
