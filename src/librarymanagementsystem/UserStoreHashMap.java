/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

import librarymanagementsystem.Entity.User;

/**
 *
 * @author 2005m
 */
public class UserStoreHashMap {

    class Cell {

        int key;   //userId
        User value;
        Cell next;//for collision

        public Cell(int key, User value) {
            this.key = key;
            this.value = value;
        }

    }
    private int capacity;
    private Cell[] table;

    public UserStoreHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Cell[capacity];
    }

    private int getIndex(int key) {
        return Math.abs(key) % this.capacity;
    }

    public void put(User u) {
        int key = u.getUserId();
        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new Cell(key, u);

        } else {
            Cell current = table[index];

            while (current != null) {
                if (current.key == key) {//orUpdate
                    current.value = u;
                    return;
                }
                if (current.next == null) {
                    break;
                }

                current = current.next;

            }
            current.next = new Cell(key, u);

        }

    }

    public User get(int key) {
        int index = getIndex(key);
        Cell current = table[index];
        while (current != null) {

            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

}
