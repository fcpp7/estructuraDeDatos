package lineales.dinamicas;

public class Pila{

    private Nodo tope;

    public Pila(){
        this.tope = null;
    }

    // Modificadoras

    public boolean apilar(Object nuevoElem){

        // Crea un nuevo nodo delante de la antigua cabecera.
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        // Actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;

        // Nunca hay un error por pila llena, entonces devuelve siempre true.
        return true;
    }

    public boolean desapilar(){
        // Método que permite desapilar el último elemento de la pila.
        // Si no esta vacía (se chequea si esta vacia llamando al método esVacia
        
        boolean exito;
        
        if(!esVacia()){
            this.tope = this.tope.getEnlace();
            exito = true;
        } else {
            exito = false;
        }
    
        return exito;

    }

    // Observadoras
     
    public Object obtenerTope(){
        // Obtiene el elemento tope como el elemento que pertenece al nodo que actualmente esta en el tope.

        Object last;
        
        if(!esVacia()){
            last = tope.getElem();;
        } else {
            last = "Pila vacía";
        }
        
        return last;
    }

    public boolean esVacia(){
        // Método que devuelve un boolean. En caso de que el tope sea null
        // sabemos que la pila esta vacía, entonces devuelve true. 
        // En caso contrario, devuelve false.
        
        return this.tope == null;
        
    }


    // Métodos NO básicos


    public void vaciar(){
    // Método que vacía la pila
    // Le asigna null al tope, rompiendo los enlaces, luego java con el garbage collector limpia la memoria.    
        this.tope = null;
    }
    
    @Override
    public Pila clone(){

        Pila nuevaPila = new Pila();

        if(!this.esVacia()){
            Nodo aux = this.tope;
            Nodo nuevo = new Nodo(aux.getElem(), null);
            nuevaPila.tope = nuevo;
            Nodo auxClon = nuevaPila.tope;

            aux = aux.getEnlace();
            while(aux != null){
                nuevo = new Nodo(aux.getElem(), null);
                auxClon.setEnlace(nuevo);
                auxClon = auxClon.getEnlace();
                aux = aux.getEnlace();
            }

        }

        return nuevaPila;


    }


    @Override
    public String toString(){

        String s = "";

        if(this.tope == null){
            s = "Pila vacía";
        } else {
            // Se ubica para recorre la pila
            Nodo aux = this.tope;
            s = "[";

            while(aux != null){
                // Agrega el texto del elemento y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if(aux != null){
                    s += ",";
                }
            }
            s += "]";

        }

        return s;
    }




}