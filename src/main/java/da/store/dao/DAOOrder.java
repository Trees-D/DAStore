package da.store.dao;

import java.util.List;

import da.store.models.Order;
import da.store.models.User;

public interface DAOOrder {
    
    void add(Order order);

    Order find(String id);

    List<Order> getAll(boolean status);

    void update(String id, boolean status);

    List<Order> getAll(User user, boolean status);

    void addLog(Order order);

}
