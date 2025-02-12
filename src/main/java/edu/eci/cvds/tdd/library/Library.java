package edu.eci.cvds.tdd.library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.*;
import edu.eci.cvds.tdd.library.user.User;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        int actualValue=0;
        if(books.containsKey(book)==true){
            actualValue = books.get(book);
        }
        books.put(book, actualValue+1);
        
        if(books.containsKey(book)==true){
            return true;
        } else return false;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */

    public Book checkBook(String isbn){
        for (Book book : books.keySet()) {
            if(book.getIsbn() == isbn && books.get(book) > 0){
                return book;
            }
        }
        return null;
    }

    public User checkUser(String userId){
        for(User user: users){
            if(user.getId()== userId){
                return user;
            }
        }
        return null;
    }

    public boolean notRepeatedLoans(User user, Book book){
        for(Loan loan: loans){
            if(loan.getUser() == user && loan.getBook() == book && loan.getStatus() == LoanStatus.ACTIVE){
                return false;
            }
        }
        return true;
    }

    public Loan loanABook(String userId, String isbn) {
        
        User user = checkUser(userId);
        Book book = checkBook(isbn);

        if(user != null && book != null && notRepeatedLoans(user, book)){

            books.put(book, books.get(book) - 1);
            Loan loan = new Loan();
            loan.setBook(book);
            loan.setUser(user);
            LocalDateTime now = LocalDateTime.now();
            loan.setLoanDate(now);
            loan.setStatus(LoanStatus.ACTIVE);
            loans.add(loan);
            return loan;
        }

        return null;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */

    public boolean loanExists(Loan entrance){
        for(Loan loan: loans){
            if(loan.getBook() == entrance.getBook() && loan.getUser() == entrance.getUser() && loan.getStatus() == LoanStatus.ACTIVE){
                return true;
            }
        }
        return false;
    }

    public Loan returnLoan(Loan loan) {

        if(loanExists(loan))
        {
            loan.setStatus(LoanStatus.RETURNED);
            loan.setReturnDate(LocalDateTime.now());
            books.put(loan.getBook(), books.get(loan.getBook()) + 1);
            return loan;
        }
        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public int getAmountSpecificBook(Book book){
        int amountBook = 0;
        if(books.containsKey(book)==true){
            amountBook = books.get(book);
        }
        return amountBook;
    }

    public int getAmountBooks(){
        int amount =0;
        for(Integer amountBook :books.values()){
            amount += amountBook;
        }
        return amount;
    }
}
