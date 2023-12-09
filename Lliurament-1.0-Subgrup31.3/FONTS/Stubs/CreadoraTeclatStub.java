package Stubs;

import Stubs.LlistaFrequenciesStub;

import java.util.*;

public class CreadoraTeclatStub {

    public CreadoraTeclatStub() {}

    private char[][] teclatQWERTY = {
            {'q', 'w', 'e','r', 't', 'y','u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'k' , 'l', 'ñ'},
            {'z', 'x', 'c','v','b','n','m'}
    };

    public char[][] crearTeclat(LlistaFrequenciesStub l, char[] i) {
        return teclatQWERTY;
    }
}