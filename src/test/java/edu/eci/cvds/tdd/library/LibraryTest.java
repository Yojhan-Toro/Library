package edu.eci.cvds.tdd.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.tdd.library.book.Book;

public class LibraryTest {
    
    Library library = new Library();

    @Test
    public void checkBook_BookAtStore_True(){

        Book book = new Book("test", "testing", "t1");
        boolean result = library.checkBook(book);
        assertEquals(true, result);
    }

    @Test
    public void checkBook_BookAtStore_False(){

        Book book = new Book("test", "testing", "t1");
        boolean result = library.checkBook(book);
        assertEquals(false, result);
    }


}
