package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;


public class testArbolGen {
    
    public static void main(String[] args) {

        testArbolGen();

    }

    public static void testArbolGen(){

        ArbolGen a1 = new ArbolGen();

        System.out.println("Arbol " + a1.toString());
        a1.insertar(20, 20);
        a1.insertar(54, 20);
        a1.insertar(13, 20);
        a1.insertar(12, 13);
        a1.insertar(15, 13);
        a1.insertar(4, 54);
        a1.insertar(27, 54);
        a1.insertar(11, 54);
        a1.insertar(17, 27);

        

        System.out.println("Arbol " + "\n" + a1.toString());

        System.out.println("Niveles 1 y 2: " + a1.listarHastaNivel(1).toString());

/*         System.out.println("Listado preorden: " + a1.listarPreorden());
        System.out.println("Listado inorden: " + a1.listarInorden());
        System.out.println("Listado posorden: " + a1.listarPosorden());
        System.out.println("Listado por niveles: " + a1.listarPorNiveles());

        System.out.println("C pertenece? espera true: " + a1.pertenece('C'));
        System.out.println("Q pertenece? espera true: " + a1.pertenece('Q'));
        System.out.println("R pertenece? espera false: " + a1.pertenece('R'));


        System.out.println("Padre de A espera null: " + a1.padre('A'));
        System.out.println("Padre de B espera A: " + a1.padre('B'));
        System.out.println("Padre de L espera F: " + a1.padre('L'));
        System.out.println("Padre de Q espera M: " + a1.padre('Q'));
        System.out.println("Padre de X espera null: " + a1.padre('X'));

        System.out.println("Ancestros de A: " + a1.ancestros('A').toString());
        System.out.println("Ancestros de B: " + a1.ancestros('B').toString());
        System.out.println("Ancestros de C: " + a1.ancestros('C').toString());
        System.out.println("Ancestros de E: " + a1.ancestros('E').toString());
        System.out.println("Ancestros de K: " + a1.ancestros('K').toString());
        System.out.println("Ancestros de Q: " + a1.ancestros('Q').toString());
        System.out.println("Ancestros de O: " + a1.ancestros('O').toString());
        System.out.println("Ancestros de X: " + a1.ancestros('X').toString());


        System.out.println("Nivel de A espera 0: " + a1.nivel('A'));
        System.out.println("Nivel de B espera 1: " + a1.nivel('B'));
        System.out.println("Nivel de C espera 1: " + a1.nivel('C'));
        System.out.println("Nivel de D espera 1: " + a1.nivel('D'));
        System.out.println("Nivel de E espera 2: " + a1.nivel('E'));
        System.out.println("Nivel de F espera 2: " + a1.nivel('F'));
        System.out.println("Nivel de G espera 2: " + a1.nivel('G'));
        System.out.println("Nivel de H espera 2: " + a1.nivel('H'));
        System.out.println("Nivel de I espera 2: " + a1.nivel('I'));
        System.out.println("Nivel de J espera 3: " + a1.nivel('J'));
        System.out.println("Nivel de K espera 3: " + a1.nivel('K'));
        System.out.println("Nivel de L espera 3: " + a1.nivel('L'));
        System.out.println("Nivel de M espera 3: " + a1.nivel('M'));
        System.out.println("Nivel de N espera 3: " + a1.nivel('N'));
        System.out.println("Nivel de O espera 3: " + a1.nivel('O'));
        System.out.println("Nivel de P espera 4: " + a1.nivel('P'));
        System.out.println("Nivel de Q espera 4: " + a1.nivel('Q'));
        System.out.println("Nivel de X espera -1: " + a1.nivel('X'));

        System.out.println("Altura del árbol, espera 4: " + a1.altura());


        System.out.println("########## CLONE ##########");
        ArbolGen a2 = a1.clone();

        System.out.println("Arbol original: " + a1.toString());
        System.out.println("Arbol clonado: " + a2.toString());


        
*/    

    }

}
