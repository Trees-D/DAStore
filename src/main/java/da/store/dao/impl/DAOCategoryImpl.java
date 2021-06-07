package da.store.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import da.store.dao.DAOCategory;
import da.store.models.Category;
import da.store.utils.JDBCUtils;

public class DAOCategoryImpl implements DAOCategory {

    @Override
    public void add(Category category) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "INSERT INTO category(id, name, description) VALUES(\"" + category.getID() + "\", \""
                        + category.getName() + "\", \"" + category.getDescription() + "\")";
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
    public void remove(Category category) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "DELETE FROM category WHERE id=\"" + category.getID() + "\"";
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
    public Category find(String id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM category WHERE id=\"" + id + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Category category = new Category();
                while (resultSet.next()) {
                    category.setID(resultSet.getString("id"));
                    category.setName(resultSet.getString("name"));
                    category.setDescription(resultSet.getString("description"));
                }
                
                JDBCUtils.close(connection, statement, resultSet);
                return category;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAll() {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM category";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                List<Category> categorys = new ArrayList<Category>();
                Category category = null;
                while (resultSet.next()) {
                    category = new Category();
                    category.setID(resultSet.getString("id"));
                    category.setName(resultSet.getString("name"));
                    category.setDescription(resultSet.getString("description"));
                    categorys.add(category);
                }
                JDBCUtils.close(connection, statement, resultSet);
                return categorys;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
