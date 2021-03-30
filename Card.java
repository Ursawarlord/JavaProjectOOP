package proiectPAO_1;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String numeDetinatorCard;
    private String dataExpirare;
    private Integer credit;
    private List<PlataCard> listaPlati = new ArrayList<PlataCard>();

    public Card(String numeDetinatorCard, String dataExpirare, Integer credit) {
        this.numeDetinatorCard = numeDetinatorCard;
        this.dataExpirare = dataExpirare;
        this.credit = credit;
    }

    public void addPlataToList(PlataCard plataCard)
    {
        listaPlati.add(plataCard);
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getNumeDetinatorCard() {
        return numeDetinatorCard;
    }

    public void setNumeDetinatorCard(String numeDetinatorCard) {
        this.numeDetinatorCard = numeDetinatorCard;
    }

    public String getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(String dataExpirare) {
        this.dataExpirare = dataExpirare;
    }
}
