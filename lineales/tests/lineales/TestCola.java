package tests.lineales;

import lineales.estaticas.Cola;

import java.util.Scanner;


public class TestCola {
    

    public static void main(String[] args){
        // Clase principal. Se crea una cola vacía y luego se establecen opciones 
        // para que el usuario decida como ir haciendo los testings.


        int opcionMenu;

        Cola miCola = new Cola();

        Scanner entrada = new Scanner(System.in);
    
        System.out.println("A continuación se presenta el testing de la clase cola implementada de manera estática con un largo predeterminado de 20 elementos");

        do{
            System.out.println("---------------------------\n" +
                "Ingrese la opción deseada: \n" +
                 "1- Poner un elemento: poner() \n"+
                 "2- Sacar un elemento: sacar() \n"+
                 "3- Obtener frente de la cola: obtenerFrente() \n"+
                 "4- Ver si la cola esta vacía: esVacia() \n"+
                 "5- Vaciar la cola: vaciar() \n"+
                 "6- Ver la cola: toString() \n"+
                 "7- Clonar la cola: clone() \n"+
                 "9- Terminar test y salir\n"+
                 "---------------------------" );
            opcionMenu = entrada.nextInt();
            menu(opcionMenu, miCola);
         }while(opcionMenu >= 1 && opcionMenu <=8);
    
    
    }

    public static void menu(int opcion, Cola cola){
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

                    System.out.println("Es " + cola.poner(paramE) + " que se ha puesto correctamente el valor ingresado");
                } else if(eos=='s'){
                    System.out.println("Ingrese una cadena: ");
                    paramS = entrada.nextLine();
                    System.out.println("Es " + cola.poner(paramS)  + " que se ha puesto correctamente el valor ingresado");
                } else {
                    System.out.println("Usted a ingresado un tipo de dato incorrecto.");
                };break;
            case 2:
                System.out.println("Es " + cola.sacar()  + " que se ha sacado correctamente el valor ingresado");break;
            case 3:
                System.out.println("El frente actual de la cola es: " + cola.obtenerFrente());break;
            case 4:
                System.out.println("Es " + cola.esVacia() + " que la cola es vacía" );break;
            case 5:
                cola.vaciar();
                System.out.println("La cola se ha vaciado");break;
            case 6:
                System.out.print("La cola es: ");            
                System.out.println(cola.toString());break;
            case 7:
                Cola nuevaCola = cola.clone();
                System.out.println("La cola original es: " + cola.toString());
                System.out.println("La cola clonada es: " + nuevaCola.toString());
                System.out.println("Desea sacar un elemento en la cola original, la clonada o ninguna. Ingrese 'o', 'c' o 'n'");
                ocn = entrada.nextLine().charAt(0);
                if(ocn == 'o'){
                    cola.sacar();
                    System.out.println("La cola original es: " + cola.toString());
                    System.out.println("La cola clonada es: " + nuevaCola.toString());
                } else if (ocn =='c'){
                    nuevaCola.sacar();
                    System.out.println("La cola original es: " + cola.toString());
                    System.out.println("La cola clonada es: " + nuevaCola.toString());
                } 
                System.out.println("---------------------------");
                System.out.println("El menú continua con la cola original");break;
            case 9:
                System.out.println("!Gracias por utilizar el testing!");
            
        }

    }
 
}