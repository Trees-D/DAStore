package da.store.dao;

import da.store.models.User;

public interface DAOUser {
    
    void add(User user);

    void setManager(User user);

    User find(String id);

    User find(String name, String password);

    boolean isManager(String id);

}
