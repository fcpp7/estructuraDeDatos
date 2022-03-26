package tests.lineales;

import lineales.estaticas.Pila;

import java.util.Scanner;


public class TestPila {
    
    public static void main(String [] args){
        // Creamos la pila
        Pila miPila = new Pila();

        // EL parametro int i es la cantidad de elementos que tenemos en la pila.
        int i = 0;

        
        // Imprimimos por pantalla la pila desde que tiene 0 elementos hasta que le agregamos
        // todos los multiplos de 10 para completarla.
        System.out.println("El test de apilado es:");
        while(i <= 20){
            System.out.println("La pila con " + i + " elementos es: " + miPila.toString());
            miPila.apilar((i+1)*10);
            i++;
        }
        
        // En este punto tenemos una pila completa.
        
        // Hacemos un test de apilar otro elemento y vemos que devuelve el método 
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de apilar elemento con la pila llena: ");
        System.out.println("Intento de apilar otro objeto con la cadena llena : " + miPila.apilar(210));
        
        // Test de desapilar un elemento:
         System.out.println("--------------------------------------------------------------");
         System.out.println("Test de desapilar un elemento:");

         
        // Casteamos al int
        int top = (int) miPila.obtenerTope();
        System.out.println("El último elemento actual de la pila es: " + top);
        
        // Desapilamos el elemento. Devolvemos el valor de verdad (verdadero si pudo desapilar y falso sino.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de método desapilar:");
        System.out.println("La operación de desapilar el último elemento fue: " + miPila.desapilar());
        
        // El nuevo elemento que ocupa el tope de la pila es
        System.out.println("--------------------------------------------------------------");
        System.out.println("Nuevo tope de la pila luego del desapilado:");
        top = (int) miPila.obtenerTope();
        System.out.println("El nuevo tope de la pila es: " + top);
        
        // Test de esVacia. En este caso tenemos una pila con elementos, por lo que tiene que retornar falso.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de método esVacia():");
        System.out.println("La pila es vacía es: " + miPila.esVacia());        
        
        // Test de vaciar. Mostramos por pantalla la lista como esta y luego como queda después de aplicar el método vaciar..
        System.out.println("--------------------------------------------------------------");
        System.out.println("Pila antes de vaciarla:");
        System.out.println("La pila actual es: " + miPila.toString());

        // Aplicamos el método vaciar.
        miPila.vaciar();
        
        // Mostramos la nueva pila vacía
        System.out.println("Pila luego de usar vaciar():");
        System.out.println("La pila luego de vaciar() es: " + miPila.toString());
        
        // Teniendo la pila vacía testeamos despilar un elemento. Debería devolver false.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de desapilar() con pila vacía:");
        System.out.println("El intento de desapilar una pila vacía toma resultado: " + miPila.desapilar());

        // Teniendo la pila vacía testeamos que el método esVacia() haya retorne true.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de esVacia() con pila vacía:");
        System.out.println("El método esVacia(), con una pila vacía, devuelve: " + miPila.esVacia());
        
        // LLenamos la pila con 10 elementos para luego clonarla.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test de clone. Se presenta una pila de 10 elementos: ");
        i = 0;
        while(i < 10){
            miPila.apilar((i+1)*10);
            i++;
        }
        System.out.println("La pila a clonar es: " + miPila.toString());
        
        // Aplicamos clone para crear una nuevaPila
        
        Pila nuevaPila = miPila.clone();
        
        System.out.println("La pila clonada es: " + nuevaPila.toString());
        
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test método equals con dos pilas iguales");
        System.out.println("equals: " + miPila.equals(nuevaPila));
        // Si apilamos un objeto en la nueva pila, no debería mostrarse en la original
        nuevaPila.apilar(110);
        
        System.out.println("--------------------------------------------------------------");
        System.out.println("Apilamos un dato en la pila clonada y vemos si son iguales o no");
        System.out.println("La pila original es: " + miPila.toString());
        System.out.println("La pila clonada es: " + nuevaPila.toString());
     
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test método equals con dos pilas distintas");
        System.out.println("equals: " + miPila.equals(nuevaPila));
        
     
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test método capicua con una pila NO capicua");
        System.out.println("capicua: " + capicua(miPila));

        // Vaciamos la pila miPila y luego le asignamos los datos [1,2,1] para que sea capicua

        miPila.vaciar();
        miPila.apilar(1);
        miPila.apilar(2);
        miPila.apilar(1);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Test método capicua con una pila capicua [1,2,1]");
        System.out.println("capicua: " + capicua(miPila));
    }
    
    public static boolean capicua(Pila pila){
        
        int i = 0;
        Pila pilaAux = pila.clone();
        Pila pilaAux2 = new Pila();
        
        while(!pilaAux.esVacia()){
            pilaAux2.apilar(pilaAux.obtenerTope());
            pilaAux.desapilar();
        }
        
        return pila.equals(pilaAux2);
        
    }
    

}
