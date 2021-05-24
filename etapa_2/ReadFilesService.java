package etapa_2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFilesService {
    private static ReadFilesService instance;

    public static ReadFilesService getInstance()
    {
        if (instance == null)
            instance = new ReadFilesService();
        return instance;
    }

    public List<Client> readClients(String path)
    {
        List<Client> list = new ArrayList<>();

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String nume = element[0];
                int varsta = Integer.parseInt(element[1]);
                String email = element[2];
                String password = element[3];
                String nrTelefon = element[4];
                String avemCard = element[5];

                Client client = null;

                if(avemCard.equals("Are Card"))
                {
                    String dataExpirare = element[6];
                    Integer credit = Integer.parseInt(element[7]);
                    Card card = new Card(nume, dataExpirare, credit);
                    client = new Client(nume, varsta, email, password, nrTelefon, card);
                }
                else if (avemCard.equals("Nu Are Card"))
                {
                    client = new Client(nume, varsta, email, password, nrTelefon);
                }

                list.add(client);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }



    public List<Locatie> readLocatii(String path)
    {
        List<Locatie> list = new ArrayList<>();

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String denumireLocatie = element[0];
                String numeOras = element[1];
                int nrLocuri = Integer.parseInt(element[2]);

                Locatie locatie = new Locatie(denumireLocatie, numeOras, nrLocuri);

                list.add(locatie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<Eveniment> readEvenimente(String path, List<Locatie> locatii)
    {
        List<Eveniment> list = new ArrayList<>();

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String denumireLocatie = element[0];
                String dataEveniment = element[1];
                int pretTichete = Integer.parseInt(element[2]);

                Locatie locatie = null;

                for (Locatie x : locatii)
                {
                    if (x.getDenumireLocatie().equals(denumireLocatie)) {
                        locatie = x;
                        break;
                    }
                }



                Eveniment eveniment = new Eveniment(locatie, dataEveniment, pretTichete);


                list.add(eveniment);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<Tichet> readTichete(String path, List<Eveniment> evenimente, List<Client> clienti)
    {
        List<Tichet> list = new ArrayList<>();

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String numeClient = element[0];
                String denumireLocatie = element[1];
                String dataEveniment = element[2];
                String avemReducere = element[3];
                Client client = null;

                for (Client x : clienti) {
                    if (x.getNume().equals(numeClient)) {
                        client = x;
                        break;
                    }
                }

                Eveniment eveniment = null;

                for (Eveniment x : evenimente)
                {
                    if(x.getDataEveniment().equals(dataEveniment) && x.getLocatie().getDenumireLocatie().equals(denumireLocatie))
                    {
                        eveniment = x;
                        break;
                    }
                }



                if (avemReducere.equals("Avem Reducere"))
                {
                    Integer discount = Integer.parseInt(element[4]);
                    String titluReducere = element[5];
                    String areCard = element[6];

                    var pret = eveniment.getPretTichete();

                    Plata plata = null;

                    if (areCard.equals("Are Card"))
                    {
                        var nrRate = Integer.parseInt(element[7]);
                        var card = client.getCard();
                        plata = new PlataCard(pret, nrRate, card);

                    }
                    else if(areCard.equals("Nu Are Card"))
                    {
                        plata = new PlataNumerar(pret);
                    }

                    Tichet tichet = new TichetReducere(client,plata, eveniment, discount, titluReducere);
                    list.add(tichet);

                }
                else if(avemReducere.equals("Nu Avem Reducere"))
                {
                    var pret = eveniment.getPretTichete();


                    String areCard = element[4];

                    Plata plata = null;

                    if (areCard.equals("Are Card"))
                    {
                        var nrRate = Integer.parseInt(element[5]);
                        var card = client.getCard();
                        plata = new PlataCard(pret, nrRate, card);

                    }
                    else if(areCard.equals("Nu Are Card"))
                    {
                        plata = new PlataNumerar(pret);
                    }
                    Tichet tichet = new Tichet(client,plata, eveniment);
                    list.add(tichet);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
