package Dades;

import Domini.Alfabet;
import Domini.Idioma;
import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.IdiomaJaExisteix;
import Excepcions.IdiomaNoExisteix;

import java.util.Map;
import java.util.TreeMap;

public class CtrlPersIdiomes {
    private static CtrlPersIdiomes singletonObject;
    private TreeMap<String, Idioma> Idiomes;

    //Pre:
    //Post: Retorna la instancia de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
    public static CtrlPersIdiomes getInstance() {
        if(singletonObject == null)
            singletonObject = new CtrlPersIdiomes(){

            };
        return singletonObject;
    }

    private CtrlPersIdiomes() {
        Idiomes = new TreeMap<String, Idioma>();
    }

    //Pre:
    //Post: S'afegeix l'Idioma identificat per nomIdioma
    public void afegirIdioma(String nomIdioma, Alfabet a, String filename, Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        if (existeix(nomIdioma)) throw new IdiomaJaExisteix(nomIdioma);
        Idioma nouIdioma = new Idioma(nomIdioma, a, filename, novesEntrades);
        Idiomes.put(nomIdioma, nouIdioma);
    }

    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        Idioma i = getIdioma(nomIdioma);
        Alfabet a = i.getAlfabet();
        a.treureIdioma(nomIdioma);
        Idiomes.remove(nomIdioma);
    }

    //Pre:
    //Post: Retorna TRUE si existeix un Idioma amb nomIdioma, FALSE en cas contrari
    public boolean existeix(String nomIdioma) {
        if (Idiomes.containsKey(nomIdioma)) return true;
        return false;
    }

    //Pre:
    //Post: Retorna l'Alfabet identificat per nomIdioma
    public Idioma getIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        if (!existeix(nomIdioma)) throw new IdiomaNoExisteix(nomIdioma);
        return Idiomes.get(nomIdioma);
    }

    //Pre:
    //Post: Retorna el conjunt d'Idiomes
    public TreeMap<String, Idioma> getIdiomes() {
        return Idiomes;
    }
}

//Classe Programada per: Arnau Tajahuerce
