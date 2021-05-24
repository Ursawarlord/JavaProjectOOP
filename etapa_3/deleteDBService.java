package etapa_3;

import java.sql.*;
import java.util.Scanner;

public class deleteDBService {

    private static deleteDBService instance;

    public static deleteDBService getInstance()
    {
        if (instance == null)
        {
            instance = new deleteDBService();
        }
        return instance;
    }

    public void deleteClient(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);



            Scanner scanner = new Scanner(System.in);
            System.out.println("Se va sterge un client e-ticket");
            System.out.print("Nume si prenume: ");
            var nume = scanner.nextLine();


            // get found in DB
            String sql = "select 1 from clients where nume = ?";
            PreparedStatement preparedStmt_pret = con.prepareStatement(sql);
            preparedStmt_pret.setString(1, nume);
            ResultSet rs = preparedStmt_pret.executeQuery();
            // single result expected
            if(!rs.next())
            {
                System.out.println("Client not found in DB. Please try again later!");
                con.close();
                return;
            }



            String query = "delete from clients " +
                    "where nume = ?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, nume);

            // execute the preparedstatement
            preparedStmt.execute();

            deleteCard(host,user,pass,nume);

            con.close();

            System.out.println("Client deleted");

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("deleteClient");

    }




    public void deleteCard(String host, String user, String pass, String numeDetinator)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);


            // the mysql insert statement
            String query = "delete from cards " + " where numeDetinator = ?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, numeDetinator);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Credit card deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("deleteCard");
    }

    public void deleteLocatie(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);


            Scanner scanner = new Scanner(System.in);

            System.out.println("Se sterge o locatie inregistrata in DB");
            System.out.print("Denumire locatie: ");
            String denumire = scanner.nextLine();

            // get found in DB
            String sql2 = "select 1 from locations where denumire = ?";
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            preparedStmt2.setString(1, denumire);
            ResultSet rs2 = preparedStmt2.executeQuery();
            // single result expected
            if(!rs2.next())
            {
                System.out.println("Location not found in DB. Please try again later!");
                con.close();
                return;
            }


            String query = " delete from locations "
                    + " where denumire= ? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, denumire);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Location deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("deleteLocation");

    }


    public void deleteEveniment(String host, String user, String pass)
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
            String query = "delete from events "
                    + " where denumirelocatie = ? and dataeveniment = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, denumireLocatie);
            preparedStmt.setString (2, dataEveniment);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Event deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("deleteEvent");
    }


    public void deleteTichet(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Se incearca stergerea unui tichet!");

            System.out.println("Denumirea locatiei: ");
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

            System.out.println("Nume client");
            String numeClient = scanner.nextLine();
            // get clientname found in DB
            String sql4 = "select 1 from clients where nume = ?";
            PreparedStatement preparedStmt4 = con.prepareStatement(sql4);
            preparedStmt4.setString(1, dataEveniment);
            ResultSet rs4 = preparedStmt4.executeQuery();
            // single result expected
            if(!rs4.next())
            {
                System.out.println("Client not found in DB. Please try again later!");
                con.close();
                return;
            }


            String query = "delete from tickets "
                    + " where denumirelocatie = ? and dataeveniment = ? and numeclient = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, denumireLocatie);
            preparedStmt.setString (2, dataEveniment);
            preparedStmt.setString (3, numeClient);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Ticket deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("deleteTicket");
    }







}
