package lineales.dinamicas;

public class Cola {
    
    private Nodo frente;
    private Nodo fin;

    // Método constructor
    public Cola(){
        this.frente = null;
        this.fin = null;
    }    
    // Métodos modificadores

    public boolean poner(Object elemento){
        // Método que poner un nuevo objeto en la cola.
        // Se tiene en cuenta el caso especial de cuando se va a poner un elemento en la cola vacía

        if(this.esVacia()){
            Nodo nuevoNodo = new Nodo(elemento, null);
            this.fin = nuevoNodo;
            this.frente = nuevoNodo;
        } else {
            Nodo nuevoNodo = new Nodo(elemento, null);
            this.fin.setEnlace(nuevoNodo);
            this.fin = nuevoNodo;
        }

        return true;

    }

    public boolean sacar(){
        // Método que saca el frente. Es decir, el primer elemento que ingreso a la cola.

        boolean exito = true;

        if(this.frente==null){
            // La cola esta vacía por lo tanto no hay nada que sacar. Se retorna false.
            exito = false;
        } else {
            // Si hay más de un elemento, frente debe apuntar al siguiente enlace 
            // y luego el recolector de basura eliminar el nodo que quedo solo.
            this.frente = this.frente.getEnlace();
            if(this.frente==null){
                // En caso de que el frente no apute a ningún objeto es que había solo 
                // uno en la cola y como lo sacamos, debemos también apuntar a null con fin.
                this.fin = null;
            }
        }


        return exito;
    }


    // Métodos visualizadores

    public Object obtenerFrente(){
        //Método que devuelve el elemento del frente de la cola.
        // Si la cola esta vacia, devuelve null, en caso contrario el elemento del nodo apuntado por el frente.

        Object last=null;
        if(!this.esVacia()){
            last = this.frente.getElem();
        }
        return last; 

    }

    public boolean esVacia(){
        // Método que devuelve true si la cola esta vacía y false en caso contrario.
        // Si alguno de los punteros no apunta a ningún nodo, la cola esta vacía.
        return (this.fin==null || this.frente==null);

    }

    public void vaciar(){
        // Método que vacía la cola.
        // Para esto, solo tenemos que quitar los punteros y poner fin y frente como null
        // Luego el recolector de basura sacara los nodos que quedaron sin apuntar.

        this.fin = null;
        this.frente = null;
    }



    // Métodos NO básicos

    public Cola clone(){
        // Método clonar la Cola.

        Cola nuevaCola = new Cola();

        if(!this.esVacia()){
            // Si NO es vacía la cola original, generamos un nodo auxiliar que nos ayudará
            // a recorrerla e ir copiando cada nodo al la nueva cola.
            Nodo aux = this.frente;
            // El primer nodo nuevo tiene el elemento del primer nodo de la cola original.
            // Y enlace null
            Nodo nuevo = new Nodo(aux.getElem(), null);
            // Se establece el nodo nuevo como frente de la nueva cola
            nuevaCola.frente = nuevo;
            // Se crea otro nodo auxiliar que apunta al frente de la nueva cola.
            // Este nodo es el que irá haciendo las conexiones entre nodos de la nueva cola.
            Nodo auxClon = nuevaCola.frente;

            // Aux pasa al siguiente enlace de la cola original.
            aux = aux.getEnlace();
            while(aux != null){
                // Mientras el enlace de la cola original no sea null (no sea el último)
                // Creamos nodo con el elemento del nodo y enlace nulo.
                nuevo = new Nodo(aux.getElem(), null);
                // Enlazamos el nodo que teniamos en la cola nueva, con el recién generado.
                auxClon.setEnlace(nuevo);
                // Pasamos el nodo auxiliar de la nueva cola al último enlace de la misma.
                auxClon = auxClon.getEnlace();
                // Pasamos al siguiente nodo de la cola original
                aux = aux.getEnlace();
                
            }
            // Dado que también tenemos que considerar el puntero fin.
            // El mismo se establece al finalizar la copia y se lo asigna al último
            // nodo de la nueva cola.
            nuevaCola.fin = auxClon;

        }

        return nuevaCola;
        

    }


    @Override
    public String toString(){

        String s = "";

        if(this.esVacia()){
            s = "Cola vacía";
        } else {
            // Se ubica para recorre la cola
            Nodo aux = this.frente;
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
