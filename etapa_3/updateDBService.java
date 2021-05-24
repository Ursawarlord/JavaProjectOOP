package etapa_3;

import java.sql.*;
import java.util.Scanner;

public class updateDBService {

    private static updateDBService instance;

    public static updateDBService getInstance()
    {
        if (instance == null)
        {
            instance = new updateDBService();
        }
        return instance;
    }

    public void updateClient(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);



            Scanner scanner = new Scanner(System.in);
            System.out.println("Se va modifica specificatiile unui client e-ticket");
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



            System.out.print("Noua varsta: ");
            var varsta = Integer.parseInt(scanner.nextLine());
            String email;
            while(true)
            {
                System.out.print("Noul email: ");
                email = scanner.nextLine();
                if(email.contains("@"))
                    break;
                else
                    System.out.println("Eroare! E-mail incorect");
            }
            String password;
            while(true)
            {
                System.out.print("Noul Password: ");
                password = scanner.nextLine();
                if(password.length() > 6)
                    break;
                else
                    System.out.println("Eroare! Parola e prea scurta.");
            }
            String nrTelefon;
            while(true)
            {
                System.out.print("Noul Nr de telefon: ");
                nrTelefon = scanner.nextLine();
                if(nrTelefon.length() == 10)
                    break;
                else
                    System.out.println("Eroare! Numarul trebuie sa aiba 10 cifre");
            }

            // get avemCard
            String sql2 = "select avemCard from clients where nume = ?";
            PreparedStatement preparedStmt_avemCard = con.prepareStatement(sql2);
            preparedStmt_avemCard.setString(1, nume);
            ResultSet rs2 = preparedStmt_avemCard.executeQuery();
            rs2.next();
            String avemcard = rs2.getString("avemcard");



            if(avemcard == "da") {
                updateCard(host,user,pass, nume);
                // the mysql insert statement
                String query = " update clients set varsta = ?, email= ?, password = ?, nrtelefon= ?, avemcard =? " +
                        "where nume = ?";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt (1, varsta);
                preparedStmt.setString(2, email);
                preparedStmt.setString (3, password);
                preparedStmt.setString (4, nrTelefon);
                preparedStmt.setString(5, "da");
                preparedStmt.setString (6, nume);

                // execute the preparedstatement
                preparedStmt.execute();
                con.close();
            }
            else
            {
                String query = "update clients set varsta = ?, email= ?, password = ?, nrtelefon= ?, avemcard =? " +
                        "where nume = ?";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt (1, varsta);
                preparedStmt.setString(2, email);
                preparedStmt.setString (3, password);
                preparedStmt.setString (4, nrTelefon);
                preparedStmt.setString(5, "nu");
                preparedStmt.setString (6, nume);

                // execute the preparedstatement
                preparedStmt.execute();
                con.close();
            }
            System.out.println("Client updated");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("updateClient");

    }




    public void updateCard(String host, String user, String pass, String numeDetinator)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);


            Scanner scanner = new Scanner(System.in);

            System.out.print("Noua data expirare card: ");
            var dataExpirare = scanner.nextLine();
            System.out.print("Noul credit pe card: ");
            var credit = Integer.parseInt(scanner.nextLine());


            // the mysql insert statement
            String query = "update cards set dataexpirare = ?, credit = ?" + " where numeDetinator = ?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, dataExpirare);
            preparedStmt.setInt   (2, credit);
            preparedStmt.setString (3, numeDetinator);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Credit card updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("updateCard");

    }

    public void updateLocatie(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);


            Scanner scanner = new Scanner(System.in);

            System.out.println("Se actualizeaza parametrii unei locatii inregistrate in DB");
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



            System.out.print("Nume oras Nou: ");
            String numeOras = scanner.nextLine();
            System.out.print("Nr Nou locuri in locatie: ");
            Integer nrLocuri = Integer.parseInt(scanner.nextLine());


            String query = " update locations set numeoras = ?, nrlocuri = ?"
                    + " where denumire= ? ";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, numeOras);
            preparedStmt.setInt   (2, nrLocuri);
            preparedStmt.setString (3, denumire);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Location updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("updateLocatie");

    }








    public void updateEveniment(String host, String user, String pass)
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

            System.out.print("Noul pret pentru un tichet la eveniment: ");
            Integer pretTichet = Integer.parseInt(scanner.nextLine());


            String query = "update events set prettichet = ? "
                    + " where denumirelocatie = ? and dataeveniment = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt   (1, pretTichet);
            preparedStmt.setString (2, denumireLocatie);
            preparedStmt.setString (3, dataEveniment);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Event updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AuditService.logAuditFile("updateEveniment");

    }










}
