package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;

import java.util.List;

import static com.ebac.proyectoJava.MenuSelector.library;
import static com.ebac.proyectoJava.MenuSelector.scanner;

public class MenuSelectorCaseBooksMethods {


    //METODO QUE COMPRUEBA SI EL VALOR INGRESADO ES UN STRING
    public static boolean isNumber(String value) {
        boolean resultado;
        try {
            Integer.parseInt(value);
            resultado = true;
        } catch (NumberFormatException e) {
            resultado = false;
        }

        return resultado;
    }

    // METODOS PARA AGREGAR UN NUEVO LIBRO A LA BIBLIOTECA
    public static void createNewBook() {
        System.out.println("---- AGREGAR LIBRO A LA BIBLIOTECA-----");
        //NOMBRE DEL LIBRO
        String bookName;
        System.out.print("Ingresa el nombre del libro: ");
        bookName = scanner.nextLine().toLowerCase();

        //NOMBRE DEL AUTOR
        String authorName;
        do {
            System.out.print("Ingresa el nombre del autor: ");
            authorName = scanner.nextLine().toLowerCase();

            if (isNumber(authorName)) {
                System.out.println("Debes ingresar un nombre correcto");
            }
        } while (isNumber(authorName));

        //AÑO DEL LANZAMIENTO
        String releaseYear;
        do {
            System.out.print("Ingresa el año de lanzamiento: ");
            releaseYear = scanner.nextLine();

            if (!releaseYear.matches("\\d{4}")) {
                System.out.println("Deben ser cuatro valores de tipo numerico");
            }
        } while (!releaseYear.matches("\\d{4}"));

        //CODIGO ISBN DEL LIBRO
        String isbnCode = "";
        do {
            System.out.print("Ingresa el codigo ISBN del Libro: ");
            isbnCode = scanner.nextLine();

            if (!isbnCode.matches("\\d{4}")) {
                System.out.println("Deben ser cuatro valores de tipo numerico");
            }

        } while (!isbnCode.matches("\\d{4}"));

        library.addBook(new Book(bookName, authorName, Integer.parseInt(releaseYear), isbnCode));

        System.out.println("--------------------");
        System.out.println("---LIBRO AGREGADO---");
        System.out.println("--------------------");

        scanner.nextLine();
    }

    //METODO QUE NOS MUESTRA LA LISTA DE LIBROS ACTUALES
    public static void listBooks() {
        System.out.println("***** Lista de libros Existentes *****");
        List<Book> listaLibros = library.getBookList();
        listaLibros.forEach(System.out::println);
        System.out.println("Hay " + listaLibros.size() + " libros en la biblioteca");
        scanner.nextLine();
    }

    // METODO QUE ACTUALIZAR LOS LIBROS //
    public static void updateBook() throws ExcepcionesPropias {
        System.out.println("***** Actualizar un libro *****");

        System.out.println("*** Lista de Libros Existentes ***");
        List<Book> bookList = library.getBookList();
        bookList.forEach(System.out::println);

        System.out.println("Ingresa el ISBN del libro a modificar: ");
        String codigoISBN;
        codigoISBN = scanner.nextLine();

        try {
            Book existBook = bookList.stream().filter(book -> book.getIsbnCode().equalsIgnoreCase(codigoISBN))
                    .findAny()
                    .orElseThrow(() -> new ExcepcionesPropias("Libro no registrado"));

            int indexBook = library.getBookList().indexOf(existBook);
            Book bookInList = library.getBookList().get(indexBook);
            System.out.println("Libro a editar: " + existBook);
            String respuestaEditar = "";

            //DECIDE SI SE CAMBIARA EL NOMBRE DEL LIBRO
            String bookNameOg = bookInList.getTitleBook();
            System.out.println("Nombre del libro actual: " + bookNameOg);

            do {
                System.out.println("Deseas cambiar el nombre del libro?: ");
                try {
                    respuestaEditar = scanner.nextLine().toLowerCase();
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("Debes ingresar si o no para continuar");
                    }
                    break;
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String bookRename;
                    while (true) {
                        try {
                            System.out.print("Ingresa el nombre del Libro: ");
                            bookRename = scanner.nextLine().toLowerCase();
                            if (isNumber(bookRename)) {
                                throw new ExcepcionesPropias("No se permiten numeros. Ingresa tu nombre");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bookInList.setTitleBook(bookRename);
                    System.out.println("----Campo Actualizado----");
                    break;
                case "no":
                    bookInList.setTitleBook(bookNameOg);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa una respuesta correcta");
                    break;
            }

            //DECIDE SI SE CAMBIARA EL NOMBRE DEL AUTOR
            String authorNameOg = bookInList.getAuthorBook();
            System.out.println("Nombre del autor actual: " + authorNameOg);

            do {
                System.out.println("Deseas cambiar el nombre del autor?: ");
                try {
                    respuestaEditar = scanner.nextLine().toLowerCase();
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("Debes ingresar si o no para continuar");
                    }
                    break;
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String authorRename;
                    while (true) {
                        try {
                            System.out.print("Ingresa el nombre del autor: ");
                            authorRename = scanner.nextLine().toLowerCase();
                            if (isNumber(authorRename)) {
                                throw new ExcepcionesPropias("No se permiten numeros. Ingresa tu nombre");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bookInList.setAuthorBook(authorRename);
                    System.out.println("----Campo Actualizado----");
                    break;
                case "no":
                    bookInList.setAuthorBook(authorNameOg);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa una respuesta correcta");
            }


            //DECIDE SI SE CAMBIARA EL AÑO DE LANZAMIENTO
            int releaseYearOg = bookInList.getReleaseYear();
            System.out.println("Año de lanzamiento actual: " + releaseYearOg);

            do {
                System.out.println("Deseas cambiar el año de lanzamiento?: ");
                try {
                    respuestaEditar = scanner.nextLine().toLowerCase();
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("Debes ingresar si o no para continuar");
                    }
                    break;
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String releaseYearRename;
                    while (true) {
                        try {
                            System.out.print("Ingresa el año de lanzamiento: ");
                            releaseYearRename = scanner.nextLine();
                            if (!isNumber(releaseYearRename)) {
                                throw new ExcepcionesPropias("Ingresa el año de lanzamiento correcto");
                                //scanner.nextLine();
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bookInList.setReleaseYear(Integer.parseInt(releaseYearRename));
                    System.out.println("----Campo Actualizado----");
                    System.out.println("---------------------------");
                    System.out.println("---- LIBRO ACTUALIZADO ----");
                    System.out.println("---------------------------");
                    break;
                case "no":
                    bookInList.setReleaseYear(releaseYearOg);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa una respuesta correcta");
            }

            //DECIDE SI SE CAMBIARA EL ISBN DEL LIBRO
            String isbnCodeOg = bookInList.getIsbnCode();
            System.out.println("Codigo ISBN actual: " + isbnCodeOg);

            do {
                System.out.println("Deseas cambiar el Codigo ISBN?: ");
                try {
                    respuestaEditar = scanner.nextLine().toLowerCase();
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("Debes ingresar si o no para continuar");
                    }
                    break;
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String isbnCodeRename;
                    while (true) {
                        try {
                            System.out.print("Ingresa el codigo ISBN: ");
                            isbnCodeRename = scanner.nextLine();
                            if (!isNumber(isbnCodeRename)) {
                                throw new ExcepcionesPropias("Ingresa un Codigo ISBN valido");
                                //scanner.nextLine();
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    bookInList.setIsbnCode(isbnCodeRename);
                    System.out.println("----Campo Actualizado----");
                    break;
                case "no":
                    bookInList.setIsbnCode(isbnCodeOg);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa una respuesta correcta");
            }

        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();

    }

    //METODO PARA ELIMINAR UN ELEMENTO
    public static void deleteBook() {
        System.out.println("***** Eliminar Libros *****");
        System.out.println("***** Lista de libros Existentes *****");
        library.getBookList().forEach(System.out::println);
        System.out.println("----- Ingresa el Codigo ESBN del libro a eliminar -----");
        String isbnCode = "";
        isbnCode = scanner.nextLine();
        try {
            library.deletedBook(isbnCode);
            System.out.println("------------------");
            System.out.println("--Autor Eliminado-");
            System.out.println("------------------");
            System.out.println("---- LIBRERIA ACTUALIZADA ----");
        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }

        scanner.nextLine();
    }

}
