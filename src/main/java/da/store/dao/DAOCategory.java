package da.store.dao;

import java.util.List;

import da.store.models.Category;

public interface DAOCategory {
    
    void add(Category category);

    void remove(Category category);
    
    Category find(String id);

    List<Category> getAll();

}
