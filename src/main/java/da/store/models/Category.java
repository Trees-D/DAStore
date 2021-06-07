package da.store.models;

public class Category {

    private String categoryID;
    private String categoryName;
    private String categoryDescription;

    public String getID() {
        return categoryID;
    }

    public String getName() {
        return categoryName;
    }

    public String getDescription() {
        return categoryDescription;
    }

    public void setID(String id) {
        categoryID = id;
    }

    public void setName(String name) {
        categoryName = name;
    }

    public void setDescription(String description) {
        categoryDescription = description;
    }
    
}
