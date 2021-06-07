package da.store.models;

public class User {

    private String userID;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;

    public String getID() {
        return userID;
    }

    public String getName() {
        return userName;
    }

    public String getPassword() {
        return userPassword;
    }

    public String getEmail() {
        return userEmail;
    }

    public String getAddress() {
        return userAddress;
    }

    public void setID(String id) {
        userID = id;
    }
    
    public void setName(String name) {
        userName = name;
    }
    
    public void setPassword(String password) {
        userPassword = password;
    }
    
    public void setEmail(String email) {
        userEmail = email;
    }
    
    public void setAddress(String address) {
        userAddress = address;
    }
    
}
