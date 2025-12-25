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
        

       // Kalbin boyutunu ayarlamak için n değerini değiştirebilirsiniz
        int n = 6;

        // Üst kısım (İki yarım daire)
        for (int i = n / 2; i <= n; i += 2) {
            for (int j = 1; j < n - i; j += 2) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Alt kısım (Ters üçgen)
        for (int i = n; i >= 1; i--) {
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (i * 2) - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    
        System.out.println("OZUR DILERIM ASKIM");

    }

}
