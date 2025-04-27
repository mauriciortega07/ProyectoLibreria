package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();

    //------------------- METODOS LIBROS ------------

    //METODO QUE AGREGA UN LIBRO A LA LISTA DE LIBROS
    public void addBook(Book book) {

        boolean existBookForName = bookList.stream()
                .anyMatch(newBook -> newBook.getTitleBook().equalsIgnoreCase(book.getTitleBook()));

        boolean existBookForIsbnCode = bookList.stream()
                .anyMatch(newBook -> newBook.getIsbnCode().equalsIgnoreCase(book.getIsbnCode()));


        if(!existBookForName && !existBookForIsbnCode){
            bookList.add(book);
            System.out.println("--------------------");
            System.out.println("---LIBRO AGREGADO---");
            System.out.println("--------------------");
        } else {
            System.out.println("*** ESTE LIBRO YA EXISTE ***");
        }

    }

    //METODO QUE OBTIENE LA LISTA DE LIBROS
    public List<Book> getBookList() {
        return bookList;
    }

    //METODOS PARA ELIMINAR LIBROS
    public void deletedBook(String isbnCode) throws ExcepcionesPropias {
        boolean existIsbnCode = bookList.stream().anyMatch(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode));

        if (!existIsbnCode) {
            throw new ExcepcionesPropias("Libro no econtrado " + isbnCode);
        }

        bookList.removeIf(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode));

    }

    //------------------- METODOS AUTORES ------------
    //METODO PARA AGREGAR UN AUTOR
    public void addAuthor(Author author) {
        boolean existNameCompleteAuthor = authorList.stream()
                .anyMatch(newAuthor -> newAuthor.getNameComplete().equalsIgnoreCase(author.getNameComplete()));

        try {
            if(!existNameCompleteAuthor) {
                authorList.add(author);
                System.out.println("---------------------------------");
                System.out.println("-----AUTOR CREADO COMPLETO-------");
                System.out.println("---------------------------------");

            } else if (!existNameCompleteAuthor && authorList.isEmpty()){
                System.out.println("---------------------------------");
                System.out.println("AUTOR CREADO SIN LIBROS AGREGADOS");
                System.out.println("---------------------------------");
            } else {
                throw new ExcepcionesPropias("Este autor ya existe");
            }

        } catch (ExcepcionesPropias e){
            System.out.println(e.getMessage());
        }
    }

    public List<Author> getAuthorList() {
        //preuploadAuthors();
        return authorList;
    }

    public void deletedAuthor(String authorName) throws ExcepcionesPropias {
        boolean existAuthor = authorList.stream().anyMatch(author -> author.getNameComplete().equalsIgnoreCase(authorName));
        if (!existAuthor) {
            throw new ExcepcionesPropias("Autor no registrado");
        }
        authorList.removeIf(author -> author.getNameComplete().equalsIgnoreCase(authorName));
        System.out.println("---------------------------");
        System.out.println("------AUTOR ELIMINADO------");
        System.out.println("---------------------------");

    }

    //----------- METODOS USUARIOS
    public void addUser(User user) {

        boolean existNameCompleteUser = userList.stream()
                .anyMatch(newUser -> newUser.getUsername().equalsIgnoreCase(user.getUsername()));

        try {
            if(!existNameCompleteUser) {
                userList.add(user);
                System.out.println("---------------------------------");
                System.out.println("-----AUTOR CREADO COMPLETO-------");
                System.out.println("---------------------------------");

            } else if (!existNameCompleteUser && user.getBorrowedBooks().isEmpty()) {
                System.out.println("---------------------------");
                System.out.println("-------USUARIO CREADO------");
                System.out.println("----SIN LIBROS RENTADOS----");
                System.out.println("---------------------------");
            } else {
                throw new ExcepcionesPropias("Este usuario ya existe");
            }

        } catch (ExcepcionesPropias e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getUserList() {
        return userList;
    }

    public void deletedUser(String userName) throws ExcepcionesPropias {
        Boolean existUser = userList.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(userName));

        if (!existUser) {
            throw new ExcepcionesPropias("Este usuario no esta registrado");
        }

        userList.removeIf(user -> user.getUsername().equalsIgnoreCase(userName));
        System.out.println("------------------");
        System.out.println("--Autor Eliminado-");
        System.out.println("------------------");
    }

}
