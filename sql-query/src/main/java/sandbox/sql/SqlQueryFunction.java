package sandbox.sql;

/**
 * @author VVinichenko
 * @since 2012-03-27 15:26
 */
public class SqlQueryFunction implements SqlQueryValue {

    private String preModifier;
    private String postModifier;

    public SqlQueryFunction(String preModifier, String postModifier) {
        this.preModifier = preModifier;
        this.postModifier = postModifier;
    }
    
    @Override
    public String getSqlValue() {
        return preModifier;
    }
}
