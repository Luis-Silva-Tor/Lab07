package Test;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.*;

public class Prueba {

    public static void main(String[] args) {
        try {
            // Creamos el árbol de tipo String
            LinkedBST<String> tree = new LinkedBST<>();

            // Insertar nodos en orden forzado para formar una jerarquía visual similar
            // Insertar nodos en orden forzado para formar una jerarquía visual similar
            tree.insert("Sales");
            tree.insert("Domestic");
            tree.insert("International");
            tree.insert("Canada");
            tree.insert("S. America");
            tree.insert("Overseas");
            tree.insert("Africa");
            tree.insert("Europe");
            tree.insert("Asia");
            tree.insert("Australia");


            // Imprimir con parenthesize()
            System.out.println("ARBOL:");
            tree.parenthesize();

        } catch (ItemDuplicated e) {
            System.err.println("Duplicado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}
