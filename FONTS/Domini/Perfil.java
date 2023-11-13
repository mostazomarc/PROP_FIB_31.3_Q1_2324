package Domini;

import java.util.*;
import java.util.Set;

public class Perfil {

    private String Usuari;
    private String Contrasenya;
    private Map<String, LlistaFrequencies>  frequencies;
    private Map<String, Teclat>  teclats;

    public Perfil (String User, String pswd) {
        Usuari = User;
        Contrasenya = pswd;
        frequencies = new HashMap<>();
    }

    public Perfil (String User) {
        Usuari = User;
        frequencies = new HashMap<>();
    }

    public String getUsuari() {
        return Usuari;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void canviaUsuari (String newUs) {
        Usuari = newUs;
    }

    public void canviaContrasenya (String newCon) {
        Contrasenya = newCon;
    }

    //Pre:
    //Post: S'afegeixen les noves entrades a la llista de frequencies name o es crea
    public void afegirLlistaFreq (String name, Idioma i, Map<String, Integer> novesEntrades) {
        if (frequencies.containsKey(name)) frequencies.get(name).insertarFrequencies(novesEntrades);
        else {
            frequencies.put(name,new LlistaFrequencies(name,i,novesEntrades));
        }
    }

    //Pre:
    //Post: Es crea una llista amb nom, buida
    public LlistaFrequencies crearLlistaFreq (String name, Idioma i) {
        if (frequencies.containsKey(name)) return null;
        else {
            frequencies.put(name,new LlistaFrequencies(name,i));
            return frequencies.get(name);
        }
    }

    public void eliminaLlista(String nomLlista) {
        frequencies.remove(nomLlista);
    }

    //Pre:
    //Post: Es retorna el conjunt de noms de les llistes guardades al perfil
    public List<String> getNomAllLlistes() {
        Set<String> noms = frequencies.keySet();
        return new ArrayList<>(noms);
    }

    //Pre: La llista amb nom nomLlista existeix
    //Post: Es retorna el nom de l'idioma de la llista amb nom nomLlista
    public String getNomIdiomaLlista(String nomLlista) {
        return frequencies.get(nomLlista).getNomIdioma();
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) {
        return frequencies.get(nomSeleccio).getFrequencies();
    }

    public void crearTeclat(String NomTeclat, String NomLlista, Idioma idioma) {
        LlistaFrequencies llista = frequencies.get(NomLlista);
        //teclats.put(NomTeclat,new Teclat(NomTeclat,llista,idioma));
    }
}