package main;

public class Book {
    String title, author;

    Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String GetTitle(){
        return title;
    }
    public String GetAuthor(){
        return author;
    }
    public void setTitle_author(String title, String author){
        this.title = title;
        this.author = author;
    }
    @Override
    public String toString(){
        return "Book{" + "title='" + title + '\'' + ", author=" + author + '\'' + '}';
    }
}
