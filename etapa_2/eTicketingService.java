package etapa_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class eTicketingService {


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

        // log into CSV file
        AuditService.logAuditFile("afiseazaClientiDupaVarsta");

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


        // log into CSV file
        AuditService.logAuditFile("afiseazaClientiCuReducere");


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


        // log into CSV file
        AuditService.logAuditFile("afiseazaPlatileTichetelor");

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


        // log into CSV file
        AuditService.logAuditFile("calculeazaVenituri");


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

        // log into CSV file
        AuditService.logAuditFile("afiseazaPlatileClientului");

    }
}
