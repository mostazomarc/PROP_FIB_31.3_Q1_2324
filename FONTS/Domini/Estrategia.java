package Domini;

import java.text.Normalizer;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Estrategia {
    private String nom;


    public static String quitarTilde(char c) {
        String s = String.valueOf(c);
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("[\\p{M}]");
        Matcher matcher = pattern.matcher(normalized);
        return matcher.replaceAll("");
    }
    //Inicializa la matriz de tráfico
    //Inicializa la matriz de tráfico
    public double[][] calculaMatTraf(Map<String, Integer> palabrasFrec, Map<Character, Integer> lletres, int n) {


        //inicializada a 0 por defecto
        double[][] trafficMatrix = new double[n][n];

        for (String palabra : palabrasFrec.keySet()) {
            int frecuencia = palabrasFrec.get(palabra);
            palabra = palabra.toLowerCase();
            if(palabra.length() != 1) {
                for (int i = 0; i < palabra.length() - 1; i++) {
                    //guardamos los índices de cada letra en el abecedario
                    char c1 = palabra.charAt(i);
                    char c2 = palabra.charAt(i + 1);

                    if(Character.isLetter(c1) && Character.isLetter(c2)){
                        if(Character.isUnicodeIdentifierPart(c1) && !lletres.containsKey(c1)){
                            String caracterSinTilde = quitarTilde(c1);
                            c1 = caracterSinTilde.charAt(0);
                        }
                        if(Character.isUnicodeIdentifierPart(c2) && !lletres.containsKey(c2)){
                            String caracterSinTilde = quitarTilde(c2);
                            c2 = caracterSinTilde.charAt(0);
                        }
                        int letra1 = lletres.get(c1);
                        int letra2 = lletres.get(c2);
                        trafficMatrix[letra1][letra2] += frecuencia;
                    }

                }
            }
        }

        return trafficMatrix;
    }

    //Inicializa la matriz de distancias
    public double[][] calculaMatDist( int n, int n_filas, int n_columnas) {
        //inicializada a 0 por defecto
        double[][] distanceMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    double distancia;
                    //ubicaciones en la misma fila
                    if(i/n_columnas == j/n_columnas) distancia = Math.abs(j-i);
                        //ubicaciones en filas distintas
                    else{
                        //número de filas de distancia
                        double dist_filas = Math.abs(i/n_columnas - j/n_columnas);
                        //número de columnas de distancia
                        double dist_columnas = Math.abs((i % n_columnas) - (j % n_columnas));
                        //distancia según la ley Euclidiana, al ser el resto de variables de la ley de Fitts constantes
                        distancia = Math.sqrt(Math.pow(dist_filas,2) + Math.pow(dist_columnas, 2));
                    }
                    distanceMatrix[i][j] = distancia;
                    distanceMatrix[j][i] = distancia;
                }
            }
        }

        return distanceMatrix;
    }

    public boolean pos_valida(Integer i, Integer j, Integer n_columnas, Integer n){
        return ((i*n_columnas) + j) < n;
    }



    abstract char[][] calculaDisposicio(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas);
}
