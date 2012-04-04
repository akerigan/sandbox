package sandbox.sql;

/**
 * @author VVinichenko
 * @since 2012-03-27 17:31
 */
public class SqlQueryMain {

    public static void main(String[] args) {
        SqlQueryStringValue stringValue = new SqlQueryStringValue("qwerty");
        System.out.println("stringValue = " + stringValue.getSqlValue());

        SqlQueryTable table1 = new SqlQueryTable();
        table1.setName("table1");

        SqlQueryColumn column1 = new SqlQueryColumn();
        column1.setTable(table1);
        column1.setName("col1");

        System.out.println("column1 = " + column1.getSqlColumn());

        SqlQueryTable table2 = new SqlQueryTable();
        table2.setName("table2");

        SqlQueryColumn column2 = new SqlQueryColumn();
        column2.setTable(table2);
        column2.setName("col2");

        SqlQuerySelect select = new SqlQuerySelect();
        select.addColumn(column1);
        select.addColumn(column2);

        System.out.println("select = " + select.getSqlQuery());

        SqlQuery.Select().addColumn(SqlQuery.Column())
    }

}
