package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String firstName;
    private String lastName;
    private String biography;
    private List<Book> booksPublished = new ArrayList<>();

    public Author(String firstName, String lastName, String biography, List<Book> booksPublished) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.booksPublished = booksPublished;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setBiography(String biography){
        this.biography = biography;
    }

    public String getBiography(){
        return biography;
    }

    public void setBooksPublished(List<Book> booksPublished){
        this.booksPublished = booksPublished;
    }

    public List<Book> getBooksPublished(){
        return booksPublished;
    }

}
