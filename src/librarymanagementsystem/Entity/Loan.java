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

    private int readerId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate lastReturnDate;

    public Loan(int readerId, int bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.lastReturnDate = this.borrowDate.plusDays(14); // A book can be borrowed for 14 days!!
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

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getLastReturnDate() {
        return lastReturnDate;
    }

    public void setLastReturnDate(LocalDate lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }

}
