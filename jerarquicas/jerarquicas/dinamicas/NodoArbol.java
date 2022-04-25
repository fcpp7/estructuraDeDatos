package jerarquicas.dinamicas;


public class NodoArbol {

    // Atributos

    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    // Constructor

    public NodoArbol(Object elemento, NodoArbol izq, NodoArbol der){

        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;

    }

    // Visualizadores

    public Object getElem(){
        // Retorna el objeto elem

        return this.elem;
    }

    public NodoArbol getIzquierdo(){
        // Retorna el hijo izquierdo

        return this.izquierdo;

    }

    public NodoArbol getDerecho(){
        // Retorna el hijo derecho

        return this.derecho;

    }

    // Modificadores

    public void setElem(Object elemento){
        // Método para modificar elem del NodoArbol

        this.elem = elemento;
    }

    public void setIzquierdo(NodoArbol izq){
        // Método para modificar el hijo izquierdo del NodoArbol

        this.izquierdo = izq;

    }

    public void setDerecho(NodoArbol der){
        // Método para modificar el hijo derecho del NodoArbol

        this.derecho = der;

    }

    
}
