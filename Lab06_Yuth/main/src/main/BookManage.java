package main;

import java.util.ArrayList;
import java.util.List;

public class BookManage {
    public List<Book> books = new ArrayList<>();

    public List<Book> listallBooks(){
        return new ArrayList<>(books);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void updateBook(int index, Book book){
        if (index >= 0 && index < books.size()){
            books.set(index, book);
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public void deleteBook(int index){
        if (index >= 0 && index < books.size()){
            books.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public void deleteAllBook(){
        books.clear();
    }
}
