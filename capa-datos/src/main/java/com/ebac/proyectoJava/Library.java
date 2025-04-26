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
            System.out.println("+++++++++++++++++++");
            System.out.println("++LIBRO AGREGADO!++");
            System.out.println("+++++++++++++++++++");
        } else {
            System.out.println("*** ESTE LIBRO YA EXISTE ***");
        }

    }

    //METODO QUE OBTIENE LA LISTA DE LIBROS
    public List<Book> getBookList() {
        return bookList;
    }

    //METODOS PARA ACTUALIZAR LIBROS
    public Book updateBookForIsbn(String isbnCode) throws ExcepcionesPropias {
        return bookList.stream()
                .filter(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode))
                .findFirst()
                .orElseThrow(() -> new ExcepcionesPropias("Libro con isbn " + isbnCode + " No econtrado"));
    }

    public void updateBook(int indexBook, Book book) {
        bookList.set(indexBook, book);
        System.out.println(bookList.get(indexBook));
        System.out.println("+++++++++++++++++++");
        System.out.println("++LIBRO ACTUALIZADO!++");
        System.out.println("+++++++++++++++++++");
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
                System.out.println("+++++++++++++++++++");
                System.out.println("++AUTOR AGREGADO!++");
                System.out.println("+++++++++++++++++++");
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
        System.out.println("Autor Eliminado");

    }

    //----------- METODOS USUARIOS
    public void addUser(User user) {
        userList.add(user);
        System.out.println("Usuario Agregado!");
    }

    public List<User> getUserList() {
        return userList;
    }

    public void deletedUser(String userName) throws ExcepcionesPropias {
        Boolean existUser = userList.stream().allMatch(user -> user.getUsername().equalsIgnoreCase(userName));

        if (!existUser) {
            throw new ExcepcionesPropias("Este usuario no esta registrado");
        }

        userList.removeIf(user -> user.getUsername().equalsIgnoreCase(userName));
        System.out.println("Autor Eliminado");
    }

}
