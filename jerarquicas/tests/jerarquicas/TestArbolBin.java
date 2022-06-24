package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolBin;
/*import lineales.dinamicas.Lista;*/

public class TestArbolBin {

	/**
	 *
	 * @author Facundo Perez Penas FAI-1021
	 * 
	 */

	static String sOk = "OK!", sErr = "ERROR";


    public static void main(String[] args) {

        testArbolBin();

    }

    public static void testArbolBin() {

        System.out.println("COMIENZO TEST ARBOL BINARIO");

        ArbolBin arbol = new ArbolBin();


        System.out.println("\n\n=============== TEST TOSTRING ====================");
        System.out.println("Arbol vacio: \t\t\t\t\t\t\t--> " + arbol.toString());

        System.out.println("\n\n=============== TEST INSERTAR ====================");
		System.out.println("Pone " + 56 + " espera true: \t\t\t\t\t" + ((arbol.insertar('A', 'A', 'I') == true) ? sOk : sErr));
		System.out.println("Pone " + 13 + " espera true: \t\t\t\t\t" + ((arbol.insertar('B', 'A', 'I') == true) ? sOk : sErr));
        System.out.println("Pone " + 78 + " espera true: \t\t\t\t\t" + ((arbol.insertar('C', 'A', 'D') == true) ? sOk : sErr));
        System.out.println("Pone " + 7 + " espera true: \t\t\t\t\t" + ((arbol.insertar('D', 'B', 'I') == true) ? sOk : sErr));
        System.out.println("Pone " + 24 + " espera true: \t\t\t\t\t" + ((arbol.insertar('E', 'C', 'I') == true) ? sOk : sErr));
        System.out.println("Pone " + 100 + " espera true: \t\t\t\t\t" + ((arbol.insertar('F', 'C', 'D') == true) ? sOk : sErr));
        System.out.println("Pone " + 15 + " espera true: \t\t\t\t\t" + ((arbol.insertar('G', 'E', 'I') == true) ? sOk : sErr));
        
        System.out.println("\n\n======================TEST TO STRING =====================");
		System.out.println("Imprime por pantalla los elementos del arbol: \n" + arbol.toString());

/*
        System.out.println("\n\n======================TEST PREORDEN =====================");
        Lista pre = arbol.listarPreorden();
		System.out.println("Genera una lista ordenada por preorden, espera: [A, B, D, C, E, G, H, F]:" + pre.toString());

        System.out.println("\n\n======================TEST INORDEN =====================");
        Lista in = arbol.listarInorden();
		System.out.println("Genera una lista ordenada por inorden, espera: [D, B, A, G, E, H, C, F]:" + in.toString());

        System.out.println("\n\n======================TEST POSORDEN =====================");
        Lista pos = arbol.listarPosorden();
		System.out.println("Genera una lista ordenada por posorden, espera: [D, B, G, H, E, F, C, A]:" + pos.toString());

        System.out.println("\n\n======================TEST ORDEN POR NIVEL =====================");
        Lista porNivel = arbol.listarPorNiveles();
        System.out.println("Genera una lista ordenada por niveles, espera: [A, B, C, D, E, F, G, H]:" + porNivel.toString());

        System.out.println("\n\n=============== TEST ALTURA ====================");
		System.out.println("Retorna la altura del árbol, espera 3: \t\t\t\t\t" + ((arbol.altura() == 3) ? sOk : sErr));        


        System.out.println("\n\n=============== TEST NIVEL ====================");
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "X" + ":\t\t\t\t\t" + ((arbol.nivel('X') == -1) ? sOk : sErr));        
		System.out.println("Retorna el nivel del nodo que contiene el elemento " + "A" + ":\t\t\t\t\t" + ((arbol.nivel('A') == 0) ? sOk : sErr));        
		System.out.println("Retorna el nivel del nodo que contiene el elemento " + "B" + ":\t\t\t\t\t" + ((arbol.nivel('B') == 1) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "C" + ":\t\t\t\t\t" + ((arbol.nivel('C') == 1) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "D" + ":\t\t\t\t\t" + ((arbol.nivel('D') == 2) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "E" + ":\t\t\t\t\t" + ((arbol.nivel('E') == 2) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "F" + ":\t\t\t\t\t" + ((arbol.nivel('F') == 2) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "G" + ":\t\t\t\t\t" + ((arbol.nivel('G') == 3) ? sOk : sErr));        
        System.out.println("Retorna el nivel del nodo que contiene el elemento " + "H" + ":\t\t\t\t\t" + ((arbol.nivel('H') == 3) ? sOk : sErr));        
        

        System.out.println("\n\n=============== TEST CLONAR ====================");
        ArbolBin arbolClonado = new ArbolBin();
        arbolClonado = arbol.clone();
        System.out.println("Imprime el arbol original \n" + (arbol.toString()));        
        System.out.println("Imprime el arbol clonado \n" + (arbolClonado.toString()));        


        System.out.println("\n\n=============== TEST PADRE ====================");
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "X" + ", espera null:\t" + (arbol.padre('X')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "A" + ", espera null:\t" + (arbol.padre('A')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "B" + ", espera 'A':\t" + (arbol.padre('B')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "C" + ", espera 'A':\t" + (arbol.padre('C')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "D" + ", espera 'B':\t" + (arbol.padre('D')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "E" + ", espera 'C':\t" + (arbol.padre('E')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "F" + ", espera 'C':\t" + (arbol.padre('F')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "G" + ", espera 'E':\t" + (arbol.padre('G')));        
        System.out.println("Retorna el elemento del nodo padre, del nodo que contiene el elemento: " + "H" + ", espera 'E':\t" + (arbol.padre('H')));        



 */
    }

}

