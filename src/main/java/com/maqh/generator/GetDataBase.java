package com.maqh.generator;

import com.maqh.config.Config;
import com.maqh.config.GlobalConfig;
import com.maqh.converts.MySqlTypeConvert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: maqh
 * @since: 2020-06-16
 */
public class GetDataBase {

    private Config config;

    private static final String SQL = "SELECT * FROM ";

    /**
     * 获取数据库连接
     *
     * @return {@link Connection}
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection 连接
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     *
     * @return {@link List< String>}
     */
    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs = null;

        try {
            DatabaseMetaData db = connection.getMetaData();
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return {@link List< String>}
     */
    public List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //获取数据库连接
        Connection connection = getConnection();
        PreparedStatement pstem = null;
        String tableSql = SQL + tableName;

        try {
            pstem = connection.prepareStatement(tableSql);
            ResultSetMetaData metaData = pstem.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                pstem.close();
                closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName 表名
     * @return {@link List< String>}
     */
    public List<String> getColumnTypes(String tableName, GlobalConfig globalConfig) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(new MySqlTypeConvert().processTypeConvert(globalConfig, rsmd.getColumnTypeName(i + 1)).getType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     *
     * @param tableName 表名
     * @return {@link List< String>}
     */
    public List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnComments;
    }

    /**
     * 获取建表语句
     *
     * @param tableName 表名
     * @return
     */
    public String getTableName(String tableName) {

        //与数据库的连接
        Connection conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                return createDDL;
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
