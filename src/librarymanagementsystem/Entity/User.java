/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Entity;

import java.util.ArrayList;
import librarymanagementsystem.DataClasses.BorrowedBooksLinkedList;

/**
 *
 * @author 2005m
 */
public class User {

    private String name;
    private String surname;
    private String userName;
    private String password;
    private int userId;
    private UserRole role;
    private BorrowedBooksLinkedList borrowedBooks = new BorrowedBooksLinkedList(getUserId());

    //edit borrowedBooks using LinkedList
    public User(String name, String surname, String userName, String password, int userId, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }

    public BorrowedBooksLinkedList getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(BorrowedBooksLinkedList borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}

enum UserRole {
    MANAGER, MEMBER

}
