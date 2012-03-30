package sandbox.sql;

/**
 * @author VVinichenko
 * @since 2012-03-23 18:27
 */
public class SqlQueryEqCondition extends SqlQueryCondition {

    private SqlQueryColumn column;
    private SqlQueryValue value;

    public SqlQueryColumn getColumn() {
        return column;
    }

    public void setColumn(SqlQueryColumn column) {
        this.column = column;
    }

    public SqlQueryValue getValue() {
        return value;
    }

    public void setValue(SqlQueryValue value) {
        this.value = value;
    }

    @Override
    public String toSql() {
        return null;
    }
}
