package tests.lineales;

import lineales.dinamicas.*;

public class TestCadenas{

    public static void main(String[] args){

        Cola cola = new Cola();
        cola.poner('A');
        cola.poner('B');
        cola.poner('#');
        cola.poner('C');
        cola.poner('#');
        cola.poner('D');
        cola.poner('E');
        cola.poner('F');

        Cola colaSalida = testCadena(cola);
        
        System.out.println("====== TEST CADENA ========");
        System.out.println("Cola original: " + cola.toString());
        System.out.println("Cola salida: " + colaSalida.toString());
    }

    public static Cola testCadena(Cola c1){

        // Clonamos la cola para usarla
        Cola c2 = c1.clone();

        // Creamos una cola vacía que será la que es la salida

        Cola cS = new Cola();

        // Creamos una pila vacía que usaremos como auxiliar
        Pila p = new Pila();

        // Creamos una cola vacía que usaremos como auxiliar
        Cola cAux = new Cola();


        while(!c2.esVacia()){
                boolean continuar = !c2.obtenerFrente().equals('#');
                while(continuar){
                    // Obtenemos el elemento que esta en el frente y lo asignamos a una variable
                    Object elemento = c2.obtenerFrente();
    
                    // Ponemos el elemento en la cola auxiliar    
                    cAux.poner(elemento);
                    // Ponemos el elemento en la pila auxiliar
                    p.apilar(elemento);
                    // Ponemos el elemento en la cola de salida (ya que primero tenemos que poner la cadena, 
                    // tal cual aparece, aprovechamos el recorrido).
                    cS.poner(elemento);
                    // Sacamos el frente para seguir iterando en la cola clonada.
                    c2.sacar();

                    // Debido a que no podemos tener el equals en el while porque cuando se llama al nodo 
                    // de la cola vacía falla por null exception, dejamos el continuar como falso en caso de 
                    // que la cola este vacía o que el elemento sea #. Si no, continuamos iterando.
                    if(c2.esVacia()){
                        continuar = false;
                    } else if(!c2.obtenerFrente().equals('#')){
                        continuar = true;
                    } else {
                        continuar = false;
                    }
                    
                }

            while(!p.esVacia()){
                // Si la pila no es vacía, vamos sacando cada elemento y poniendolo en la cola principal
                cS.poner(p.obtenerTope());
                // Desapilamos el elemento que acabamos de usar.
                p.desapilar();

            }

            while(!cAux.esVacia()){
                // Mientras la cola auxiliar no este vacía, iteramos.
                // Ponemos el elemento del frente de la cola en la cola de salida.
                cS.poner(cAux.obtenerFrente());
                // Quitamos el elemento utilizado de la cola auxiliar
                
                cAux.sacar();
             
            }
            
            if(c2.obtenerFrente()!=null){
                // En caso de que el frente de la cola original (la clonada) que estamos recorriendo
                // sea no sea nulo (la única posibilidad es que venga de un corte por #)
                // entonces ponemos el numeral en la cola de salida
                cS.poner('#');

            }

            // Sacamos el elemento para continuar iterando.
            c2.sacar();

            System.out.println(cS.toString());
            System.out.println(c2.toString());

        }

        return cS;

    }





}