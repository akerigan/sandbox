/**
 * @author VVinichenko
 * @since 2012-04-06 13:14
 */
public class SqlQueryMain {

    public static void main(String[] args) {
        SqlQuery selectQuery1 =
                SqlQuery.select(
                        "p.ogr_fid, p.type, p.status, p.id, p.routeid, p.objectid",
                        "mnemo_action_p p"
                ).where("p.routeid=? and type=?");
        System.out.println("selectQuery1 = " + selectQuery1);
        SqlQuery selectQuery2 = selectQuery1.where("and id in (1, 2, 3)");
        System.out.println("selectQuery2 = " + selectQuery2);
        System.out.println("selectQuery1 = " + selectQuery1);
        SqlQuery updateQuery1 = SqlQuery.update("mnemo_action_p").set("status=?").set("type=?").where("p.routeid=?");
        System.out.println("updateQuery1 = " + updateQuery1);
        SqlQuery updateQuery2 = updateQuery1.where("and objectid=?");
        System.out.println("updateQuery2 = " + updateQuery2);
        System.out.println("updateQuery1 = " + updateQuery1);
    }

}
