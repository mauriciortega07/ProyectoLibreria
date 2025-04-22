package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String username, String email, String password, List<Book> borrowedBooks) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks){
        this.password = password;
    }

    public List<Book> getBorrowedBooks() {
       return borrowedBooks;
    }

}
