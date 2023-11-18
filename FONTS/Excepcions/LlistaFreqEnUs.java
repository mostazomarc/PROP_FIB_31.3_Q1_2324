package Excepcions;

public class LlistaFreqEnUs extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "LlistaFreqEnUs";
    }

    public LlistaFreqEnUs() {
        super("La llista de freqüències està en ús per algun Teclat");
    }

    public LlistaFreqEnUs(String s) {
        super("La llista de freqüències amb id "+ s + " està en ús per algun Teclat");
    }

}

//Classe Programada per: Marc