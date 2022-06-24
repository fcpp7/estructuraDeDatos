package conjuntistas.tests;

import conjuntistas.dinamicas.Hash;

public class TestHashAbierto {

    public static void main(String[] arg) {
		testHashAbierto();
	}

    public static void testHashAbierto(){

        Hash t1 = new Hash();

        System.out.println("Tabla hash, espera vacía: " + t1.toString());

        t1.insertar(3);
        t1.insertar(5);
        t1.insertar(13);
        t1.insertar(6);
        t1.insertar(25);
        t1.insertar(35);

        System.out.println("Tabla hash: " + t1.toString());

        t1.eliminar(6);
        t1.eliminar(13);

        System.out.println("Tabla hash: " + t1.toString());

/*         System.out.println("Elemento 3 encontrado, espera true: " + t1.pertenece(3));
        t1.eliminar(3);
        System.out.println("Elemento 3 encontrado, espera false: " + t1.pertenece(3));
        t1.eliminar(15);

        System.out.println("Tabla hash: " + t1.toString());
*/
    }

    
}
