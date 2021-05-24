package etapa_3;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.*;
import java.util.Scanner;

public class createDBService {
    private static createDBService instance;

    public static createDBService getInstance()
    {
        if (instance == null)
        {
            instance = new createDBService();
        }
        return instance;
    }

    public void createClient(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);



            Scanner scanner = new Scanner(System.in);
            System.out.println("Se citeste parametrii unui client e-ticket");
            System.out.print("Nume si prenume: ");
            var nume = scanner.nextLine();
            System.out.print("Varsta: ");
            var varsta = Integer.parseInt(scanner.nextLine());
            String email;
            while(true)
            {
                System.out.print("Email: ");
                email = scanner.nextLine();
                if(email.contains("@"))
                    break;
                else
                    System.out.println("Eroare! E-mail incorect");
            }
            String password;
            while(true)
            {
                System.out.print("Password: ");
                password = scanner.nextLine();
                if(password.length() > 6)
                    break;
                else
                    System.out.println("Eroare! Parola e prea scurta.");
            }
            String nrTelefon;
            while(true)
            {
                System.out.print("Nr de telefon: ");
                nrTelefon = scanner.nextLine();
                if(nrTelefon.length() == 10)
                    break;
                else
                    System.out.println("Eroare! Numarul trebuie sa aiba 10 cifre");
            }

            System.out.println("Clientul foloseste card? ");
            boolean avemCard;
            while(true)
            {
                var input = scanner.nextLine();
                if(input.toLowerCase().contains("da")) {
                    avemCard = true;
                    break;
                }
                else if(input.toLowerCase().contains("nu")) {
                    avemCard = false;
                    break;
                }
                else
                    System.out.println("Eroare! Raspundeti da/nu");
            }

            if(avemCard) {
                createCard(host,user,pass, nume);
                // the mysql insert statement
                String query = " insert into clients (nume, varsta, email, password, nrtelefon, avemcard)"
                        + " values (?, ?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, nume);
                preparedStmt.setInt (2, varsta);
                preparedStmt.setString(3, email);
                preparedStmt.setString (4, password);
                preparedStmt.setString (5, nrTelefon);
                preparedStmt.setString(6, "da");

                // execute the preparedstatement
                preparedStmt.execute();
                con.close();
            }
            else
            {
                String query = " insert into clients (nume, varsta, email, password, nrtelefon, avemcard)"
                        + " values (?, ?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, nume);
                preparedStmt.setInt (2, varsta);
                preparedStmt.setString(3, email);
                preparedStmt.setString (4, password);
                preparedStmt.setString (5, nrTelefon);
                preparedStmt.setString(6, "nu");

                // execute the preparedstatement
                preparedStmt.execute();
                con.close();
            }
            System.out.println("New client added");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("clientCreated");

    }




    public void createCard(String host, String user, String pass, String numeDetinator)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);


            Scanner scanner = new Scanner(System.in);

            System.out.print("Data expirare card: ");
            var dataExpirare = scanner.nextLine();
            System.out.print("Credit pe card: ");
            var credit = Integer.parseInt(scanner.nextLine());


            // the mysql insert statement
            String query = " insert into cards (numeDetinator, dataexpirare, credit)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, numeDetinator);
            preparedStmt.setString (2, dataExpirare);
            preparedStmt.setInt   (3, credit);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Credit card added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("creditCardCreated");
    }




    public void createLocatie(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Se citesc parametrii locatiei");
            System.out.print("Denumire locatie: ");
            String denumire = scanner.nextLine();
            System.out.print("Nume oras: ");
            String numeOras = scanner.nextLine();
            System.out.print("Nr locuri in locatie: ");
            Integer nrLocuri = Integer.parseInt(scanner.nextLine());


            String query = " insert into locations (denumire, numeoras, nrlocuri)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, denumire);
            preparedStmt.setString (2, numeOras);
            preparedStmt.setInt   (3, nrLocuri);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("New location added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("locationCreated");
    }


    public void createEveniment(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            Scanner scanner = new Scanner(System.in);


            System.out.println("Denumirea locatiei unde se tine evenimentul: ");
            String denumireLocatie = scanner.nextLine();
            System.out.print("Data evenimentului: ");
            String dataEveniment = scanner.nextLine();
            System.out.print("Pretul tichetului la eveniment: ");
            Integer pretTichet = Integer.parseInt(scanner.nextLine());


            String query = " insert into events (denumirelocatie, dataeveniment, prettichet)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, denumireLocatie);
            preparedStmt.setString (2, dataEveniment);
            preparedStmt.setInt   (3, pretTichet);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("New event added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("eventCreated");
    }


    public void createTichet(String host, String user, String pass)
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection(host, user, pass);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Se citeste pentru crearea unui tichet!");
            System.out.println("Denumirea locatiei: ");
            var denumireLocatie = scanner.nextLine();
            System.out.println("Data eveniment: ");
            var dataEveniment = scanner.nextLine();


            // get pret tichet
            String sql = "select prettichet from events where denumireLocatie = ? and dataEveniment = ?";
            PreparedStatement preparedStmt_pret = con.prepareStatement(sql);
            preparedStmt_pret.setString(1, denumireLocatie);
            preparedStmt_pret.setString(2,dataEveniment);
            ResultSet rs = preparedStmt_pret.executeQuery();
            // single result expected
            if(!rs.next())
            {
                System.out.println("Location and event date not found. Please try again later!");
                con.close();
                return;
            }
            int pret = rs.getInt("prettichet");


            System.out.println("Nume client");
            String numeClient = scanner.nextLine();

            // get avemCard
            String sql2 = "select avemcard from clients where nume = ?";
            PreparedStatement preparedStmt_client = con.prepareStatement(sql2);
            preparedStmt_client.setString(1, numeClient);
            ResultSet rs2 = preparedStmt_client.executeQuery();
            // single result expected
            if(!rs2.next())
            {
                System.out.println("Client not found. Please try again later!");
                con.close();
                return;
            }
            String avemCard = rs2.getString("avemcard");



            System.out.println("Clientul " + numeClient + " cumpara tichet la evenimentul de pe data "
                    + dataEveniment + " la locatia " + denumireLocatie);

            System.out.println("Clientul " + numeClient + " are acces la reduceri?");

            // read avemreducere
            boolean avemReducere;
            while(true)
            {
                var input = scanner.nextLine();
                if(input.toLowerCase().contains("da")) {
                    avemReducere = true;
                    break;
                }
                else if(input.toLowerCase().contains("nu")) {
                    avemReducere = false;
                    break;
                }
                else
                    System.out.println("Eroare! Raspundeti da/nu");
            }
            var discount = 0;
            var tipReducere = "fara reducere";
            if(avemReducere)
            {
                System.out.println("Cat discount avem?");
                discount = Integer.parseInt(scanner.nextLine());
                System.out.println("Ce titlu de beneficiar avem?");
                tipReducere = scanner.nextLine();
            }


            // sql insert into tickets
            String query = " insert into tickets (numeClient, denumirelocatie, dataeveniment, tipreducere, discount," +
                    "avemrate, plata)"
                    + " values (?, ?, ?, ? ,? ,? ,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, numeClient);
            preparedStmt.setString (2, denumireLocatie);
            preparedStmt.setString   (3, dataEveniment);
            preparedStmt.setString (4, tipReducere);
            preparedStmt.setInt (5, discount);
            preparedStmt.setString   (6, avemCard);
            if(avemCard == "da")
            {
                System.out.println("In cate rate se plateste cu cardul? ");
                var rate = Integer.parseInt(scanner.nextLine());
                preparedStmt.setInt(7,rate);
            }
            else
            {
                System.out.println("Clientul nu are card. Se va plati pretul integral cu numerar!");
                preparedStmt.setInt(7,pret);
            }
            preparedStmt.execute();
            con.close();
            System.out.println("New ticket added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AuditService.logAuditFile("ticketCreated");
    }




}
