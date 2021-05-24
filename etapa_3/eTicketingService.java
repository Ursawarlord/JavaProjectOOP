package etapa_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

abstract public class eTicketingService {


    public static void afiseazaClientiDupaVarsta(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select *  from clients order by varsta";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            System.out.println("Se afiseaza toti clientii de pe platforma sortati dupa varsta!");
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
        AuditService.logAuditFile("afiseazaClientiDupaVarsta");

    }

    public static void afiseazaClientiCuReducere(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            String sql = " select * from tickets where tipreducere <> 'fara reducere' ";
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
        AuditService.logAuditFile("afiseazaClientiCuReducere");
    }

    public static void afiseazaPlatileTichetelorLaEveniment(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Denumirea locatiei unde se tine evenimentul: ");
            String denumireLocatie = scanner.nextLine();

            // get locationname found in DB
            String sql2 = "select 1 from locations where denumire = ?";
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            preparedStmt2.setString(1, denumireLocatie);
            ResultSet rs2 = preparedStmt2.executeQuery();
            // single result expected
            if(!rs2.next())
            {
                System.out.println("Location not found in DB. Please try again later!");
                con.close();
                return;
            }


            System.out.print("Data evenimentului: ");
            String dataEveniment = scanner.nextLine();


            // get eventdate found in DB
            String sql3 = "select 1 from events where dataeveniment = ?";
            PreparedStatement preparedStmt3 = con.prepareStatement(sql3);
            preparedStmt3.setString(1, dataEveniment);
            ResultSet rs3 = preparedStmt3.executeQuery();
            // single result expected
            if(!rs3.next())
            {
                System.out.println("Event not found in DB. Please try again later!");
                con.close();
                return;
            }


            String sql = " select *  from tickets where denumirelocatie = ? and dataeveniment = ?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, denumireLocatie);
            stat.setString(2,dataEveniment);
            ResultSet rs = stat.executeQuery();
            String p = "";
            System.out.println("Se afiseaza toate platile la evenimentul citit!");
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

        AuditService.logAuditFile("afiseazaPlatileTichetelorLaEveniment");

    }

    public static void calculeazaVenituri(String host, String user, String pass)
    {
        Connection con = null;
        int profit = 0;
        try {
            con = DriverManager.getConnection(host, user, pass);
            Scanner scanner = new Scanner(System.in);

            String sql = " select *  from tickets";
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int discount = rs.getInt("discount");
                String avemrate = rs.getString("avemrate");
                int plata = rs.getInt("plata");

                profit += plata * (100 - discount) / 100 ;

            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Venituri: " + profit);
        AuditService.logAuditFile("calculeazaVenituri");


    }

    public static void afiseazaPlatileClientului(String host, String user, String pass)
    {
        Connection con = null;
        int profit = 0;
        try {
            con = DriverManager.getConnection(host, user, pass);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nume si prenume client: ");
            var nume = scanner.nextLine();

            // get found in DB
            String sql2 = "select 1 from clients where nume = ?";
            PreparedStatement preparedStmt_pret = con.prepareStatement(sql2);
            preparedStmt_pret.setString(1, nume);
            ResultSet rs2 = preparedStmt_pret.executeQuery();
            // single result expected
            if(!rs2.next())
            {
                System.out.println("Client not found in DB. Please try again later!");
                con.close();
                return;
            }


            String sql = "select * from tickets where numeclient=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, nume);
            ResultSet rs = stat.executeQuery();
            String p = "";
            System.out.println("Se afiseaza toate tichetele ale clientului!");
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
        AuditService.logAuditFile("afiseazaPlatileClientului");

    }
}
