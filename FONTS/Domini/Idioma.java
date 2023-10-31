package Domini;

public class Idioma {
    private String nom;
    private Alfabet alfabet;
    private LlistaFrequencies llistaFreq;

    //Pre: No existeix un idioma amb nomIdioma. Existeix l'alfabet alphabet. Existeix la llista de freqüències FreqList
    //Post: S'ha creat un idioma amb nom = nomIdioma, alfabet = alphabet i llistaFreq = FreqList
    //      i s'afegeix aquest idioma a l'alfabet donat
    public Idioma (String nomIdioma, Alfabet alphabet, LlistaFreq FreqList) {
        nom = nomIdioma;
        alfabet = alphabet;
        llistaFreq = FreqList;
        alfabet.afegirIdioma(this);
    }

    //Retorna el nom de l'idioma
    public void getNom() {
        return nom;
    }

    //Retorna les lletres que té l'alfabet de l'idioma
    public Set<char> getLletres() {
        return alfabet.getLletres();
    }

    //Retorna la llista de frequències de l'idioma
    public void getLlistaFreq() {
        return llistaFreq;
    }

}