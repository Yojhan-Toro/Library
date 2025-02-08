package edu.eci.cvds.tdd.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

public class LibraryTest {
    
    Library library = new Library();

    @Test
    public void checkBook_BookAtStore_True(){

        Book book = new Book("test", "testing", "t1");
        library.addBook(book);
        boolean result = library.checkBook(book);
        assertEquals(true, result);
    }

    @Test
    public void checkBook_BookNotAtStore_False(){

        Book book = new Book("test", "testing", "t1");
        boolean result = library.checkBook(book);
        assertEquals(false, result);
    }

    @Test
    public void checkUser_RegisteredUser_True(){

        User user = new User();
        library.addUser(user);
        boolean result = library.checkUser(user);
        assertEquals(true, result);
    }

    @Test
    public void checkUser_RegisteredUser_False(){

        User user = new User();
        boolean result = library.checkUser(user);
        assertEquals(false, result);
    }

    @Test
    public void loanABook_UserCanLoanBook_True(){

        User user = new User();
        library.addUser(user);

        

        boolean result = library.checkUser(user);
        assertEquals(true, result);
    }


}
