package da.store.models;

import java.util.HashSet;
import java.util.Set;

public class Order {

    private String orderID;
    private String orderTime;
    private boolean orderStatus;
    private double orderTotalPrice;
    private User orderUser;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public String getID() {
        return orderID;
    }

    public String getTime() {
        return orderTime;
    }

    public boolean getStatus() {
        return orderStatus;
    }

    public double getTotalPrice() {
        return orderTotalPrice;
    }

    public User getUser() {
        return orderUser;
    }

    public Set<OrderItem> getItems() {
        return orderItems;
    }

    public void setID(String id) {
        orderID = id;
    }

    public void setDate(String time) {
        orderTime = time;
    }

    public void setStatus(boolean status) {
        orderStatus = status;
    }

    public void setTotalPrice(double totalPrice) {
        orderTotalPrice = totalPrice;
    }

    public void setUser(User user) {
        orderUser = user;
    }

    public void setItems(Set<OrderItem> items) {
        orderItems = items;
    }
    
}
