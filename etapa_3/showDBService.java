package etapa_3;


import java.sql.*;
import java.util.Scanner;

public class showDBService {
    private static showDBService instance;

    public static showDBService getInstance()
    {
        if (instance == null)
        {
            instance = new showDBService();
        }
        return instance;
    }

    public void showClients(String host, String user, String pass)
    {
        Connection con = null;
        try {
                con = DriverManager.getConnection(host, user, pass);

                String sql = " select *  from clients";
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                String p = "";
                System.out.println("Se afiseaza toti clientii de pe platforma!");
                while (rs.next()) {
                    String nume = rs.getString("nume");
                    String varsta = rs.getString("varsta");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String nrtelefon = rs.getString("nrtelefon");
                    String avemcard = rs.getString("avemcard");
                    System.out.println("Clientul " + nume + " cu varsta de " + varsta + " adresa de mail: " + email +
                        " si parola: " + password + " si nr de telefon: " + nrtelefon + " are card: " + avemcard
                    );
                }
                con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("showClients");
    }




    public void showCards(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select *  from cards";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            System.out.println("Se afiseaza toate cardurile de pe platforma!");
            while (rs.next()) {
                String numedetinator = rs.getString("numeDetinator");
                String dataexpirare = rs.getString("dataexpirare");
                int credit = rs.getInt("credit");
                System.out.println("Cardul clientului " + numedetinator + " cu data de expirare: " + dataexpirare +
                        " are creditul: " + credit);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("showCards");
    }




    public void showLocatii(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select *  from locations";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            System.out.println("Se afiseaza toate locatiile inregistrate pe platforma!");
            while (rs.next()) {
                String denumireLocatie = rs.getString("denumire");
                String numeOras = rs.getString("numeoras");
                int nrlocuri = rs.getInt("nrlocuri");
                System.out.println("Locatia " + denumireLocatie + " aflata in orasul " + numeOras +
                        " are un numar de locuri egal cu: " + nrlocuri);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("showLocations");
    }


    public void showEvenimente(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select *  from events";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            System.out.println("Se afiseaza toate evenimentele de pe platforma!");
            while (rs.next()) {
                String denumirelocatie = rs.getString("denumirelocatie");
                String data = rs.getString("dataeveniment");
                int prettichet = rs.getInt("prettichet");
                System.out.println("Evenimentul de pe data: " + data  + " ce are locatia: " + denumirelocatie +
                        " are tichete cu pretul: " + prettichet);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("showEvents");
    }


    public void showTichete(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select *  from tickets";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            System.out.println("Se afiseaza toate tichetele inregistrate pe platforma!");
            while (rs.next()) {
                String numeClient = rs.getString("numeclient");
                String denumirelocatie = rs.getString("denumirelocatie");
                String dataeveniment = rs.getString("dataeveniment");
                String tipreducere = rs.getString("tipreducere");
                int discount = rs.getInt("discount");
                String avemrate = rs.getString("avemrate");
                int plata = rs.getInt("plata");
                if(tipreducere == "fara reducere")
                {
                    if(avemrate=="da")
                    {
                        System.out.println("Clientul " + numeClient + " are tichet la evenimentul de pe data: " + dataeveniment +
                                " la locatia " + denumirelocatie + " si va plati cu cardul in rate de " + plata);
                    }
                    else
                    {
                        System.out.println("Clientul " + numeClient + " are tichet la evenimentul de pe data: " + dataeveniment +
                                " la locatia " + denumirelocatie + " si va achita integral cu numerar suma de " + plata);
                    }
                }
                else
                {
                    if(avemrate=="da")
                    {
                        System.out.println("Clientul " + numeClient + " are tichet la evenimentul de pe data: " + dataeveniment +
                                " la locatia " + denumirelocatie + " si va plati cu cardul in rate de " + plata +
                                ". Acesta are discount " + discount + "% si titlul de reducere: " + tipreducere);
                    }
                    else
                    {
                        System.out.println("Clientul " + numeClient + " are tichet la evenimentul de pe data: " + dataeveniment +
                                " la locatia " + denumirelocatie + " si va achita integral cu numerar suma de " + plata +
                                ". Acesta are discount " + discount + "% si titlul de reducere: " + tipreducere);
                    }
                }
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("showTickets");
    }




}
