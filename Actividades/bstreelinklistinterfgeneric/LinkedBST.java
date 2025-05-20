package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.*;

public class LinkedBST<E> implements BinarySearchTree<E> {

    // 1. Clase interna Node
    class Node {
        public E data;
        public Node left, right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // 2. Atributo raíz
    private Node root;

    // 3. Constructor
    public LinkedBST() {
        this.root = null;
    }

    // 4. Insertar
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node insert(Node node, E data) throws ItemDuplicated {
        if (node == null) return new Node(data);

        int cmp = ((Comparable<E>) data).compareTo(node.data);
        if (cmp == 0)
            throw new ItemDuplicated("Elemento duplicado: " + data);
        else if (cmp < 0)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);

        return node;
    }

    // 5. Buscar
    @Override
    public E search(E data) throws ItemNoFound {
        return search(root, data);
    }

    private E search(Node node, E data) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("Elemento no encontrado: " + data);

        int cmp = ((Comparable<E>) data).compareTo(node.data);
        if (cmp == 0)
            return node.data;
        else if (cmp < 0)
            return search(node.left, data);
        else
            return search(node.right, data);
    }

    // 6. Eliminar
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("El árbol está vacío.");
        root = delete(root, data);
    }

    private Node delete(Node node, E data) {
        if (node == null) return null;

        int cmp = ((Comparable<E>) data).compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null && node.right == null)
                return null;
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // 7. In-Orden
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("Visitar la raíz: " + node.data);
            inOrder(node.right);
        }
    }

    public void printInOrder() {
        System.out.println("Recorrido en In-Orden:");
        inOrder(root);
    }

    // 8. Pre-Orden
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println("Visitar la raíz: " + node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void printPreOrder() {
        System.out.println("Recorrido en Pre-Orden:");
        preOrder(root);
    }

    // 9. Post-Orden
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println("Visitar la raíz: " + node.data);
        }
    }

    public void printPostOrder() {
        System.out.println("Recorrido en Post-Orden:");
        postOrder(root);
    }

    // 10. Mínimo y máximo
    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("Subárbol vacío: no se puede encontrar el mínimo.");

        while (node.left != null) {
            node = node.left;
        }

        return search(node.data);
    }

    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("Subárbol vacío: no se puede encontrar el máximo.");

        while (node.right != null) {
            node = node.right;
        }

        return search(node.data);
    }

    public E getMin() throws ItemNoFound {
        return findMinNode(root);
    }

    public E getMax() throws ItemNoFound {
        return findMaxNode(root);
    }

    // Método requerido por la interfaz
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // toString como inorden
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString().trim();
    }

    private void toString(Node node, StringBuilder sb) {
        if (node != null) {
            toString(node.left, sb);
            sb.append(node.data).append(" ");
            toString(node.right, sb);
        }
    }
}
