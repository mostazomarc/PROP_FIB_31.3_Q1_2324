package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Excepcions.*;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CtrlPersFreq es una classe que permet guardar i carregar llistes de frequencies
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència de llistes de frequencies</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersFreq {
    /**
     * Instància de CtrlPersFreq
     */
    private static CtrlPersFreq singletonObject;


    /**
     * Mapa de llistes de frequencies guardades
     * <p> La clau és el nom de la llista</p>
     * <p> El valor és la llista de frequencies</p>
     * <p> Si canvies de perfil es guarda aquest map i es carrega el del nou perfil</p>
     */
    private Map<String, LlistaFrequencies> frequencies;

    /**
     * Instància de CtrlDomini
     */
    private final CtrlDomini controlador;
    /**
     * Nom d'usuari del perfil actual
     */
    String usuari;

    /**
     * Creadora de CtrlPersFreq
     * <p> Crea un conjunt de llistes de frequencies i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersFreq(CtrlDomini c) {
        frequencies = new HashMap<>();
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersFreq, si no existeix cap CtrlPersFreq es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersFreq
     */
    public static CtrlPersFreq getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersFreq(c) {

            };
        return singletonObject;
    }

    /**
     * Comprova que la llista identificada per nomLlista existeix i si no ho fa llença una excepció
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    /**
     * Comprova que la llista identificada per nomLlista ja existeix i si ho fa llença una excepció
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }

    /**
     * Guarda les llistes de frequencies del perfil actual al .json de persistencia
     */
    public void guardar() {
        if (usuari == null) return;
        System.out.println("Guardant llistes de frequencies");
        //guardar totes les llistes
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/LlistesUsuarisActius.json")){
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

        JSONObject nouUsuari = new JSONObject(); //creem un nou objecte JSON per el usuari actual
        nouUsuari.put ("nomUsuari", usuari);  //guardem el nom d'usuari actual
        JSONArray llistes = new JSONArray();    //Generem l'array de llistes del perfil
        for (Map.Entry<String, LlistaFrequencies> llista : frequencies.entrySet()) {
            if (!llista.getKey().contains("LlistaPred")) {
                System.out.println("Guardant llista " + llista.getValue().getNom() + " de l'usuari " + usuari);
                JSONObject llistaJSON = new JSONObject(); //Creem un nou objecte JSON per la llista
                llistaJSON.put("nomLlista", llista.getValue().getNom()); //Guardem el nom de la llista
                llistaJSON.put("nomIdioma", llista.getValue().getNomIdioma()); //Guardem el nom de l'idioma de la llista
                JSONArray paraules = new JSONArray();   //Generem l'array de paraules de la llista
                for (Map.Entry<String, Integer> paraula : llista.getValue().getFrequencies().entrySet()) {
                    JSONObject paraulaJSON = new JSONObject(); //Creem un nou objecte JSON per la paraula
                    paraulaJSON.put("paraula", paraula.getKey()); //Guardem la paraula
                    paraulaJSON.put("freq", paraula.getValue()); //Guardem la freqüència
                    paraules.add(paraulaJSON);  //Afegim la paraula a l'array de paraules
                }
                llistaJSON.put("paraules", paraules); //Afegim l'array de paraules a l'objecte JSON de la llista
                llistes.add(llistaJSON);    //Afegim l'objecte JSON de la llista a l'array de llistes
            }
        }
        nouUsuari.put("llistes", llistes);  //Afegim l'array de llistes a l'objecte JSON del usuari
        CjtUsuaris.add(nouUsuari);  //Afegim l'objecte JSON del usuari al conjunt d'usuaris
        try (FileWriter file = new FileWriter("./DATA/Saves/LlistesUsuarisActius.json")) {
            file.write(CjtUsuaris.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Carrega les llistes de frequencies del perfil actual del .json de persistencia
     * @throws Exception Si no es pot carregar
     */
    public void carregar() throws Exception{
        System.out.println("Carregant llistes de frequencies");
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/LlistesUsuarisActius.json")) {
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < CjtUsuaris.size() && !trobat; ++i) {
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String) next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if (nomUsuari != null && nomUsuari.equals(usuari)) {    //Si el nom d'usuari coincideix
                    JSONArray llistes = (JSONArray) next.get("llistes"); //Obtenim l'array de llistes del usuari
                    for (int j = 0; j < llistes.size(); ++j) {
                        JSONObject llista = (JSONObject) llistes.get(j); //Obtenim l'objecte de la llista jessim
                        String nomLlista = ((String) llista.get("nomLlista"));  //Obtenim el nom de la llista jessim
                        String nomIdioma = ((String) llista.get("nomIdioma"));  //Obtenim el nom de l'idioma de la llista jessim
                        JSONArray paraules = (JSONArray) llista.get("paraules"); //Obtenim l'array de paraules de la llista jessim
                        Map<String, Integer> paraulesLlista = new HashMap<>();
                        for (int k = 0; k < paraules.size(); ++k) {
                            JSONObject paraula = (JSONObject) paraules.get(k); //Obtenim l'objecte de la paraula kessim
                            String paraulaString = ((String) paraula.get("paraula"));  //Obtenim la paraula kessim
                            Long freq = ((Long) paraula.get("freq"));  //Obtenim la freqüència de la paraula kessim
                            paraulesLlista.put(paraulaString, freq.intValue()); //Afegim la paraula i la freqüència a la llista
                        }
                        System.out.println("Carregant llista " + nomLlista + " de l'usuari " + usuari);
                        controlador.novaLlistaPerfil("Carregada", nomLlista, nomIdioma, paraulesLlista);
                    }
                }
            }
        } catch (IOException e) {
        } catch (ParseException e) {
        } catch (LlistaFreqJaExisteix e) {
            System.out.println("La llista ja estaba carregada");
        }
    }

    /**
     * Canvia el perfil actual per un altre
     * <p> Guarda el conjunt de llistes del perfil actual i carrega el del perfil nou</p>
     * @param usuari El nom d'usuari del perfil a canviar
     * @throws Exception Si no es pot canviar
     */
    public void canviaPerfil(String usuari) throws Exception {
        //guardar llistes del usuari antic
        String usuariAntic = this.usuari;
        if (usuariAntic != null) guardar();
        this.usuari = usuari;
        frequencies = new HashMap<>();
        //carregar llistes del usuari nou
        carregar();
    }

    /**
     * Elimina de la capa de persistencia les llistes del perfil identificat per nomPerfil
     * @param nomPerfil El nom del perfil a esborrar
     */
    public void eliminaPerfil(String nomPerfil) {
        System.out.println("Eliminant les llistes del perfil" + nomPerfil);
        if (usuari.equals(nomPerfil)) usuari = null;
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/LlistesUsuarisActius.json")){
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

        try (FileWriter file = new FileWriter("./DATA/Saves/LlistesUsuarisActius.json")) {
            file.write(CjtUsuaris.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Afegeix una llista de frequencies al conjunt de llistes del perfil actual
     * @param llista La llista de frequencies a afegir
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    public void guardarLlistaFreq(LlistaFrequencies llista) throws LlistaFreqJaExisteix{
        comprovaLlistaJaExisteix(llista.getNom());
        frequencies.put(llista.getNom(), llista);
    }

    /**
     * Elimina la llista de frequencies identificada per nomLlista del conjunt de llistes del perfil actual
     * @param nomLlista El nom de la llista a eliminar
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public void eliminarLlista(String nomLlista) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        frequencies.remove(nomLlista);
    }

    // (de moment no comprova llistes de frequencies d'altres usuaris perquè fa falta capa de persistencia) FALTA FER-HOOOOO
    /**
     * Comprova que l'idioma identificat per nomIdioma no està en ús en cap llista de frequencies
     * @param nomIdioma El nom de l'idioma
     * @throws IdiomaEnUs Si l'idioma està en ús
     */
    public void comprovarUsIdioma(String nomIdioma) throws IdiomaEnUs {
        for (Map.Entry<String, LlistaFrequencies> llista : frequencies.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma) && !llista.getValue().getNom().equals("LlistaPred"+nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
        //comprovar us idioma a les llistes del .json
        JSONParser jsP = new JSONParser();
        JSONArray CjtUsuaris = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/LlistesUsuarisActius.json")){
            CjtUsuaris = (JSONArray) jsP.parse(rd);
            for (int i = 0; i < CjtUsuaris.size(); ++i){
                JSONObject next = (JSONObject) CjtUsuaris.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && !nomUsuari.equals(usuari)){    //Si el nom d'usuari coincideix
                    JSONArray llistesUsuari = (JSONArray) next.get("llistes");
                    for (int j = 0; j < llistesUsuari.size(); ++j) {
                        JSONObject nextLlista = (JSONObject) llistesUsuari.get(j);
                        String nomIdiomaLlista = ((String) nextLlista.get("nomIdioma"));
                        if (nomIdiomaLlista.equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
                    }
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }
    }

    /**
     * Obté la llista de frequencies identificada per nomLlista
     * <p> Només comprova al conjunt de llistes del perfil actual</p>
     * @param nomLlista El nom de la llista
     * @return La llista de frequencies identificada per nomLlista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public LlistaFrequencies getLlistaFreq(String nomLlista) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        return frequencies.get(nomLlista);
    }

}


//Classe Programada per: Marc