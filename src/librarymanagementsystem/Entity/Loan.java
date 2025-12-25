/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Entity;

import java.time.LocalDate;

/**
 *
 * @author 2005m
 */
public class Loan {

    private int readerId;//user
    private int bookId;
    private boolean isLend;//or borrow operation
    private LocalDate operationDate;
    private LocalDate lastReturnDate;

    public Loan(int readerId, int bookId, boolean isLend) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.operationDate = LocalDate.now();
        this.lastReturnDate = isLend ? this.operationDate.plusDays(14) : null; // A book can be borrowed for 14 days!!
        this.isLend = isLend;
    }
    public boolean isIsLend() {
        return isLend;
    }

    public void setIsLend(boolean isLend) {
        this.isLend = isLend;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public LocalDate getLastReturnDate() {
        return lastReturnDate;
    }

    public void setLastReturnDate(LocalDate lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }

    public void printLoanInfo() {
        System.out.println("Member ID :" + getReaderId());
        System.out.println("Book ID :" + getBookId());
        String op = isIsLend() ? "LEND" : "BORROW";
        System.out.print("OPERATİON " + op);
        System.out.println(op + " DATE : " + getOperationDate());
        String returnDate = isLend ? getLastReturnDate().toString() : "---";
        System.out.println("LAST RETURN DATE :" + returnDate);
    }

}
