package com.rimoldi.practico1.model;

public class NumeroPrimo {
    public static boolean esPrimo(int nro) {
        if(nro<1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(nro); i++) {
            if (nro % i == 0) {
                return false;
            }
        }
        return true;
    }
}
