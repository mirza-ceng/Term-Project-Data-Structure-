/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Entity;

import librarymanagementsystem.DataClasses.WaitListWithQueue;
import librarymanagementsystem.Entity.Genre.BookGenres;

/**
 *
 * @author 2005m
 */
public class Book {

    private String title;
    private String author;
    private String publisher;
    private Genre.BookGenres genre;
    private int id;
    private boolean isAvailable;
    private String publicationYear;
    private int borrowCount = 0;

    private WaitListWithQueue waitList;

    // add WaitList using Queue
    public Book(String title, String author, String publisher, BookGenres genre, String publicationYear, int id) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isAvailable = true;
        this.publicationYear = publicationYear;
        this.waitList = new WaitListWithQueue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WaitListWithQueue getWaitList() {
        return waitList;
    }

    public void setWaitList(WaitListWithQueue waitList) {
        this.waitList = waitList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BookGenres getGenre() {
        return genre;
    }

    public void setGenre(BookGenres genre) {
        this.genre = genre;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    //popülarite takibi için
    public void incrementBorrowCount() {
        borrowCount++;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void printBook() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Title :" + this.title);
        System.out.println("Author :" + this.author);
        System.out.println("Genre :" + this.genre);
        System.out.println("Published By :" + this.publisher);
        System.out.println("Year :" + this.publicationYear);
        System.out.println("--------------------------------------------------------");

    }

    public void addUserToWaitList(int userId) {
        waitList.addToWaitList(userId, this.id);
    }

    public int[] getNextWaitingUser() {
        return waitList.getNextFromWaitList();
    }

    public boolean hasWaitingUsers() {
        return waitList.hasWaitersForBook(this.id);
    }

    public void showWaitList() {
        System.out.println("=== Waitlist for: " + this.title + " ===");
        waitList.showWaitList();
    }

}
