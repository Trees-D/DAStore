package da.store.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import da.store.dao.DAOUser;
import da.store.models.User;
import da.store.utils.JDBCUtils;

public class DAOUserImpl implements DAOUser {

    @Override
    public void add(User user) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "INSERT INTO user(id, name, password, email, address) VALUES(\"" + user.getID() + "\", \""
                        + user.getName() + "\", \"" + user.getPassword() + "\", \"" + user.getEmail() + "\", \""
                        + user.getAddress() + "\")";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = null;
                statement.executeUpdate(sql);
                JDBCUtils.close(connection, statement, resultSet);
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setManager(User user) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("INSERT INTO manager(id, username) VALUES(\"%s\", \"%s\")", user.getID(), user.getName());
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = null;
                statement.executeUpdate(sql);
                JDBCUtils.close(connection, statement, resultSet);
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM user WHERE id=\"" + id + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setID(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                }
                JDBCUtils.close(connection, statement, resultSet);
                return user;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String name, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM user WHERE name=\"" + name + "\" and password=\"" + password + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setID(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                }
                JDBCUtils.close(connection, statement, resultSet);
                return user;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isManager(String id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("SELECT * FROM manager WHERE id=\"%s\"", id);
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                boolean result = resultSet.next();
                JDBCUtils.close(connection, statement, resultSet);
                return result;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
