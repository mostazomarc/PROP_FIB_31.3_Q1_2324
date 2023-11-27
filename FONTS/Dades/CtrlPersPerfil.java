package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Perfil;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CtrlPersPerfil es una classe que permet guardar i carregar perfils
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència dels perfils</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersPerfil {
    /**
     * Instància de CtrlPersPerfil
     */
    private static CtrlPersPerfil singletonObject;
    /**
     * Controlador de domini
     */
    private final CtrlDomini controlador;
    /**
     * Mapa de perfils guardats
     */
    private final HashMap<String, Perfil> PerfilsActius;

    /**
     * Creadora de CtrlPersPerfil
     * <p> Crea un conjunt de perfils i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersPerfil(CtrlDomini c) {
        PerfilsActius = new HashMap<>();
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersPerfil, si no existeix cap CtrlPersPerfil es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersPerfil
     */
    public static CtrlPersPerfil getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersPerfil(c) {
            };
        return singletonObject;
    }

    /**
     * Carrega els perfils dels arxius on estàn guardats (de moment crea noves, funcionarà al tenir capa de persistencia)
     * @throws ExcepcionsCreadorTeclat Si no es pot crear el perfil
     */
    public void carregarPerfil() throws ExcepcionsCreadorTeclat {
        controlador.iniciaInstancia("Prova");
    }


    /**
     * Crea i guarda un perfil
     * @param nomPerfil El nom del perfil
     * @return El perfil creat
     * @throws PerfilJaExisteix Si el perfil ja existeix
     */
    public Perfil afegirPerfil(String nomPerfil) throws PerfilJaExisteix {
        if (PerfilsActius.containsKey(nomPerfil)) throw new PerfilJaExisteix(nomPerfil);
        Perfil nouPerfil = new Perfil(nomPerfil, controlador);
        PerfilsActius.put(nomPerfil, nouPerfil);
        return nouPerfil;
    }

    /**
     * Obté el perfil identificat per nomPerfil
     * @param nomPerfil El nom del perfil
     * @return El perfil identificat per nomPerfil
     * @throws PerfilNoExisteix Si el perfil no existeix
     */
    public Perfil getPerfil(String nomPerfil) throws PerfilNoExisteix{
        if (!PerfilsActius.containsKey(nomPerfil)) throw new PerfilNoExisteix(nomPerfil);
        Perfil nouPerfil = PerfilsActius.get(nomPerfil);
        return nouPerfil;
    }

    /**
     * Obté el conjunt de noms dels perfils
     * @return El conjunt de noms dels perfils
     */
    public List<String> getAllPerfils() {
        return new ArrayList<>(PerfilsActius.keySet());
    }


}
