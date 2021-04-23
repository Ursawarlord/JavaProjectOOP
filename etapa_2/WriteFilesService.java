package etapa_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class WriteFilesService {
    private static WriteFilesService instance;

    public static WriteFilesService getInstance()
    {
        if (instance == null)
        {
            instance = new WriteFilesService();
        }
        return instance;
    }

    public void writeClient(Client client, String path)
    {
        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.append(client.getNume() + "," + client.getVarsta() + "," + client.getEmail() + "," +
                    client.getNrTelefon() + "," + client.getPassword() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuditService.logAuditFile("writeClient");

    }


    public void writeLocatie(Locatie locatie, String path)
    {
        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.append(locatie.getDenumireLocatie() + "," + locatie.getNumeOras() + ",");
            var size = locatie.getClientiInLocatie().size();
            for (var i=0; i<size; i++) {
                var client = locatie.getClientiInLocatie().iterator().next();
                if (i == size-1)
                    bw.append(client.getNume());
                else
                    bw.append(client.getNume() + ",");
            }
            bw.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuditService.logAuditFile("writeLocatie");

    }


    public void writeEveniment(Eveniment eveniment, String path)
    {
        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.append(eveniment.getLocatie().getDenumireLocatie() + "," + eveniment.getLocatie().getNumeOras() + ","
                    + eveniment.getDataEveniment()  + "," + eveniment.getPretTichete() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuditService.logAuditFile("writeEveniment");

    }

    public void writeTichet(Tichet tichet, String path)
    {
        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.append(tichet.getIdTichet() + "," + tichet.getEveniment().getLocatie().getDenumireLocatie() + ","
                    + tichet.getEveniment().getLocatie().getNumeOras() + ","
                    + tichet.getEveniment().getDataEveniment() + "," + tichet.getClient().getNume()  + ","
                    + tichet.getClient().getVarsta() + ","
                    + tichet.getPlata().getPlata() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuditService.logAuditFile("writeTichet");

    }



}
