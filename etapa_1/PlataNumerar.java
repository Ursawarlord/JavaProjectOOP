package etapa_1;

public class PlataNumerar extends Plata{
    public PlataNumerar(Integer plata) {
        this.plata = plata;
    }

    @Override
    public void detaliiPlata() {
        System.out.println("Plata in suma de " + this.plata + " a fost efectuata pe numele lui " +
                this.tichet.getClient().getNume() + "pentru tichetul " + tichet.getIdTichet() + "la locatia "
                + tichet.getEveniment().getLocatie());
    }
}
