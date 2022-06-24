package tests.lineales;

import lineales.dinamicas.Lista;

public class testLista {

    public static void main(String[] arg) {

        testLista();
        System.out.println("==================================================================================== ");
        
    }

    public static void testLista(){
        Lista l1 = new Lista();
        l1.insertar('A', 1);
        l1.insertar('B', 2);
        l1.insertar('C', 3);
        l1.insertar('D', 4);
        l1.insertar('E', 5);
        l1.insertar('F', 6);
        l1.insertar('G', 7);
        l1.insertar('H', 8);
        l1.insertar('I', 9);
        l1.insertar('J', 10);
        System.out.println("Lista original"+l1.toString());

    }


    
}
