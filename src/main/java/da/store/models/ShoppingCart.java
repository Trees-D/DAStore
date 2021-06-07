package da.store.models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    
    private Map<String, CartItem> cartItems = new HashMap<String, CartItem>();
    private double totalPrice;

    public void add(Goods goods) {
        CartItem item = cartItems.get(goods.getID());

        if (item == null) {
            item = new CartItem();
            item.setGoods(goods);
            item.setNumber(1);
            cartItems.put(goods.getID(), item);
        } else {
            item.setNumber(item.getNumber() + 1);
        }
    }

    public Map<String, CartItem> getItems() {
        return cartItems;
    }

    public void setItems(Map<String, CartItem> items) {
        cartItems = items;
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for (Map.Entry<String, CartItem> entry : cartItems.entrySet())
            totalPrice += entry.getValue().getTotalPrice();
        return totalPrice;
    }

}
