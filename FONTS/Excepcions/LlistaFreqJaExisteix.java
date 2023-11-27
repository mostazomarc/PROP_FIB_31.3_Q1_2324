package Excepcions;

/**
 * LlistaFreqJaExisteix es una classe que representa una excepció que es llença quan la llista de freqüències ja existeix
 * <p>LlistaFreqJaExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
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