package da.store.models;

public class CartItem {
    
    private Goods goods;
    private int number;
    private double totalPrice;

    public Goods getGoods() {
        return goods;
    }

    public int getNumber() {
        return number;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setNumber(int number) {
        this.number = number;
        this.totalPrice = this.goods.getPrice() * this.number;
    }

}
