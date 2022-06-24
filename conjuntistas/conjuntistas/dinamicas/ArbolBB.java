package conjuntistas.dinamicas;

import jerarquicas.dinamicas.NodoGen;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;;

public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB(){
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento){

        boolean exito = true;
        if(this.raiz==null){
            this.raiz = new NodoABB(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }


    private boolean insertarAux(NodoABB n, Comparable elemento){
        // Precondición: n no es nulo
        boolean exito = true;

        if((elemento.compareTo(n.getElem())==0)){
            // Reportar error: Elemento repetido
            exito = false;
        } else if(elemento.compareTo(n.getElem())<0){
            // Elemento es menor que n.getElem()
            // Si tiene HI baja a la izquierda, sino agrega elemento
            if(n.getIzquierdo()!=null){
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoABB(elemento, null, null));
            }
        } else {
            //Elemento es mayor que n.getElem()
            // Si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDerecho()!=null){
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoABB(elemento, null, null));
            }
        }
        return exito;
        }

        public boolean eliminar(Comparable elemento){
            // Método que busca un elemento ingresado por parametro y en caso de que esté, lo elimina
            // Retorna true si pudo eliminar el elemento y false en caso de que no lo haya encontrado

            boolean exito = false;
            NodoABB n = this.raiz;
            NodoABB padre = null;

            while(n != null){
                if(elemento.compareTo(n.getElem())==0){
                    // Se encontró el elemento, entonces vemos a que caso pertenece
                    // Caso uno, el elemento es una hoja
                    if(n.getIzquierdo()==null && n.getDerecho()==null){
                        // Si ambos hijos son nulos es que es una hoja
                            padre.setDerecho(null);
                            padre.setIzquierdo(null);
                    } else if(n.getIzquierdo()==null || n.getDerecho()==null){
                        // Si alguno de los dos son null, quiere decir que solo uno de llos lo es.
                        // Es el caso 2, donde tiene el elemento encontrado tiene un solo hijo
                        // entonces debe cambiarse con el.
                            if(n.getElem().compareTo(padre.getElem())>0){
                                // Si el elemento es mayor que el padre, entonces es el hijo
                                // derecho y seteamos a null al padre para su hd
                                if(n.getIzquierdo()!=null){
                                    // El elemento tiene hijo SOLO izquierdo, entonces le asignamos al padre
                                    // el valor del hijo izquierdo
                                    padre.setDerecho(n.getIzquierdo());
                                } else {
                                    // Si no, el hijo del elemento encontrado es el derecho, 
                                    // entonces seteamos el hd del elemento al padre.
                                    padre.setDerecho(n.getDerecho());
                                }
                            } else {
                                // El elemento es el izquierdo del padre
                                if(n.getIzquierdo()!=null){
                                    // El elemento tiene hijo SOLO izquierdo, entonces le asignamos al padre
                                    // el valor del hijo izquierdo
                                    padre.setIzquierdo(n.getIzquierdo());
                                } else {
                                    // Si no, el hijo del elemento encontrado es el derecho, 
                                    // entonces seteamos el hd del elemento al padre.
                                    padre.setIzquierdo(n.getDerecho());
                                }
                            }
                            
                        } else {
                            // Caso 3, tiene los dos hijos, entonces buscamos candidato
                            // Se utiliza el candidato A "Mayor valor del subarbol izquierdo"
                            NodoABB candidato = n.getIzquierdo();
                            NodoABB padreCandidato = n;
                            while(candidato.getDerecho()!=null){
                                padreCandidato = candidato;
                                candidato = candidato.getDerecho();
                            }

                            if(padreCandidato.getElem().compareTo(candidato.getElem())>0){
                                padreCandidato.setIzquierdo(candidato.getDerecho());
                            } else {
                                padreCandidato.setDerecho(candidato.getDerecho());
                            }

                            // Seteamos el valor del candidato en el lugar que vamos
                            // a eliminar
                            n.setElem(candidato.getElem());
                            

                        }

                        exito = true;
                        n = null;

                } else if(elemento.compareTo(n.getElem())<0){
                    padre = n;
                    n = n.getIzquierdo();
                } else {
                    padre = n;
                    n = n.getDerecho();
                }

            }
            return exito;
        }

        public boolean pertenece(Comparable elemento){
            // Método que retorna true si el elemento pertenece al árbol y false en caso contrario.

            boolean exito = false;

            NodoABB n = this.raiz;

            while((n != null)){
                if(elemento.compareTo(n.getElem())==0){
                    exito = true;
                    n = null;
                } else if(elemento.compareTo(n.getElem())<0) {
                    n = n.getIzquierdo();
                } else {
                    n = n.getDerecho();
                }
            }

            return exito;


        }




        public boolean esVacio(){
            // Si la raiz es null, entonces el árbol es vacío, por lo que se retorna true
            return this.raiz == null;
        }


        public void vaciar(){
            // Seteamos la raiz a null y el garbage collector elimina todos los nodos en Java
            this.raiz = null;
        }


        public Comparable minimoElem(){

            Comparable min;
            NodoABB n = this.raiz;
            
            while(n.getIzquierdo()!=null){
                n = n.getIzquierdo();
            }
            min = n.getElem();

            return min;
        }

        public Comparable maximoElem(){

            Comparable max;
            NodoABB n = this.raiz;
            
            while(n.getDerecho()!=null){
                n = n.getDerecho();
            }
            max = n.getElem();

            return max;
        }

        public Lista listar(){
            Lista lis = new Lista();
            listarAux(this.raiz, lis);
            return lis;
    
        }
    
    
        private void listarAux(NodoABB nodo, Lista lis){
            // Método recursivo PRIVADO para recorrer inorden (genera listado ordenado).
    
            if(nodo!=null){
                // Recorrer hijo izquierdo en inorden
    
                listarAux(nodo.getIzquierdo(), lis);
    
                // Visitar nodo izquierdo
                lis.insertar(nodo.getElem(), lis.longitud()+1);
    
                // Visitar hijo derecho
                listarAux(nodo.getDerecho(), lis);
            }
    
        } 

        public Lista listarRango(Comparable elemMin, Comparable elemMax){
            Lista lis = new Lista();
            listarRangoAux(this.raiz, lis, elemMin, elemMax);
            return lis;
    
        }

        private void listarRangoAux(NodoABB nodo, Lista lis, Comparable elemMin, Comparable elemMax){
        
            if(nodo.getElem().compareTo(elemMin)>=0){
                // Recorrer hijo izquierdo en inorden
                if(nodo.getIzquierdo()!=null){
                    listarRangoAux(nodo.getIzquierdo(), lis, elemMin, elemMax);
                }
            }
            
    
                // Visitar hijo derecho
            if(nodo.getElem().compareTo(elemMax)<=0){
                if(nodo.getDerecho()!=null){
                    listarRangoAux(nodo.getDerecho(), lis, elemMin, elemMax);
                }
            }
            if(nodo.getElem().compareTo(elemMin)>=0 & nodo.getElem().compareTo(elemMax)<=0){
                lis.insertar(nodo.getElem(), lis.longitud()+1);
            }
    
        }

        @Override
        public ArbolBB clone(){
            // Método público de clone, que crea un arbol vacío y llama al método
            // privado cloneAux que recorrera el arbol original 
            // e irá agregando los nodos a la nueva estructura
    
            ArbolBB clon = new ArbolBB();
    
            if(!this.esVacio()){
               clon.raiz= cloneAux(this.raiz);
            }
            return clon;
    
        }
    
        private NodoABB cloneAux(NodoABB n){
            // Método privado iterativo que va creando los nodos en la nueva estructura
    
            NodoABB nuevo = new NodoABB(null, null, null);
    
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
            
            if(this.raiz==null){
                s = "Arbol vacío";
            } else {
                s = concatenarToString(this.raiz);
            }
    
            return s;
    
        }
    
        private String concatenarToString(NodoABB nodo){
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
        
        public boolean eliminarMinimo(){
            NodoABB aux = this.raiz;
            NodoABB auxPadre = null;
            boolean minimoEncontrado = false;
            
            if(this.raiz!=null){
                // Primero hacemos un if para considerar el caso de la raiz
                if(this.raiz.getIzquierdo()==null){
                    // si el hijo izquierdo de la raiz es nulo, es que la raiz es el minimo
                    // Entonces damos a la raiz el valor del hijo derecho
                    this.raiz = this.raiz.getDerecho();
                } else {
                    // Empezamos a iterar.
                    while(aux!=null && ! minimoEncontrado){
                        if(aux.getIzquierdo()!=null){
                            // Si aux tiene hijo izquierdo seguimos bajando hasta que no tenga más
                            // Actualizamos aux y auxPadre
                            auxPadre = aux;
                            aux = aux.getIzquierdo();
                        } else {
                            // LLegamos al último hijo izquierdo, entonces cortamos el while
                            // poniendo minimoEncontrado como true
                            minimoEncontrado=true;
                        }
                    }
                    // Tenemos en aux el mínimo. 
                    if(aux.getDerecho()==null){
                        // El mínimo no tiene hijo derecho, entonces seteamos al padre a null
                        auxPadre.setIzquierdo(null);
                    } else {
                        // Como tiene hijo derecho, que es más grande, este debe seguir en el arból
                        // pero pasa a ser un hijo izquierdo de auxpadre
                        auxPadre.setIzquierdo(aux.getDerecho());
                    }
                }

            }
            return minimoEncontrado;
    
        }

        public Lista listarMayorIgual(Comparable elem){

            Lista l = new Lista();

            if(this.raiz!=null){
                listarMayorIgualAux(this.raiz, l,  elem);
            }

            return l;

        }

        private void listarMayorIgualAux(NodoABB n, Lista l, Comparable e){

            if(n!=null){
                if(n.getDerecho()!=null){
                    listarMayorIgualAux(n.getDerecho(), l, e);
                }
                if(n.getElem().compareTo(e)>=0){
                
                    l.insertar(n.getElem(), l.longitud()+1);
                    
                    NodoABB hi = n.getIzquierdo();
                    if(hi!=null){
                        listarMayorIgualAux(hi, l, e);
                    }
                }
            }
        }

        public Lista listarMenores(Comparable elem){

            Lista l = new Lista();

            if(this.raiz!=null){
                listarMenoresAux(this.raiz, l,  elem);
            }

            return l;

        }

        private void listarMenoresAux(NodoABB n, Lista l, Comparable elem){

            if(n!=null){
                if(n.getIzquierdo()!=null){
                        listarMenoresAux(n.getIzquierdo(), l, elem);
                    }
                if(n.getElem().compareTo(elem)<0){
                    l.insertar(n.getElem(), l.longitud()+1);
                    if(n.getDerecho()!=null){
                        listarMenoresAux(n.getDerecho(), l, elem);
                    }
                }
            }


        }
    
    
}
