package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL(){
        this.raiz = null;
    }

    private NodoAVL rotarIzquierda(NodoAVL n){
        
        NodoAVL h = n.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(n);
        n.setDerecho(temp);
        System.out.println("rote a izquierda con pivote: " + n.getElem());
        return h;
    }
    
    private NodoAVL rotarDerecha(NodoAVL n){
        
        NodoAVL h = n.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(n);
        n.setIzquierdo(temp);
        System.out.println("rote a derecha con pivote: " + n.getElem());
        return h;
    }

    public boolean insertar(Comparable elemento){

        boolean exito = true;
        if(this.raiz==null){
            this.raiz = new NodoAVL(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, elemento, null);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, Comparable elemento, NodoAVL padre){
        // Precondición: n no es nulo
        boolean exito = true;

        if((elemento.compareTo(n.getElem())==0)){
            // Reportar error: Elemento repetido
            exito = false;
        } else if(elemento.compareTo(n.getElem())<0){
            // Elemento es menor que n.getElem()
            // Si tiene HI baja a la izquierda, sino agrega elemento
            if(n.getIzquierdo()!=null){
                exito = insertarAux(n.getIzquierdo(), elemento, n);
            } else {
                n.setIzquierdo(new NodoAVL(elemento, null, null));
            }
        } else {
            //Elemento es mayor que n.getElem()
            // Si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDerecho()!=null){
                exito = insertarAux(n.getDerecho(), elemento, n);
            } else {
                n.setDerecho(new NodoAVL(elemento, null, null));
            }

        } 
        if(exito){
            rotar(n, elemento, padre);
        }    

        return exito;
    }


    public boolean eliminar(Comparable elemento){

        boolean exito = true;
        if(this.raiz!=null){
            exito = eliminarAux(this.raiz, elemento, null);
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean eliminarAux(NodoAVL n, Comparable elemento, NodoAVL padre){
        // Método que busca un elemento ingresado por parametro y en caso de que esté, lo elimina
        // Retorna true si pudo eliminar el elemento y false en caso de que no lo haya encontrado

        boolean exito = true;

        if(elemento.compareTo(n.getElem())==0){
            // Se encontró el elemento, entonces vemos a que caso pertenece
            // Caso uno, el elemento es una hoja
            exito = false;
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
                    NodoAVL candidato = n.getIzquierdo();
                    NodoAVL padreCandidato = n;
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
                
        } else if(elemento.compareTo(n.getElem())<0){
            exito = eliminarAux(n.getIzquierdo(), elemento, n);
        } else {
            exito = eliminarAux(n.getDerecho(), elemento, n);
        }
        if(exito){
            rotar(n, elemento, padre);
        }    
        return exito;
    }

    private void rotar(NodoAVL n, Comparable elem, NodoAVL padre){
    // Método utilizado para las rotaciones de ajuste.
    // Es un método privado ya que es un auxiliar que se utiliza en insertar y eliminar

        n.recalcularAltura();
        int balancePadre = balance(n);
        NodoAVL aux;


        if(balancePadre == -2 ){
            int balanceHijo = balance(n.getDerecho());
            if(balanceHijo == 0 | balanceHijo == -1){
                System.out.println("Se hace rotación simple a izquierda");
                // Rotación simple a izquierda porque esta caido a la derecha
                aux = rotarIzquierda(n);
                if(n == this.raiz){
                    this.raiz = aux;
                } else {
                    if(padre.getDerecho()==n){
                        padre.setDerecho(aux);
                    } else {
                        padre.setIzquierdo(aux);
                    }
                }

            } else {
                // Sería 1 entonces se hace un rotación doble (derecha, izquierda)
                n.setDerecho(rotarDerecha(n.getDerecho()));
                aux = rotarIzquierda(n);

                if(n == this.raiz){
                    this.raiz = aux;
                } else {
                    if(padre.getDerecho()==n){
                        padre.setDerecho(aux);
                    } else {
                        padre.setIzquierdo(aux);
                    }
                }
            }
        }

        if(balancePadre == 2 ){
            int balanceHijo = balance(n.getIzquierdo());
            if(balanceHijo == 0 | balanceHijo == 1){
                // Rotación simple a derecha porque esta caido a la izquierda
                aux = rotarDerecha(n);
                if(n==this.raiz){
                    this.raiz = aux;
                } else {
                    if(padre.getDerecho()==n){
                        padre.setDerecho(aux);
                    } else {
                        padre.setIzquierdo(aux);
                    }
                }

            } else {
                // Sería -1 entonces se hace un rotación doble (izquierda, derecha)
                n.setIzquierdo(rotarIzquierda(n.getIzquierdo()));
                aux = rotarDerecha(n);            
                
                if(n==this.raiz){
                    this.raiz = aux;
                } else {
                    if(padre.getDerecho()==n){
                        padre.setDerecho(aux);
                    } else {
                        padre.setIzquierdo(aux);
                    }
                }

            }
                
        }


    }


    private int balance(NodoAVL n){

        int balance, hI, hD;

        if(n.getIzquierdo()==null){
            hI = -1;
        } else {
            hI = n.getIzquierdo().getAltura();
        }
        if(n.getDerecho()==null){
            hD = -1;
        } else {
            hD = n.getDerecho().getAltura();
        }

        balance = hI - hD;

        return balance; 

    }


    public boolean pertenece(Comparable elemento){
        // Método que retorna true si el elemento pertenece al árbol y false en caso contrario.

        boolean exito = false;

        NodoAVL n = this.raiz;

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
        NodoAVL n = this.raiz;
        
        while(n.getIzquierdo()!=null){
            n = n.getIzquierdo();
        }
        min = n.getElem();

        return min;
    }

    public Comparable maximoElem(){

        Comparable max;
        NodoAVL n = this.raiz;
        
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


    private void listarAux(NodoAVL nodo, Lista lis){
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

    private void listarRangoAux(NodoAVL nodo, Lista lis, Comparable elemMin, Comparable elemMax){
        
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
    public ArbolAVL clone(){
        // Método público de clone, que crea un arbol vacío y llama al método
        // privado cloneAux que recorrera el arbol original 
        // e irá agregando los nodos a la nueva estructura

        ArbolAVL clon = new ArbolAVL();

        if(!this.esVacio()){
           clon.raiz= cloneAux(this.raiz);
        }
        return clon;

    }

    private NodoAVL cloneAux(NodoAVL n){
        // Método privado iterativo que va creando los nodos en la nueva estructura

        NodoAVL nuevo = new NodoAVL(null, null, null);

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

    private String concatenarToString(NodoAVL nodo){
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




}
