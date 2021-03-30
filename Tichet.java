package proiectPAO_1;

public class Tichet {
    private Integer idTichet;
    private Client client;
    private Plata plata;
    private Eveniment eveniment;

    public Tichet(Client client, Plata plata, Eveniment eveniment) {
        this.client = client;
        this.plata = plata;
        this.eveniment = eveniment;
        this.idTichet = eveniment.getNewTicketId();
        plata.setTichet(this);
        client.addTichetToLista(this);
        eveniment.addTicketToList(this);
        eveniment.getLocatie().addClientToSet(client);
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Plata getPlata() {
        return plata;
    }

    public void setPlata(Plata plata) {
        this.plata = plata;
    }

    public Integer getIdTichet() {
        return idTichet;
    }

    public void setIdTichet(Integer idTichet) {
        this.idTichet = idTichet;
    }

    public Eveniment getEveniment() {
        return eveniment;
    }

    public void setEveniment(Eveniment eveniment) {
        this.eveniment = eveniment;
    }
}
