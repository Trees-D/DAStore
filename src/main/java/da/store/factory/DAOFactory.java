package da.store.factory;

import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {

    private static Properties properties = new Properties();

    private DAOFactory() {
        try {
            InputStream in = DAOFactory.class.getClassLoader().getResourceAsStream("DAO.properties");
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T createDAO(Class<T> interfaceClass) {
        try {
            String key = interfaceClass.getSimpleName();
            String className = properties.getProperty(key);
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
