package proiectPAO_1;

public class PlataCard extends Plata{
    private Integer nrRate;
    private Card cardPlatitor;

    public PlataCard(Integer plata, Integer nrRate, Card card) {
        this.nrRate = nrRate;
        this.cardPlatitor = card;
        this.plata = plata;
        cardPlatitor.addPlataToList(this);
        cardPlatitor.setCredit(cardPlatitor.getCredit() - plata);

    }

    public Integer getNrRate() {
        return nrRate;
    }

    public void setNrRate(Integer nrRate) {
        this.nrRate = nrRate;
    }

    public Card getCardPlatitor() {
        return cardPlatitor;
    }

    public void setCardPlatitor(Card cardPlatitor) {
        this.cardPlatitor = cardPlatitor;
    }

    @Override
    public void detaliiPlata() {
        System.out.print("Plata in suma de " + this.plata + " a fost efectuata pe numele lui "
                + this.cardPlatitor.getNumeDetinatorCard());
        if(nrRate >= 1)
            System.out.println(" si va fi achitata in " + nrRate + " rate egale");
    }
}
