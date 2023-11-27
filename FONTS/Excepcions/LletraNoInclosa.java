package Excepcions;

/**
 * LletraNoInclosa es una classe que representa una excepció que es llença quan la lletra no està inclosa a l'alfabet d'un idioma
 * <p>LletraNoInclosa exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class LletraNoInclosa extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "LletraNoInclosa";
    }

    public LletraNoInclosa() {
        super("La lletra no està inclosa a l'idioma");
    }

    public LletraNoInclosa(char lletra, String idioma) {
        super("La lletra: '"+ lletra + "' no està inclosa a l'idioma: " + idioma);
    }

}