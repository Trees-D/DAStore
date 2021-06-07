package da.store.models.query;

public class QueryHeader {

    private String queryName;
    private String queryValue;
    private String queryStatement;

    public String getQueryName() {
        return queryName;
    }

    public String getQueryValue() {
        return queryValue;
    }

    public String getQueryStatement() {
        if (queryName == null || queryName.trim().equals(""))
            queryStatement = "";
        else
            queryStatement = "where " + queryName + "=\"" + queryValue + "\"";
        return queryStatement;
    }

    public void setQueryName(String name) {
        queryName = name;
    }

    public void setQueryValue(String value) {
        queryValue = value;
    }

}
