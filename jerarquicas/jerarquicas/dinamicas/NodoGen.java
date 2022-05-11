package jerarquicas.dinamicas;

public class NodoGen {

    Object elemento;
    NodoGen hijoIzquierdo;
    NodoGen hermanoDerecho;

    // Constructor

    public NodoGen(Object elem, NodoGen hi, NodoGen hd){
        this.elemento = elem;
        this.hijoIzquierdo = hi;
        this.hermanoDerecho = hd;
   }

   // Observadores

   public Object getElem(){
       return this.elemento;
   }

   public NodoGen getHijoIzquierdo(){
       return this.hijoIzquierdo;
   }

   public NodoGen getHermanoDerecho(){
       return this.hermanoDerecho;
   }

   // Modificadores

   public void setElem(Object elem){

    this.elemento = elem;

   }

   public void setHijoIzquierdo(NodoGen hi){
       this.hijoIzquierdo = hi;
   }

   public void setHermanoDerecho(NodoGen hd){
       this.hermanoDerecho = hd;
   }


}
