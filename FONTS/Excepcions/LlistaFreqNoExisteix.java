package Excepcions;

/**
 * LlistaFreqNoExisteix es una classe que representa una excepció que es llença quan la llista de freqüències no existeix
 * <p>LlistaFreqNoExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class LlistaFreqNoExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "LlistaFreqNoExisteix";
    }

    public LlistaFreqNoExisteix() {
        super("La llista de freqüències no existeix");
    }

    public LlistaFreqNoExisteix(String s) {
        super("La llista de freqüències amb id "+ s + " no existeix");
    }

}

//Classe Programada per: Marc