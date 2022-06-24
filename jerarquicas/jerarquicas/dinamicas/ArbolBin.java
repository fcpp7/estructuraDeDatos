package jerarquicas.dinamicas;

import lineales.dinamicas.*;



public class ArbolBin {


    // Atributos
    private NodoArbol raiz;
    
    // Constructor
    public ArbolBin(){
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar){
        // Método que permite insertar un elemento. Se ingresa por parametro
        // el elemento a ingresar, el elemento padre y si va a ser hijo izquierdo o derecho ('I' o 'D')

        boolean exito = true;

        if(this.raiz==null){
            // El arbol esta vacío, entonces se establece el elemento como raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // Dado que el arbol no esta vacío, busca la ubicación del padre.
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            // Si padre existe y lugar no está ocupado lo pone, sino da error
            if(nPadre != null){
                if(lugar == 'I' && nPadre.getIzquierdo()==null){
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if(lugar == 'D' && nPadre.getDerecho()==null){
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }

        return exito;

    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        // Método PRIVADO que busca un elemento y devuelve el nodo que 
        // lo contiene. Si no encuentra buscado devuelve null

        NodoArbol resultado = null;

        if(n !=null ){
            if(n.getElem().equals(buscado)){
                resultado = n;
            } else {
                // No es el buscado: busca primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // Si no lo encontró en el HI, busca en el HD
                if(resultado == null){
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        } 
        return resultado;

    }

    public boolean esVacio(){
        // Retorna true si el arbol es vacío y false en caso contrario

        return this.raiz == null;

    }

    public void vaciar(){
        // Método para vaciar el árbol
        // Debido al funcionamiento del garbage collector en java, se puede vaciar el arbol, 
        // estableciendo la raiz como null

        this.raiz = null;

    }

    public int altura(){
        // Método que calcula la altura.
        // Predefine la variable en -1 ya que si el arbol es vacío se retorna -1
        int alt = -1;
        if(this.raiz != null){
            // Si el árbol no es vacío se llama al método cálculo altura.
            alt = calculoAltura(this.raiz);
        }

        return alt;
    }

    private int calculoAltura(NodoArbol nodo){
        // Método recursivo privado para recorrer el arbol y extraer la máxima altura.

        int alturaMaxima = -1;
        int altIzquierda = 0;
        int altDerecha = 0;
        if(nodo != null){
            // Si el nodo ingresado por parametro no es nulo, ingresa y llama recuesivamente
            // al hijo izquierdo y al derecho.

            altIzquierda = calculoAltura(nodo.getIzquierdo());
            altDerecha = calculoAltura(nodo.getDerecho());
            // A la altura máxima que encontró entre la izquierda y la derecha lo va incrementando en 1
            // y lo asigna a la altura máxima.

            if(altIzquierda >= altDerecha){
                alturaMaxima = altIzquierda +1;
            } else {
                alturaMaxima = altDerecha +1;
            }
        }

        return alturaMaxima;
    }


    public int nivel(Object elemento){

        // Método 
        int niv=-1;

        if(this.raiz!=null){
            // Si no es nulo, llamamos a un método recursivo que busque
            niv = buscadorNivel(this.raiz, elemento, 0);
        }

        return niv;
    }

    private int buscadorNivel(NodoArbol nodo, Object elem, int nivelActual){
        // Método recursivo para buscar el nivel
        int nivelElem = -1;

        if(nodo != null){
            // Si el nodo no es nulo, pregunta si el elemento que contiene el nodo es igual al
            // elemento buscado
            if(nodo.getElem().equals(elem)){
                // En caso de que encuentre el elemento, actualiza el valor de nivelElemento
                // Como nivel actual +1. Nivel actual era el nivel del nodo anterior, por eso se suma 1.
                nivelElem+= nivelActual + 1;
            } else {
                // En caso de que no encuentra, empieza buscando sobre el hijo izquierdo, 
                // aumentando también en 1 el nivel en el que se encuentra.
                nivelElem = buscadorNivel(nodo.getIzquierdo(), elem, nivelActual+1);
                // Se consulta si nivelElem sigue en -1, lo cual marcaría que no se 
                // encontro dentro de la rama izquierda, y entonces se busca en la derecha.
                if(nivelElem==-1){
                    nivelElem = buscadorNivel(nodo.getDerecho(), elem, nivelActual+1);
                }
            }
             
        }
        return nivelElem;
    }

    public Object padre(Object elemento){
        // Método que retorna el valor almacenado en su nodo padre.
        Object elemPadre = null;
        
        
        // Se hace un algoritmo recursivo PRIVADO para buscar al padre.
        if(!this.esVacio()){
            elemPadre = busquedaPadre(this.raiz, elemento);
        }

        return elemPadre;

    }

    private Object busquedaPadre(NodoArbol nodo, Object elemento){
        // Método PRIVADO recursivo que busca el elemento y retorna el elemento de su padre.

        Object elementoPadre = null;

            if(nodo != null){
                if(!nodo.getElem().equals(elemento)){
                    if((nodo.getIzquierdo()!=null && nodo.getIzquierdo().getElem().equals(elemento)) 
                    || (nodo.getDerecho()!=null && nodo.getDerecho().getElem().equals(elemento))){
                       elementoPadre = nodo.getElem();
                    } else {
                        elementoPadre = busquedaPadre(nodo.getIzquierdo(), elemento);
                        if(elementoPadre==null){
                            elementoPadre = busquedaPadre(nodo.getDerecho(), elemento);
                        }
                    }
    
                }
    
    
    
        }        
        return elementoPadre;

    }

    // Métodos de orden
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis){
        // Método recursivo PRIVADO porque su parámetro es de tipo NodoArbol

        if(nodo != null){
            // Visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud()+1);

            // Recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);

        }
    }
    
    public Lista listarInorden(){
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;

    }


    private void listarInordenAux(NodoArbol nodo, Lista lis){
        // Método recursivo PRIVADO para recorrer inorden.

        if(nodo!=null){
            // Recorrer hijo izquierdo en inorden

            listarInordenAux(nodo.getIzquierdo(), lis);

            // Visitar nodo izquierdo
            lis.insertar(nodo.getElem(), lis.longitud()+1);

            // Visitar hijo derecho
            listarInordenAux(nodo.getDerecho(), lis);
        }

    } 

    public Lista listarPosorden(){
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;

    }

    private static void listarPosordenAux(NodoArbol nodo, Lista lis){
        // Método recursivo privado para recorrer posorden.

        if(nodo!= null){
            // Recorremos hijo izquierdo
            listarPosordenAux(nodo.getIzquierdo(), lis);

            // Recorremos hijo derecho
            listarPosordenAux(nodo.getDerecho(), lis);

            // Agregamos a la lista la raiz del subarbol
            lis.insertar(nodo.getElem(), lis.longitud()+1);

        }


    }


    public Lista listarPorNiveles(){
        // Método que retorna una lista con los elementos del arbol por nivel.

        // Se crea la lista vacía
        Lista lis = new Lista();

        // Se crea una cola vacía
        Cola q = new Cola();

        // Se pone la raiz en la cola
        q.poner(this.raiz);

        if(!this.esVacio()){
            // Si el arbol NO es vacío sigue, sino queda una lista vacía
            while(!q.esVacia()){
                // Mientras la cola no esté vacía se crea un nuevo nodo que tiene el valor del frente de la cola
                
                // Es decir, el nodo actual que se esta visitando.
                NodoArbol nodoActual = (NodoArbol) q.obtenerFrente();
                
                // Se saca el nodo que se acaba de visitar
                q.sacar();
    
                // Se inserta el elemento del nodo en la lista, como string.
                lis.insertar(nodoActual.getElem().toString(), lis.longitud()+1);
                
                if(nodoActual.getIzquierdo()!= null){
                    //Si existe el hijo izquierdo del nodo actual, lo pone en la cola
                    q.poner(nodoActual.getIzquierdo());
                }
                
                if(nodoActual.getDerecho()!=null){
                    // Si existe el hijo derecho del nodo actual, lo pone en la cola.
                    q.poner(nodoActual.getDerecho());
                }
    
            }
    
        }
        return lis;

    }



    // No básicas


    @Override
    public ArbolBin clone(){
        // Método público de clone, que crea un arbol vacío y llama al método
        // privado cloneAux que recorrera el arbol original 
        // e irá agregando los nodos a la nueva estructura

        ArbolBin clon = new ArbolBin();

        if(!this.esVacio()){
           clon.raiz= cloneAux(this.raiz);
        }
        return clon;

    }

    private NodoArbol cloneAux(NodoArbol n){
        // Método privado iterativo que va creando los nodos en la nueva estructura

        NodoArbol nuevo = new NodoArbol(null, null, null);

        if(n != null){
            nuevo.setElem(n.getElem());
            if(n.getIzquierdo() != null){
                nuevo.setIzquierdo(cloneAux(n.getIzquierdo()));
            }
            if(n.getDerecho()!= null){
                nuevo.setDerecho(cloneAux(n.getDerecho()));
            }
        }
        return nuevo;

    }


    @Override
    public String toString(){


        String s = "";
        
        if(this.esVacio()){
            s = "Arbol vacío";
        } else {
            s = concatenarToString(this.raiz);
        }

        return s;

    }

    private String concatenarToString(NodoArbol nodo){
        // Método recursivo PRIVADO para recorrer inorden.

        String salida = "";
        String elemento = "";
        String hijoIzq = "";
        String hijoDer = "";
        if(nodo !=null){
            elemento = nodo.getElem().toString();
            if(nodo.getIzquierdo() != null){
                hijoIzq = nodo.getIzquierdo().getElem().toString();
            } else {
                hijoIzq = "-";
            }
            if(nodo.getDerecho()!= null){
                hijoDer = nodo.getDerecho().getElem().toString(); 
            }  else {
                hijoDer = "-";
            }
            salida = "Nodo: " + elemento + " HI: " + hijoIzq + " HD: " + hijoDer + " \n";
            salida += concatenarToString(nodo.getIzquierdo());
            salida += concatenarToString(nodo.getDerecho());
        }
        
        return salida;
    } 

    public Lista frontera(){

        Lista frontLista = new Lista();
        int i = 0;


        if(!this.esVacio()){
            frontLista = fronteraAux(this.raiz, i, frontLista);         
        }
        return frontLista;

    }

    private Lista fronteraAux(NodoArbol nodo, int pos, Lista l){

        
        if(nodo!= null){
            if(nodo.getIzquierdo()==null && nodo.getDerecho()==null){
                l.insertar(nodo.getElem(), pos+1);
                pos +=1;
            } else{
                if(nodo.getDerecho()!=null){
                    l = fronteraAux(nodo.getDerecho(), pos, l);
                }
                if(nodo.getIzquierdo()!=null){
                    l = fronteraAux(nodo.getIzquierdo(), pos, l);
                }

            }
        }
        return l;

    }

    // Ejercicio practica parcial

    public boolean verificarPatron(Lista l){
        boolean ver =  false;
        int pos = 1;
        if(!this.esVacio()){
            ver = verificarPatronAux(this.raiz, l, pos);
        }

        return ver;
    }

    private boolean verificarPatronAux(NodoArbol nodo, Lista l, int pos){

        boolean verifica = false;
        int largo = l.longitud();

        if(nodo!=null){
            if(nodo.getElem().equals(l.recuperar(pos))){
                verifica = true;
            }
            if(verifica && pos < largo){
                verifica = verificarPatronAux(nodo.getIzquierdo(), l, pos+1);
                if(!verifica){
                    verifica = verificarPatronAux(nodo.getDerecho(), l, pos+1);
                }
            }
        }

        return verifica;

    }

    


}
