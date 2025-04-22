package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();

    public void addBook(Book book) {
        List<Book> nuevosLibros = new ArrayList<>();

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void updateBook(int indexBook, Book book) {
        bookList.set(indexBook, book);
        System.out.println(bookList.get(indexBook));
        System.out.println("+++++++++++++++++++");
        System.out.println("++LIBRO ACTUALIZADO!++");
        System.out.println("+++++++++++++++++++");
    }

    public Book updateBookForIsbn(String isbnCode) throws ExcepcionesPropias {
            return bookList.stream()
                    .filter(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode))
                    .findFirst()
                    .orElseThrow(() -> new ExcepcionesPropias("Libro con isbn " + isbnCode + " No econtrado"));
    }


    public void deletedBook(String isbnCode) throws ExcepcionesPropias {
        boolean existIsbnCode = bookList.stream().anyMatch(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode));

        if (!existIsbnCode) {
            throw new ExcepcionesPropias("Libro no econtrado " + isbnCode);
        }

        bookList.removeIf(book -> book.getIsbnCode().equalsIgnoreCase(isbnCode));
    }


    public void addAuthor(Author author) {
        
    }

    public void addUser(User user) {
        userList.add(user);
        System.out.println("Usuario Agregado!");
    }
}
