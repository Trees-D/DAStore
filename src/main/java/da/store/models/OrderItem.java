package da.store.models;

public class OrderItem {

    private String orderItemID;
    private Goods orderItemGoods;
    private int orderItemNumber;
    private double orderItemTotalPrice;

    public String getID() {
        return orderItemID;
    }

    public Goods getGoods() {
        return orderItemGoods;
    }

    public int getNumber() {
        return orderItemNumber;
    }

    public double getTotalPrice() {
        return orderItemTotalPrice;
    }

    public void setID(String id) {
        orderItemID = id;
    }

    public void setGoods(Goods goods) {
        orderItemGoods = goods;
    }

    public void setNumber(int number) {
        orderItemNumber = number;
    }

    public void setTotalPrice(double totalPrice) {
        orderItemTotalPrice = totalPrice;
    }

}
