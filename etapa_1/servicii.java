package etapa_1;

import java.util.*;

public class servicii {
    public Client readClient() {
        System.out.println();
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
        Client client;
        if(avemCard) {
            var card = readCard(nume);
            client = new Client(nume, varsta, email, password, nrTelefon,card);
        }
        else
        {
            client = new Client(nume, varsta, email, password, nrTelefon);
        }
        return client;
    }

    public Card readCard(String numeDetinator)
    {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Data expirare card: ");
        var dataExpirare = scanner.nextLine();
        System.out.print("Credit pe card: ");
        var credit = Integer.parseInt(scanner.nextLine());
        Card card = new Card(numeDetinator, dataExpirare, credit);
        return card;
    }

    public Locatie readLocatie()
    {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Se citesc parametrii locatiei");
        System.out.print("Denumire locatie: ");
        String denumire = scanner.nextLine();
        System.out.print("Nume oras: ");
        String numeOras = scanner.nextLine();
        System.out.print("Nr locuri in locatie: ");
        Integer nrLocuri = Integer.parseInt(scanner.nextLine());
        Locatie locatie = new Locatie(denumire,numeOras,nrLocuri);
        return locatie;
    }


    public Eveniment readEveniment(Locatie locatie)
    {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Data evenimentului: ");
        String dataEveniment = scanner.nextLine();
        System.out.print("Pretul tichetului la eveniment: ");
        Integer pretTichet = Integer.parseInt(scanner.nextLine());
        Eveniment eveniment = new Eveniment(locatie, dataEveniment, pretTichet);
        return eveniment;
    }

    public Tichet readTichet(Client client, Eveniment eveniment)
    {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        var pret = eveniment.getPretTichete();
        System.out.println("Clientul " + client.getNume() + " cumpara tichet la evenimentul de pe data "
                + eveniment.getDataEveniment() + " in orasul " + eveniment.getLocatie().getNumeOras());
        System.out.println("Clientul " + client.getNume() + " are acces la reduceri?");
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
        Tichet tichet;
        Plata plata;
        var discount = 0;
        var tipReducere = "fara reducere";
        if(avemReducere)
        {
            System.out.println("Cat discount avem?");
            discount = Integer.parseInt(scanner.nextLine());
            System.out.println("Ce titlu de beneficiar avem?");
            tipReducere = scanner.nextLine();
        }

        if(client.hasCard())
        {
            System.out.println("In cate rate se plateste cu cardul? ");
            var rate = Integer.parseInt(scanner.nextLine());
            plata = new PlataCard(pret,rate,client.getCard());
            if(avemReducere)
                tichet = new TichetReducere(client,plata,eveniment,discount,tipReducere);
            else
                tichet = new Tichet(client, plata, eveniment);
        }
        else
        {
            System.out.println("Clientul nu are card. Se va plati cu numerar!");
            plata = new PlataNumerar(pret);
            if(avemReducere)
                tichet = new TichetReducere(client,plata,eveniment,discount,tipReducere);
            else
                tichet = new Tichet(client, plata, eveniment);
        }
        return tichet;
    }

    public void afiseazaClientiDupaVarsta(Locatie locatie)
    {
        System.out.println();
        var setClienti = locatie.getClientiInLocatie();
        List<Client> list = new ArrayList<Client>(setClienti);
        Collections.sort(list, Client.sortareDupaVarsta);
        System.out.println("In locatia " + locatie.getDenumireLocatie() + " din orasul " + locatie.getNumeOras()
            + " au participat la evenimente urmatorii clienti ");
        for (var client : list)
        {
            System.out.println("Clientul " + client.getNume() + " cu varsta " + client.getVarsta());
        }
    }

    public void afiseazaClientiCuReducere(Eveniment eveniment)
    {
        System.out.println();
        var tichete = eveniment.getTicheteEveniment();
        System.out.println("Pentru evenimentul de la locatia " + eveniment.getLocatie().getDenumireLocatie() + " din data de " +
                eveniment.getDataEveniment() + " au avut parte de reducere la tichet urmatorii clienti:");
        for (var tichet : tichete)
        {
            if(tichet instanceof TichetReducere)
                System.out.println("Clientul " + tichet.getClient().getNume() + " cu titlul de reducere " +
                    ((TichetReducere) tichet).getTipReducere() + " a primit discount de " +
                        ((TichetReducere) tichet).getDiscount());
        }
    }

    public void afiseazaPlatileTichetelor(Eveniment eveniment)
    {
        System.out.println();
        var list = eveniment.getTicheteEveniment();
        System.out.println("Pentru evenimentul de la locatia " + eveniment.getLocatie().getDenumireLocatie() + " din data de " +
                eveniment.getDataEveniment() + " tichetele au fost achizitionate astfel: ");
        for(var tichet : list)
        {
            var plataTichet = tichet.getPlata();
            if(plataTichet instanceof PlataCard)
            {
                System.out.println("Tichetul cu id-ul " + plataTichet.getTichet().getIdTichet() + " a fost achitat de catre clientul "
                + ((PlataCard) plataTichet).getCardPlatitor().getNumeDetinatorCard() + " cu un card ce expira pe " +
                        ((PlataCard) plataTichet).getCardPlatitor().getDataExpirare() + " in " + ((PlataCard) plataTichet).getNrRate()
                    + " rate egale");
            }
            else if(plataTichet instanceof PlataNumerar)
            {
                System.out.println("Tichetul cu id-ul " + plataTichet.getTichet().getIdTichet() + " a fost achitat de catre clientul "
                + plataTichet.getTichet().getClient().getNume());
            }
        }
    }

    public Integer calculeazaVenituri(Eveniment eveniment)
    {
        var list = eveniment.getTicheteEveniment();
        Integer venituri = 0;
        for(var tichet : list)
        {
            if(tichet instanceof TichetReducere)
               venituri += tichet.getPlata().plata * (1 - ((TichetReducere) tichet).getDiscount() / 100);
            else if(tichet instanceof Tichet)
            {
                venituri += tichet.getPlata().plata;
            }
        }
        return venituri;
    }

    public void afiseazaPlatileClientului(Client client)
    {
        System.out.println();
        if(client.getTichetePosesie().size() == 0)
            System.out.println("Clientul " + client.getNume() + " nu are tichete in posesie");
        else
        {
            System.out.println("Clientul " + client.getNume() + " a cumparat urmatoarele tichete ");
            for (var tichet : client.getTichetePosesie())
            {
                var bani = tichet.getPlata().getPlata();
                if(tichet instanceof TichetReducere)
                    bani = bani * (1 - ((TichetReducere) tichet).getDiscount() / 100);

                if(tichet.getPlata() instanceof PlataCard)
                {
                    System.out.println("Tichet la evenimentul din locatia " + tichet.getEveniment().getLocatie().getDenumireLocatie()
                        + " pe data de " + tichet.getEveniment().getDataEveniment() + " achitat cu cardul in valoare de " +
                            bani + " cu achitare in " + ((PlataCard) tichet.getPlata()).getNrRate() + " rate egale");
                }
                else if(tichet.getPlata() instanceof PlataNumerar)
                {

                    System.out.println("Tichet la evenimentul din locatia " + tichet.getEveniment().getLocatie().getDenumireLocatie()
                            + " pe data de " + tichet.getEveniment().getDataEveniment() + " achitat in numerar in valoare de " +
                            bani);
                }
            }
        }
    }
}
