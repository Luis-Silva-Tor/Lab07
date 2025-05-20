package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.*;
import java.util.Queue;
import java.util.LinkedList;

public class LinkedBST<E> implements BinarySearchTree<E> {
    // Representa cada nodo del árbol

    // Nodo interno
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
    // Nodo raíz del árbol

    private Node root;
    // Constructor

    public LinkedBST() {
        root = null;
    }

    //  = OPERACIONES BÁSICAS DEL BST =====

    // Inserta un dato, lanza excepción si está duplicado DE LA INTERFAZ 

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

    // Busca un dato, lanza excepción si no se encuentra
    public E search(E data) throws ItemNoFound {
        return search(root, data);
    }

    private E search(Node node, E data) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("No se encontró el elemento: " + data);
        int cmp = ((Comparable<E>) data).compareTo(node.data);
        if (cmp == 0)
            return node.data;
        else if (cmp < 0)
            return search(node.left, data);
        else
            return search(node.right, data);
    }

    // Elimina un nodo con el valor indicado
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("El árbol está vacío.");
        root = delete(root, data);
    }

    private Node delete(Node node, E data) {
        if (node == null) return null;
        int cmp = ((Comparable<E>) data).compareTo(node.data);
        if (cmp < 0)
            node.left = delete(node.left, data);
        else if (cmp > 0)
            node.right = delete(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

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

    // Destruye todos los nodos del árbol

    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null)
            throw new ExceptionIsEmpty("El árbol ya está vacío.");
        root = null;
        System.out.println("🌳 Todos los nodos han sido eliminados.");
    }
    // Cuenta todos los nodos del árbol (incluye hojas)

    public int countAllNodes() {
        return countAllNodes(root);
    }
    // Cuenta solo los nodos no-hoja
 
    private int countAllNodes(Node node) {
        if (node == null) return 0;
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }
    // Retorna la altura del subárbol con raíz igual a x

    public int countNodes() {
        return countNodes(root);
    }
    // Retorna la cantidad de nodos en un nivel específico

    private int countNodes(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int height(E x) {
        Node node = root;
        while (node != null) {
            int cmp = ((Comparable<E>) x).compareTo(node.data);
            if (cmp == 0) break;
            else if (cmp < 0) node = node.left;
            else node = node.right;
        }
        if (node == null) return -1;
        return calculateHeight(node);
    }

    private int calculateHeight(Node node) {
        if (node == null) return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return height;
    }

    public int amplitude(int nivel) {
        if (nivel < 0) return 0;
        return countNodesAtLevel(root, nivel, 0);
    }

    private int countNodesAtLevel(Node node, int targetLevel, int currentLevel) {
        if (node == null) return 0;
        if (currentLevel == targetLevel) return 1;
        return countNodesAtLevel(node.left, targetLevel, currentLevel + 1)
             + countNodesAtLevel(node.right, targetLevel, currentLevel + 1);
    }

    // ========== EJERCICIO 02 ==========
    // Retorna el área del árbol: hojas * altura

    public int areaBST() {
        if (root == null) return 0;

        int hojas = 0;
        int altura = calculateHeight(root);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == null && current.right == null)
                hojas++;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return hojas * altura;
    }
    // Dibuja el árbol visualmente con indentación

    public void drawBST() {
        drawBST(root, 0);
    }

    private void drawBST(Node node, int nivel) {
        if (node != null) {
            drawBST(node.right, nivel + 1);
            System.out.println("    ".repeat(nivel) + "📍 " + node.data);
            drawBST(node.left, nivel + 1);
        }
    }

    // JERCICIO 03 
    // Imprime el árbol con sangría y paréntesis (estilo jerárquico)

    public void parenthesize() {
        parenthesize(root, 0);
    }

    private void parenthesize(Node node, int level) {
        if (node == null) return;

        System.out.println("  ".repeat(level) + node.data);

        if (node.left != null || node.right != null) {
            System.out.println("  ".repeat(level) + "(");
            if (node.left != null)
                parenthesize(node.left, level + 1);
            if (node.right != null)
                parenthesize(node.right, level + 1);
            System.out.println("  ".repeat(level) + ")");
        }
    }
    // Retorna el valor mínimo del árbol

    public E getMin() throws ItemNoFound {
        return findMinNode(root);
    }
    // Retorna el valor máximo del árbol

    public E getMax() throws ItemNoFound {
        return findMaxNode(root);
    }

    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("No hay mínimo.");
        while (node.left != null) node = node.left;
        return node.data;
    }

    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("No hay máximo.");
        while (node.right != null) node = node.right;
        return node.data;
    }
}
