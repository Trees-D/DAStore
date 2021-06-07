package da.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static String url = "jdbc:mysql://localhost:3306/DAStore?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
    private static String username = "root";
    private static String password = "jiayu";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
    }

}
