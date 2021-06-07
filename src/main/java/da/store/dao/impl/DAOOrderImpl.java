package da.store.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import da.store.dao.DAOOrder;
import da.store.models.Goods;
import da.store.models.Order;
import da.store.models.OrderItem;
import da.store.models.User;
import da.store.utils.JDBCUtils;

public class DAOOrderImpl implements DAOOrder {

    @Override
    public void add(Order order) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format(
                        "INSERT INTO orders(id, time, status, total_price, user_id) VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                        order.getID(), order.getTime(), (order.getStatus() ? "1" : "0"), order.getTotalPrice(),
                        order.getUser().getID());
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = null;
                statement.executeUpdate(sql);
                JDBCUtils.close(connection, statement, resultSet);

                Set<OrderItem> items = order.getItems();
                for (OrderItem item : items) {
                    sql = String.format(
                            "INSERT INTO orderitem(id, goods_id, number, total_price, order_id) VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                            item.getID(), item.getGoods().getID(), item.getNumber(), item.getTotalPrice(),
                            order.getID());
                    connection = JDBCUtils.getConnection();
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(sql);
                    JDBCUtils.close(connection, statement, resultSet);
                }
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(String id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("SELECT * FROM orders WHERE id=\"%s\"", id);
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Order order = new Order();
                while (resultSet.next()) {
                    order.setID(resultSet.getString("id"));
                    order.setDate(resultSet.getString("time"));
                    order.setStatus(resultSet.getInt("status") == 0 ? false : true);
                    order.setTotalPrice(resultSet.getDouble("total_price"));
                }
                JDBCUtils.close(connection, statement, resultSet);

                sql = String.format("SELECT * FROM orderitem WHERE order_id=\"%s\"", order.getID());
                connection = JDBCUtils.getConnection();
                statement = (Statement) connection.createStatement();
                resultSet = statement.executeQuery(sql);
                List<OrderItem> items = new ArrayList<OrderItem>();
                while (resultSet.next()) {
                    OrderItem item = new OrderItem();
                    item.setID(resultSet.getString("id"));
                    item.setNumber(resultSet.getInt("number"));
                    item.setTotalPrice(resultSet.getDouble("total_price"));
                    items.add(item);
                }
                JDBCUtils.close(connection, statement, resultSet);
                for (OrderItem item : items) {
                    sql = String.format("SELECT g.* FROM orderitem oi, goods g WHERE oi.id=\"%s\" and g.id=oi.goods_id",
                            item.getID());
                    connection = JDBCUtils.getConnection();
                    statement = (Statement) connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    Goods goods = new Goods();
                    while (resultSet.next()) {
                        goods.setID(resultSet.getString("id"));
                        goods.setName(resultSet.getString("name"));
                        goods.setDescription(resultSet.getString("description"));
                        goods.setPrice(resultSet.getDouble("price"));
                        item.setGoods(goods);
                    }
                    JDBCUtils.close(connection, statement, resultSet);
                }
                order.getItems().addAll(items);

                sql = String.format("SELECT u.* FROM orders o, user u WHERE o.id=\"%s\" and u.id=o.user_id", id);
                connection = JDBCUtils.getConnection();
                statement = (Statement) connection.createStatement();
                resultSet = statement.executeQuery(sql);
                User user = new User();
                while (resultSet.next()) {
                    user.setID(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    order.setUser(user);
                }
                JDBCUtils.close(connection, statement, resultSet);

                return order;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll(boolean status) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM orders WHERE status=\"" + (status ? 1 : 0) + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<Order> orders = new ArrayList<Order>();

                while (resultSet.next()) {
                    Order order = new Order();
                    order.setID(resultSet.getString("id"));
                    order.setDate(resultSet.getString("time"));
                    order.setStatus(resultSet.getInt("status") == 0 ? false : true);
                    order.setTotalPrice(resultSet.getDouble("total_price"));
                    orders.add(order);
                }
                JDBCUtils.close(connection, statement, resultSet);
                for (Order order : orders) {
                    sql = String.format("SELECT u.* FROM orders o, user u WHERE o.id=\"%s\" and u.id=o.user_id",
                            order.getID());
                    connection = JDBCUtils.getConnection();
                    statement = (Statement) connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    User user = new User();
                    while (resultSet.next()) {
                        user.setID(resultSet.getString("id"));
                        user.setName(resultSet.getString("name"));
                        user.setPassword(resultSet.getString("password"));
                        user.setEmail(resultSet.getString("email"));
                        user.setAddress(resultSet.getString("address"));
                        order.setUser(user);
                    }
                    JDBCUtils.close(connection, statement, resultSet);
                }
                return orders;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String id, boolean status) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = String.format("UPDATE orders SET status=\"%s\" WHERE id=\"%s\"", (status ? 1 : 0), id);
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
    public List<Order> getAll(User user, boolean status) {
        try {
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM orders WHERE status=\"" + (status ? 1 : 0) + "\" and user_id=\""
                        + user.getID() + "\"";
                Statement statement = (Statement) connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<Order> orders = new ArrayList<Order>();

                while (resultSet.next()) {
                    Order order = new Order();
                    order.setID(resultSet.getString("id"));
                    order.setDate(resultSet.getString("time"));
                    order.setStatus(resultSet.getInt("status") == 0 ? false : true);
                    order.setTotalPrice(resultSet.getDouble("total_price"));
                    order.setUser(user);
                    orders.add(order);
                }
                JDBCUtils.close(connection, statement, resultSet);
                return orders;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addLog(Order order) {
        try {
            Connection connection = null;
            try {
                Set<OrderItem> items = order.getItems();
                String sql = null;
                Statement statement = null;
                ResultSet resultSet = null;
                for (OrderItem item : items) {
                    sql = String.format("SELECT * FROM logs WHERE userid = \"%s\" and goodsid = \"%s\"",
                            order.getUser().getID(), item.getGoods().getID());
                    connection = JDBCUtils.getConnection();
                    statement = (Statement) connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    boolean flag = resultSet.next();
                    JDBCUtils.close(connection, statement, resultSet);
                    
                    if (!flag) {
                        sql = String.format(
                                "INSERT INTO logs(id, userid, goodsid, num) VALUES(\"%s\", \"%s\", \"%s\", \"%s\")",
                                UUID.randomUUID().toString(), order.getUser().getID(), item.getGoods().getID(),
                                item.getNumber());
                    } else {
                        sql = String.format("UPDATE logs SET num=num+\"%s\" WHERE userid=\"%s\" and goodsid=\"%s\"",
                                item.getNumber(), order.getUser().getID(), item.getGoods().getID());
                    }
                    connection = JDBCUtils.getConnection();
                    statement = (Statement) connection.createStatement();
                    resultSet = null;
                    statement.executeUpdate(sql);
                    JDBCUtils.close(connection, statement, resultSet);
                }
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
