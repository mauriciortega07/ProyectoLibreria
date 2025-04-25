package com.ebac.proyectoJava;

import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseAuthorMethods;
import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseBooksMethods;
import com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseUsersMethods;

import java.util.*;

import static com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuOptionsTags.menuOptions;
import static com.ebac.proyectoJava.MenuSelectorCaseMethods.MenuSelectorCaseUsersMethods.isNumber;

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
                //Variable que contiene un libro nuevo a agregar
                Book newBook = MenuSelectorCaseBooksMethods.NewBook();

                //METODO QUE AGREGA LIBRO A LA LISTA DE LIBROS DE LA BIBLIOTECA
                library.addBook(newBook);
                break;

            case 2:
                //Metodo que regresa la lista de libros
                MenuSelectorCaseBooksMethods.ListBooks();
                break;

            case 3:
                //Metodo que actualiza un libro
                int indexBook = MenuSelectorCaseBooksMethods.searchBookIsbn();
                Book updateBook = MenuSelectorCaseBooksMethods.UpdateBook();
                library.updateBook(indexBook, updateBook);
                break;

            case 4:
                //Metodo que elimina un libro
                MenuSelectorCaseBooksMethods.DeleteBook();
                break;

            case 5:
                //Metodo que Agrega un autor
                library.addAuthor(MenuSelectorCaseAuthorMethods.newAuthor());
                break;

            case 6:
                //Metodo que regresa la lista de autores
                MenuSelectorCaseAuthorMethods.ListAuthors();
                break;

            case 7:
                //Metodo que actualiza un autor
                int indexAutor = MenuSelectorCaseAuthorMethods.searchAuthorName();
                Author updateAutor = MenuSelectorCaseAuthorMethods.UpdateAuthor();
                library.updateAuthor(indexAutor, updateAutor);
                break;

            case 8:
                //Metodo que elimina un author
                MenuSelectorCaseAuthorMethods.deleteAuthor();
                break;

            case 9:
                //Metodo para agregar un usuario

                //Usename
                String userName;

                while (true) {
                    try {
                        System.out.println("Ingresa un nombre de usuario: ");
                        userName = scanner.nextLine();

                        if (isNumber(userName)) {
                            throw new ExcepcionesPropias("No se permiten numeros. Ingresa tu nombre completo");
                        }
                        break;

                    } catch (ExcepcionesPropias e) {
                        System.out.println(e.getMessage());
                    }
                }

                //email
                String email;
                while (true) {
                    System.out.println("Ingresa tu email");
                    email = scanner.nextLine();
                    try {
                        if (!MenuSelectorCaseUsersMethods.isValidEmail(email)) {
                            throw new ExcepcionesPropias("Ingresa una direccion de correo correcta");
                        }
                        break;
                    } catch (ExcepcionesPropias e) {
                        System.out.println(e.getMessage());
                    }
                }


                //password
                String password;
                while (true) {
                    System.out.println("Ingresa tu password");
                    System.out.println("Debe tener al menos una letra mayuscula y minuscula\nAl menos un numero y 8 caracteres");
                    password = scanner.nextLine();
                    try {
                        if (!MenuSelectorCaseUsersMethods.isValidPassword(password)) {
                            throw new ExcepcionesPropias("Ingresa una contraseña correcta");
                        }
                        break;
                    } catch (ExcepcionesPropias e) {
                        System.out.println(e.getMessage());
                    }
                }


                //lista de libros
                String respuesta = "";
                do {
                    System.out.println("Quieres pedir prestado un libro?");
                    try {
                        respuesta = scanner.nextLine();
                        if (isNumber(respuesta)) {
                            throw new ExcepcionesPropias("Ingresa si o no para continuar");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (isNumber(respuesta));

                List<Book> borrowedBooks = new ArrayList<>();

                switch (respuesta) {
                    case "si":
                        String cantidadLibros;
                        while (true) {
                            try {
                                System.out.println("Cantidad de libros a rentar:");
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

                            System.out.println("---- AGREGAR LIBRO A LA BIBLIOTECA -----");
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

                            String releaseYear;
                            do {
                                System.out.print("Ingresa el año de lanzamiento: ");
                                releaseYear = scanner.nextLine();

                                if (!releaseYear.matches("\\d{4}")){
                                    System.out.println("Deben ser cuatro valores de tipo numerico");
                                }
                            }while (!releaseYear.matches("\\d{4}"));

                            //CODIGO ISBN DEL LIBRO
                            String isbnCode = "";
                            do {
                                System.out.print("Ingresa el ISBN del Libro: ");
                                isbnCode = scanner.next();

                                if (!isbnCode.matches("\\d{4}")) {
                                    System.out.println("Deben ser cuatro valores de tipo numerico");
                                }

                            } while (!isbnCode.matches("\\d{4}"));
                            scanner.nextLine();

                            borrowedBooks.add(new Book(bookName, authorName, Integer.parseInt(releaseYear), isbnCode));
                        }
                        library.addUser(new User(userName, email, password, borrowedBooks));
                        System.out.println("USUARIO CREADO");
                        break;

                    case "no":
                        library.addUser(new User(userName.toLowerCase(), email, password, borrowedBooks));
                        System.out.println("USUARIO CREADO");
                        break;
                    default:
                        System.out.println("Ingresa si o no, para continuar");
                }
                break;
            case 10:
                MenuSelectorCaseUsersMethods.ListUsers();

                break;

            case 11:
                System.out.println("***** Editar Usuarios Existentes *****");
                System.out.println("*** Lista de Usuarios Existentes ***");
                List<User> listaUsuarios = library.getUserList();
                listaUsuarios.forEach(System.out::println);

                System.out.println("Ingresa el nombre del usuario a editar: ");
                String username;
                username = scanner.nextLine();

                try {

                    User existUser = listaUsuarios.stream().filter(user -> user.getUsername().equalsIgnoreCase(username))
                            .findAny()
                            .orElseThrow(() -> new ExcepcionesPropias("Este usuario no esta registrado"));

                    int indexUser = library.getUserList().indexOf(existUser);
                    System.out.println("Usuario a editar: " + existUser);
                    String respuestaEditar = "";

                    //DECIDE SI EL NOMBRE DE USUARIO SE EDITARA
                    String userNameOg = library.getUserList().get(indexUser).getUsername();
                    System.out.println("Nombre Actual: " + userNameOg);

                    do {
                        System.out.println("¿Deseas cambiar tu nombre de usuario?: ");
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

                    //respuestaEditar = scanner.nextLine().toLowerCase();

                    switch (respuestaEditar) {
                        case "si":
                            String userRename;
                            while (true) {
                                try {
                                    System.out.println("Ingresa tu nuevo nombre de usuario: ");
                                    userRename = scanner.nextLine();
                                    if (isNumber(userRename)) {
                                        throw new ExcepcionesPropias("No se permiten numeros. Ingresa tu nombre completo");
                                    }
                                    break;
                                } catch (ExcepcionesPropias e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            library.getUserList().get(indexUser).setUsername(userRename);
                            break;


                        case "no":
                            library.getUserList().get(indexUser).setUsername(userNameOg);
                            break;

                        default:
                            System.out.println("Ingresa si o no para continuar");
                    }

                    //DECIDE SI EL EMAIL DE USUARIO SE EDITARA

                    String userEmailOg = library.getUserList().get(indexUser).getEmail();
                    System.out.println("Email Actual: " + userEmailOg);

                    do {
                        System.out.println("¿Deseas cambiar tu email?: ");
                        try {
                            respuestaEditar = scanner.nextLine();
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
                            String userReemail;

                            do {
                                System.out.println("Ingresa la nueva direccion de correo: ");
                                userReemail = scanner.nextLine();
                                try {
                                    if (!MenuSelectorCaseUsersMethods.isValidEmail(userReemail)) {
                                        throw new ExcepcionesPropias("Ingresa un direccion de correo valida");
                                    }
                                } catch (ExcepcionesPropias e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (!MenuSelectorCaseUsersMethods.isValidEmail(userReemail));

                            library.getUserList().get(indexUser).setEmail(userReemail);
                            break;

                        case "no":
                            library.getUserList().get(indexUser).setEmail(userEmailOg);
                            break;
                        default:
                            System.out.println("Ingresa si o no para continuar");
                    }

                    //DECIDE SI EL PASS DE USUARIO SE EDITARA
                    String userPassOg = library.getUserList().get(indexUser).getPassword();
                    System.out.println("Password Actual: " + userPassOg);

                    do {
                        System.out.println("¿Deseas cambiar tu contrasena?: ");
                        try {
                            respuestaEditar = scanner.nextLine();
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
                            String userRepass;

                            do {
                                System.out.println("Ingresa la nueva contraseña: ");
                                userRepass = scanner.nextLine();
                                try {
                                    if (!MenuSelectorCaseUsersMethods.isValidPassword(userRepass)) {
                                        throw new ExcepcionesPropias("Ingresa un constraseña valida");
                                    }
                                } catch (ExcepcionesPropias e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (!MenuSelectorCaseUsersMethods.isValidPassword(userRepass));

                            library.getUserList().get(indexUser).setEmail(userRepass);
                            break;

                        case "no":
                            library.getUserList().get(indexUser).setEmail(userPassOg);
                            break;
                        default:
                            System.out.println("Ingresa si o no para continuar");
                    }

                    //DECIDE SI SE RENTARAN MAS LIBROS SE EDITARA
                    List<Book> userBorrowedListOg = library.getUserList().get(indexUser).getBorrowedBooks();
                    System.out.print("Libros Rentados: ");
                    userBorrowedListOg.forEach(System.out::println);

                    do {
                        System.out.println("¿Deseas rentar mas libros?");
                        try {
                            respuestaEditar = scanner.nextLine();
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
                                    System.out.println("Cantidad de libros a rentar:");
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

                                System.out.println("---- AGREGAR LIBRO A LA RENTA -----");
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

                                String releaseYear;
                                do {
                                    System.out.print("Ingresa el año de lanzamiento: ");
                                    releaseYear = scanner.nextLine();

                                    if (!releaseYear.matches("\\d{4}")){
                                        System.out.println("Deben ser cuatro valores de tipo numerico");
                                    }
                                }while (!releaseYear.matches("\\d{4}"));


                                //CODIGO ISBN DEL LIBRO
                                String isbnCode = "";
                                do {
                                    System.out.print("Ingresa el ISBN del Libro: ");
                                    isbnCode = scanner.nextLine();

                                    if (!isbnCode.matches("\\d{4}")) {
                                        System.out.println("Deben ser cuatro valores de tipo numerico");
                                    }

                                } while (!isbnCode.matches("\\d{4}"));
                                //scanner.nextLine();

                                library.getUserList().get(indexUser).getBorrowedBooks().add(new Book(bookName, authorName, Integer.parseInt(releaseYear), isbnCode));
                            }
                            System.out.println("Libros Rentados Actulizados");
                            break;

                        case "no":
                            System.out.println("No se reciben cambios en los libros rentados");
                            break;

                        default:
                            System.out.println("Debes ingresar si o no para continuar");
                    }


                } catch (ExcepcionesPropias e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }

                System.out.println("Usuario Actualizado");

                break;

            case 12:
                System.out.println("***** Eliminar un usuario *****");
                System.out.println("Lista de usuarios registrados en la biblioteca");
                library.getUserList().forEach(System.out::println);
                System.out.println("Hay " + library.getUserList().size() + " usuarios registrados");
                System.out.println("Ingresa el nombre del usuario a eliminar");
                String nameUserDelete = scanner.nextLine().toLowerCase();
                library.deletedUser(nameUserDelete);
                break;
            case 13:
                break;

            default:
                System.out.println("Ingresa una opcion valida!");

        }
    }
}
