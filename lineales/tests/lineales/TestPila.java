package tests.lineales;

import lineales.estaticas.Pila;

import java.util.Scanner;


public class TestPila {
    

    public static void main(String[] args){
        // Clase principal. Se crea una pila vacía y luego se establecen opciones 
        // para que el usuario decida como ir haciendo los testings.


        int opcionMenu;

        Pila miPila = new Pila();

        Scanner entrada = new Scanner(System.in);
    
        System.out.println("A continuación se presenta el testing de la clase pila implementada de manera estática con un largo predeterminado de 20 elementos");

        do{
            System.out.println("---------------------------\n" +
                "Ingrese la opción deseada: \n" +
                 "1- Apilar un elemento: apilar() \n"+
                 "2- Desapilar un elemento: desapilar() \n"+
                 "3- Obtener tope de la pila: obtenerTope() \n"+
                 "4- Ver si la pila esta vacía: esVacia() \n"+
                 "5- Vaciar la pila: vaciar() \n"+
                 "6- Ver la pila: toString() \n"+
                 "7- Clonar la pila: clone() \n"+
                 "8- Es capicua la pila: capicua() \n"+
                 "9- Terminar test y salir\n"+
                 "---------------------------" );
            opcionMenu = entrada.nextInt();
            menu(opcionMenu, miPila);
         }while(opcionMenu >= 1 && opcionMenu <=8);
    
    
    }

    public static void menu(int opcion, Pila pila){
        // Menú del testing
        // Se da la funcionalidad para cada una de las opciones que puede elegir el/la usuaria.


        Scanner entrada = new Scanner(System.in);
        int paramE;
        String paramS ; 
        char eos, ocn;

        switch(opcion){
            case 1:
                System.out.println("Desea ingresar un entero o una cadena. Ingrese 'e' o 's': ");
                eos = entrada.nextLine().charAt(0);
                if(eos == 'e'){
                    System.out.println("Ingrese un entero: ");
                    paramE = entrada.nextInt();

                    System.out.println("Es " + pila.apilar(paramE) + " que se ha apilado correctamente el valor ingresado");
                } else if(eos=='s'){
                    System.out.println("Ingrese una cadena: ");
                    paramS = entrada.nextLine();
                    System.out.println("Es " + pila.apilar(paramS)  + " que se ha apilado correctamente el valor ingresado");
                } else {
                    System.out.println("Usted a ingresado un tipo de dato incorrecto.");
                };break;
            case 2:
                System.out.println("Es " + pila.desapilar()  + " que se ha desapilado correctamente el valor ingresado");break;
            case 3:
                System.out.println("El tope actual de la pila es: " + pila.obtenerTope());break;
            case 4:
                System.out.println("Es " + pila.esVacia() + " que la pila es vacía" );break;
            case 5:
                pila.vaciar();
                System.out.println("La pila se ha vaciado");break;
            case 6:
                System.out.print("La pila es: ");            
                System.out.println(pila.toString());break;
            case 7:
                Pila nuevaPila = pila.clone();
                System.out.println("La pila original es: " + pila.toString());
                System.out.println("La pila clonada es: " + nuevaPila.toString());
                System.out.println("Desea desapilar un elemento en la pila original, la clonada o ninguna. Ingrese 'o', 'c' o 'n'");
                ocn = entrada.nextLine().charAt(0);
                if(ocn == 'o'){
                    pila.desapilar();
                    System.out.println("La pila original es: " + pila.toString());
                    System.out.println("La pila clonada es: " + nuevaPila.toString());
                } else if (ocn =='c'){
                    nuevaPila.desapilar();
                    System.out.println("La pila original es: " + pila.toString());
                    System.out.println("La pila clonada es: " + nuevaPila.toString());
                } 
                System.out.println("---------------------------");
                System.out.println("El menú continua con la pila original");break;
            case 8:
                System.out.println("Es " + capicua(pila) + " que la pila es capicua");break;
            case 9:
                System.out.println("!Gracias por utilizar el testing!");
            
        }

    }
    public static boolean capicua(Pila pila){
        // Método capicua. Recibe una pila y se fija si los elementos que la componen son capicuas.

        Pila pilaAux = pila.clone();
        Pila pilaAux2 = new Pila();
        
        while(!pilaAux.esVacia()){
            pilaAux2.apilar(pilaAux.obtenerTope());
            pilaAux.desapilar();
        }
        
        return pila.equals(pilaAux2);
        
    }


}