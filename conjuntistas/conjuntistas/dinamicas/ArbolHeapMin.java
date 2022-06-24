package conjuntistas.dinamicas;

public class ArbolHeapMin{
    
    private Comparable[] heap;
    private int ultimo;
    private static final int TAMANIO = 20;


    public ArbolHeapMin(){
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

 

    public boolean insertar(Comparable elem){

        boolean exito;

        if(this.ultimo+1<=TAMANIO){
            // Si el tamaño del arreglo nos permite lo agregamos, sino se retorna falso
            this.ultimo += 1; 
            int posI = this.ultimo;
            exito = true;
            boolean continuar = true;
            this.heap[posI] = elem;

            while(continuar && ((posI/2)>0)){
                
                int posP = posI/2;

                if(this.heap[posP].compareTo(elem)>0){
                    // El valor del padre es mayor, entonces se intercambian
                    // En el lugar del valor insertado ponemos al padre
                    this.heap[posI] = this.heap[posP];
                    // En la posición del padre ponemos al nodo que viene subiendo
                    this.heap[posP] = elem;
                    // Ahora la posición del nodo insertado es donde estaba el padre
                    posI = posP;
                } else {
                    continuar = false;
                }
            }
        } else {
            exito = false;
        }

        return exito;

    }

    public boolean eliminarCima(){

        boolean exito;
        if(this.ultimo==0){
            // estructura vacía
            exito = false;
        } else {
            // Saca la raiz y pone la última hoja en su lugar
            this.heap[1]= this.heap[ultimo];
            this.ultimo--;
            // Restablece la propiedad de heap mínimo
            hacerBajar(1);
            exito = true;
        }
        return exito;

    }

    private void hacerBajar(int posPadre){
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while(!salir){
            posH = posPadre * 2;
            if(posH <= this.ultimo){
                // temp tiene al menos un hijo (izq) y lo considera menor

                if(posH < this.ultimo){
                    // Hijo menor tiene hermano derecho

                    if(this.heap[posH + 1].compareTo(this.heap[posH]) < 0){
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }

                // Compara al hijo menor con el padre
                if(this.heap[posH].compareTo(temp) < 0) {
                    // El hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else{
                    // El padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // El temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Object recuperarCima(){

        return this.heap[1];

    }

    public boolean esVacio(){
        return this.ultimo!=0;
    }

    public void vaciar(){
        this.heap = new Comparable[TAMANIO];
    }

    
    public String toString(){
        String s = "";
        int i;

        if(this.ultimo != 0){
            s += '[';
            for(i=1;i<=this.ultimo;i++){
                s += this.heap[i].toString();
                if(i < this.ultimo){
                    s+="-";
                }
            }
            s += ']';
        }
        return s;
    }


}
