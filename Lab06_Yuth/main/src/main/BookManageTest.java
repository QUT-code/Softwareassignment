package main;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class BookManageTest {
    
    BookManage manage = new BookManage();

    @Test
    public void testAddBook(){
        Book book = new Book("C++ programming", "Rithy");
        manage.addBook(book);
        assertEquals(1, manage.listallBooks().size());
    }

    @Test
    public void testUpdateBook(){
        Book books = new Book("C++ programming", "Rithy");
        manage.addBook(books);
        Book updateBook = new Book("Java programming", "Jin");
        manage.updateBook(0, updateBook);
        assertEquals("Java programming", manage.listallBooks().get(0).GetTitle());
    }

    @Test
    public void testDeleteBook(){
        Book book = new Book("Java programming", "Jin");
        manage.addBook(book);
        manage.deleteBook(0);
        assertEquals(0, manage.listallBooks().size());
    }

    @Test
    public void testDeleteAllBook(){
        manage.addBook(new Book("C++ programming", "Rithy"));
        manage.addBook(new Book("Java programming", "Jin"));
        manage.deleteAllBook();
        assertEquals(0, manage.listallBooks().size());
    }

    @Test
    public void testlistAllBook(){
        manage.addBook(new Book("C++ programming", "Rithy"));
        manage.addBook(new Book("Java programming", "Jin"));
        assertEquals(2, manage.listallBooks().size());
    }

}
