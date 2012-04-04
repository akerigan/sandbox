package sandbox.sql;

/**
 * @author VVinichenko
 * @since 2012-03-27 15:31
 */
public class SqlQueryStringValue implements SqlQueryValue{

    private String value;

    public SqlQueryStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getSqlValue() {
        return '\'' + value + '\'';
    }
}
