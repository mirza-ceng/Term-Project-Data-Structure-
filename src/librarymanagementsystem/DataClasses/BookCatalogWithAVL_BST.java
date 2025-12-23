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
public class BookCatalogWithAVL_BST {

    // isme göre sıralanmış bst yapısı
    class BinaryNode {

        int height;
        Book data;
        BinaryNode left, right;

        public BinaryNode() {
            height = 1;
            data = null;
            left = right = null;
        }

        public BinaryNode(Book item) {
            height = 1;
            data = item;
            left = right = null;
        }

        public BinaryNode(Book item, BinaryNode leftChild, BinaryNode rightChild) {
            data = item;
            left = leftChild;
            right = rightChild;
        }

    }

    private BinaryNode root;

    public BookCatalogWithAVL_BST() {
        root = null;
    }

    public int height(BinaryNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(BinaryNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private BinaryNode rightRotate(BinaryNode y) {
        //y is current root
        //x is left child of y
        //T2 is right child of x

        BinaryNode x = y.left;
        BinaryNode T2 = x.right;

        //rotation
        x.right = y;
        y.left = T2;

        //update height;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        //now x is final root
        return x;
    }

    private BinaryNode leftRotate(BinaryNode x) {
        //x is current root
        //y is right child of x
        //T2 is left child of y
        BinaryNode y = x.right;
        BinaryNode T2 = y.left;

        //rotation
        y.left = x;
        x.right = T2;

        //update height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        //y is final root
        return y;
    }

    public void insert(Book item) {
        root = insertRec(root, item);
    }

    private int compareTo(String item1, String item2) {
        return item1.compareToIgnoreCase(item2);

    }

    private BinaryNode insertRec(BinaryNode root, Book item) {
        if (root == null) {
            root = new BinaryNode(item);

            return root;
        }
        if (compareTo(item.getTitle(), root.data.getTitle()) < 0) {
            root.left = insertRec(root.left, item);
        } else if (compareTo(item.getTitle(), root.data.getTitle()) > 0) {
            root.right = insertRec(root.right, item);
        }

        //update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);
        //in case of imbalance
        //LL
        if (balance > 1 && compareTo(item.getTitle(), root.left.data.getTitle()) < 0) {
            return rightRotate(root);
        }
        //RL
        if (balance < -1 && compareTo(item.getTitle(), root.right.data.getTitle()) < 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        //RR
        if (balance < -1 && compareTo(item.getTitle(), root.right.data.getTitle()) > 0) {

            return leftRotate(root);
        }
        //LR  
        if (balance > 1 && compareTo(item.getTitle(), root.left.data.getTitle()) > 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        return root;
    }

    public Book searchByTitle(String item) {

        return searchByTitleRec(root, item).data;
    }

    private BinaryNode searchByTitleRec(BinaryNode root, String item) {
        if (root == null) {
            System.out.println("Book wasn't founded: " + item);
            return null;
        } else {
            if (compareTo(item, root.data.getTitle()) == 0) {
                System.out.println("Book was founded:" + root.data.getTitle());
                return root;
            } else {
                if (compareTo(item, root.data.getTitle()) < 0) {
                    return searchByTitleRec(root.left, item);
                } else {
                    if (compareTo(item, root.data.getTitle()) > 0) {

                        return searchByTitleRec(root.right, item);

                    }

                }
            }
        }
        return null;

    }

    public Book searchByAuthor(String item) {

        return searchByAuthorRec(root, item).data;
    }

    private BinaryNode searchByAuthorRec(BinaryNode root, String item) {
        if (root == null) {
            System.out.println("Book wasn't founded: " + item);
            return null;
        } else {
            if (compareTo(item, root.data.getAuthor()) == 0) {
                System.out.println("Book was founded:" + root.data.getTitle());
                return root;
            } else {
                if (compareTo(item, root.data.getAuthor()) < 0) {
                    return searchByAuthorRec(root.left, item);
                } else {
                    if (compareTo(item, root.data.getAuthor()) > 0) {

                        return searchByAuthorRec(root.right, item);

                    }

                }
            }
        }
        return null;

    }

    public void delete(Book item) {
        deleteRec(root, item);
    }

    //change and inject  for AVL
    //https://gemini.google.com/app/1561a55dcd9430f5
    private BinaryNode deleteRec(BinaryNode root, Book item) {
        if (root == null) {
            return null; // Kitap bulunamadı veya ağaç boş.
        }

        // A. Arama/Dolaşma (root.left VEYA root.right'ı güncellemek için dönüş değerini kullanırız)
        if (compareTo(item.getTitle(), root.data.getTitle()) < 0) {
            root.left = deleteRec(root.left, item); // Sol alt ağacın yeni kökünü alır.
        } else if (compareTo(item.getTitle(), root.data.getTitle()) > 0) {
            root.right = deleteRec(root.right, item); // Sağ alt ağacın yeni kökünü alır.
        } // B. Düğüm Bulundu (root = silinecek düğüm)
        else {
            // Durum 1 & 2: 0 veya 1 çocuk
            if (root.left == null) {
                return root.right; // Tek çocuğu (sağ) döndür veya null döndür (0 çocuk).
            } else if (root.right == null) {
                return root.left; // Tek çocuğu (sol) döndür.
            }

            // Durum 3: İki çocuk
            // 1. Successor'ı bul (en küçük sağ alt ağaç)
            BinaryNode successor = findMin(root.right);

            // 2. Successor'ın değerini silinecek düğüme kopyala
            root.data = successor.data; // ÖNEMLİ: Değer kopyalanır.

            // 3. Successor'ı orijinal konumundan sil
            // Successor, sağ alt ağacın yeni kökü olacak şekilde deleteRec'i çağırırız.
            root.right = deleteRec(root.right, successor.data);
        }

        return root; // Ağacın kökünü (veya alt ağacın kökünü) döndür.
    }

    private BinaryNode findMin(BinaryNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;

        }

        return root;

    }

}
