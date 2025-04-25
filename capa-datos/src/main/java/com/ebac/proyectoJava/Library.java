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
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*public void preuploadAuthors() {
        List<Book> booksPublished1 = new ArrayList<>();
        List<Book> booksPublished2 = new ArrayList<>();

        Book book1 = new Book("300", "Mauricio Ortega", 2000, "0987");
        Book book2 = new Book("Hairdress On Fire", "Mauricio Ortega", 1997, "7392");
        Book book3 = new Book("BonaDrag", "David Hamilton", 1987, "3023");
        Book book4 = new Book("1984", "David Hamilton", 1972, "6721");

        booksPublished1.add(book1);
        booksPublished1.add(book2);

        booksPublished2.add(book3);
        booksPublished2.add(book4);

        Author author1 = new Author("Mauricio Ortega", "Mexico 1981", booksPublished1);
        Author author2 = new Author("David Hamilton", "Londres 1910 - 2007", booksPublished2);
        authorList.add(author1);
        authorList.add(author2);
    }*/

    public List<Author> getAuthorList() {
        //preuploadAuthors();
        return authorList;
    }

    public Author updateAuthorForName(String name) throws ExcepcionesPropias {
        return authorList.stream().filter(author -> author.getNameComplete().toLowerCase().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new ExcepcionesPropias("Autor no encontrado"));
    }

    public void updateAuthor (int indexAutor, Author author){
        authorList.set(indexAutor, author);
        System.out.println(authorList.get(indexAutor));
        System.out.println("+++++++++++++++++++");
        System.out.println("++AUTOR ACTUALIZADO!++");
        System.out.println("+++++++++++++++++++");

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
