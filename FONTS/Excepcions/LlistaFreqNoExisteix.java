package Excepcions;

public class LlistaFreqNoExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "LlistaFreqNoExisteix";
    }

    public LlistaFreqNoExisteix() {
        super("La llista de freqüències no existeix");
    }

    public LlistaFreqNoExisteix(String s, String u) {
        super("La llista de freqüències amb id "+ s + " no existeix");
    }

}