/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.DataClasses;

/**
 *
 * @author 2005m
 */
public class WaitListWithQueue {
    
    class WaitListNode{
        int userId;
        int bookId;
        String requestDate;
        WaitListNode next;
        
        public WaitListNode(int userId, int bookId) {
            this.userId = userId;
            this.bookId = bookId;
            this.requestDate = java.time.LocalDate.now().toString();
            this.next = null;
        }
    }
    
    private WaitListNode front;
    private WaitListNode rear;
    private int size;
    
    public WaitListWithQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    public void addToWaitList(int userId,int bookId) {
        WaitListNode newNode = new WaitListNode(userId,bookId);
        if(front == null) {
            front = rear =newNode; 
        }else{
        rear.next = newNode;
        rear = newNode;
    }
        size++;
        System.out.println("Kullanıcı: " + userId + " bekleme listesine " + bookId
        + " IDli kitabı ekledi.");
    }
    
    public int[] getNextFromWaitList() {
        if (front == null) {
            System.out.println("Waitlist is empty!");
            return null;
        }
        
        int[] result = new int[2];
        result[0] = front.userId;  // userId
        result[1] = front.bookId;  // bookId
        
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
        }
        size--;
        System.out.println("Next: User " + result[0] + " for book " + result[1]);
        return result;
    }
    
    public void showWaitList() {
        if(front==null) {
            System.out.println("Waitlist ise empty!");
            return;
        }
        System.out.println("\n============= WAITLIST ==============");
        WaitListNode current = front;
        int pos = 1;
        while(current!= null) {
            System.out.println(pos + ". User: " + current.userId + "--> Book " + 
                    current.bookId);
            current = current.next;
            pos++;
        }
        System.out.println(" ");
    }
    
    public boolean removeUser(int userId, int bookId) {
        if(front == null) {
            return false;
        }
        if(front.userId == userId && front.bookId == bookId) {
            if(front==rear) {
                front = null;
                rear = null;
            }else{
                front = front.next;
            }
            size--;
            return true;
        }
        WaitListNode current = front;
        while (current.next != null) {
            if (current.next.userId == userId && current.next.bookId == bookId) {
                if (current.next == rear) {
                    rear = current;
                }
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    public boolean hasWaitersForBook(int bookId) {
        WaitListNode current = front;
        while (current != null) {
            if (current.bookId == bookId) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    //????????????????????????????????????????????????**
     public void peekNext() {
        if (front == null) {
            System.out.println("Waitlist is empty");
        } else {
            System.out.println("Next: User " + front.userId + 
                             " for book " + front.bookId);
        }
    }
     
     public int getSize() {
         return size;
     }
}
