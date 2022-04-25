package lineales.estaticas;

public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola(){
        // Constructor de una cola vacía. El arreglo toma tamaño fijo y frente y fin empiezan en 0.
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;

    }


    // Métodos modificadores

    public boolean poner(Object elemento){
        // Método que pone un elemento en el fin del arreglo y modifica la variable fin.
        boolean exito = true;

        if(this.esCompleta()){
            // Si la cola esta completa, no puedo agregar elementos.
            exito = false;
        } else {
            // Si la cola tiene lugar, agrego el elemento en la posición "fin" y corro fin 1 lugar.
            this.arreglo[this.fin] = elemento;
            this.fin = (this.fin+1)%this.TAMANIO;
        }


        return exito;
    }


    public boolean sacar(){
        // Método que saca el primer elemento del arreglo y modifica la variable frente.
        boolean exito = true;

        if(this.esVacia()){
            // Si es vacía no hay nada para sacar, se retorna FALSE.
            exito = false;
        } else {
            // En caso de que tenga elementos, se pone el elemento del frente en null (eliminandolo)
            // y frente pasa a ser puntero del siguiente elemento.
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
        return exito;
    }
    


    // Métodos observadores

    public Object obtenerFrente(){
        // Método que retorna el elemento que esta en el frente, es decir el primero 
        // que entró a la cola.

        return this.arreglo[this.frente];

    }

    public boolean esVacia(){
        // Método que devuelve true si la cola esta vacía y false en caso contrario.

        return this.frente == this.fin;

    }

    public void vaciar(){
        // Método para vaciar la cola. La recorre mientras no sea vacia y 
        // va poniendo como null cada elemento.

        while(!this.esVacia()){
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }


    }

    public boolean esCompleta(){
        // Método que devuelve true si la cola esta completa y false en caso contrario.

        return this.frente == (this.fin+1)%this.TAMANIO;

    }

    // Métodos NO básicos

    @Override
    public Cola clone(){
        // Método para clonar la cola. Crea la nueva cola, le pasa los parametros (frente y fin)
        // y luego clona el arreglo original en la nueva cola clonada.

        Cola nuevaCola = new Cola();

        if(!this.esVacia()){
            nuevaCola.frente = this.frente;
            nuevaCola.fin = this.fin;
            nuevaCola.arreglo = this.arreglo.clone();
        }
        return nuevaCola;
    }


    @Override
    public String toString() {
        // Método para debbuging, devuelve un print de la cola.

        String s;
        if(this.esVacia()){

            s = "Cola vacía";

        } else {

            int frenteAux = this.frente;
            s = "[";

            while(frenteAux!=this.fin){
                s += this.arreglo[frenteAux].toString();
                frenteAux = (frenteAux + 1) % this.TAMANIO;
                if(frenteAux!=this.fin){
                    s += ", ";
                }
            }
            s += "]";
        }
        return s;

    }

}
