package com.maqh;

import com.maqh.config.Config;
import com.maqh.config.GlobalConfig;
import com.maqh.generator.Generator;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)throws SQLException {

        Config config = new Config();
        config.setUrl("jdbc:mysql://127.0.0.1:3306/ly_app?&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        config.setUserName("root");
        config.setPassword("Henanlingyu0.");
        Generator generator = new Generator();
        generator.setConfig(config);
        generator.setGlobalConfig(new GlobalConfig());
        generator.runGener("t_menu");
    }
}
