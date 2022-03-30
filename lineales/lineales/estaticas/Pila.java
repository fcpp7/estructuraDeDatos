
package lineales.estaticas;




public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;
    
    // Constructor vacío
    
    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }
    
    
    // Modificadoras
    
    public boolean apilar(Object nuevoElemento){
        // Método que apila un nuevo elemento.
        
        // Retorna un boolean. En caso de que 
        // el tope sea menor que el tamanio-1, aumenta en 1 el tope, agrega
        // el nuevo elemento en el tope y devuelve "true".
        
        
        boolean exito;
        
        if(this.tope<(TAMANIO-1)){
            // La pila tiene por lo menos un espacio y se agrega el elemento allí.
            this.tope += 1;
            this.arreglo[this.tope] = nuevoElemento;
            exito = true;
        } else {
            // La pila esta llena
            exito = false;
        }
        
        return exito;
    }
   
    public boolean desapilar(){
        // Método que permite desapilar el último elemento de la pila.
        
        // Si no esta vacía (se chequea si esta vacia llamando al método esVacia
        
        boolean exito;
        
        if(!esVacia()){
            this.arreglo[this.tope] = null;
            this.tope -=1;
            exito = true;
        } else {
            exito = false;
        }
    
        return exito;
        
    }
    
    // Observadoras
    
    public Object obtenerTope(){
        
        Object last = null;
  
        if(!esVacia()){
            last = this.arreglo[this.tope];
        }
        
        return last;
        
    }
    
    public boolean esVacia(){
        // Método que devuelve un boolean. En caso de que el tope sea -1
        // sabemos que la pila esta vacía, entonces devuelve true. 
        // En caso contrario, devuelve false.
        
        return this.tope == -1;
        
    }
    
    
    // Métodos NO básicos
    
    public void vaciar(){
    // Método que vacía la pila
    // Se usa una estructura repetitiva que se ejecuta mientras la pila 
    // NO sea vacía. De esta manera, cuando la pila NO es vacía, va sacando 
    // el último elemento con el método desapilar.
        
        while(!esVacia()){
                desapilar();
            } 
        }
        
    @Override
    public Pila clone(){
        // método que genera una copia exacta de la pila en una nueva instancia.
        
        Pila nuevaPila = new Pila(); 
        

        nuevaPila.tope = this.tope;
        if(!(this.esVacia())){
            nuevaPila.arreglo = this.arreglo.clone();
        }
        return nuevaPila;


    }
    
    @Override
    public String toString(){
        
        String s;
        
        if(!esVacia()){
            int i;
            s = "[";
            for(i = 0; i <= this.tope;i++){
                s +=  this.arreglo[i];
                if (i != this.tope){
                    s+=",";
                } 
            }
            s += "]";
        } else {
            s = "Pila vacía";
        }
        
        
        return s;

    }


    public boolean equals(Pila otraPila){
        // Método equals, crea dos clones de las pilas, para desapilarlas e ir comparando 
        // elemento a elemento sin modificar los objetos originales.
        
        boolean eq = true;
        int i;
        Pila pila1 = this.clone();
        Pila pila2 = otraPila.clone();
        
        for(i = this.TAMANIO;i >=0 ; i--){
            if(pila1.obtenerTope() == pila2.obtenerTope()){
                pila1.desapilar();
                pila2.desapilar();
            } else {
                eq = false;
            }
            
        }
        
        return eq;
    }
        
     
}
