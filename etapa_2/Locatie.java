package etapa_2;

import java.util.HashSet;
import java.util.Set;

public class Locatie {
    private String denumireLocatie;
    private Set<Client> clientiInLocatie = new HashSet<Client>();
    private String numeOras;
    private Integer nrLocuri;

    void addClientToSet(Client client)
    {
        clientiInLocatie.add(client);
    }

    public Locatie(String denumireLocatie, String numeOras, Integer nrLocuri) {
        this.denumireLocatie = denumireLocatie;
        this.numeOras = numeOras;
        this.nrLocuri = nrLocuri;
    }

    public String getNumeOras() {
        return numeOras;
    }

    public void setNumeOras(String numeOras) {
        this.numeOras = numeOras;
    }

    public Set<Client> getClientiInLocatie() {
        return clientiInLocatie;
    }

    public void setClientiInLocatie(Set<Client> clientiInLocatie) {
        this.clientiInLocatie = clientiInLocatie;
    }

    public String getDenumireLocatie() {
        return denumireLocatie;
    }

    public void setDenumireLocatie(String denumireLocatie) {
        this.denumireLocatie = denumireLocatie;
    }

}
