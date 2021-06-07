package da.store.models;

public class Goods {

    private String goodsID;
    private String goodsName;
    private String goodsDescription;
    private double goodsPrice;
    private Category goodsCategory;

    public String getID() {
        return goodsID;
    }

    public String getName() {
        return goodsName;
    }

    public String getDescription() {
        return goodsDescription;
    }

    public double getPrice() {
        return goodsPrice;
    }

    public Category getCategory() {
        return goodsCategory;
    }

    public void setID(String id) {
        goodsID = id;
    }

    public void setName(String name) {
        goodsName = name;
    }

    public void setDescription(String description) {
        goodsDescription = description;
    }

    public void setPrice(double price) {
        goodsPrice = price;
    }

    public void setCategory(Category category) {
        goodsCategory = category;
    }

}
