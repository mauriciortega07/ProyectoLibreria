package com.ebac.proyectoJava;

import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseAuthorMethods;
import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseBooksMethods;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuOptionsTags.menuOptions;

public class MenuSelector {
    static MenuSelectorCaseBooksMethods menuSelectorCaseBooksMethods = new MenuSelectorCaseBooksMethods();
    static MenuSelectorCaseAuthorMethods menuSelectorCaseAuthorMethods = new MenuSelectorCaseAuthorMethods();
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();

    public static int menu(int opcion) {
        opcion = 0;
        do {
            menuOptions();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("xxxxx Valor invalido. Ingresa un valor listado xxxx");
            }

        } while (opcion < 1 || opcion > 12);
        return opcion;
    }

    public static void operacionDelMenu (int opcion) throws ExcepcionesPropias {
         //Book newBook;
        switch (opcion) {
            case 1:
                //Variable que contiene un libro nuevo a agregar
                Book newBook = menuSelectorCaseBooksMethods.NewBook();

                //METODO QUE AGREGA LIBRO A LA LISTA DE LIBROS DE LA BIBLIOTECA
                library.addBook(newBook);
                break;

            case 2:
                //Metodo que regresa la lista de libros
                menuSelectorCaseBooksMethods.ListBooks();

                break;

            case 3:
                //Metodo que actualiza un libro
                int indexBook = menuSelectorCaseBooksMethods.searchBookIsbn();
                Book updateBook = menuSelectorCaseBooksMethods.UpdateBook();
                library.updateBook(indexBook, updateBook);
                break;
            case 4:
                //Metodo que elimina un libro
                menuSelectorCaseBooksMethods.DeleteBook();
                break;

            case 5:
                menuSelectorCaseAuthorMethods.addFirstNameAuthorMethodCase();
                break;
            case 12:

                break;

            default:
                System.out.println("Ingresa una opcion valida!");

        }
    }
}
