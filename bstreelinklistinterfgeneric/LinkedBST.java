package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.*;

public class LinkedBST<E> implements BinarySearchTree<E> {

    // 1. Clase interna Node
    class Node {
        public E data;
        public Node left;
        public Node right;

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
        // Si el nodo está vacío, se crea uno nuevo con el dato
        if (node == null) return new Node(data);

        // Se compara el nuevo dato con el dato del nodo actual
        int cmp = ((Comparable<E>) data).compareTo(node.data);

        if (cmp == 0) {
            // Si son iguales, el dato ya existe: lanzar excepción
            throw new ItemDuplicated("Elemento duplicado: " + data);
        } else if (cmp < 0) {
            // Si es menor, insertar en el subárbol izquierdo
            node.left = insert(node.left, data);
        } else {
            // Si es mayor, insertar en el subárbol derecho
            node.right = insert(node.right, data);
        }

        // Se devuelve el nodo actual con sus hijos actualizados
        return node;
    
    }

    // 5. Buscar
    @Override
    public E search(E data) throws ItemNoFound {
        return search(root, data);
    }

    private E search(Node node, E data) throws ItemNoFound {
        // Si el nodo es nulo, el valor no está en el árbol
        if (node == null)
            throw new ItemNoFound("No se encontró el elemento: " + data);

        // Se compara el dato buscado con el del nodo actual
        int cmp = ((Comparable<E>) data).compareTo(node.data);

        if (cmp == 0)
            // El valor es igual: lo encontró
            return node.data;
        else if (cmp < 0)
            // Si es menor: buscar en el subárbol izquierdo
            return search(node.left, data);
        else
            // Si es mayor: buscar en el subárbol derecho
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
        // Si el nodo actual es null, no hay nada que eliminar
        if (node == null) return null;

        // Comparar el dato a eliminar con el dato del nodo actual
        int cmp = ((Comparable<E>) data).compareTo(node.data);

        if (cmp < 0) {
            // Si el dato es menor, buscar en el subárbol izquierdo
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            // Si el dato es mayor, buscar en el subárbol derecho
            node.right = delete(node.right, data);
        } else {
            // Si cmp == 0, el nodo actual es el que se debe eliminar

            // Caso 1 y 2: uno o ningún hijo
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Caso 3: el nodo tiene dos hijos
            // Buscar el nodo mínimo del subárbol derecho (sucesor inorden)
            Node min = findMin(node.right);

            // Reemplazar el dato actual con el del sucesor
            node.data = min.data;

            // Eliminar el nodo duplicado en el subárbol derecho
            node.right = delete(node.right, min.data);
        }

        // Retornar el nodo actualizado
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
            System.out.println(" la raíz: " + node.data);
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
            System.out.println(" la raíz: " + node.data);
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
            System.out.println(" la raíz: " + node.data);
        }
    }

    public void printPostOrder() {
        System.out.println("Recorrido en Post-Orden:");
        postOrder(root);
    }

    // 10. Mínimo y máximo
    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("");

        while (node.left != null) {
            node = node.left;
        }

        return search(node.data);
    }

    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("");

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
