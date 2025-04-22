package com.ebac.proyectoJava;

public class Book {
    private String titleBook;
    private String authorBook;
    private int releaseYear;
    private String isbnCode;

    public Book (String titleBook, String authorBook, int releaseYear, String isbnCode){
        this.titleBook = titleBook;
        this.authorBook = authorBook;
        this.releaseYear = releaseYear;
        this.isbnCode = isbnCode;
    }

    public void setTitleBook(String titleBook){
        this.titleBook = titleBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setAuthorBook(String authorBook){
        this.authorBook = authorBook;
    }

    public String getAuthorBook(){
        return  authorBook;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear(){
        return releaseYear;
    }


    public void setIsbnCode(String isbnCode){
        this.isbnCode = isbnCode;
    }

    public String getIsbnCode(){
        return isbnCode;
    }

    @Override
    public String toString() {
        return "Libro = " +
                "authorBook= '" + authorBook + '\'' +
                ", titleBook= '" + titleBook + '\'' +
                ", releaseYear= " + releaseYear +
                ", isbnCode= " + isbnCode;
    }
}
