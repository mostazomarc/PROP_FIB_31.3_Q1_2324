package Excepcions;

/**
 * FormatNoValid es una classe que representa una excepció que es llença quan el format d'un arxiu no és el correcte
 * <p>FormatNoValid exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class FormatNoValid extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "FormatNoValid";
    }

    public FormatNoValid() {
        super("L'arxiu no està en el format demanat");
    }

    public FormatNoValid(String s) {
        super("L'Input introdüit no està en un format "+ s + " vàlid");
    }

}

//Classe Programada per: Marc