package sandbox.sql;

import sandbox.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author VVinichenko
 * @since 2012-03-23 18:22
 */
public class SqlQuery {

    public static Select Select() {
        return new Select();
    }

    public static class Select{

        public Set<Column> columns = new LinkedHashSet<Column>();

        public Select addColumn(Column column) {
            columns.add(column);
            return this;
        }

        public String getSqlQuery() {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            Map<String, String> tables = new LinkedHashMap<String, String>();
            boolean notFirst = false;
            int tableAliasIdx = 1;
            for (Column column : columns) {
                Table table = column.getTable();
                String tableAlias = table.getPrefix();
                String tableName = table.getName();
                if (!tables.containsValue(tableName)) {
                    if (StringUtils.isEmpty(tableAlias)) {
                        tableAlias = "T" + tableAliasIdx;
                        tableAliasIdx += 1;
                    }
                    tables.put(tableName, tableAlias);
                }
                if (notFirst) {
                    sb.append(", ");
                } else {
                    notFirst = true;
                }
                sb.append(tableAlias);
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

    public class Column {

        private Table table;
        private String name;



        public Table getTable() {
            return table;
        }

        public void setTable(Table table) {
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
            Column column = (Column) o;
            return name.equals(column.name) && table.equals(column.table);
        }

        @Override
        public int hashCode() {
            int result = table.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    public static class Table {

        private String name;
        private String prefix;

        public Table(String name) {
            this.name = name;
        }

        public Table(String name, String alias) {
            this.name = name;
            this.prefix = alias;
        }

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
            Table table = (Table) o;
            return !(name != null ? !name.equals(table.name) : table.name != null);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

}
