package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Author;
import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;
import static com.ebac.proyectoJava.MenuSelector.library;
import static com.ebac.proyectoJava.MenuSelector.scanner;

import java.util.ArrayList;
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

    public static String addFirstNameAuthorMethodCase() {
        String firtsName = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingresa el nombre del autor: ");
            firtsName = scanner.nextLine();
            try {
                if (isNumber(firtsName)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa un nombre correcto");
                }

                break;
            } catch (ExcepcionesPropias e) {
                System.out.println("Error " + e.getMessage());
            }
        }

        //scanner.close();
        return firtsName;

    }

    public static String addBiographyAuthorMethodCase() throws ExcepcionesPropias {
        String biography = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Escribe su biografia: ");
            biography = scanner.nextLine();

            try {
                if (isNumber(biography)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa su biografia");
                }
                break;
            } catch (ExcepcionesPropias e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        //scanner.close();
        return biography;
    }

    public static List<Book> addBookListAuthorMethodCase() {

        System.out.println("---- AGREGAR LIBRO AL AUTOR -----");
        //Book newBook = MenuSelectorCaseBooksMethods.NewBook();
        List<Book> listBooksAuhtor = new ArrayList<>();
        //Scanner scanner1 = new Scanner(System.in);

        //String newName = scanner.next();
        //NOMBRE DEL LIBRO
        String bookName;
        System.out.print("Ingresa el nombre del libro: ");
        bookName = scanner.nextLine();

        //NOMBRE DEL AUTOR
        String authorName;
        do {
            System.out.print("Ingresa el nombre del autor: ");
            authorName = scanner.nextLine();

            if (isNumber(authorName)) {
                System.out.println("Debes ingresar un nombre correcto");
            }
        } while (isNumber(authorName));

        //AÑO DEL LANZAMIENTO
        System.out.print("Ingresa el año de lanzamiento: ");
        int releaseYear;
        while (!scanner.hasNextInt()) {
            System.out.println("Ingresa un formato numerico");
            System.out.print("Ingresa el año de lanzamiento: ");
            scanner.next();
        }
        releaseYear = scanner.nextInt();

        //CODIGO ISBN DEL LIBRO
        String isbnCode = "";
        do {
            System.out.print("Ingresa el ISBN del Libro: ");
            isbnCode = scanner.next();

            if (!isbnCode.matches("\\d{4}")) {
                System.out.println("Deben ser cuatro valores de tipo numerico");
            }

        } while (!isbnCode.matches("\\d{4}"));



        listBooksAuhtor.add(new Book(bookName, authorName, releaseYear, isbnCode));
        return  listBooksAuhtor;

    }

    public static Author newAuthor() throws ExcepcionesPropias {
        return new Author(addFirstNameAuthorMethodCase(),addBiographyAuthorMethodCase(), addBookListAuthorMethodCase());
    }

    public static int searchAuthorName() {
        int indexAuhtor = -0;
        String nameAuthor;

        System.out.println("***** Actualizar Autores *****");
        System.out.println("Lista de Autores Existentes");
        List<Author> authorList = library.getAuthorList();
        authorList.forEach(System.out::println);

        System.out.println("Ingresa el nombre del autor a editar");
        nameAuthor = scanner.nextLine();

        try {
            Author getAuthor = library.updateAuthorForName(nameAuthor);
            indexAuhtor = authorList.indexOf(getAuthor);
            System.out.println("Libro a editar: " + getAuthor);
        } catch (ExcepcionesPropias e){
            System.out.println("Error: " + e.getMessage());
        }


        return indexAuhtor;

    }
    public static Author UpdateAuthor() throws ExcepcionesPropias {
        return new Author(addFirstNameAuthorMethodCase(), addBiographyAuthorMethodCase(), addBookListAuthorMethodCase());
    }

    public static void ListAuthors(){
        System.out.println("***** Lista de Autores Existentes *****");
        List<Author> listaAutores = library.getAuthorList();
        listaAutores.forEach(System.out::println);
        System.out.println("Hay " + listaAutores.size() + " Autores en la biblioteca");
    }

    public static void deleteAuthor() {
        System.out.println("***** Eliminar un autor *****");
        System.out.println("*** Lista de Autores Existente ***");
        library.getAuthorList().forEach(System.out::println);
        System.out.println("Ingresa el nombre del autor a eliminar");
        String authorName = "";
        authorName = scanner.nextLine();

        try {
            library.deletedAuthor(authorName);
        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }

    }
}
