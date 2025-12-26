/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagementsystem;

import librarymanagementsystem.Bussiness.LibraryManager;
import librarymanagementsystem.Entity.Book;
import librarymanagementsystem.Entity.Genre;
import static librarymanagementsystem.Entity.Genre.BookGenres.*;
import librarymanagementsystem.Entity.User;

/*
 *
 * @author 2005m
 */
public class LibraryManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();

// Sıralama: title, author, publisher, genre, publicationYear, id
        Book b1 = new Book("Suç ve Ceza", "Dostoyevski", "Can", Genre.BookGenres.CLASSICS, "1866", 1);
        Book b2 = new Book("1984", "George Orwell", "Can", Genre.BookGenres.LITERARY_FICTION, "1949", 2);
        Book b3 = new Book("Yüzüklerin Efendisi", "Tolkien", "Metis", Genre.BookGenres.FANTASY, "1954", 3);
        Book b4 = new Book("Sapiens", "Yuval Noah Harari", "Kolektif", Genre.BookGenres.HISTORY, "2011", 4);
        Book b5 = new Book("Sherlock Holmes", "A. Conan Doyle", "Everest", Genre.BookGenres.MYSTERY_THRILLER, "1887", 5);
        Book b6 = new Book("Dune", "Frank Herbert", "İthaki", Genre.BookGenres.SCIENCE_FICTION, "1965", 6);
        Book b7 = new Book("Simyacı", "Paulo Coelho", "Can", Genre.BookGenres.LITERARY_FICTION, "1988", 7);
        Book b8 = new Book("Nutuk", "Mustafa Kemal Atatürk", "YKY", Genre.BookGenres.HISTORY, "1927", 8);
        Book b9 = new Book("Küçük Prens", "Saint-Exupéry", "Mavi Bulut", Genre.BookGenres.OTHER, "1943", 9);
        Book b10 = new Book("Kürk Mantolu Madonna", "Sabahattin Ali", "YKY", Genre.BookGenres.ROMANCE, "1943", 10);

        manager.addBookToCatalog(b1);
        manager.addBookToCatalog(b2);
        manager.addBookToCatalog(b3);
        manager.addBookToCatalog(b4);
        manager.addBookToCatalog(b5);
        manager.addBookToCatalog(b6);
        manager.addBookToCatalog(b7);
        manager.addBookToCatalog(b8);
        manager.addBookToCatalog(b9);
        manager.addBookToCatalog(b10);
        System.out.println("--------------------------------------------------");
        manager.searchInCatalogByTitle("Suç ve Ceza");
        System.out.println("--------------------------------------------------");
        manager.searchInCatalogByAuthor("George Orwell");
        System.out.println("--------------------------------------------------");
        manager.removeFromCatalogByTitle("Küçük Prens");
        System.out.println("--------------------------------------------------");

        User u1 = new User("Seran", "AKTAS", "SERAN_AKTAS", "CIMBOMBOM");
        manager.addUser(u1);
        User u2 = new User("Ali", "Yilmaz", "ali_yilmaz", "Sifre123!");
        manager.addUser(u2);
        User u3 = new User("Ayse", "Demir", "ayse.demir", "Ayse_2025*");
        manager.addUser(u3);
        User u4 = new User("Ece", "Can", "ece", "123");
        manager.addUser(u4);
        User u5 = new User("Mehmet", "Oz", "mehmet34", "pass_9900");
        manager.addUser(u5);
        System.out.println("-----------------------------------------");
        manager.lendToUser(b1.getId(), u1.getUserId());
        
        manager.lendToUser(b1.getId(), u2.getUserId());
        
        manager.returnFromUser(b1.getId(), u1.getUserId());
manager.lendToUser(b4.getId(), u4.getUserId());
        System.out.println("---------------------UNDO---------------");
        
        manager.undo();
        
        manager.printMostPopularBook();
    }

}
