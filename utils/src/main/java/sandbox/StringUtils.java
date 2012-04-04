package sandbox;

/**
 * @author VVinichenko
 * @since 2012-03-27 17:46
 */
public class StringUtils {

    public static String toUpper(String s) {
        if (s != null) {
            return s.toUpperCase();
        } else {
            return null;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
