package proiectPAO_1;

public class main {
    public static void main(String[] args) {

        servicii servicii = new servicii();

        Client client = servicii.readClient();
        Client client2 = servicii.readClient();

        Locatie locatie = servicii.readLocatie();
        Eveniment eveniment = servicii.readEveniment(locatie);
        Locatie locatie2 = servicii.readLocatie();
        Eveniment eveniment2 = servicii.readEveniment(locatie2);
        Tichet tichet = servicii.readTichet(client,eveniment);
        Tichet tichet2 = servicii.readTichet(client,eveniment2);
        Tichet tichet3 = servicii.readTichet(client2,eveniment2);
        servicii.afiseazaClientiCuReducere(eveniment);
        servicii.afiseazaClientiDupaVarsta(locatie);
        servicii.afiseazaPlatileClientului(client);
        servicii.afiseazaPlatileTichetelor(eveniment2);
        servicii.calculeazaVenituri(eveniment2);
    }
}
