package proiectPAO_1;

abstract public class Plata {
    protected Integer plata;
    protected Tichet tichet;

    public Integer getPlata() {
        return plata;
    }

    public void setPlata(Integer plata) {
        this.plata = plata;
    }

    public Tichet getTichet() {
        return tichet;
    }

    public void setTichet(Tichet tichet) {
        this.tichet = tichet;
    }

    abstract public void detaliiPlata();
}
