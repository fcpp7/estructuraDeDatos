package conjuntistas.tests;

import conjuntistas.dinamicas.*;

public class TestArbolHeapMin {
    
	static String sOk = "OK!", sErr = "ERROR";

	public static void main(String[] arg) {
		testArbolHeaMin();
	}

	public static void testArbolHeaMin() {

        System.out.println("COMIENZO TEST ARBOL HEAP");
		ArbolHeapMin ah1 = new ArbolHeapMin();
		System.out.println("Arbol vacio: \t\t\t\t\t\t\t--> " + ah1.toString());
		boolean exito = true;

        ah1.insertar(10);
        ah1.insertar(3);
        ah1.insertar(5);
        ah1.insertar(74);
        ah1.insertar(2);
        ah1.insertar(7);
        ah1.insertar(1);
        ah1.insertar(100);

        System.out.println("Arbol completo: \t\t\t\t\t\t\t--> " + ah1.toString());






    }


}
