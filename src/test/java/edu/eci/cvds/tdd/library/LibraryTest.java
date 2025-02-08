package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private final Book booktest = new Book("Cien años de soledad", "Gabriel Garcia Marques", "10002");

    @Test
    public void addBook_bookAdded_True() {
        assertEquals(true, library.addBook(booktest));
    }

    @Test
    public void addBook_StorageSize_Increase1(){
        int initialSize = library.getAmountBooks();
        library.addBook(booktest);
        assertEquals(initialSize+1, library.getAmountBooks());
    }

    @Test
    public void addBook_StorageForBook_Increase1(){
        int initialAmount = library.getAmountSpecificBook(booktest);
        library.addBook(booktest);
        assertEquals(initialAmount+1, library.getAmountSpecificBook(booktest));
    }

    @Test
    public void addBook_IncreaseSavedBook_Increase1(){
        library.addBook(booktest);
        int initialAmount = library.getAmountSpecificBook(booktest);
        library.addBook(booktest);
        assertEquals(initialAmount+1, library.getAmountSpecificBook(booktest));
    }

}

