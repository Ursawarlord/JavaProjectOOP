package etapa_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

abstract public class AuditService {

    private static File auditFile = new File("etapa_2/output/AuditFile.csv");

    public static void logAuditFile(String actionName)
    {
        try {
            FileWriter fw = new FileWriter(auditFile, true);
            Date d = new Date();

            //getTime() returns current time in milliseconds
            long t = d.getTime();

            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(t);
            fw.write(actionName + "," +  ts.toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
