package Excepcions;

public class LayoutMassaGran extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LayoutMassaGran";
    }

    public LayoutMassaGran(int size, int n, int m) {super("Layout Massa Gran: Hi ha " + size +" lletres i el layout de " +n+ " files * " +m+ " columnes deixa files o columnes buïdes" );}

}
