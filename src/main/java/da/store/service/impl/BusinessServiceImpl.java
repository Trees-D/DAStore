package da.store.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import da.store.dao.DAOCategory;
import da.store.dao.DAOGoods;
import da.store.dao.DAOOrder;
import da.store.dao.DAOUser;
import da.store.factory.DAOFactory;
import da.store.models.CartItem;
import da.store.models.Category;
import da.store.models.Goods;
import da.store.models.Order;
import da.store.models.OrderItem;
import da.store.models.ShoppingCart;
import da.store.models.User;
import da.store.models.query.QueryHeader;
import da.store.models.query.QueryResult;
import da.store.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

    private DAOCategory categoryDAO = DAOFactory.getInstance().createDAO(DAOCategory.class);
    private DAOGoods goodsDAO = DAOFactory.getInstance().createDAO(DAOGoods.class);
    private DAOUser userDAO = DAOFactory.getInstance().createDAO(DAOUser.class);
    private DAOOrder orderDAO = DAOFactory.getInstance().createDAO(DAOOrder.class);

    @Override
    public void addCategory(Category category) {
        categoryDAO.add(category);
    }

    @Override
    public void removeCategory(Category category) {
        categoryDAO.remove(category);
    }

    @Override
    public Category findCategory(String id) {
        return categoryDAO.find(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDAO.getAll();
    }

    @Override
    public void addGoods(Goods goods) {
        goodsDAO.add(goods);
    }

    @Override
    public void removeGoods(Goods goods) {
        goodsDAO.remove(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsDAO.update(goods);
    }

    @Override
    public Goods findGoods(String id) {
        return goodsDAO.find(id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDAO.getAll();
    }

    @Override
    public List<Goods> goodsQuery(QueryHeader header) {
        QueryResult result = goodsDAO.query(header.getQueryStatement());
        return result.getList();
    }

    @Override
    public void addUser(User user) {
        userDAO.add(user);
    }

    @Override
    public User findUser(String id) {
        return userDAO.find(id);
    }

    @Override
    public User findUser(String username, String password) {
        return userDAO.find(username, password);
    }

    @Override
    public boolean isManager(String id) {
        return userDAO.isManager(id);
    }

    @Override
    public void setManager(User user) {
        userDAO.setManager(user);
    }

    @Override
    public void createOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setID(UUID.randomUUID().toString());
        order.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(false);
        order.setUser(user);
        
        Set<OrderItem> orderItems = new HashSet<OrderItem>();
        Set<Map.Entry<String, CartItem>> set = cart.getItems().entrySet();
        for (Map.Entry<String, CartItem> entry : set) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem();
            orderItem.setGoods(cartItem.getGoods());
            orderItem.setID(UUID.randomUUID().toString());;
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setNumber(cartItem.getNumber());

            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        orderDAO.add(order);
        orderDAO.addLog(order);
    }

    @Override
    public Order findOrder(String id) {
        return orderDAO.find(id);
    }

    @Override
    public List<Order> findOrder(boolean status) {
        return orderDAO.getAll(status);
    }

    @Override
    public void updateOrder(String id, boolean status) {
        orderDAO.update(id, status);
    }

    @Override
    public List<Order> findOrder(String id, boolean status) {
        return orderDAO.getAll(userDAO.find(id), status);
    }

    @Override
    public List<Goods> recommend(User user) {
        Goods goods = goodsDAO.getMVG(user);
        if (goods == null)
            return goodsDAO.getAll();
        return goodsDAO.getRecommend(goods);
    }

}
