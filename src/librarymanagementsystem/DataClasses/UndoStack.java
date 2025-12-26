/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.DataClasses;

import librarymanagementsystem.Entity.Loan;

/**
 *
 * @author 2005m
 */
public class UndoStack {
    //create using Stack....

    Node head;

    public class Node {

        Node next;
        Loan data;

        public Node(Loan data) {
            this.data = data;
            this.next = null;
        }

    }

    public void push(Loan data) {
        Node newNode = new Node(data);

        if (head == null) {
            newNode = head;
        } else {
            newNode.next = head;
            head = newNode;
        }

    }

    public Loan pop() {
        Node t = head;
        if (t != null) {

            if (head.next != null) {
                head = head.next;
                t.next = null;

            } else {
                head = null;
            }

            return t.data;
        }
        return null;
    }

    public Loan top() {
        if (head!=null) {
           return head.data; 
        }return null;
        
    }

    public String undoLastOperation() {
        Loan loan = pop();
        String cancelledOperation = "-";
        if (loan != null) {
            loan.printLoanInfo();

            if (loan.isIsLend()) {

                System.out.println("Kitap  alımı iptal edildi.");
                cancelledOperation = "LEND";
                //go return

            } else {
                System.out.println("Kitap iade iptal edildi.");
                cancelledOperation = "RETURN";
                //go lend

            }

        }
        return cancelledOperation;
    }

}
