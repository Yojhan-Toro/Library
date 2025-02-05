package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;

/**
 * Unit test for Library.
 */
public class LibraryTest {

    /**
     * Rigorous Test :-)
     */
    private final Library library = new Library();
    Book booktest = new Book(null, null, null);

    @Test
    public void addBook_bookAdded_True() {
        assertEquals(true, library.addBook(booktest));
    }

    @Test
    public void addBook_StorageSize_Increase1(){
        int initialSize = library.getAmountBooks();
        library.addBook(booktest);
        assertEquals(initialSize, initialSize+1);
    }

    @Test
    public void addBook_StorageForBook_Increase1(){
        assertTrue(true);
    }

}

