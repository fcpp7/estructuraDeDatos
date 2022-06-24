package conjuntistas.dinamicas;

public class NodoAVL {

    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elemento, NodoAVL izq, NodoAVL der){

        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;

    }

    public Comparable getElem(){
        return this.elem;
    }

    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }

    public NodoAVL getDerecho(){
        return this.derecho;
    }

    public int getAltura(){
        recalcularAltura();
        return this.altura;
    }

    public void setElem(Comparable elemento){
        this.elem = elemento;
    }

    public void setIzquierdo(NodoAVL izq){
        this.izquierdo = izq;
    }

    public void setDerecho(NodoAVL der){
        this.derecho = der;
    }

    public void recalcularAltura(){

        int altI; 
        int altD;
        if(this.getIzquierdo()==null){
            altI = -1;
        } else {
            altI = this.getIzquierdo().getAltura();
        }

        if(this.getDerecho()==null){
            altD = -1;
        } else {
            altD = this.getDerecho().getAltura();
        }

        if(altI > altD){
            this.altura = altI +1;
        } else {
            this.altura = altD +1;
        }

    }
    
}
