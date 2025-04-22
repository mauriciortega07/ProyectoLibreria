package com.ebac.proyectoJava;

import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseMethods;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuOptionsTags.menuOptions;

public class MenuSelector {
    static MenuSelectorCaseMethods menuSelectorCaseMethods = new MenuSelectorCaseMethods();
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
                Book newBook = menuSelectorCaseMethods.NewBook();

                //METODO QUE AGREGA LIBRO A LA LISTA DE LIBROS DE LA BIBLIOTECA
                library.addBook(newBook);
                break;

            case 2:
                //Metodo que regresa la lista de libros
                menuSelectorCaseMethods.checkListBook();

                break;

            case 3:
                //Metodo que actualiza un libro
                int indexBook = menuSelectorCaseMethods.searchBookIsbn();
                Book updateBook = menuSelectorCaseMethods.UpdateBook();
                library.updateBook(indexBook, updateBook);
                break;
            case 4:
                System.out.println("----- Ingresa el Codigo ESBN del libro a eliminar");
                String isbnCode = "";
                isbnCode = scanner.next();
                try {
                    library.deletedBook(isbnCode);
                    System.out.println("Libro Eliminado");
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }


                break;

            case 12:

                break;

            default:
                System.out.println("Ingresa una opcion valida!");

        }
    }
}
