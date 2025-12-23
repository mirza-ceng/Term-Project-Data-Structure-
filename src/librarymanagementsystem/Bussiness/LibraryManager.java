/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Bussiness;

import librarymanagementsystem.DataClasses.BookCatalogWithAVL_BST;
import librarymanagementsystem.DataClasses.BorrowedBooksLinkedList;
import librarymanagementsystem.DataClasses.WaitListWithQueue;
import librarymanagementsystem.Entity.Book;
import librarymanagementsystem.Entity.User;

/**
 *
 * @author 2005m
 */
public class LibraryManager {
    // WaitListWithQueue waitListWithQueue; 
    // BorrowedBooksLinkedList borrowedBooksLinkedList;

    BookCatalogWithAVL_BST bookCatalog = new BookCatalogWithAVL_BST();

    public LibraryManager() {
    }

    //Katalog İşlemleri
    public void addBookToCatalog(Book book) {
        bookCatalog.insert(book);
        System.out.println("'" + book.getTitle() + "'  eklendi.");
    }

    public void removeFromCatalog(String title) {
        Book book = bookCatalog.search(title);
        if (book != null) {
            bookCatalog.delete(book);
        }
    }

    public void searchInCatalog(String title) {
        Book book = bookCatalog.search(title);
        if (book != null) {
            System.out.println("ARAMA SONUCU : ");
            
            book.printBook();
        }
    }
    
    //Reader İşlemleri
    
    //geçici(user depolama sistemi yazılmadı!!)
    public User getUserById(int userId){
    return null;
    }
    
    
    
    public void lendToUser(Book book, int userId){
    User u=getUserById(userId);
    if(book!=null&&u!=null){
    u.getBorrowedBooks().add(book);
        System.out.println(book.getTitle()+" kitabı  "+u.getUserName()+" adlı kullanıcıya verildi.");
    }
        
    }
    public void returnFromUser(){}
    
    
    
    

}
