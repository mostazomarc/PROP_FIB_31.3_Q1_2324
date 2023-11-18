package Excepcions;

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