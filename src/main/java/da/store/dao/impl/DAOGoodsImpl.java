package da.store.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import da.store.dao.DAOCategory;
import da.store.dao.DAOGoods;
import da.store.models.query.QueryResult;
import da.store.models.Category;
import da.store.models.Goods;
import da.store.models.User;
import da.store.utils.JDBCUtils;

public class DAOGoodsImpl implements DAOGoods {

    @Override
    public void add(Goods goods) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "INSERT INTO goods(id, name, description, price, category_id) VALUES(\"" + goods.getID()
                        + "\", \"" + goods.getName() + "\", \"" + goods.getDescription() + "\", \"" + goods.getPrice()
                        + "\", \"" + goods.getCategory().getID() + "\")";
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
    public void remove(Goods goods) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "DELETE FROM goods WHERE id=\"" + goods.getID() + "\"";
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
    public void update(Goods goods) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format(
                        "UPDATE goods SET name=\"%s\", description=\"%s\", price=\"%s\", category_id=\"%s\" WHERE id=\"%s\"",
                        goods.getName(), goods.getDescription(), goods.getPrice(), goods.getCategory().getID(), goods.getID());
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
    public Goods find(String id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM goods WHERE id=\"" + id + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Goods goods = new Goods();
                while (resultSet.next()) {
                    goods.setID(resultSet.getString("id"));
                    goods.setName(resultSet.getString("name"));
                    goods.setDescription(resultSet.getString("description"));
                    goods.setPrice(resultSet.getDouble("price"));
                    DAOCategory categoryDAO = new DAOCategoryImpl();
                    Category category = categoryDAO.find(resultSet.getString("category_id"));
                    goods.setCategory(category);
                }
                JDBCUtils.close(connection, statement, resultSet);
                return goods;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> getAll() {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM goods";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                List<Goods> goodsList = new ArrayList<Goods>();
                DAOCategory categoryDAO = new DAOCategoryImpl();
                while (resultSet.next()) {
                    Goods goods = new Goods();
                    goods.setID(resultSet.getString("id"));
                    goods.setName(resultSet.getString("name"));
                    goods.setDescription(resultSet.getString("description"));
                    goods.setPrice(resultSet.getDouble("price"));
                    Category category = categoryDAO.find(resultSet.getString("category_id"));
                    goods.setCategory(category);
                    goodsList.add(goods);
                }
                JDBCUtils.close(connection, statement, resultSet);
                return goodsList;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Goods> getPages(String condition) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                Statement statement = (Statement) connection.createStatement();
                String sql;
                ResultSet resultSet = null;
                List<Goods> goodsList = new ArrayList<Goods>();
                if (condition == null || condition.trim().equals("")) {
                    sql = "SELECT * FROM goods";
                } else {
                    sql = "SELECT * FROM goods " + condition;
                }
                resultSet = statement.executeQuery(sql);
                DAOCategory categoryDAO = new DAOCategoryImpl();
                while (resultSet.next()) {
                    Goods goods = new Goods();
                    goods.setID(resultSet.getString("id"));
                    goods.setName(resultSet.getString("name"));
                    goods.setDescription(resultSet.getString("description"));
                    goods.setPrice(resultSet.getDouble("price"));
                    Category category = categoryDAO.find(resultSet.getString("category_id"));
                    goods.setCategory(category);
                    goodsList.add(goods);
                }
                JDBCUtils.close(connection, statement, resultSet);
                return goodsList;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QueryResult query(String condition) {
        try {
            List<Goods> list = getPages(condition);
            QueryResult result = new QueryResult();
            result.setList(list);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods getMVG(User user) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("SELECT * FROM logs WHERE userid=\"%s\" ORDER BY num desc limit 1", user.getID());
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Goods goods = null;
                if (resultSet.next())
                    goods = find(resultSet.getString("goodsid"));
                JDBCUtils.close(connection, statement, resultSet);
                return goods;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> getRecommend(Goods goods) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("SELECT * FROM goods WHERE category_id=\"%s\"", goods.getCategory().getID());
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                List<Goods> goodsRecomendList = new ArrayList<Goods>();
                DAOCategory categoryDAO = new DAOCategoryImpl();
                while (resultSet.next()) {
                    Goods tmp = new Goods();
                    tmp.setID(resultSet.getString("id"));
                    tmp.setName(resultSet.getString("name"));
                    tmp.setDescription(resultSet.getString("description"));
                    tmp.setPrice(resultSet.getDouble("price"));
                    Category category = categoryDAO.find(resultSet.getString("category_id"));
                    tmp.setCategory(category);
                    goodsRecomendList.add(tmp);
                }
                JDBCUtils.close(connection, statement, resultSet);
                
                sql = String.format("SELECT * FROM goods WHERE category_id<>\"%s\"", goods.getCategory().getID());
                connection = JDBCUtils.getConnection();
                statement = (Statement) connection.createStatement();
                resultSet = statement.executeQuery(sql);
                List<Goods> goodsList = new ArrayList<Goods>();
                while (resultSet.next()) {
                    Goods tmp = new Goods();
                    tmp.setID(resultSet.getString("id"));
                    tmp.setName(resultSet.getString("name"));
                    tmp.setDescription(resultSet.getString("description"));
                    tmp.setPrice(resultSet.getDouble("price"));
                    Category category = categoryDAO.find(resultSet.getString("category_id"));
                    tmp.setCategory(category);
                    goodsList.add(tmp);
                }
                JDBCUtils.close(connection, statement, resultSet);
                goodsRecomendList.addAll(goodsList);
                return goodsRecomendList;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
