/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagementsystem;

/**
 *
 * @author 2005m
 */
import java.util.Scanner;
import librarymanagementsystem.Bussiness.LibraryManager;
import librarymanagementsystem.Entity.Book;
import librarymanagementsystem.Entity.Genre;

import librarymanagementsystem.Entity.User;

public class TestClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        LibraryManager manager = new LibraryManager();
        System.out.println("KÜTÜPHANE SİSTEMİNE HOŞGELDİNİZ!!");

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

        User u1 = new User("Seran", "AKTAS", "SERAN_AKTAS", "CIMBOMBOM");
        User u2 = new User("Ali", "Yilmaz", "ali_yilmaz", "Sifre123!");
        User u3 = new User("Ayse", "Demir", "ayse.demir", "Ayse_2025*");
        User u4 = new User("Ece", "Can", "ece", "123");
        User u5 = new User("Mehmet", "Oz", "mehmet34", "pass_9900");

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

        manager.addUser(u1);
        manager.addUser(u2);
        manager.addUser(u3);
        manager.addUser(u4);
        manager.addUser(u5);

        int choice;
        while (true) {
            System.out.println("=========== KUTUPHANE YONETIM SISTEMI ===========");
            System.out.println("1. Kitap Ara (Başlık ile)");
            System.out.println("2. Kitap Ara (Yazar ile)");
            System.out.println("3. Kitap Ödünç Ver");
            System.out.println("4. Kitap İade Et");
            System.out.println("5. Bekleme Listesini Görüntüle");
            System.out.println("6. En Popüler Kitabı Görüntüle");
            System.out.println("7. Son İşlemi Geri Al (Undo)");
            System.out.println("8. Kitap Sil");
            System.out.println("0. Çıkış");

            System.out.print("Seçiminiz: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Aranacak kitap başlığını giriniz:");
                    String searchTitle = scanner.nextLine();
                    manager.searchInCatalogByTitle(searchTitle);
                    break;

                case 2:
                    System.out.print("Aranacak yazar adını giriniz:");
                    String searchAuthor = scanner.nextLine();
                    manager.searchInCatalogByAuthor(searchAuthor);
                    break;
                case 3:
                    System.out.print("Ödünç verilecek kitap ID:");
                    int lendBookId = scanner.nextInt();

                    System.out.println("Mevcut Kullanıcı ID'leri:");
                    System.out.println("Seran: " + u1.getUserId());
                    System.out.println("Ali: " + u2.getUserId());
                    System.out.println("Ayşe: " + u3.getUserId());
                    System.out.println("Ece: " + u4.getUserId());
                    System.out.println("Mehmet: " + u5.getUserId());

                    System.out.print("Ödünç alacak kullanıcı ID: ");
                    int lendUserId = scanner.nextInt();

                    manager.lendToUser(lendBookId, lendUserId);
                    break;

                case 4:
                    System.out.print("İade edilecek kitap ID: ");
                    int returnBookId = scanner.nextInt();
                    System.out.println("İade eden kullanıcı ID: ");
                    int returnUserId = scanner.nextInt();

                    manager.returnFromUser(returnBookId, returnUserId);
                    break;

                case 5:
                    System.out.println("Bekleme listesini görüntülemek istedğiniz kitap ID: ");
                    int waitlistBookId = scanner.nextInt();

                    Book waitlistBook = getBookByIdFromManager(manager, waitlistBookId);

                    if (waitlistBook != null) {
                        waitlistBook.showWaitList();
                    } else {
                        System.out.println("Kitap bulunamadı!!");
                    }
                    break;

                case 6:
                    System.out.println("En populer kitap");
                    manager.printMostPopularBook();
                    break;

                case 7:
                    System.out.println("Son işlem geri alınıyor...");
                    manager.undo();
                    break;

                case 8:
                    System.out.println("Silinecek kitap basligini girin:");
                    String deleteTitle = scanner.nextLine();
                    manager.removeFromCatalogByTitle(deleteTitle);
                    break;

                case 0:
                    System.out.println("Kutuphane sisteminden cıkıs yapiliyor");

                default:
                    System.out.println("Gecersiz secim! Lutfen 0-9 arasinda sayi giriniz...");
            }
        }
    }

    private static Book getBookByIdFromManager(LibraryManager manager, int bookId) {
        try {
            // Reflection kullanarak private field'a erişim
            java.lang.reflect.Field booksByIdField = manager.getClass().getDeclaredField("booksById");
            booksByIdField.setAccessible(true);
            java.util.HashMap<Integer, Book> booksById = (java.util.HashMap<Integer, Book>) booksByIdField.get(manager);
            return booksById.get(bookId);
        } catch (Exception e) {
            System.out.println("Kitap bulunamadı veya erişim hatası: " + e.getMessage());
            return null;
        }
    }

}
