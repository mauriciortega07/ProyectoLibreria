package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Author;
import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;

import java.util.List;
import java.util.Scanner;

public class MenuSelectorCaseAuthorMethods {

    public static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public String addFirstNameAuthorMethodCase() throws ExcepcionesPropias {
        String firtsName = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingresa el nombre del autor: ");
            firtsName = scanner.next();

            try {
                if (isNumber(firtsName)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa un nombre correcto");
                }

                break;
            } catch (ExcepcionesPropias e) {
                System.out.println("Error " + e.getMessage());
            }
        }

        scanner.close();
        return firtsName;

    }

    public String addLastNameAuthorMethodCase() throws ExcepcionesPropias {
        String lastName = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingresa los apellidos del autor");
            lastName = scanner.next();

            try {
                if (isNumber(lastName)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa un nombre correcto");
                }
                break;
            } catch (ExcepcionesPropias e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
        return lastName;
    }

    public String addBiographyAuthorMethodCase() throws ExcepcionesPropias {
        String biography = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escribe su biografia");
            biography = scanner.next();

            try {
                if (isNumber(biography)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa su biografia");
                }
                break;
            } catch (ExcepcionesPropias e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        scanner.close();
        return biography;
    }
    
}
