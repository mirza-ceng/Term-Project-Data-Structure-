/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.DataClasses;

import java.util.ArrayList;
import librarymanagementsystem.Entity.Book;

/**
 *
 * @author 2005m
 */
public class PopularityWithHeap {

    private ArrayList<Book> heap;

    public PopularityWithHeap() {
        this.heap = new ArrayList<Book>();
    }

    private int getRightChild(int i) {
        return 2 * i + 2;
    }

    private int getLeftChild(int i) {
        return 2 * i + 1;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    public void insert(Book book) {
        heap.add(book);
        heapifyUp(heap.size() - 1);

    }

    public void removeBook(Book book) {
        int index = heap.indexOf(book); // Kitabı bul
        if (index == -1) {
            return;
        }

        int lastIndex = heap.size() - 1;

        // Eğer silinecek kitap zaten listenin sonundaysa direkt sil
        if (index == lastIndex) {
            heap.remove(lastIndex);
            return;
        }

        // Ortada bir yerdeyse: Son elemanı silinecek yere taşı
        Book lastItem = heap.remove(lastIndex);
        heap.set(index, lastItem);

        // Yeni gelen elemanı doğru konuma yerleştir
        // (Büyük mü küçük mü bilmediğimiz için ikisini de çağırıyoruz, 
        // hangisi gerekliyse o çalışacaktır)
        heapifyUp(index);
        heapifyDown(index);
    }

    public void heapifyUp(int i) {
        while (i > 0) {
            int parent = getParent(i);
            if (heap.get(i).getBorrowCount() > heap.get(parent).getBorrowCount()) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }

        }

    }

    public void heapifyDown(int i) {
        int max = i;
        int left = getLeftChild(i);
        int right = getRightChild(i);

        if (left < heap.size() && heap.get(left).getBorrowCount() > heap.get(max).getBorrowCount()) {
            max = left;
        }
        if (right < heap.size() && heap.get(right).getBorrowCount() > heap.get(max).getBorrowCount()) {
            max = right;
        }
        if (max != i) {
            swap(i, max);
            heapifyDown(i);
        }

    }

    public void swap(int i, int j) {
        Book temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

    }

    public Book getMostPopular() {

        return heap.getFirst();
    }
}
