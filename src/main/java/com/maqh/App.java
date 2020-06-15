package com.maqh;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ly_app?&characterEncoding=utf8&useSSL=false&serverTimezone=UTC","root","Henanlingyu0.");
        DatabaseMetaData metaData = connection.getMetaData();

        String[] types = {"TABLE"};
        ResultSet tables = metaData.getTables(connection.getCatalog(), connection.getCatalog(), "%", types);

        while (tables.next()) {
            System.out.println(tables.getString(3));
        }

        //PreparedStatement show_tables = connection.prepareStatement("show tables");
        //ResultSetMetaData md = show_tables.getMetaData();
        //for (int i = 1; i < md.getColumnCount(); i++) {
        //    System.out.println(md.getColumnTypeName(i) + " =====" + md.getColumnName(i));
        //}


        //PreparedStatement ps = connection.prepareStatement("select * from t_menu");
        //ResultSet rs = ps.executeQuery();
        //ResultSetMetaData md = rs.getMetaData();
        //for (int i = 1; i < md.getColumnCount(); i++) {
        //    System.out.println(md.getColumnTypeName(i) + " =====" + md.getColumnName(i));
        //}

    }
}
