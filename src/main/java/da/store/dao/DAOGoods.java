package da.store.dao;

import java.util.List;

import da.store.models.Goods;
import da.store.models.User;
import da.store.models.query.QueryResult;

public interface DAOGoods {
    
    void add(Goods goods);

    void remove(Goods goods);

    void update(Goods goods);

    Goods find(String id);

    List<Goods> getAll();

    QueryResult query(String condition);

    Goods getMVG(User user);

    List<Goods> getRecommend(Goods goods);

}
