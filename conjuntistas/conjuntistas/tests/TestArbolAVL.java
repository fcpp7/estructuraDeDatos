package conjuntistas.tests;

import conjuntistas.dinamicas.*;

public class TestArbolAVL {

    static String sOk = "OK!", sErr = "ERROR";

	public static void main(String[] arg) {
		testArbolAVL();
	}

	public static void testArbolAVL() {

		System.out.println("COMIENZO TEST ARBOL HEAP");
		ArbolAVL abb1 = new ArbolAVL();
		System.out.println("Arbol vacio: \t\t\t\t\t\t\t--> " + abb1.toString());
		boolean exito = true;

        abb1.insertar(4);
        abb1.insertar(2);
		abb1.insertar(8);
		abb1.insertar(6);
		abb1.insertar(10);
		abb1.insertar(5);


		System.out.println("Arbol completo: \n" + abb1.toString());


/*

 		System.out.println("Prueba pertenece 1 espera true: \t" + abb1.pertenece(1));
		System.out.println("Prueba pertenece 99 espera false: \t" + abb1.pertenece(99));
		System.out.println("Prueba pertenece 34 espera true: \t" + abb1.pertenece(34));

		System.out.println("Prueba listar: \t" + abb1.listar().toString());
		System.out.println("Prueba listar entre [12,66]: \t" + abb1.listarRango(12, 66).toString());		
		ArbolAVL abb2 = abb1.clone();

		System.out.println("Clonado : " + abb2.toString());

 		abb2.eliminar(8);
		System.out.println("Clonado sin 8 : " + abb2.toString());
		System.out.println("Original, mantiene 8 : " + abb1.toString());

 *//*		System.out.println("Prueba elemento mínimo espera 1: \t" + abb1.minimoElem());
		System.out.println("Prueba elemento máximo espera 96: \t" + abb1.maximoElem());

		System.out.println("Prueba eliminar 1 espera true: \t" + abb1.eliminar(1));
		System.out.println("Arbol sin 1: \t" + abb1.toString());
		System.out.println("Prueba eliminar 55 espera true: \t" + abb1.eliminar(55));
		System.out.println("Arbol sin 55: \t" + abb1.toString());
		System.out.println("Prueba eliminar 96 espera true: \t" + abb1.eliminar(96));
		System.out.println("Arbol sin 96: \t" + abb1.toString());

		System.out.println("Prueba elemento mínimo espera 3: \t" + abb1.minimoElem());
		System.out.println("Prueba elemento máximo espera 73: \t" + abb1.maximoElem());

		System.out.println("Prueba listar: \t" + abb1.listar().toString());
		System.out.println("Prueba listar entre [2,15]: \t" + abb1.listarRango(2, 15).toString());
*/ 	}
    
}
