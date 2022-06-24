package tests.lineales;

import lineales.dinamicas.*;

public class Matematica {
    
    public static void main(String[] Args){

        Cola c = new Cola();
        c.poner('{');
        c.poner('5');
        c.poner('+');
        c.poner('[');
        c.poner('8');
        c.poner('*');
        c.poner('9');
        c.poner('-');
        c.poner('(');
        c.poner('4');
        c.poner('/');
        c.poner('2');
        c.poner(')');
        c.poner('+');
        c.poner('7');
        c.poner(']');
        c.poner('-');
        c.poner('1');
        c.poner('}');

        Cola c2 = new Cola();
        c2.poner('{');
        c2.poner('5');
        c2.poner('+');
        c2.poner('8');
        c2.poner('*');
        c2.poner('9');
        c2.poner('-');
        c2.poner('(');
        c2.poner('4');
        c2.poner('/');
        c2.poner('2');
        c2.poner(')');
        c2.poner('+');
        c2.poner('7');
        c2.poner(']');
        c2.poner('-');
        c2.poner('1');
        c2.poner('}');

        Cola c3 = new Cola();
     
        Cola c4 = new Cola();
        c4.poner('(');
        c4.poner('4');
        c4.poner('+');
        c4.poner('3');
        c4.poner('(');
        c4.poner(')');


        System.out.println("Balanceo cola 1: espera true: " + verificarBalanceo(c));
        System.out.println("Balanceo cola 2: espera false: " + verificarBalanceo(c2));
        System.out.println("Balanceo cola vacia: espera true: " + verificarBalanceo(c3));
        System.out.println("Balanceo cola 4: espera false: " + verificarBalanceo(c4));
    }

    public static boolean verificarBalanceo(Cola q){

        // Creamos un clone de la cola para ir sacandole elementos sin alterar la original
        Cola qAux = q.clone();

        // Creamos variables de balanceo para llave, corchete y parentesis
        int balLlave = 0;
        int balCorchete = 0;
        int balParentesis = 0;

        // Creamos colas vacias para cada llaves, corchetes y parentesis
        Cola cLlaves = new Cola();
        Cola cCorchetes = new Cola();
        Cola cPatenresis = new Cola();

        // Iteramos sobre la copia de la cola que ingreso por parametro.
        // Iremos guardando cada tipo de dato en su correspondiente cola.

        while(!qAux.esVacia()){
            if(qAux.obtenerFrente().equals('{') || qAux.obtenerFrente().equals('}')){
                // Si es una llave, ponemos la llave en la cola de llaves
                cLlaves.poner(qAux.obtenerFrente());
                // Sacamos el elemento de la cola auxiliar para seguir iterando
                qAux.sacar();
            } else if(qAux.obtenerFrente().equals('[') || qAux.obtenerFrente().equals(']')){
                // Si es un corchete, ponemos el corchete en la cola de corchete
                cCorchetes.poner(qAux.obtenerFrente());
                // Sacamos el elemento de la cola auxiliar para seguir iterando
                qAux.sacar();
            } else if (qAux.obtenerFrente().equals('(') || qAux.obtenerFrente().equals(')')){
                // Si es un parentesis, ponemos el parentesis en la cola de parentesis
                cPatenresis.poner(qAux.obtenerFrente());
                // Sacamos el elemento de la cola auxiliar para seguir iterando
                qAux.sacar();
            } else {
                // Si no fue ninguno de estos casos, sacamos el elemento para seguir iterando
                qAux.sacar();
            }
        }

        // Tenemos 3 colas con los elementos correspondientes en cada una.
        // A continuación vemos si estan balanceados.
        while(balLlave >= 0 && !cLlaves.esVacia()){
            // Mientras el balance de llaves sea 0 o más y la cola de llaves no este vacía.
            if(cLlaves.obtenerFrente().equals('{')){
                // En caso de que abra una llave, sumamos uno al balanca
                balLlave += 1;
            } else {
                // En caso de que la cierre, los restamos. Si se compensan queda en 0.
                // En caso de que el balance quede negativo, es que primero aparecio una llave
                // cerrando y entonces debe finalizarse el recorrido ya que seguro esta mal balanceado.
                balLlave -= 1;
            }
            // Avanzamos en la cola
            cLlaves.sacar();

        }

        while(balCorchete >= 0 && !cCorchetes.esVacia()){
            // Mientras el balance de corchetes sea 0 o más y la cola de corchetes no este vacía.
            if(cCorchetes.obtenerFrente().equals('[')){
                // En caso de que abra un corchetes, sumamos uno al balance
                balCorchete += 1;
            } else {
                // En caso de que la cierre, los restamos. Si se compensan queda en 0.
                // En caso de que el balance quede negativo, es que primero aparecio un corchetes
                // cerrando y entonces debe finalizarse el recorrido ya que seguro esta mal balanceado.
                balCorchete -= 1;
            }
            // Avanzamos en la cola
            cCorchetes.sacar();

        }

        while(balParentesis >= 0 && !cPatenresis.esVacia()){
            // Mientras el balance de parentesis sea 0 o más y la cola de parentesis no este vacía.
            if(cPatenresis.obtenerFrente().equals('(')){
                // En caso de que abra un parentesis, sumamos uno al balance
                balParentesis += 1;
            } else {
                // En caso de que la cierre, los restamos. Si se compensan queda en 0.
                // En caso de que el balance quede negativo, es que primero aparecio un parentesis
                // cerrando y entonces debe finalizarse el recorrido ya que seguro esta mal balanceado.
                balParentesis -= 1;
            }
            // Avanzamos en la cola
            cPatenresis.sacar();
        }

        // Retornamos verdadero en caso de que todos los balances hayan terminado en 0.
        return (balLlave == 0 && balCorchete == 0 && balParentesis == 0);
    }
}
