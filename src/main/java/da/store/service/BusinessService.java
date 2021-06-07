package da.store.service;

import java.util.List;

import da.store.models.Category;
import da.store.models.Goods;
import da.store.models.Order;
import da.store.models.ShoppingCart;
import da.store.models.User;
import da.store.models.query.QueryHeader;

public interface BusinessService {

    void addCategory(Category category);

    void removeCategory(Category category);

    Category findCategory(String id);

    List<Category> getAllCategory();

    void addGoods(Goods goods);

    void removeGoods(Goods goods);

    void updateGoods(Goods goods);

    Goods findGoods(String id);

    List<Goods> getAllGoods();

    List<Goods> goodsQuery(QueryHeader header);

    void addUser(User user);

    User findUser(String id);

    User findUser(String username, String password);

    boolean isManager(String id);

    void setManager(User user);

    void createOrder(ShoppingCart cart, User user);

    Order findOrder(String id);

    List<Order> findOrder(boolean status);

    void updateOrder(String id, boolean status);

    List<Order> findOrder(String id, boolean status);

    List<Goods> recommend(User user);

}
