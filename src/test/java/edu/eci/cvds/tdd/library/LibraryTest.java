package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.*;

/**
 * Unit test for Library.
 */
public class LibraryTest {

    /**
     * Rigorous Test :-)
     */
    private final Library library = new Library();
    private final Book booktest = new Book("Cien años de soledad", "Gabriel Garcia Marques", "10002");
    private final User user = new User();


    @Test
    public void checkBook_BookAtStore_NotNull(){


        library.addBook(booktest);
        Book result = library.checkBook("10002");
        assertNotNull(result);
    }

    @Test
    public void checkBook_BookNotAtStore_Null(){

        Book result = library.checkBook("10002");
        assertNull(result);
    }

    @Test
    public void checkUser_RegisteredUser_NotNull(){

        user.setId("p1");
        library.addUser(user);
        User result = library.checkUser("p1");
        assertNotNull(result);
    }

    @Test
    public void checkUser_RegisteredUser_Null(){

        user.setId("p1");
        User result = library.checkUser("p1");
        assertNull(result);
    }

    @Test
    public void loanABook_UserCanLoanBook_NotNull(){

        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan result = library.loanABook("p1","10002");
        assertNotNull(result);
    }

    @Test
    public void loanABook_UserCanLoanBook_Null(){

        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan result = library.loanABook("p1","10001");
        assertNull(result);
    }

    @Test
    public void loanABook_UserCantRepeatedLoanBook_Null(){

        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan test = library.loanABook("p1","10002");
        Loan result = library.loanABook("p1","10002");
        assertNull(result);
    }

    @Test
    public void notRepeatedLoans_UserRepeatLoan_false(){

        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan loan = library.loanABook("p1","10002");
        boolean result = library.notRepeatedLoans(user, booktest);
        assertEquals(false, result);
    }

    @Test
    public void notRepeatedLoans_UserRepeatLoan_true(){

        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);
        User usert2 = new User();
        usert2.setId("p2");

        Loan loan = library.loanABook("p1","10002");
        boolean result = library.notRepeatedLoans(usert2, booktest);
        assertEquals(true, result);
    }


    //Tests addBook

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


