package sandbox.sql;

import sandbox.StringUtils;

/**
 * @author VVinichenko
 * @since 2012-03-23 18:25
 */
public class SqlQueryColumn {

    private SqlQueryTable table;
    private String name;

    public SqlQueryTable getTable() {
        return table;
    }

    public void setTable(SqlQueryTable table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.toUpper(name);
    }
    
    public String getSqlColumn() {
        StringBuilder sb = new StringBuilder();
        if (table != null ) {
            String prefix = table.getPrefix();
            if (!StringUtils.isEmpty(prefix)) {
                sb.append(prefix);
                sb.append('.');
            }
        }
        sb.append(name);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqlQueryColumn column = (SqlQueryColumn) o;
        return name.equals(column.name) && table.equals(column.table);
    }

    @Override
    public int hashCode() {
        int result = table.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
