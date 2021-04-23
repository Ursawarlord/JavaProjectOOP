package etapa_1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Client {
    private String nume;
    private Integer varsta;
    private String email;
    private String password;
    private String nrTelefon;
    private List<Tichet> tichetePosesie = new ArrayList<Tichet>();
    private Card card;

    public void addTichetToLista(Tichet tichet)
    {
        this.tichetePosesie.add(tichet);
    }

    static Comparator<Client> sortareDupaVarsta = new Comparator<Client>() {
        @Override
        public int compare(Client o1, Client o2) {
            return o1.getVarsta() - o2.getVarsta();
        }
    };

    public Client(String nume, Integer varsta, String email, String password, String nrTelefon, Card card) {
        this.nume = nume;
        this.varsta = varsta;
        this.email = email;
        this.password = password;
        this.nrTelefon = nrTelefon;
        this.card = card;
    }

    public Client(String nume, Integer varsta, String email, String password, String nrTelefon) {
        this.nume = nume;
        this.varsta = varsta;
        this.email = email;
        this.password = password;
        this.nrTelefon = nrTelefon;
    }

    public boolean hasCard()
    {
        if(card != null)
            return true;
        return false;
    }

    public List<Tichet> getTichetePosesie() {
        return tichetePosesie;
    }

    public void setTichetePosesie(List<Tichet> tichetePosesie) {
        this.tichetePosesie = tichetePosesie;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public Integer getVarsta() {
        return varsta;
    }
}
