package sandbox.sql;

import sandbox.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author VVinichenko
 * @since 2012-03-27 17:41
 */
public class SqlQuerySelect implements SqlQuery {

    public Set<SqlQueryColumn> columns = new LinkedHashSet<SqlQueryColumn>();

    public void addColumn(SqlQueryColumn column) {
        columns.add(column);
    }

    @Override
    public String getSqlQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        Map<String, String> tables = new LinkedHashMap<String, String>();
        boolean notFirst = false;
        int tablePrefixIdx = 1;
        for (SqlQueryColumn column : columns) {
            SqlQueryTable table = column.getTable();
            String tablePrefix = table.getPrefix();
            String tableName = table.getName();
            if (!tables.containsValue(tableName)) {
                if (StringUtils.isEmpty(tablePrefix)) {
                    tablePrefix = "T" + tablePrefixIdx;
                    tablePrefixIdx += 1;
                }
                tables.put(tableName, tablePrefix);
            }
            if (notFirst) {
                sb.append(", ");
            } else {
                notFirst = true;
            }
            sb.append(tablePrefix);
            sb.append('.');
            sb.append(column.getName());
        }
        sb.append(" FROM ");
        notFirst = false;
        for (Map.Entry<String, String> entry : tables.entrySet()) {
            if (notFirst) {
                sb.append(", ");
            } else {
                notFirst = true;
            }
            sb.append(entry.getKey());
            sb.append(' ');
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

}
