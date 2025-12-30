/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.DataClasses;

import librarymanagementsystem.Entity.Book;

/**
 *
 * @author 2005m
 */
public class BorrowedBooksLinkedList {
    
    class Node {
        
        public Node next;
        public Book data;
        
        public Node(int userId, Book data) {
            
            this.data = data;
            this.next = null;
        }
        
        public Node(Book data) {
            
            this.data = data;
            this.next = null;
        }
        
    }
    int userId;
    Node head;

    public BorrowedBooksLinkedList(int userId) {
        this.userId = userId;
        this.head = null;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Node getHead() {
        return head;
    }
    
    public void setHead(Node head) {
        this.head = head;
    }
    
    public void add(Book data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            
        }
        
    }
    
    public void delete(Node node) {
        Node temp = head;
        //silinecek öğe;
        //head ise..
        if (head == null) {
            System.out.println("there is no book");
            return;
        }
        if (temp.equals(node)) {
            head = temp.next;
            temp.next = null;
            return;
        }
        //arada ise...
        while (temp.next != null) {
            if (temp.next.equals(node)) {
                temp.next = node.next;
                node.next = null;

                //silme işkemi
                return;
            }
            temp = temp.next;
            
        }
        
    }
    
    public Node reachNode(Book reached) {
        Node temp = head;
        if (temp == null) {
            System.out.println("LL is empty");
            return null;
        }
        
        while (temp.next != null) {
            if (temp.data.equals(reached)) {
                return temp;
            }
            temp = temp.next;
            
        }
        if (temp.data.equals(reached)) {
            return temp;
        }
        System.out.println("The Searched Book isn't exist");
        return null;
        
    }
    
    public void printList() {
        Node temp = head;
        if (temp != null) {
            while (temp.next != null) {
                
                temp.data.printBook();
                temp = temp.next;
            }
            temp.data.printBook();
            
        }
    }
    
}
