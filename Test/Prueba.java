package Test;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.*;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            // Insertar elementos
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);

            // Mostrar árbol en inorden
            System.out.println("\n Recorrido In-Orden ");
            bst.printInOrder();

            // Mostrar árbol en preorden
            System.out.println("\n Recorrido Pre-Orden ");
            bst.printPreOrder();

            // Mostrar árbol en postorden
            System.out.println("\n Recorrido Post-Orden ");
            bst.printPostOrder();

            // Mostrar el árbol como String (inorden)
            System.out.println("\n toString() del árbol ");
            System.out.println(bst.toString());

            // Buscar un elemento
            System.out.println("\n  Búsqueda ");
            System.out.println("Buscar numero: " + bst.search(75));

            
            
            
            // Mostrar mínimo y máximo
            System.out.println("\n Mínimo y Máximo ");
            System.out.println("Mínimo: " + bst.getMin());
            System.out.println("Máximo: " + bst.getMax());

            
            
            
            // Eliminar un elemento
            System.out.println("\n  Eliminar nodo 100 ");
            bst.delete(100);
            System.out.println("");
            bst.printInOrder();

            
            
            
            // Intentar insertar duplicado
            System.out.println("\n Insertar un ndo duplicado ");
            bst.insert(400); // Esto lanzará excepción

            
            
            
            
            
            
        } catch (ItemDuplicated e) {
            System.out.println("⚠️ Duplicado: " + e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println("❌ No encontrado: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.out.println("🚫 Árbol vacío: " + e.getMessage());
        }
    }
}
