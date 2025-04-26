package com.ebac.proyectoJava;

import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseAuthorMethods;
import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseBooksMethods;
import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseUsersMethods;

import java.util.*;

import static com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuOptionsTags.menuOptions;

public class MenuSelector {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();

    public static int menu(int opcion) {
        opcion = 0;
        do {
            menuOptions();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("xxxxx Valor invalido. Ingresa un valor listado xxxx");
            }

        } while (opcion < 1 || opcion > 13);
        return opcion;
    }

    public static void operacionDelMenu(int opcion) throws ExcepcionesPropias {
        //MenuSelectorCaseAuthorMethods.preuploadAuthors();
        switch (opcion) {
            case 1:
                //Metodo que agrega un libro a la biblioteca
                MenuSelectorCaseBooksMethods.createNewBook();
                break;

            case 2:
                //Metodo que regresa la lista de libros
                MenuSelectorCaseBooksMethods.listBooks();
                break;

            case 3:
                //Metodo que actualiza un libro
                MenuSelectorCaseBooksMethods.updateBook();
                break;

            case 4:
                //Metodo que elimina un libro
                MenuSelectorCaseBooksMethods.deleteBook();
                break;

            case 5:
                //Metodo que Agrega un autor
                MenuSelectorCaseAuthorMethods.createAuthor();
                break;

            case 6:
                //Metodo que regresa la lista de autores
                MenuSelectorCaseAuthorMethods.listAuthors();
                break;

            case 7:
                //Metodo que actualiza un autor
                //int indexAutor = MenuSelectorCaseAuthorMethods.searchAuthorName();
                //Author updateAutor = MenuSelectorCaseAuthorMethods.UpdateAuthor();
                MenuSelectorCaseAuthorMethods.updateAuthor();
                break;

            case 8:
                //Metodo que elimina un author
                MenuSelectorCaseAuthorMethods.deleteAuthor();
                break;
            case 9:
                //Metodo para agregar un usuario
                MenuSelectorCaseUsersMethods.CreateUser();
                break;
            case 10:
                //Metodo para consultar la lista de usuarios
                MenuSelectorCaseUsersMethods.ListUsers();
                break;
            case 11:
                //Metodo para modifica un usuario de la lista de usuarios
                MenuSelectorCaseUsersMethods.UpdateUser();
                break;
            case 12:
                //Metodo para elimina un usuario de la lista de usuarios
                MenuSelectorCaseUsersMethods.DeleteUser();
                break;
            case 13:
                break;

            default:
                System.out.println("Ingresa una opcion valida!");

        }
    }
}
