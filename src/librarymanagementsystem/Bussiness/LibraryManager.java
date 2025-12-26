/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.Bussiness;

import java.util.HashMap;
import librarymanagementsystem.DataClasses.BookCatalogWithAVL_BST;
import librarymanagementsystem.DataClasses.PopularityWithHeap;
import librarymanagementsystem.DataClasses.UndoStack;

import librarymanagementsystem.Entity.Book;
import librarymanagementsystem.Entity.Loan;
import librarymanagementsystem.Entity.User;
import librarymanagementsystem.UserStoreHashMap;

/**
 *
 * @author 2005m
 */
public class LibraryManager {

    UndoStack undoStack = new UndoStack();
    PopularityWithHeap popularity = new PopularityWithHeap();
    BookCatalogWithAVL_BST bookCatalog = new BookCatalogWithAVL_BST();
    UserStoreHashMap users = new UserStoreHashMap(32);
    //inject hashing
    private HashMap<Integer, Book> booksById = new HashMap<>();

    public LibraryManager() {
    }

    //Katalog İşlemleri
    public void addBookToCatalog(Book book) {
        //addToHeap
        popularity.insert(book);
        bookCatalog.insert(book);
        booksById.put(book.getId(), book);
        System.out.println("'" + book.getTitle() + "'  eklendi.");
    }
    //tekrar bak

    public void removeFromCatalogByTitle(String title) {
        Book book = bookCatalog.searchByTitle(title);
        if (book != null) {
            popularity.removeBook(book);
            bookCatalog.delete(book);
            System.out.println("SİLME İŞLEMİ BAŞARILI ");
            book.printBook();
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
    public void addUser(User u) {
        users.put(u);
        System.out.println(u.getName() + " " + u.getSurname() + "  kullanıcılara  eklendi.");
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

    private void autoLend(int bookId) {
        int[] next = booksById.get(bookId).getNextWaitingUser(); //get userId from waitList   and remove it
        int userId = next[0];

        if (!booksById.get(bookId).hasWaitingUsers()) {//sorun var
            lendToUser(bookId, userId);

        } else {
            System.out.println("yanlıs");
        }

    }
    //kitabın müsaitlik durumu sorgulanır,user ve book

    public void lendToUser(int bookId, int userId) {
        Book book = booksById.get(bookId);
        Book bookBST = bookCatalog.searchByTitle(book.getTitle());
        User u = getUserById(userId);

        if (book != null && u != null) {
            if (book.isIsAvailable() == true) {
                popularity.removeBook(book);//for update popularity
                u.getBorrowedBooks().add(book);
                bookBST.setIsAvailable(false);
                book.setIsAvailable(false);
                book.incrementBorrowCount();
                popularity.insert(book);//for update popularity

                //kayıt stack'te tutulur
                Loan loan = new Loan(userId, bookId, true);
                undoStack.push(loan);

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
        bookBST.setIsAvailable(true);
        book.setIsAvailable(true);
        System.out.println(book.getTitle() + " kitabı  " + u.getUserName() + " adlı kullanıcıdan geri alındı.");
        //kayıt stack'te tutulur
        Loan loan = new Loan(userId, bookId, false);
        undoStack.push(loan);

        autoLend(bookId);

    }

    //undo last operation(lend or return)
    //SORUN VAR
    public void undo() {
        Loan loan = undoStack.top();
        String cancell = undoStack.undoLastOperation();

        if (cancell.equals("LEND")) {
            //cancell lend
            Book book = booksById.get(loan.getBookId());
            Book bookBST = bookCatalog.searchByTitle(book.getTitle());
            User u = getUserById(loan.getReaderId());

            if (book != null && u != null) {
                if (book.isIsAvailable() == false) {
                    popularity.removeBook(book);//for update popularity
                    u.getBorrowedBooks().delete(u.getBorrowedBooks().reachNode(book));
                    bookBST.setIsAvailable(true);
                    book.setIsAvailable(true);
                    book.decrementBorrowCount();
                    popularity.insert(book);//for update popularity

                } else {
                    autoLend(loan.getBookId());

                }
            }
            System.out.println("KITAP VERME ISLEMI GERI ALINDI.");
        } else {
            if (cancell.equals("RETURN")) {
                //cancell return

                Book book = booksById.get(loan.getBookId());
                Book bookBST = bookCatalog.searchByTitle(book.getTitle());
                User u = getUserById(loan.getReaderId());

                u.getBorrowedBooks().add(book);
                bookBST.setIsAvailable(false);
                book.setIsAvailable(false);
                System.out.println("KITAP VERME ISLEMI GERI ALINDI.");
            }

        }

    }

    public void printMostPopularBook() {
        popularity.getMostPopular().printBook();

    }

}
