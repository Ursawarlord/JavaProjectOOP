package etapa_2;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void WriteToFile_Client(Client client, String path)
    {
        var service = WriteFilesService.getInstance();
        service.writeClient(client, path);
    }

    public static void WriteToFile_Locatie(Locatie locatie, String path)
    {
        var service = WriteFilesService.getInstance();
        service.writeLocatie(locatie, path);
    }

    public static void WriteToFile_Eveniment(Eveniment eveniment, String path)
    {
        var service = WriteFilesService.getInstance();
        service.writeEveniment(eveniment, path);
    }

    public static void WriteToFile_Tichet(Tichet tichet, String path)
    {
        var service = WriteFilesService.getInstance();
        service.writeTichet(tichet, path);
    }






    public static List<Client> ReadFromFile_Clients(String path)
    {
        var service = ReadFilesService.getInstance();
        var list = service.readClients(path);
        return list;
    }

    public static List<Locatie> ReadFromFile_Locatii(String path)
    {
        var service = ReadFilesService.getInstance();
        var list = service.readLocatii(path);
        return list;
    }

    public static List<Eveniment> ReadFromFile_Evenimente(String path, List<Locatie> locatii)
    {
        var service = ReadFilesService.getInstance();
        var list = service.readEvenimente(path, locatii);
        return list;
    }

    public static List<Tichet> ReadFromFile_Tichete(String path, List<Eveniment> evenimente, List<Client> clienti )
    {
        var service = ReadFilesService.getInstance();
        var list = service.readTichete(path, evenimente, clienti);
        return list;
    }



    public static void main(String[] args) {

        String PATH = "etapa_2/data/";
        String PATH2 = "etapa_2/output/";


        // citire din fisiere CSV
        var clienti = ReadFromFile_Clients(PATH+"clients.csv");
        var locatii = ReadFromFile_Locatii(PATH+"locatii.csv");
        var evenimente = ReadFromFile_Evenimente(PATH + "evenimente.csv", locatii);
        var tichete = ReadFromFile_Tichete(PATH+"tichete.csv", evenimente, clienti);


        eTicketingService eTicketingService = new eTicketingService();

        var client = clienti.get(0);
        var client2 = clienti.get(1);
        var locatie = locatii.get(0);
        var locatie2 = locatii.get(1);
        var eveniment = evenimente.get(0);
        var eveniment2 = evenimente.get(1);
        var tichet = tichete.get(0);
        var tichet2 = tichete.get(1);
        var tichet3 = tichete.get(2);


        // logheaza auditul in fisier CSV
        eTicketingService.afiseazaClientiCuReducere(eveniment);
        eTicketingService.afiseazaClientiDupaVarsta(locatie);
        eTicketingService.afiseazaPlatileClientului(client);
        eTicketingService.afiseazaPlatileTichetelor(eveniment2);
        eTicketingService.calculeazaVenituri(eveniment2);


        // bonus: audit
        WriteToFile_Client(client2, PATH2+"clients.csv");
        WriteToFile_Client(client, PATH2+"clients.csv");

        WriteToFile_Eveniment(eveniment, PATH2+"evenimente.csv");
        WriteToFile_Eveniment(eveniment2, PATH2+"evenimente.csv");

        WriteToFile_Locatie(locatie, PATH2+"locatii.csv");
        WriteToFile_Locatie(locatie2, PATH2+"locatii.csv");

        WriteToFile_Tichet(tichet, PATH2+"tichete.csv");
        WriteToFile_Tichet(tichet2, PATH2+"tichete.csv");
        WriteToFile_Tichet(tichet3, PATH2+"tichete.csv");


    }
}
