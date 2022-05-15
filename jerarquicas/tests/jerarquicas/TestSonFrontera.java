package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;

import lineales.dinamicas.*;

public class TestSonFrontera {

    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";

    public static void main(String args[]) {

        System.out.println("**************************************************************");
        System.out.println("*                  Test Son Frontera                        *");
        System.out.println("**************************************************************");

        ArbolGen a1 = new ArbolGen();

        Lista l1 = new Lista();

        System.out.println("Test lista vacía, árbol vacío espera false: --> " + ((!a1.sonFrontera(l1)) ? sOk : sErr));

        l1.insertar('A', 1);
        System.out.println("Lista: " + l1.toString()+ " árbol vacío espera false: --> " + ((!a1.sonFrontera(l1)) ? sOk : sErr));


        a1.insertar('A', 'A');
        a1.insertar('D', 'A');
        a1.insertar('C', 'A');
        a1.insertar('B', 'A');
        a1.insertar('F', 'B');
        a1.insertar('E', 'B');
        a1.insertar('H', 'D');
        a1.insertar('G', 'D');
        a1.insertar('I', 'H');
        a1.insertar('J', 'I');

        System.out.println("Árbol : " + "\n" + a1.toString());
        

        l1.vaciar();
        System.out.println("Lista vacía, árbol completo, espera false: -->" + ((!a1.sonFrontera(l1)) ? sOk : sErr));

        l1.insertar('A', 1);
        System.out.println("Lista: " + l1.toString() +", sonFrontera espera false: -->" +((!a1.sonFrontera(l1)) ? sOk : sErr));


        l1.eliminar(1);
        l1.insertar('C', 1);
        System.out.println("Lista : " + l1.toString() + ", sonFrontera espera true: -->" +((a1.sonFrontera(l1)) ? sOk : sErr));

        l1.insertar('E', 2);
        l1.insertar('F', 3);
        l1.insertar('G', 4);
        l1.insertar('J', 5);

        System.out.println("Lista con todos los elementos de la frontera: " + l1.toString() + ", sonFrontera espera true: -->" +((a1.sonFrontera(l1)) ? sOk : sErr));


        l1.insertar('A', 6);
        System.out.println("Lista: " + l1.toString() + ", sonFrontera espera false: -->" +(!(a1.sonFrontera(l1)) ? sOk : sErr));



    }
}