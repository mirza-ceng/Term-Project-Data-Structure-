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




    }

}
