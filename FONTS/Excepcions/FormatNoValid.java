package Excepcions;

public class FormatNoValid extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "FormatNoValid";
    }

    public FormatNoValid() {
        super("L'arxiu no està en el format demanat");
    }

    public FormatNoValid(String s) {
        super("L'arxiu no està en un format "+ s + " vàlid");
    }

}