package com.ebac.proyectoJava.MenuSelectorCaseMethods;

import com.ebac.proyectoJava.Book;
import com.ebac.proyectoJava.ExcepcionesPropias;
import com.ebac.proyectoJava.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ebac.proyectoJava.MenuSelector.library;
import static com.ebac.proyectoJava.MenuSelector.scanner;

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

    public static void CreateUser() {
        //username
        String userName;
        while (true) {
            try {
                System.out.println("Ingresa un nombre de usuario: ");
                userName = scanner.nextLine().toLowerCase();

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
                respuesta = scanner.nextLine().toLowerCase();
                if (isNumber(respuesta)) {
                    throw new ExcepcionesPropias("Ingresa si o no para continuar");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (isNumber(respuesta));

        //LISTA DE LIBROS RENTADOS
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

                    System.out.println("---- AGREGAR LIBRO AL USUARIO -----");
                    //NOMBRE DEL LIBRO
                    String bookName;
                    System.out.println("Ingresa el nombre del libro " + (i + 1) + " :");
                    bookName = scanner.nextLine().toLowerCase();

                    //NOMBRE DEL AUTOR
                    String authorName;
                    do {
                        System.out.println("Ingresa el nombre del autor: ");
                        authorName = scanner.nextLine().toLowerCase();

                        if (isNumber(authorName)) {
                            System.out.println("Debes ingresar un nombre correcto");
                        }
                    } while (isNumber(authorName));

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
                        System.out.println("Ingresa el codigo ISBN del Libro: ");
                        isbnCode = scanner.next();

                        if (!isbnCode.matches("\\d{4}")) {
                            System.out.println("Deben ser cuatro valores de tipo numerico");
                        }

                    } while (!isbnCode.matches("\\d{4}"));
                    scanner.nextLine();

                    borrowedBooks.add(new Book(bookName, authorName, Integer.parseInt(releaseYear), isbnCode));
                }
                library.addUser(new User(userName.toLowerCase(), email, password, borrowedBooks));
                break;

            case "no":
                library.addUser(new User(userName.toLowerCase(), email, password, borrowedBooks));

                break;
            default:
                System.out.println("Ingresa si o no, para continuar");
        }
        scanner.nextLine();
    }

    public static void ListUsers() {
        System.out.println("***** Lista de Usuarios Existentes *****");
        List<User> listaUsuarios = library.getUserList();
        listaUsuarios.forEach(System.out::println);
        System.out.println("Hay " + listaUsuarios.size() + " usuarios registrados en la biblioteca");

        scanner.nextLine();
    }

    public static void UpdateUser() {
        System.out.println("***** Actualizar Usuarios Existentes *****");
        System.out.println("*** Lista de Usuarios Existentes ***");
        List<User> listaUsuarios = library.getUserList();
        listaUsuarios.forEach(System.out::println);

        System.out.println("Ingresa el nombre del usuario a editar: ");
        String username;
        username = scanner.nextLine().toLowerCase();

        try {

            User existUser = listaUsuarios.stream().filter(user -> user.getUsername().equalsIgnoreCase(username))
                    .findAny()
                    .orElseThrow(() -> new ExcepcionesPropias("Este usuario no esta registrado"));

            int indexUser = library.getUserList().indexOf(existUser);
            System.out.println("Usuario a editar: " + existUser);
            String respuestaEditar = "";

            //AQUI NOS QUEDAMOS
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
                            userRename = scanner.nextLine().toLowerCase();
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
                    String userReemail;

                    do {
                        System.out.println("Ingresa la nueva direccion de correo: ");
                        userReemail = scanner.nextLine();
                        try {
                            if (!MenuSelectorCaseUsersMethods.isValidEmail(userReemail)) {
                                throw new ExcepcionesPropias("Ingresa un direccion de correo valida");
                            }
                            break;
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

                    library.getUserList().get(indexUser).setPassword(userRepass);
                    break;

                case "no":
                    library.getUserList().get(indexUser).setPassword(userPassOg);
                    break;
                default:
                    System.out.println("Ingresa si o no para continuar");
            }

            //DECIDE SI SE RENTARAN MAS LIBROS SE EDITARA
            List<Book> userBorrowedListOg = library.getUserList().get(indexUser).getBorrowedBooks();
            System.out.println("Libros Rentados: ");
            userBorrowedListOg.forEach(System.out::println);

            do {
                System.out.println("¿Deseas rentar mas libros?");
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
                        System.out.println("Ingresa el nombre del libro: " + (i+1) + " : ");
                        bookName = scanner.nextLine().toLowerCase();

                        //NOMBRE DEL AUTOR
                        String authorName;
                        do {
                            System.out.println("Ingresa el nombre del autor: ");
                            authorName = scanner.nextLine().toLowerCase();

                            if (isNumber(authorName)) {
                                System.out.println("Debes ingresar un nombre correcto");
                            }
                        } while (isNumber(authorName));

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

                        library.getUserList().get(indexUser).getBorrowedBooks().add(new Book(bookName, authorName, Integer.parseInt(releaseYear), isbnCode));
                    }
                    System.out.println("---------------------------");
                    System.out.println("LIBROS RENTADOS ACTUALIZADOS");
                    System.out.println("---------------------------");

                    System.out.println("---------------------------");
                    System.out.println("-----USUARIO ACTUALIZADO---");
                    System.out.println("---------------------------");

                    break;

                case "no":
                    System.out.println("--------------------------------------------");
                    System.out.println("No se reciben cambios en los libros rentados");
                    System.out.println("--------------------------------------------");


                    break;

                default:
                    System.out.println("Debes ingresar si o no para continuar");
            }


        } catch (ExcepcionesPropias e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }

        scanner.nextLine();
    }

    public static void DeleteUser() {
        System.out.println("***** Eliminar un usuario *****");

            try {
                if (library.getUserList().isEmpty()) {
                    throw new ExcepcionesPropias("La Lista esta VACIA!!");
                } else {
                    System.out.println("Lista de usuarios registrados en la biblioteca");
                    library.getUserList().forEach(System.out::println);
                    System.out.println("Hay " + library.getUserList().size() + " usuarios registrados");
                    System.out.println("Ingresa el nombre del usuario a eliminar");
                    String nameUserDelete = scanner.nextLine().toLowerCase();
                    library.deletedUser(nameUserDelete);
                }
                //break;
            } catch (ExcepcionesPropias e){
                System.out.println(e.getMessage());
            }

        scanner.nextLine();

    }

}
