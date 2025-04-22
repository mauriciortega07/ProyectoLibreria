package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contexto {
    public static void main(String[] args) throws ExcepcionesPropias {
        int opcion = 0;
        do {
            opcion = MenuSelector.menu(opcion);
            MenuSelector.operacionDelMenu(opcion);
            //System.out.println();

        } while (opcion != 12);
        System.out.println("Fin del Programa");
    }
}
