package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Author;
import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;

import static com.ebac.proyectoJava.MenuSelector.library;
import static com.ebac.proyectoJava.MenuSelector.scanner;

import java.util.ArrayList;
import java.util.List;

public class MenuSelectorCaseAuthorMethods {

    public static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static void createAuthor() {
        System.out.println("---- AGREGAR AUTOR A LA BIBLIOTECA-----");
        //Agrega Nombre Completo de autor
        String authorName = "";
        while (true) {
            System.out.println("Ingresa el nombre del autor: ");
            authorName = scanner.nextLine().toLowerCase();
            try {
                if (isNumber(authorName)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa un nombre correcto");
                }
                break;
            } catch (ExcepcionesPropias e) {
                System.out.println(e.getMessage());
            }
        }

        //Agrega la biografia
        String biography = "";
        while (true) {
            System.out.println("Escribe su biografia: ");
            biography = scanner.nextLine().toLowerCase();

            try {
                if (isNumber(biography)) {
                    throw new ExcepcionesPropias("No se permiten numeros. Ingresa su biografia");
                }
                break;
            } catch (ExcepcionesPropias e) {
                System.out.println(e.getMessage());
            }
        }

        //DECIDE SI AGREGAR LIBROS AL AUTOR EN DURANTE SU REGISTRO
        String respuesta = "";
        do {
            System.out.println("Deseas agregar libros a este autor? (Puedes hacerlo despues en la seccion de editar autor): ");
            try {
                respuesta = scanner.nextLine().toLowerCase();
                if (isNumber(respuesta)) {
                    throw new ExcepcionesPropias("Ingresa si o no para continuar");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (isNumber(respuesta));

        //CREA LA LISTA DE LIBROS PUBLICADOS
        List<Book> booksPublished = new ArrayList<>();
        switch (respuesta) {
            case "si":
                String cantidaLibros;
                while (true) {
                    try {
                        System.out.println("Ingresa la cantidad de libros para esta autor: ");
                        cantidaLibros = scanner.nextLine();
                        if (!isNumber(cantidaLibros)) {
                            throw new ExcepcionesPropias("Ingresa una cantidad numerica correcta");
                        }
                        break;
                    } catch (ExcepcionesPropias e) {
                        System.out.println(e.getMessage());
                    }
                }

                for (int i = 0; i < Integer.parseInt(cantidaLibros); i++) {

                    System.out.println("---- AGREGAR LIBRO AL AUTOR ---");
                    //TOMA EL NOMBRE DEL LIBRO A AGREGAR A LA LISTA DE LIBROS PUBLICADOS
                    String bookname;
                    System.out.println("Ingresa el nombre del libro " + (i+1) + " : ");
                    bookname = scanner.nextLine().toLowerCase();

                    //TOMA EL AÑO DE PUBLICACION
                    String releaseYear;
                    do {
                        System.out.println("Ingresa el año de lanzamiento: ");
                        releaseYear = scanner.nextLine();
                        try {
                            if (!releaseYear.matches("\\d{4}")) {
                                throw new ExcepcionesPropias("Debe ser el año del lanzamiento");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);

                    //TOMA EL CODIGO ISBN DEL LIBRO
                    String isbnCode = "";
                    do {
                        System.out.println("Ingresa el codigo ISBN del libro: ");
                        isbnCode = scanner.nextLine();
                        try {
                            if (!isbnCode.matches("\\d{4}")) {
                                throw new ExcepcionesPropias("El codigo ISBN debe ser de 4 digitos");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!isbnCode.matches("\\d{4}"));

                    booksPublished.add(new Book(bookname, authorName, Integer.parseInt(releaseYear), isbnCode));
                }

                //AQUI SE AGREGA EL AUTOR CREADO Y SE AGREGA A LA LISTA DE AUTORES
                library.addAuthor(new Author(authorName, biography, booksPublished));

                break;

            case "no":
                /*AQUI SE AGREGA EL AUTOR CREADO SIN LIBROS EN SU
                LISTA DE LIBROS PUBLICADOS Y SE AGREGA A LA LISTA DE AUTORES*/

                library.addAuthor(new Author(authorName, biography, booksPublished));

                break;
            default:
                System.out.println("Ingresa si o no para continuar");
        }
        scanner.nextLine();
    }

    public static void updateAuthor() {
        System.out.println("***** Actualizar Autores *****");
        System.out.println("Lista de Autores Existentes");
        List<Author> authorList = library.getAuthorList();
        authorList.forEach(System.out::println);

        System.out.println("Ingresa el nombre del autor a editar: ");
        String nameAuthor;
        nameAuthor = scanner.nextLine().toLowerCase();

        try {

            Author existAuthor = authorList.stream().filter(author -> author.getNameComplete().toLowerCase().equalsIgnoreCase(nameAuthor))
                    .findAny()
                    .orElseThrow(() -> new ExcepcionesPropias("Autor no encontrado"));

            int indexAuhtor = library.getAuthorList().indexOf(existAuthor);
            Author authorInList = library.getAuthorList().get(indexAuhtor);
            System.out.println("Autor a editar: " + existAuthor);
            String respuestaEditar = "";

            //DECIDE SI SE CAMBIARA EL NOMBRE DEL AUTOR
            String authorNameOg = authorInList.getNameComplete();
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
                            System.out.println("Ingresa el nombre del autor: ");
                            authorRename = scanner.nextLine().toLowerCase();
                            if (isNumber(authorRename)) {
                                throw new ExcepcionesPropias("No se permiten numeros. Ingresa tu nombre");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    authorInList.setNameComplete(authorRename);
                    System.out.println("----Campo Actualizado----");
                    break;
                case "no":
                    authorInList.setNameComplete(authorNameOg);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa una respuesta correcta");
            }

            //DECIDE SI SE EDITARA LA BIOGRAFIA DEL AUTOR
            String authorBiographyOG = authorInList.getBiography();
            System.out.println("Biografia actual: " + authorBiographyOG);
            do {
                System.out.println("Deseas hacer cambios en su biografia?: ");
                respuestaEditar = scanner.nextLine().toLowerCase();
                try {
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("No ingreses numeros como inciales, escribe su biografia");
                    }
                    break;
                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String authorRebiography = "";
                    do {
                        System.out.println("Escribe la nueva biografia: ");
                        authorRebiography = scanner.nextLine().toLowerCase();
                        try {
                            if (isNumber(authorRebiography)) {
                                throw new ExcepcionesPropias("No ingreses numeros como iniciales, escribe bien su biografia");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    } while (isNumber(authorRebiography));

                    authorInList.setBiography(authorRebiography);
                    System.out.println("----Campo Actualizado----");
                    break;
                case "no":
                    authorInList.setBiography(authorBiographyOG);
                    System.out.println("----Campo Sin Cambios----");
                    break;
                default:
                    System.out.println("Ingresa si o no para continuar");
            }

            //DECIDE SI EDITARA LOS LIBROS EN LA LISTA DE PUBLICADOS DEL AUTOR
            List<Book> autorPublishedBooksOg = authorInList.getBooksPublished();
            System.out.println("Libros Publicados por el autor: ");
            if (autorPublishedBooksOg.isEmpty()) {
                System.out.println("La lista esta vacia");
            }
            autorPublishedBooksOg.forEach(System.out::println);

            do {
                System.out.println("¿Deseas registrarle un libro al autor?: ");
                try {
                    respuestaEditar = scanner.nextLine().toLowerCase();
                    if (isNumber(respuestaEditar)) {
                        throw new ExcepcionesPropias("Ingresa si o no para continuar");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (isNumber(respuestaEditar));

            switch (respuestaEditar) {
                case "si":
                    String cantidadLibros;
                    while (true) {
                        try {
                            System.out.println("Cantidad de libros a agregar: ");
                            cantidadLibros = scanner.nextLine();
                            if (!isNumber(cantidadLibros)) {
                                throw new ExcepcionesPropias("Ingresa una cantidad en numero correcta!");
                            }
                            break;
                        } catch (ExcepcionesPropias e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    for (int i = 0; i < Integer.parseInt(cantidadLibros); i++) {

                        System.out.println("---- AGREGAR LIBRO AL AUTOR -----");
                        //NOMBRE DEL LIBRO
                        String bookName;
                        System.out.println("Ingresa el nombre del libro: " + (i+1) + " : ");
                        bookName = scanner.nextLine().toLowerCase();

                        //NOMBRE DEL AUTOR
                        System.out.println("Ingresa el nombre del autor: " + authorNameOg);


                        //AÑO DEL LANZAMIENTO
                        String releaseYear;
                        do {
                            System.out.println("Ingresa el año de lanzamiento: ");
                            releaseYear = scanner.nextLine();

                            if (!releaseYear.matches("\\d{4}")){
                                System.out.println("Deben ser cuatro valores de tipo numerico");
                            }
                        }while (!releaseYear.matches("\\d{4}"));


                        //CODIGO ISBN DEL LIBRO
                        String isbnCode = "";
                        do {
                            System.out.println("Ingresa el ISBN del Libro: ");
                            isbnCode = scanner.nextLine();

                            if (!isbnCode.matches("\\d{4}")) {
                                System.out.println("Deben ser cuatro valores de tipo numerico");
                            }

                        } while (!isbnCode.matches("\\d{4}"));
                        //scanner.nextLine();

                        authorInList.getBooksPublished().add(new Book(bookName, authorNameOg, Integer.parseInt(releaseYear), isbnCode));
                        System.out.println("---------------------------");
                        System.out.println("---- AUTOR ACTUALIZADO ----");
                        System.out.println("---------------------------");
                    }
                    System.out.println("Libros Del Autor Actualizados");
                    break;

                case "no":
                    System.out.println("No se reciben cambios en los libros del autor");
                    System.out.println("-----AUTOR ACTUALIZADO-----");
                    break;
                default:
                    System.out.println("Debes ingresar si o no para continuar");
            }


        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }

        scanner.nextLine();
    }

    public static void listAuthors() {
        System.out.println("***** Lista de Autores Existentes *****");
        List<Author> listaAutores = library.getAuthorList();
        listaAutores.forEach(System.out::println);
        System.out.println("Hay " + listaAutores.size() + " Autores en la biblioteca");

        scanner.nextLine();
    }

    public static void deleteAuthor() {
        System.out.println("***** Eliminar un autor *****");
        System.out.println("*** Lista de Autores Existente ***");
        library.getAuthorList().forEach(System.out::println);
        System.out.println("Ingresa el nombre del autor a eliminar");
        String authorName = "";
        authorName = scanner.nextLine().toLowerCase();

        try {
            library.deletedAuthor(authorName);
        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
        }

        scanner.nextLine();
    }
}
