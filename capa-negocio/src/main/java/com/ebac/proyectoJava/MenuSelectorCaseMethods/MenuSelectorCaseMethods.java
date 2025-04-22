package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;

import java.util.List;
import java.util.Scanner;

import static com.ebac.proyectoJava.MenuSelector.library;
import static com.ebac.proyectoJava.MenuSelector.scanner;

public class MenuSelectorCaseMethods {

    public boolean isString(String bookName) {
        boolean resultado;
        try {
            Integer.parseInt(bookName);
            resultado = false;
        } catch (NumberFormatException e) {
            resultado = true;
        }

        return resultado;
    }

    // METODOS PARA AGREGAR:

    //NOMBRE DEL LIBRO
    public String addBookMethodCase() {
        System.out.println("***** Agregar un Libro *****");
        //NOMBRE DEL LIBRO
        String bookName = "";
        do {
            System.out.print("Ingresa el nombre del libro: ");
            String newName = scanner.nextLine();
            bookName = newName;

            if (!isString(bookName)) {
                System.out.println("Debes ingresar un titulo correcto");
            }


        } while (!isString(bookName));

        return bookName;
    }

    //AUTOR DEL LIBRO
    public String addAuthorMethodCase() {
        String authorName;
        do {
            System.out.print("Ingresa el nombre del autor: ");
            authorName = scanner.nextLine();

            if (!isString(authorName)) {
                System.out.println("Debes ingresar un nombre correcto");
            }
        } while (!isString(authorName));

        return authorName;
    }

    //AÑO DEL LANZAMIENTO
    public int addReleaseYearMethodCase() {
        System.out.print("Ingresa el año de lanzamiento: ");
        int releaseYear;
        while (!scanner.hasNextInt()) {
            System.out.println("Ingresa un formato numerico");
            System.out.print("Ingresa el año de lanzamiento: ");
            scanner.next();
        }
        releaseYear = scanner.nextInt();

        return releaseYear;
    }

    //CODIGO ISBN DEL LIBRO
    public String addISBNCodeMethodCase() {
        String isbnCode = "";

        do {
            System.out.print("Ingresa el ISBN del Libro: ");
            isbnCode = scanner.next();

            if (!isbnCode.matches("\\d{4}")) {
                System.out.println("Deben ser cuatro valores de tipo numerico");
            }

        } while (!isbnCode.matches("\\d{4}"));

        return isbnCode;


    }


    /*METODO PARA CREAR UN LIBRO CON LOS VALORES DE LOS METODOS ANTERIORES*/
    public Book NewBook() {
        return new Book(addBookMethodCase(), addAuthorMethodCase(), addReleaseYearMethodCase(), addISBNCodeMethodCase());
    }


    //METODO QUE NOS MUESTRA LA LISTA DE LIBROS
    public void checkListBook() {
        System.out.println("***** Lista de libros Existentes *****");
        List<Book> listaLibros = library.getBookList();
        listaLibros.forEach(System.out::println);
        System.out.println("Hay " + listaLibros.size() + " libros en la biblioteca");
        System.out.println();
    }

    public int searchBookIsbn() throws ExcepcionesPropias {
        int isbnCode = -0;
        String codigo;
        System.out.println("***** Actualizar un libro *****");

        System.out.println("Lista de Libros Existentes");
        List<Book> bookList = library.getBookList();
        bookList.forEach(System.out::println);

        System.out.println("Ingresa el ISBN del libro a modificar");
        codigo = scanner.next();

        try {
            Book getBook = library.updateBookForIsbn(codigo);
            isbnCode = bookList.indexOf(getBook);
            System.out.println("Libro a editar: " + getBook);
            
        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }

        return isbnCode;
    }

    public Book UpdateBook() {

        String bookRename = "";
        do {
            System.out.print("Ingresa el nombre del libro: ");
            bookRename = scanner.next();

            if (!isString(bookRename)) {
                System.out.println("Debes ingresar un titulo correcto");
            }

        } while (!isString(bookRename));

        /// /
        String authorName;
        do {
            System.out.print("Ingresa el nombre del autor: ");
            authorName = scanner.next();

            if (!isString(authorName)) {
                System.out.println("Debes ingresar un nombre correcto");
            }
        } while (!isString(authorName));

        /// //

        System.out.print("Ingresa el año de lanzamiento: ");
        int releaseYear;
        while (!scanner.hasNextInt()) {
            System.out.println("Ingresa un formato numerico");
            System.out.print("Ingresa el año de lanzamiento: ");
            scanner.next();
        }
        releaseYear = scanner.nextInt();

        //ISBN CODE//
        String isbnCode = "";

        do {
            System.out.print("Ingresa el ISBN del Libro: ");
            isbnCode = scanner.next();

            if (!isbnCode.matches("\\d{4}")) {
                System.out.println("Deben ser cuatro valores de tipo numerico");
            }

        } while (!isbnCode.matches("\\d{4}"));


        return new Book(bookRename, authorName, releaseYear, isbnCode);

    }

}
