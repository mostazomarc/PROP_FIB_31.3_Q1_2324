package Excepcions;

public class LlistaFreqJaExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "LlistaFreqJaExisteix";
    }

    public LlistaFreqJaExisteix() {
        super("La llista de freqüències ja existeix");
    }

    public LlistaFreqJaExisteix(String s) {
        super("La llista de freqüències amb id "+ s + " ja existeix");
    }

}

//Classe Programada per: Marc