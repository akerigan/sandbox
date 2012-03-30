package sandbox.sql;

import sandbox.StringUtils;

/**
 * @author VVinichenko
 * @since 2012-03-23 18:24
 */
public class SqlQueryTable {

    private String name;
    private String prefix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.toUpper(name);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = StringUtils.toUpper(prefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqlQueryTable table = (SqlQueryTable) o;
        return !(name != null ? !name.equals(table.name) : table.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
