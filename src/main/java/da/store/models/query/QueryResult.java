package da.store.models.query;

import java.util.List;

import da.store.models.Goods;

public class QueryResult {

    private List<Goods> recordList;
    // private int numberRecord;

    public List<Goods> getList() {
        return recordList;
    }

    // public int getRecordNumber() {
    //     return numberRecord;
    // }

    public void setList(List<Goods> list) {
        recordList = list;
    }

    // public void setRecordNumber(int number) {
    //     numberRecord = number;
    // }

}
