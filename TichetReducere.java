package proiectPAO_1;

public class TichetReducere extends Tichet{
    private Integer discount;
    private String tipReducere;

    public TichetReducere(Client client, Plata plata, Eveniment eveniment, Integer discount, String tipReducere) {
        super(client, plata, eveniment);
        this.discount = discount;
        this.tipReducere = tipReducere;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTipReducere() {
        return tipReducere;
    }

    public void setTipReducere(String tipReducere) {
        this.tipReducere = tipReducere;
    }
}
