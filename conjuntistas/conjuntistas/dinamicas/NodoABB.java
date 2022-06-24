package conjuntistas.dinamicas;

public class NodoABB {

    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable elemento, NodoABB izq, NodoABB der){

        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;

    }

    public Comparable getElem(){
        return this.elem;
    }

    public NodoABB getIzquierdo(){
        return this.izquierdo;
    }

    public NodoABB getDerecho(){
        return this.derecho;
    }

    public void setElem(Comparable elemento){
        this.elem = elemento;
    }

    public void setIzquierdo(NodoABB izq){
        this.izquierdo = izq;
    }

    public void setDerecho(NodoABB der){
        this.derecho = der;
    }
    
}
