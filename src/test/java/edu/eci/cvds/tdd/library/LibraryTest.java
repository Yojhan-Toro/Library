package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    public void notRepeatedLoans_BookRepeatLoan_true(){

        library.addUser(user);
        user.setId("p1");
        Book book1 = new Book("1000 años de seridad", "Ivan no", "13");
        library.addBook(booktest);
        User usert2 = new User();
        usert2.setId("p2");

        Loan loan = library.loanABook("p1","10002");
        boolean result = library.notRepeatedLoans(user, book1);
        assertEquals(true, result);
    }

    @Test
    public void notRepeatedLoans_BookAndUserRepeatLoan_true(){

        library.addUser(user);
        user.setId("p1");
        Book book1 = new Book("1000 años de seridad", "Ivan no", "13");
        library.addBook(booktest);
        User usert2 = new User();
        usert2.setId("p2");

        Loan loan = library.loanABook("p1","10002");
        boolean result = library.notRepeatedLoans(usert2, book1);
        assertEquals(true, result);
    }

    @Test
    public void loanABook_IncreaseQuantity_true(){
 
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        int before = library.getAmountSpecificBook(booktest);
        Loan test = library.loanABook("p1","10002");

        assertTrue(library.getAmountSpecificBook(booktest) + 1  == before);
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



    // Tets ReturnLoan

    @Test
    public void returnLoan_CanLoanReturned_NotNull(){
        
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan loan = library.loanABook("p1","10002");
        Loan result = library.returnLoan(loan);
        assertNotNull(result);
    }

    @Test
    public void returnLoan_ChangeState_True(){
        
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan loan = library.loanABook("p1","10002");
        Loan result = library.returnLoan(loan);  

        assertEquals(LoanStatus.RETURNED, result.getStatus());
    }

    @Test
    public void returnLoan_ChangeState_False(){
        
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan loan = library.loanABook("p1","10002");
        Loan result = library.returnLoan(loan);  

        assertEquals(false, (LoanStatus.ACTIVE == result.getStatus()));
    }

    @Test
    public void returnLoan_ChngeState_False(){
        
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);

        Loan loan = library.loanABook("p1","10002");
        Loan result = library.returnLoan(loan);
        Loan loan1 = library.loanABook("p1","10002");

        assertNotNull(loan1);
    }

    @Test
    public void returnLoan_IncreaseQuantity_true(){
 
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);
        Loan test = library.loanABook("p1","10002");
        int before = library.getAmountSpecificBook(booktest);

        Loan loan1 = library.returnLoan(test);

        assertTrue(library.getAmountSpecificBook(booktest) - 1  == before);
    }

    @Test
    public void returnLoan_loanNotExist_Null(){
 
        library.addUser(user);
        user.setId("p1");
        library.addBook(booktest);
        Loan test = new Loan();
        Loan loan1 = library.returnLoan(test);

        assertNull(loan1);
    }

}


