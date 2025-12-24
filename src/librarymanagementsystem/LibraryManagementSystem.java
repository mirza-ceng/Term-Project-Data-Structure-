/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagementsystem;

import librarymanagementsystem.Bussiness.LibraryManager;
import librarymanagementsystem.Entity.Book;
import librarymanagementsystem.Entity.Genre;
import static librarymanagementsystem.Entity.Genre.BookGenres.*;

/*
 *
 * @author 2005m
 */
public class LibraryManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Book book = new Book("İrade", "jorge", "aaa", CLASSICS, "", 0);
        
        

        LibraryManager manager = new LibraryManager();

        manager.addBookToCatalog(book);
        manager.searchInCatalogByTitle("İrade");

    }

}
