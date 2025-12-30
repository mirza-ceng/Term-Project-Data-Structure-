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

    private BinaryNode rootByTitle;
    private BinaryNode rootByAuthor;

    public BookCatalogWithAVL_BST() {
        rootByTitle = null;
        rootByAuthor = null;
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

    private int compareTo(String item1, String item2) {
        return item1.compareToIgnoreCase(item2);

    }

    public void insert(Book item) {

        rootByTitle = insertRec(rootByTitle, item, true);
        rootByAuthor = insertRec(rootByAuthor, item, false);
    }

    private BinaryNode insertRec(BinaryNode root, Book item, Boolean isTitle) {
        if (root == null) {
            root = new BinaryNode(item);

            return root;
        }

        String compareField = isTitle ? item.getTitle() : item.getAuthor();
        String rootCompareField = isTitle ? root.data.getTitle() : root.data.getAuthor();

        if (compareTo(compareField, rootCompareField) < 0) {
            root.left = insertRec(root.left, item, isTitle);
        } else if (compareTo(compareField, rootCompareField) > 0) {
            root.right = insertRec(root.right, item, isTitle);
        }

        //update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);
        //in case of imbalance

        //LL
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        //RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        //RR
        if (balance < -1 && getBalance(root.right) <= 0) {

            return leftRotate(root);
        }
        //LR  
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        return root;
    }

    public Book searchByTitle(String item) {
        BinaryNode node = searchByTitleRec(rootByTitle, item);  // Önce node'u al
    if (node != null && node.data != null) {  // null kontrolü yap
        return node.data;
    }
    return null;
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
        BinaryNode node = searchByAuthorRec(rootByAuthor, item);
        if (node != null && node.data != null) {
            return node.data;
        }else{
            System.out.println("Book wasnt founded: " + item);
            return null;
    }      
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

    //---------------------------------------------------------------------------------------------------
    public void delete(Book item) {
        rootByTitle = deleteRec(rootByTitle, item, true);
        rootByAuthor = deleteRec(rootByAuthor, item, false);
    }

    private BinaryNode deleteRec(BinaryNode root, Book item, boolean isTitle) {
        if (root == null) {
            return null; // Kitap bulunamadı veya ağaç boş
        }

        // Arama/Dolaşma
        String compareField = isTitle ? item.getTitle() : item.getAuthor();
        String rootCompareField = isTitle ? root.data.getTitle() : root.data.getAuthor();

        if (compareTo(compareField, rootCompareField) < 0) {
            root.left = deleteRec(root.left, item, isTitle); // Sol alt ağacın yeni kökünü alır
        } else if (compareTo(compareField, rootCompareField) > 0) {
            root.right = deleteRec(root.right, item, isTitle); // Sağ alt ağacın yeni kökünü alır
        } // Düğüm Bulundu (root = silinecek düğüm)
        else {
            // Durum 1 & 2: 0 veya 1 çocuk
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // Durum 3: İki çocuk
                BinaryNode successor = findMin(root.right);
                root.data = successor.data;
                root.right = deleteRec(root.right, successor.data, isTitle);
            }
        }

        if (root == null) {
            return null;
        }

        root.height = 1 + Math.max(height(root.right), height(root.left));//hata

        int balance = getBalance(root);
        //Rotations
        //LL

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // LR 
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR 
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // RL 
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;

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
