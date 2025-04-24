package com.ebac.proyectoJava;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String NameComplete;
    //private String lastName;
    private String biography;
    private List<Book> booksPublished = new ArrayList<>();

    public Author(String NameComplete,String biography, List<Book> booksPublished) {
        this.NameComplete = NameComplete;

        this.biography = biography;
        this.booksPublished = booksPublished;
    }

    public void setNameComplete(String NameComplete){
        this.NameComplete = NameComplete;
    }

    public String getNameComplete(){
        return NameComplete;
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

    @Override
    public String toString() {
        return "Autor = {" + "Nombre = " + NameComplete +
                " , Biografia = " + biography + ", Libros publicados: " + booksPublished;

    }
}

