package conjuntistas.tests;

import conjuntistas.dinamicas.TablaHash;

public class TestTablaHash {
    public static void main(String[] arg) {
		testTablaHash();
	}

    public static void testTablaHash(){

        TablaHash t1 = new TablaHash();

        System.out.println("Tabla hash, espera vacía: " + t1.toString());

        t1.insertar(3);
        t1.insertar(5);
        t1.insertar(7);
        t1.insertar(9);
        t1.insertar(15);
        t1.insertar(25);
        t1.insertar(18);


        System.out.println("Tabla hash: " + t1.toString());

        System.out.println("Elemento 3 encontrado, espera true: " + t1.pertenece(3));
        t1.eliminar(3);
        System.out.println("Elemento 3 encontrado, espera false: " + t1.pertenece(3));
        t1.eliminar(15);

        System.out.println("Tabla hash: " + t1.toString());

    }


}
