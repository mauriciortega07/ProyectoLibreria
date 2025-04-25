package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ebac.proyectoJava.MenuSelector.library;

public class MenuSelectorCaseUsersMethods {
    public static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean isValidEmail(String email){
        String emailFormato = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailFormato);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String passFormato = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(passFormato);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void ListUsers() {
        System.out.println("***** Lista de Usuarios Existentes *****");
        List<User> listaUsuarios = library.getUserList();
        listaUsuarios.forEach(System.out::println);
        System.out.println("Hay " + listaUsuarios.size() + " usuarios registrados en la biblioteca");
    }

}
