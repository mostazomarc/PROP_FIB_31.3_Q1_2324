package Domini;

import ControladorsDomini.CtrlDomini;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.*;

import java.util.*;
import java.util.Set;

public class Perfil {

    private String Usuari;
    private String Contrasenya;
    private Map<String, LlistaFrequencies>  frequencies;
    private Map<String, Teclat>  teclats;

    private CtrlDomini controlador;

    //Pre:
    //Post: Si la llista idetificada per nomLlista no existeix llença una excepció
    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    //Pre:
    //Post: Si la llista idetificada per nomLlista ja existeix llença una excepció
    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }


    public Perfil (String User, String pswd, CtrlDomini cd) {
        Usuari = User;
        Contrasenya = pswd;
        frequencies = new HashMap<>();
        teclats = new HashMap<>();
        controlador = cd;
    }

    public Perfil (String User, CtrlDomini cd) {
        Usuari = User;
        frequencies = new HashMap<>();
        teclats = new HashMap<>();
        controlador = cd;
    }

    //Pre:
    //Post: S'obté el nom d'usuari del perfil
    public String getUsuari() {
        return Usuari;
    }

    //Pre:
    //Post: S'obté la contrasenya del perfil
    public String getContrasenya() {
        return Contrasenya;
    }

    //Pre:
    //Post: Es canvia el nom d'usuari del perfil
    public void canviaUsuari (String newUs) {
        Usuari = newUs;
    }

    //Pre:
    //Post: Es canvia la contrasenya del perfil
    public void canviaContrasenya (String newCon) {
        Contrasenya = newCon;
    }

    //Pre:
    //Post: S'afegeixen les noves entrades a la llista de frequencies name o es crea
    public void afegirLlistaFreq (LlistaFrequencies llista) throws ExcepcionsCreadorTeclat {
        comprovaLlistaJaExisteix(llista.getNom());
        frequencies.put(llista.getNom(),llista);
    }


    //Pre:
    //Post: S'elimina la llista indicada per nomLlista
    public void eliminaLlista(String nomLlista) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(nomLlista);
        for (Map.Entry<String, Teclat> Teclat : teclats.entrySet()) {
            String nom = Teclat.getKey();
            Teclat teclat = Teclat.getValue();
            if (teclat.getNomLlistaFreq().equals(nomLlista)) throw new LlistaFreqEnUs(nomLlista);
        }
        frequencies.remove(nomLlista);
    }

    //Pre:
    //Post: Es modifica la llista indicada per nomLlista
    public void modificarLlista(String nomLlista, Map<String,Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(nomLlista);
        frequencies.get(nomLlista).modificarLlista(novesEntrades);
    }

    //Pre:
    //Post: Es retorna el conjunt de noms de les llistes guardades al perfil
    public List<String> getNomAllLlistes() {
        Set<String> noms = frequencies.keySet();
        return new ArrayList<>(noms);
    }

    //Pre: La llista amb nom nomLlista existeix
    //Post: Es retorna el nom de l'idioma de la llista amb nom nomLlista
    public String getNomIdiomaLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        comprovaLlistaNoExisteix(nomLlista);
        return frequencies.get(nomLlista).getNomIdioma();
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(nomSeleccio);
        return frequencies.get(nomSeleccio).getFrequencies();
    }

    //Pre:
    //Post: Es crea un teclat amb nom: NomTeclat i a partir de la llista identificada per NomLlista, l'idioma idioma i amb layout n*m
    public void crearTeclatLlistaPropia(String NomTeclat, String NomLlista, Idioma idioma, int n, int m) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(NomLlista);
        if (teclats.containsKey(NomTeclat)) throw new TeclatJaExisteix(NomTeclat);
        teclats.put(NomTeclat,new Teclat(NomTeclat,NomLlista, frequencies.get(NomLlista).getFrequencies(), idioma, n, m));
    }

    //Pre:
    //Post: Es crea un teclat amb nom: NomTeclat i a partir de la llista de l'idioma i l'idioma idioma i amb layout n*m
    public void crearTeclatLlistaIdioma(String NomTeclat, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat {
        if (teclats.containsKey(NomTeclat)) throw new TeclatJaExisteix(NomTeclat);
        teclats.put(NomTeclat,new Teclat(NomTeclat, i, n, m));
    }

    //Pre:
    //Post: S'elimina el teclat identificat per NomTeclat
    public void eliminarTeclat(String NomTeclat) throws ExcepcionsCreadorTeclat{
        if (!teclats.containsKey(NomTeclat)) throw new TeclatNoExisteix(NomTeclat);
        teclats.remove(NomTeclat);
    }

    //Pre:
    //Post: S'obté una llist aamb els noms de tots els teclats del perfil
    public List<String> getNomsTeclats()  {
        Set<String> noms = teclats.keySet();
        return new ArrayList<>(noms);
    }

    //Pre:
    //Post: S'obté el nom de l'idioma del teclat identificat per nomTeclat
    public String getIdiomaTeclat (String nomTeclat) throws ExcepcionsCreadorTeclat {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getNomIdiomaTeclat();
    }

    //Pre:
    //Post: S'obté la el nom de la llista utilitzada per crear el teclat identificat per nomTeclat
    public String getLlistaTeclat (String nomTeclat) throws ExcepcionsCreadorTeclat {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getNomLlistaFreq();
    }

    //Pre:
    //Post: S'obté la disposició del teclat identficat per nomTeclat
    public char[][] consultaTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getDisposicio();
    }

}

//Classe Programada per: Marc