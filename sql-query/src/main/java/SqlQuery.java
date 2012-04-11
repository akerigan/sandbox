import java.util.ArrayList;
import java.util.List;

/**
 * @author VVinichenko
 * @since 2012-04-06 12:03
 */
public class SqlQuery {

    private static final int TYPE_SELECT = 1;
    private static final int TYPE_UPDATE = 2;

    private int type;
    // select related
    private CharSequence selectPart;
    private CharSequence fromPart;
    private StringBuilder orderPart;
    // update related
    private String tableName;
    private List<String> setExpressions;
    // unified
    private StringBuilder wherePart;

    public static SqlQuery select(CharSequence selectPart, CharSequence fromPart) {
        SqlQuery result = new SqlQuery();
        result.type = TYPE_SELECT;
        result.selectPart = selectPart;
        result.fromPart = fromPart;
        return result;
    }

    public static SqlQuery update(String tableName) {
        SqlQuery result = new SqlQuery();
        result.type = TYPE_UPDATE;
        result.tableName = tableName;
        return result;
    }

    public SqlQuery set(String expression) {
        if (type == TYPE_UPDATE && expression != null) {
            if (setExpressions == null) {
                setExpressions = new ArrayList<String>();
            }
            setExpressions.add(expression);
        }
        return this;
    }

    public SqlQuery where(CharSequence wherePart) {
        if (wherePart != null) {
            if (this.wherePart == null) {
                this.wherePart = new StringBuilder();
            }
            if (this.wherePart.length() > 0) {
                this.wherePart.append(" ");
            }
            this.wherePart.append(wherePart);
        }
        return this;
    }

    public SqlQuery order(CharSequence orderPart) {
        if (type == TYPE_SELECT && orderPart != null) {
            if (this.orderPart == null) {
                this.orderPart = new StringBuilder();
            }
            if (this.orderPart.length() > 0) {
                this.orderPart.append(" ");
            }
            this.orderPart.append(orderPart);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (type == TYPE_SELECT) {
            result.append("select ");
            result.append(selectPart);
            result.append(" from ");
            result.append(fromPart);
            if (wherePart != null) {
                result.append(" where ");
                result.append(wherePart.toString());
            }
            if (orderPart != null) {
                result.append(" order by ");
                result.append(orderPart.toString());
            }
        } else if (type == TYPE_UPDATE) {
            result.append("update ");
            result.append(tableName);
            result.append(" set ");
            boolean notFirst = false;
            for (String setExpression : setExpressions) {
                if (notFirst) {
                    result.append(", ");
                } else {
                    notFirst = true;
                }
                result.append(setExpression);
            }
            if (wherePart != null) {
                result.append(" where ");
                result.append(wherePart.toString());
            }
        }
        return result.toString();
    }

    public SqlQuery copy() {
        SqlQuery result = new SqlQuery();
        result.type = type;
        if (type == TYPE_SELECT) {
            result.selectPart = selectPart;
            result.fromPart = fromPart;
            if (wherePart != null) {
                result.wherePart = new StringBuilder(wherePart);
            }
            if (orderPart != null) {
                result.orderPart = new StringBuilder(orderPart);
            }
        } else if (type == TYPE_UPDATE) {
            result.tableName = tableName;
            if (setExpressions != null) {
                result.setExpressions = new ArrayList<String>(setExpressions);
            }
            if (wherePart != null) {
                result.wherePart = new StringBuilder(wherePart);
            }
        }
        return result;
    }

}
