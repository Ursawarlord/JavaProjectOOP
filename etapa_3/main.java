package etapa_3;

import java.util.Scanner;

public class main {


    public static void main(String[] args) {



        String host = "jdbc:mysql://localhost:3306/eticketing";
        String username = "madalin";
        String pass = "#eticketing123";

        createDBService c_instance = createDBService.getInstance();
        showDBService s_instance = showDBService.getInstance();
        updateDBService u_instance = updateDBService.getInstance();
        deleteDBService d_instance = deleteDBService.getInstance();
        AuditService.setDBSettings(host,username,pass);

        Integer input = 333;

        Scanner scanner = new Scanner(System.in);


        while(input != 0)
        {
            input = 333;
            System.out.println("Ce operatie se efectueaza?");
            System.out.println("1. Create");
            System.out.println("2. Print");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Afiseaza clienti sortati pe varsta");
            System.out.println("6. Afiseaza tichetele cu reducere");
            System.out.println("7. Afiseaza platile unui client");
            System.out.println("8. Afiseaza tichetele cumparate la un eveniment");
            System.out.println("9. Calculeaza veniturile incasate pe platforma");
            System.out.println("0. Inchide programul");
            while( ! (0 <= input && input <= 9) )
            {
                System.out.print("Input: ");
                input = Integer.parseInt(scanner.nextLine());
            }
            if (input == 1) {
                Integer input2 = 333;
                System.out.println("1. Create client");
                System.out.println("2. Create locatie");
                System.out.println("3. Create eveniment");
                System.out.println("4. Create tichet");
                System.out.println("0. Abort");

                while (!(0 <= input2 && input2 <= 4)) {
                    System.out.print("Input: ");
                    input2 = Integer.parseInt(scanner.nextLine());
                }

                switch (input2) {
                    case 1:
                        c_instance.createClient(host, username, pass);
                        break;
                    case 2:
                        c_instance.createLocatie(host, username, pass);
                        break;
                    case 3:
                        c_instance.createEveniment(host, username, pass);
                        break;
                    case 4:
                        c_instance.createTichet(host, username, pass);
                        break;
                }
            }

            else if (input == 2) {
                Integer input2 = 333;
                System.out.println("1. Print clients");
                System.out.println("2. Print locations");
                System.out.println("3. Print events");
                System.out.println("4. Print tickets");
                System.out.println("0. Abort");

                while (!(0 <= input2 && input2 <= 4)) {
                    System.out.print("Input: ");
                    input2 = Integer.parseInt(scanner.nextLine());
                }

                switch (input2) {
                    case 1:
                        s_instance.showClients(host, username, pass);
                        break;
                    case 2:
                        s_instance.showLocatii(host, username, pass);
                        break;
                    case 3:
                        s_instance.showEvenimente(host, username, pass);
                        break;
                    case 4:
                        s_instance.showTichete(host, username, pass);
                        break;
                }
            }

            else if (input == 3) {
                Integer input2 = 333;
                System.out.println("1. Update client & card");
                System.out.println("2. Update locatie");
                System.out.println("3. Update eveniment");
                System.out.println("0. Abort");

                while (!(0 <= input2 && input2 <= 3)) {
                    System.out.print("Input: ");
                    input2 = Integer.parseInt(scanner.nextLine());
                }

                switch (input2) {
                    case 1:
                        u_instance.updateClient(host, username, pass);
                        break;
                    case 2:
                        u_instance.updateEveniment(host, username, pass);
                        break;
                    case 3:
                        u_instance.updateLocatie(host, username, pass);
                        break;
                }
            }
            else if (input == 4) {
                Integer input2 = 333;
                System.out.println("1. Delete client & card");
                System.out.println("2. Delete locatie");
                System.out.println("3. Delete eveniment");
                System.out.println("4. Delete ticket");
                System.out.println("0. Abort");

                while (!(0 <= input2 && input2 <= 3)) {
                    System.out.print("Input: ");
                    input2 = Integer.parseInt(scanner.nextLine());
                }

                switch (input2) {
                    case 1:
                        d_instance.deleteClient(host, username, pass);
                        break;
                    case 2:
                        d_instance.deleteLocatie(host, username, pass);
                        break;
                    case 3:
                        d_instance.deleteEveniment(host, username, pass);
                        break;
                    case 4:
                        d_instance.deleteTichet(host,username,pass);
                        break;
                }
            }
            else if(input == 5)
            {
                eTicketingService.afiseazaClientiDupaVarsta(host,username,pass);
            }
            else if(input == 6)
            {
                eTicketingService.afiseazaClientiCuReducere(host,username,pass);
            }
            else if(input == 7)
            {
                eTicketingService.afiseazaPlatileClientului(host,username,pass);
            }
            else if(input == 8)
            {
                eTicketingService.afiseazaPlatileTichetelorLaEveniment(host,username,pass);
            }
            else if(input == 9)
            {
                eTicketingService.calculeazaVenituri(host,username,pass);
            }
        }
    }



}
