package Excepcions;

@SuppressWarnings("serial") //La classe RunTimeException té un atribut que aquesta classe no te sentit que l'usi

public abstract class ExcepcionsCreadorTeclat extends Exception{

    public abstract String getTipusExcepcio();

    public ExcepcionsCreadorTeclat() {
        super();
    }

    public ExcepcionsCreadorTeclat(String s) {
        super(s);
    }
}

//Classe Programada per: Marc