import sandbox.StringUtils;

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
    private String selectPart;
    private String fromPart;
    private String orderPart;
    // update related
    private String tableName;
    private List<String> setExpressions;
    // unified
    private String wherePart;

    public static SqlQuery select(String selectPart, String fromPart) {
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
        if (type == TYPE_UPDATE && !StringUtils.isEmpty(expression)) {
            SqlQuery result = copy();
            if (result.setExpressions == null) {
                result.setExpressions = new ArrayList<String>();
            }
            result.setExpressions.add(expression.trim());
            return result;
        } else {
            return this;
        }
    }

    public SqlQuery where(String wherePart) {
        if (!StringUtils.isEmpty(wherePart)) {
            SqlQuery result = copy();
            if (StringUtils.isEmpty(result.wherePart)) {
                result.wherePart = wherePart.trim();
            } else {
                result.wherePart = result.wherePart + " " + wherePart.trim();
            }
            return result;
        } else {
            return this;
        }
    }

    public SqlQuery order(String orderPart) {
        if (type == TYPE_SELECT && !StringUtils.isEmpty(orderPart)) {
            SqlQuery result = copy();
            if (StringUtils.isEmpty(result.orderPart)) {
                result.orderPart = orderPart.trim();
            } else {
                result.orderPart = result.orderPart + " " + orderPart.trim();
            }
            return result;
        } else {
            return this;
        }
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
                result.append(wherePart);
            }
            if (orderPart != null) {
                result.append(" order by ");
                result.append(orderPart);
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
                result.append(wherePart);
            }
        }
        return result.toString();
    }

    private SqlQuery copy() {
        SqlQuery result = new SqlQuery();
        result.type = type;
        if (type == TYPE_SELECT) {
            result.selectPart = selectPart;
            result.fromPart = fromPart;
            result.wherePart = wherePart;
            result.orderPart = orderPart;
        } else if (type == TYPE_UPDATE) {
            result.tableName = tableName;
            if (setExpressions != null) {
                result.setExpressions = new ArrayList<String>(setExpressions);
            }
            result.wherePart = wherePart;
        }
        return result;
    }

}
