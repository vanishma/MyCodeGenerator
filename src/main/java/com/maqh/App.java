package com.maqh;

import com.maqh.config.Config;
import com.maqh.config.GlobalConfig;
import com.maqh.config.StrategyConfig;
import com.maqh.generator.Generator;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)throws SQLException {

        Config config = new Config();
        config.setUrl("jdbc:mysql://192.168.50.165:3306/ly_app?&characterEncoding=utf8&useSSL=false&serverTimezone" +
                "=UTC");
        config.setUserName("root");
        config.setPassword("Henanlingyu0.");

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setPackDir("com.maqh.user1");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);

        Generator generator = new Generator();
        generator.setConfig(config);
        generator.setGlobalConfig(globalConfig);
        generator.setStrategyConfig(strategyConfig);
        generator.runGener("t_area");
    }
}
