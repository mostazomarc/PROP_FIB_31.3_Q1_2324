package Domini;

import java.util.Map;
import java.util.Set;

public interface Estrategia {


    public void solve(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas);
}