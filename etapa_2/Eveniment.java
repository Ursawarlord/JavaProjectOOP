package etapa_2;

import java.util.ArrayList;
import java.util.List;

public class Eveniment {
    private Integer idTichetCurent = 101;
    private Locatie locatie;
    private String dataEveniment;
    private List<Tichet> ticheteEveniment = new ArrayList<Tichet>();
    private Integer pretTichete;



    public void addTicketToList(Tichet tichet)
    {
        ticheteEveniment.add(tichet);
    }

    public Eveniment(Locatie locatie, String dataEveniment, Integer pretTichete) {
        this.locatie = locatie;
        this.dataEveniment = dataEveniment;
        this.pretTichete = pretTichete;
    }

    public Integer getPretTichete() {
        return pretTichete;
    }

    public void setPretTichete(Integer pretTichete) {
        this.pretTichete = pretTichete;
    }

    public String getDataEveniment() {
        return dataEveniment;
    }

    public void setDataEveniment(String dataEveniment) {
        this.dataEveniment = dataEveniment;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public List<Tichet> getTicheteEveniment() {
        return ticheteEveniment;
    }

    public void setTicheteEveniment(List<Tichet> ticheteEveniment) {
        this.ticheteEveniment = ticheteEveniment;
    }

    public Integer getNewTicketId()
    {
        return idTichetCurent++;
    }

    public Integer getIdTichetCurent() {
        return idTichetCurent;
    }

    public void setIdTichetCurent(Integer idTichetCurent) {
        idTichetCurent = idTichetCurent;
    }

}
