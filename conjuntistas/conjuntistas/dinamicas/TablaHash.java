package conjuntistas.dinamicas;

import conjuntistas.funcionesHash.funciones;;

public class TablaHash {
    
    private static final int TAMANIO = 20;
    private CeldaHash[] hash;
    private int cant;
    private static final int OCUPADO=1;
    private static final int BORRADO=-1;

    public TablaHash(){
        this.hash = new CeldaHash[TAMANIO];
        this.cant = 0;
    }

    public boolean pertenece(Object elem){
        int pos = elem.hashCode() % this.TAMANIO;
        int incremento = funciones.rehash(elem) % this.TAMANIO;

        int intento = 1;
        boolean encontrado = false;

        while((!encontrado && this.hash[pos]!=null) && (intento < this.TAMANIO)){
            if(this.hash[pos].getElem().equals(elem) && this.hash[pos].getEstado()!=BORRADO){
                encontrado = true;
            } else {
                pos = (pos + incremento * intento) % this.TAMANIO;
                intento++;
            }
        }

        return encontrado;


    }


    public boolean insertar(Object elemento){
        // Calcula posición inicial e incremento

        boolean estaCargado = pertenece(elemento);
        if(!estaCargado){
            int pos = elemento.hashCode()% this.TAMANIO;
            int incremento = funciones.rehash(elemento) % this.TAMANIO;
            boolean ubicado = false;
            int intento = 1;

            while((!ubicado && (intento < this.TAMANIO))){
                if(this.hash[pos]==null || this.hash[pos].getEstado()==BORRADO){
                    this.hash[pos]= new CeldaHash(elemento, OCUPADO);
                    ubicado = true;
                } else {
                    pos = (pos + incremento * intento) % this.TAMANIO;
                    intento++;
                }
            }
        }

        return !estaCargado;

    }    

    public boolean eliminar(Object buscado){
        // Calcula posición inicial e incremento
        int pos = buscado.hashCode() % this.TAMANIO;
        int incremento = funciones.rehash(buscado) % this.TAMANIO;

        boolean encontrado = false;
        int intento = 1;

        // Busca el elemento hasta encontrarlo o encontrar una celda vacia 
        // o para despues de TAM intentos.
        while(!encontrado && intento < this.TAMANIO && this.hash[pos]!=null){
            if(this.hash[pos].getEstado()==OCUPADO){
                encontrado = this.hash[pos].getElem()==buscado;
                if(encontrado){
                    // Si lo encuentra lo marca y para el ciclo
                    this.hash[pos].setEstado(BORRADO);
                    this.cant--;
                }
            }

            pos = (pos + intento * incremento)%this.TAMANIO;
            intento++;
        }
        return encontrado;
    }

    @Override
    public String toString(){

        String s ="";
        int i;
        for(i=0; i< this.TAMANIO;i++){
            if(this.hash[i]!=null){
                s += "Pos: " + i + " Valor: " + this.hash[i].getElem() + " estado: " + this.hash[i].getEstado() + "\n";
            }
        } 
        return s;
    }

}
