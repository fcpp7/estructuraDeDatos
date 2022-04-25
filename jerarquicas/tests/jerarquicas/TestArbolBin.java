package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.Lista;

public class TestArbolBin {

    public static void main(String[] args) {

        testArbolBin();

    }

    public static void testArbolBin() {

        ArbolBin arbol = new ArbolBin();

       arbol.insertar('A', 'A', 'I');
        arbol.insertar('B', 'A', 'I');
        arbol.insertar('C', 'A', 'D');
        arbol.insertar('D', 'B', 'I');
        arbol.insertar('E', 'C', 'I');
        arbol.insertar('F', 'C', 'D');
        arbol.insertar('G','E', 'I');
        arbol.insertar('H', 'E', 'D');

        Lista pre = arbol.listarPreorden();
        Lista in = arbol.listarInOrden();
        Lista pos = arbol.listarPosOrden();
        Lista porNivel = arbol.listarNiveles();

        System.out.println("=============== to string ====================");
        System.out.println(arbol.toString());

        System.out.println("=============== Preorden ====================");
        System.out.println(pre.toString());

        System.out.println("=============== Inorden ====================");
        System.out.println(in.toString());

        System.out.println("=============== Posorden ====================");
        System.out.println(pos.toString());

        System.out.println("=============== Por nivel ====================");
        System.out.println(porNivel.toString());

        System.out.println("=============== Altura ====================");
        System.out.println(arbol.altura());


        System.out.println("=============== Nivel A ====================");
        System.out.println(arbol.nivel('A'));

        System.out.println("=============== Nivel B ====================");
        System.out.println(arbol.nivel('B'));

        System.out.println("=============== Nivel C ====================");
        System.out.println(arbol.nivel('C'));

        System.out.println("=============== Nivel D ====================");
        System.out.println(arbol.nivel('D'));

        System.out.println("=============== Nivel E ====================");
        System.out.println(arbol.nivel('E'));

        System.out.println("=============== Nivel F ====================");
        System.out.println(arbol.nivel('F'));

        System.out.println("=============== Nivel G ====================");
        System.out.println(arbol.nivel('G'));

        System.out.println("=============== Nivel H ====================");
        System.out.println(arbol.nivel('H'));


        System.out.println("=============== CLONAR ====================");
        ArbolBin arbolClonado = new ArbolBin();
        arbolClonado = arbol.clone();
        System.out.println(arbolClonado.toString());

        System.out.println("=============== PADRE A ====================");
        System.out.println(arbol.padre('A'));

        System.out.println("=============== PADRE B ====================");
        System.out.println(arbol.padre('B'));

        System.out.println("=============== PADRE C ====================");
        System.out.println(arbol.padre('C'));

        System.out.println("=============== PADRE D ====================");
        System.out.println(arbol.padre('D'));

        System.out.println("=============== PADRE E ====================");
        System.out.println(arbol.padre('E'));

        System.out.println("=============== PADRE F ====================");
        System.out.println(arbol.padre('F'));

        System.out.println("=============== PADRE G ====================");
        System.out.println(arbol.padre('G'));

        System.out.println("=============== PADRE H ====================");
        System.out.println(arbol.padre('H'));




    }

}