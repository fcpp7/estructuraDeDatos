package conjuntistas.dinamicas;

public class Hash {

    private int cant;
    private static final int TAMANIO = 10;
    private Nodo[] hash;
    
    // Constructor vacío
    public Hash(){
        this.cant = 0;
        this.hash = new Nodo[TAMANIO];
    }


    
    public boolean insertar(Object nuevoElem){
        // Primero verifica si el elemento ya esta cargado.
        // Si no lo encuentra, lo pone adelante del resto

        int pos = nuevoElem.hashCode() %this.TAMANIO;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;

        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(nuevoElem);
            aux = aux.getEnlace();
        }

        if(!encontrado){
            this.hash[pos] = new Nodo(nuevoElem, this.hash[pos]);
            this.cant++;
        }
        return !encontrado;

    } 

     public boolean eliminar(Object elemento){

        int pos = elemento.hashCode() %this.TAMANIO;
        Nodo aux = this.hash[pos];
        Nodo auxAnterior = this.hash[pos];
        boolean encontrado = false;

        if(aux.getElem().equals(elemento)){
            encontrado = true;
            if(encontrado){
                if(this.hash[pos].getEnlace()!=null){
                    this.hash[pos]=(this.hash[pos].getEnlace());
                } else {
                    this.hash[pos]=null;
                }
                this.cant--;
            }
        }

        while(!encontrado && aux !=null){
            encontrado = aux.getElem().equals(elemento);
            if(encontrado){
                if(aux.getEnlace()!=null){
                    auxAnterior.setEnlace(aux.getEnlace());
                } else {
                    auxAnterior.setEnlace(null);
                }
                this.cant--;
            }
            if(aux!=null){
                auxAnterior = aux;
                aux = aux.getEnlace();
            }
        }
        return encontrado;

    }

    public boolean pertenece(Object elemento){

        int pos = elemento.hashCode() %this.TAMANIO;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;

        while(!encontrado && aux !=null){
            encontrado = aux.getElem().equals(elemento);
            aux = aux.getEnlace();
        }
        
        return encontrado;

    }

    public boolean esVacia(){
        return this.cant==0;
    }

    public void vaciar(){

        int i = 0;

        while(i < this.TAMANIO){
            this.hash[i]=null;
            i++;
        }
    }

    @Override
    public String toString(){

        String s = "[";
        int i = 0;

        while(i < this.TAMANIO){
            if(this.hash[i]!=null){
                Nodo aux = this.hash[i]; 
                s += "En pos: " + i + " estan los elementos: [" + aux.getElem();
                aux = aux.getEnlace();
                while(aux!=null){
                    s += " - " + aux.getElem();
                    aux = aux.getEnlace();
                }
                s += "]\n";
            }
            i++;
        }
        s += "]";
        return s;

    }


    


}
