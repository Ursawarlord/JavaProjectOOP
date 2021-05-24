package etapa_3;

import java.sql.*;
import java.util.Date;

public class AuditService {

    private static String host = new String();
    private static String user = new String();
    private static String pass = new String();

    public static void setDBSettings(String h, String u, String p)
    {
        host = h;
        user = u;
        pass = p;
    }



    public static void logAuditFile(String actionName)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            Date d = new Date();
            long t = d.getTime();
            Timestamp ts = new Timestamp(t);


            // the mysql insert statement
            String query = " insert into audit  (actionName, time)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, actionName);
            preparedStmt.setString (2, ts.toString());

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
