/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Bussiness;

import java.util.HashMap;
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
    private HashMap<Integer, Book> booksById = new HashMap<>();

    public LibraryManager() {
    }

    //Katalog İşlemleri
    public void addBookToCatalog(Book book) {
        bookCatalog.insert(book);
        booksById.put(book.getId(), book);
        System.out.println("'" + book.getTitle() + "'  eklendi.");
    }
    //tekrar bak

    public void removeFromCatalogByTitle(String title) {
        Book book = bookCatalog.searchByTitle(title);
        if (book != null) {
            bookCatalog.delete(book);
            booksById.remove(book.getId());
        }
    }

    public void removeFromCatalogByAuthor(String author) {
        Book book = bookCatalog.searchByAuthor(author);
        if (book != null) {
            bookCatalog.delete(book);
            booksById.remove(book.getId());

        }
    }

    public void searchInCatalogByTitle(String title) {
        Book book = bookCatalog.searchByTitle(title);
        if (book != null) {
            System.out.println("ARAMA SONUCU : ");

            book.printBook();
        }
    }

    public void searchInCatalogByAuthor(String author) {
        Book book = bookCatalog.searchByAuthor(author);
        if (book != null) {
            System.out.println("ARAMA SONUCU : ");

            book.printBook();
        }
    }

    //Reader İşlemleri
    //geçici(user depolama sistemi yazılmadı!!)
    public User getUserById(int userId) {
        return null;
    }

   

    
    public void autoLend(int bookId) {
        int[] next = booksById.get(bookId).getNextWaitingUser();
        //get userId from waitList
        int userId = next[0];//neden array?
        User u = getUserById(userId);
        if (booksById.get(bookId).hasWaitingUsers()) {
            lendToUser(bookId, userId);
            System.out.println("Sıradaki kullanıcıya verildi.");
        }

    }
    //kitabın müsaitlik durumu sorgulanır,user ve book

    public void lendToUser(int bookId, int userId) {
        Book book = booksById.get(bookId);
        Book bookBST = bookCatalog.searchByTitle(book.getTitle());
        User u = getUserById(userId);

        if (book != null && u != null) {
            if (book.isIsAvailable() == true) {
                u.getBorrowedBooks().add(book);

                bookBST.setIsAvailable(false);
                book.setIsAvailable(false);
                book.incrementBorrowCount();
                System.out.println(book.getTitle() + " kitabı  " + u.getUserName() + " adlı kullanıcıya verildi.");
            } else {
                book.addUserToWaitList(userId);

            }
        }

    }

    public void returnFromUser(int bookId, int userId) {
        Book book = booksById.get(bookId);
        Book bookBST = bookCatalog.searchByTitle(book.getTitle());

        User u = getUserById(userId);

        u.getBorrowedBooks().delete(u.getBorrowedBooks().reachNode(book));
        bookBST.setIsAvailable(false);
        book.setIsAvailable(true);

        autoLend(bookId);

        //waitlist güncelle
    }

}
