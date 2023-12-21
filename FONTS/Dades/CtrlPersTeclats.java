package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Teclat;
import Excepcions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CtrlPersTeclats es una classe que permet guardar i carregar teclats
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència de teclats</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersTeclats {
    /**
     * Instància de CtrlPersTeclats
     */
    private static CtrlPersTeclats singletonObject;
    /**
     * Instància de CtrlDomini
     */
    CtrlDomini controlador;
    //si canviesis de perfil tindires una nova llista de teclats
    /**
     * Mapa de teclats guardats
     */
    private Map<String, Teclat> teclats;

    /**
     * Nom d'usuari del perfil actual
     */
    private String usuari;


    /**
     * Creadora de CtrlPersTeclats
     * <p> Crea un conjunt de teclats i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersTeclats(CtrlDomini c) {
        teclats = new HashMap<>();
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersTeclats, si no existeix cap CtrlPersTeclats es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersTeclats
     */
    public static CtrlPersTeclats getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersTeclats(c) {

            };
        return singletonObject;
    }

    /**
     * Comprova que el teclat identificat per nomTeclat existeix
     * @param nomTeclat El nom del teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    private void comprovaTeclatNoExisteix(String nomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
    }


    /**
     * Comprova que el teclat identificat per nomTeclat ja existeix
     * @param nomTeclat El nom del teclat
     * @throws TeclatJaExisteix Si el teclat ja existeix
     */
    private void comprovaTeclatJaExisteix(String nomTeclat) throws TeclatJaExisteix {
        if (teclats.containsKey(nomTeclat)) throw new TeclatJaExisteix(nomTeclat);
    }

    /**
     * Canvia el perfil actual per un altre
     * <p> Guarda el conjunt de teclats del perfil actual i carrega la del perfil nou</p>
     * @param usuari El nom d'usuari del perfil al que es vol canviar
     */
    public void canviaPerfil(String usuari) {
        //guardar teclats del usuari antic
        String usuariAntic = this.usuari;
        if (usuariAntic != null) guardar();
        this.usuari = usuari;
        teclats = new HashMap<>();
        //carregar llistes del usuari nou
        carregar();
    }

    /**
     * Guarda els teclats del perfil actual al .json de teclats
     */
    public void guardar() {
        if (usuari == null) return;
        //guardar teclats de l'usuari
        System.out.println("Guardant Teclats de l'usuari: " + usuari );
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/TeclatsUsuarisActius.json")){
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < CjtUsuaris.size() && !trobat; ++i){
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && nomUsuari.equals(usuari)){    //Si el nom d'usuari coincideix
                    CjtUsuaris.remove(next); //L'esborrem i despres l'afegirem
                    trobat = true;  //Deixem de recorrer el vector
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }

        JSONObject nouUsuari = new JSONObject();
        nouUsuari.put("nomUsuari", usuari);
        JSONArray teclatsUsuari = new JSONArray();
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            System.out.println("Guardant Teclat " + llista.getKey());
            JSONObject nouTeclat = new JSONObject();
            nouTeclat.put("nomTeclat", llista.getKey());
            nouTeclat.put("nomIdioma", llista.getValue().getNomIdioma());
            nouTeclat.put("nomLlistaFreq", llista.getValue().getNomLlistaFreq());
            JSONArray disposicio = new JSONArray();
            for (char[] fila : llista.getValue().getDisposicio()) {
                JSONArray filaJSON = new JSONArray();
                for (char c : fila) {
                    filaJSON.add(String.valueOf(c));
                }
                disposicio.add(filaJSON);
            }
            nouTeclat.put("disposicio", disposicio);
            nouTeclat.put("dimX", llista.getValue().getDimX());
            nouTeclat.put("dimY", llista.getValue().getDimY());
            nouTeclat.put("estrategia", llista.getValue().getNomEstrategia());

            teclatsUsuari.add(nouTeclat);
        }
        nouUsuari.put("teclats", teclatsUsuari);
        CjtUsuaris.add(nouUsuari);
        try (FileWriter file = new FileWriter("./DATA/Saves/TeclatsUsuarisActius.json")) {
            file.write(CjtUsuaris.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Carrega els teclats del perfil actual del .json de teclats
     *
     */
    public void carregar()  {
        System.out.println("Carregant teclats");
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/TeclatsUsuarisActius.json")) {
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < CjtUsuaris.size() && !trobat; ++i) {
                System.out.println("Carregant teclats de l'usuari: " + usuari);
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String) next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if (nomUsuari != null && nomUsuari.equals(usuari)) {    //Si el nom d'usuari coincideix
                    trobat = true;
                    JSONArray teclatsUsuari = (JSONArray) next.get("teclats");
                    for (int j = 0; j < teclatsUsuari.size(); ++j) {
                        JSONObject nextTeclat = (JSONObject) teclatsUsuari.get(j);
                        String nomTeclat = ((String) nextTeclat.get("nomTeclat"));
                        System.out.println("Carregant Teclat " + nomTeclat);
                        String nomIdioma = ((String) nextTeclat.get("nomIdioma"));
                        String nomLlistaFreq = ((String) nextTeclat.get("nomLlistaFreq"));
                        String estrategia = ((String) nextTeclat.get("estrategia"));
                        int dimX = ((Long) nextTeclat.get("dimX")).intValue();
                        int dimY = ((Long) nextTeclat.get("dimY")).intValue();
                        JSONArray disposicio = (JSONArray) nextTeclat.get("disposicio");
                        char[][] disposicioTeclat = new char[dimX][dimY];
                        for (int k = 0; k < disposicio.size(); ++k) {
                            JSONArray fila = (JSONArray) disposicio.get(k);
                            for (int l = 0; l < fila.size(); ++l) {
                                disposicioTeclat[k][l] = ((String) fila.get(l)).charAt(0);
                            }
                        }
                        Teclat t = controlador.afegirTeclat(nomTeclat, nomIdioma, nomLlistaFreq, dimX, dimY, estrategia, disposicioTeclat);
                        teclats.put(nomTeclat, t);
                    }
                }
            }
        } catch (IOException e) {
        } catch (ParseException e) {
        } catch (ExcepcionsCreadorTeclat e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Guarda el teclat t al conjunt de teclats
     * @param t El teclat a guardar
     * @throws TeclatJaExisteix Si el teclat ja existeix
     */
    public void afegirTeclat(Teclat t) throws TeclatJaExisteix {
        comprovaTeclatJaExisteix(t.getNom());
        teclats.put(t.getNom(), t);
    }

    /**
     * Elimina el teclat identificat per nomTeclat del conjunt de teclats, si no existeix llença una excepció
     * @param nomTeclat El nom del teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public void eliminarTeclat(String nomTeclat) throws TeclatNoExisteix {
        comprovaTeclatNoExisteix(nomTeclat);
        teclats.remove(nomTeclat);
    }

    /**
     * Elimina els teclats del perfil identificat per nomPerfil del conjunt de teclats
     * @param nomPerfil El nom del perfil
     */
    public void eliminaPerfil(String nomPerfil) {
        if (usuari.equals(nomPerfil)) usuari = null;
        System.out.println("Eliminant teclats de l'usuari: " + nomPerfil );
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/TeclatsUsuarisActius.json")){
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < CjtUsuaris.size() && !trobat; ++i){
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && nomUsuari.equals(nomPerfil)){    //Si el nom d'usuari coincideix
                    CjtUsuaris.remove(next); //L'esborrem i despres l'afegirem
                    trobat = true;  //Deixem de recorrer el vector
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }

        try (FileWriter file = new FileWriter("./DATA/Saves/TeclatsUsuarisActius.json")) {
            file.write(CjtUsuaris.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }


    /**
     * Comprova que l'idioma identificat per nomIdioma no està en ús en cap teclat i si ho està llença una excepció
     * (de moment no comprova teclats d'altres usuaris perquè fa falta capa de persistencia)
     * @param nomIdioma El nom de l'idioma
     * @throws IdiomaEnUs Si l'idioma està en ús per algun teclat
     */
    public void comprovarUsIdioma(String nomIdioma) throws IdiomaEnUs {
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
        //comprovar us de l'idioma nomIdioma en els teclats del .json
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/TeclatsUsuarisActius.json")){
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            for (int i = 0; i < CjtUsuaris.size(); ++i){
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && !nomUsuari.equals(usuari)){    //Si el nom d'usuari coincideix
                    JSONArray teclatsUsuari = (JSONArray) next.get("teclats");
                    for (int j = 0; j < teclatsUsuari.size(); ++j) {
                        JSONObject nextTeclat = (JSONObject) teclatsUsuari.get(j);
                        String nomIdiomaTeclat = ((String) nextTeclat.get("nomIdioma"));
                        if (nomIdiomaTeclat.equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
                    }
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }
    }


    /**
     * Comprova que la llista identificada per nomLlista no està en ús en cap teclat i si ho està llença una excepció
     * (de moment no comprova teclats d'altres usuaris perquè fa falta capa de persistencia)
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqEnUs Si la llista està en ús per algun teclat
     */
    public void comprovarUsLlista(String nomLlista) throws LlistaFreqEnUs {
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            if (llista.getValue().getNomLlistaFreq().equals(nomLlista)) throw new LlistaFreqEnUs(nomLlista);
        }
    }

    /**
     * Obté el teclat identificat per nomTeclat
     * @param nomTeclat El nom del teclat
     * @return El teclat identificat per nomTeclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public Teclat getTeclat(String nomTeclat) throws TeclatNoExisteix {
        comprovaTeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat);
    }

}