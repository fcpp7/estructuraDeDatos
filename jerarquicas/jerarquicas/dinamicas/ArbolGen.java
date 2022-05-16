package jerarquicas.dinamicas;

import lineales.dinamicas.*;

public class ArbolGen {
   
    private NodoGen raiz;
    

    // Constructor
    public ArbolGen(){
        this.raiz = null;
    }

    // 

    public boolean insertar(Object elemNuevo, Object elemPadre){

        boolean exito = true;

        if(this.raiz==null){
            // El arbol esta vacío, entonces se establece el elemento como raíz
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            // Dado que el arbol no esta vacío, busca la ubicación del padre.
            NodoGen nPadre = obtenerNodo(this.raiz, elemPadre);

            // Si padre existe va buscando donde ponerlo
            if(nPadre != null){
                
                if(nPadre.getHijoIzquierdo()== null){
                    // No tiene hijo izquierdo, puede ponerlo en ese lugar
                    nPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));

                } else {
                    NodoGen nuevoHijoIzquierdo = new NodoGen(elemNuevo, null, nPadre.getHijoIzquierdo());
                    nPadre.setHijoIzquierdo(nuevoHijoIzquierdo);

                }
            } else {
                exito = false;
            }

        }
        return exito;


    }

    private NodoGen obtenerNodo(NodoGen n, Object buscado){
        // Método PRIVADO que busca un elemento y devuelve el nodo que 
        // lo contiene. Si no encuentra buscado devuelve null

        NodoGen resultado = null;

        if(n !=null ){
            if(n.getElem().equals(buscado)){
                resultado = n;
            } else {
                // No es el buscado: busca primero en el HI
                resultado = obtenerNodo(n.getHijoIzquierdo(), buscado);

                // Si no lo encontró en el HI, busca en los hermanos
                while(resultado == null && n!=null){
                    resultado = obtenerNodo(n.getHermanoDerecho(), buscado);
                    n = n.getHermanoDerecho();
                }
            }
        } 
        return resultado;

    }

    public boolean esVacio(){
        // Método que retorna un boolean, true si el árbol es vacío y false en caso contrario

        return this.raiz==null;
    }

    public boolean pertenece(Object elemento){
        // Método que retorna verdadero si el elemento pertenece al arbol y falso en caso contrario.

        boolean exito = false;
        if(!this.esVacio()){
            exito = perteneceAux(this.raiz, elemento);
        }
        return exito;

    }


    private boolean perteneceAux(NodoGen nodo, Object elem){

        boolean exito = false;

        if(nodo !=null ){
            if(nodo.getElem().equals(elem)){
                exito = true;
            } else {
                // No es el buscado: busca primero en el HI
                // Se establece un if para que no de error de puntero nulo
                if(nodo.getHijoIzquierdo()!=null){
                    exito = perteneceAux(nodo.getHijoIzquierdo(), elem);
                    // Si no lo encontró en el HI, busca en los hermanos
                    // Se establece un hermano derecho que irá iterando.
                    NodoGen hd = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while(!exito && hd!=null){
                        exito = perteneceAux(hd, elem);
                        hd = hd.getHermanoDerecho();
                    }
    
                }
            }
        }
        return exito;

    }

    public Lista ancestros(Object elem){

        Lista ls = new Lista();
        int pos = 0;

        if(!this.esVacio() && !this.raiz.getElem().equals(elem)){

            ancestrosAux(this.raiz, ls, elem, pos);

        }


        return ls;

    }

    private boolean ancestrosAux(NodoGen n, Lista lista, Object elemento, int pos){

        boolean exito = false;
        if(n != null){

            // Buscamos en los hijos del nodo si esta el elemento
            NodoGen hi = n.getHijoIzquierdo();
            if(hi!=null){
                exito = hi.getElem().equals(elemento);

                NodoGen hd = hi.getHermanoDerecho();
                while(hd!=null && !exito){
                    exito = hd.getElem().equals(elemento);
                    hd = hd.getHermanoDerecho();
                        
                }
            }

            if(exito){
                // Si encontramos al elemento entre sus hijos, agregamos a la lista al 
                // nodo
                lista.insertar(n.getElem(), pos+1);
            } else {
                // En caso de no haberlo encontrado, empazamos a iterar recursivamente
                if(hi != null){
                    // Primero vamos con el hijo izquierdo
                    exito = ancestrosAux(hi, lista, elemento, pos);

                    NodoGen hd = hi.getHermanoDerecho();
                    while(hd != null && !exito){
                        exito = ancestrosAux(hd, lista, elemento, pos);
           
                        hd = hd.getHermanoDerecho();
                    }
                    // Si encuentra cuando sale de recorrer los hijos se encontró el elemento
                    // exito sera true y entonces vamos insertando el elemento "padre"
           
                    if(exito){
                        lista.insertar(n.getElem(), pos+1);
                    }

                }
            }

        }

        return exito;
    }



    public int altura(){
        // Método que calcula la altura.
        // Predefine la variable en -1 ya que si el arbol es vacío se retorna -1
        int alt = -1;
        if(this.raiz != null){
            // Si el árbol NO es vacío se llama al método cálculo altura.
            alt = alturaAux(this.raiz);
        }

        return alt;
    }

    private int alturaAux(NodoGen nodo){
        // Método recursivo privado para recorrer el arbol y extraer la máxima altura.

        int alturaMaxima = -1;
        int altIzquierda = 0;
        int altDerecha = 0;
        int alturaHermanosDerechos = -1;
        if(nodo != null){
            // Si el nodo ingresado por parametro no es nulo, ingresa y llama recuesivamente
            // al hijo izquierdo.

            // Tengo la altura del hijo izquierdo
            altIzquierda = alturaAux(nodo.getHijoIzquierdo());

            NodoGen hi = nodo.getHijoIzquierdo();

            if(hi!=null){
                // Luego voy por los hermanos derechos
                NodoGen hd = hi.getHermanoDerecho();
                while(hd!=null){
                    // Para cada hermano derecho calculo la altura recursivamente
                    altDerecha = alturaAux(hd);
                    // Con una variable auxiliar, voy guardando cual es la máxima altura de todos los hnos
                    // derechos.
                    if(altDerecha > alturaHermanosDerechos){
                        alturaHermanosDerechos = altDerecha;
                    }
                    hd = hd.getHermanoDerecho();
                }
            }
            // A la altura máxima que encontró entre la izquierda y los hermanos derecha lo va incrementando en 1
            // y lo asigna a la altura máxima.

            if(altIzquierda >= alturaHermanosDerechos){
                alturaMaxima = altIzquierda +1;
            } else {
                alturaMaxima = alturaHermanosDerechos +1;
            }
        }

        return alturaMaxima;
    }

    public int nivel(Object elemento){

        // Método 
        int niv=-1;

        if(this.raiz!=null){
            // Si no es nulo, llamamos a un método recursivo que busque
            niv = nivelAux(this.raiz, elemento, 0);
        }

        return niv;
    }

    private int nivelAux(NodoGen nodo, Object elem, int nivelActual){
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
                NodoGen hi = nodo.getHijoIzquierdo();
                if(hi!=null){
                    nivelElem = nivelAux(hi, elem, nivelActual+1);
                    // Se consulta si nivelElem sigue en -1, lo cual marcaría que no se 
                    // encontro dentro de la rama izquierda, y entonces se busca en los hermanos derechos.
    
                    if(nivelElem==-1){
                        NodoGen hd = hi.getHermanoDerecho();
                        while(hd !=null){
                            nivelElem = nivelAux(hd, elem, nivelActual+1);
                            if(nivelElem==-1){
                                // Si no encontro, seguimos con los otros hermanos derechos
                                hd = hd.getHermanoDerecho();
                            } else {
                                // Si encontró, cortamos el while asignando null a hd
                                hd = null;
                            }
    
                        }
                    }
    
                }
            }
             
        }
        return nivelElem;
    }



    public Object padre(Object elemento){
        // Método que retorna el valor almacenado en el nodo padre, del nodo ingresado como parametro (primera aparición)

        Object elemPadre = null;

        if(!this.esVacio()){
            elemPadre = padreAux(this.raiz, elemento);
        }

        return elemPadre;

    }

    private Object padreAux(NodoGen n, Object elem){

        Object elemPadre = null;

        if(n !=null){
        // Ingresamos si el nodo no es nulo, sino, retornamos nulo

            if(!n.getElem().equals(elem)){
                // Si el nodo ingresado tiene el elemento, es que no tiene padre, por lo tanto no ingresamos.
                // Creamos el hijo izquierdo para la iteraciones.
                NodoGen hi = n.getHijoIzquierdo();
                if(hi!=null){
                    // Chequeamos si el hi es nulo, para no tener problemas de puntero nulo
                    if(hi.getElem().equals(elem)){
                        // En caso de que el elemento buscado este en el nodo del hijo izquierdo, 
                        // encontramos al elemento, por lo tanto le asignamos a elemento padre el 
                        // nodo inicial
                        elemPadre = n.getElem();
                    } else {
                        // En caso de que NO este en el hijo izquierdo, hacemos una recursividad 
                        // con el hijo izquierdo
                        elemPadre = padreAux(hi, elem);

                        if(elemPadre==null){
                            // En caso de que luego de recorrer todos los hijos izquierdo, elemPadre
                            // siga siendo nulo, significa que NO encontro el elemento, por lo cual,
                            // empezamos a ver los hermanos derechos.
                            // Creamos el nodo hermano derecho
                            NodoGen hd = hi.getHermanoDerecho();

                            // Dado que pueden ser varios, necesitamos iterar por TODOS los hermanos
                            // derechos, mientras estos NO sean nulos
                            while(hd!=null){

                                if(hd.getElem().equals(elem)){
                                    // En caso de que encontremos el elemento en un hermano derecho,
                                    // asignamos el a elemento padre el elemento del nodo inicial
                                    elemPadre = n.getElem();
                                    // Seteamos el hermano derecho a null, para que corte el while
                                    hd = null;
                                } else {
                                    // En caso de que NO este el elemento en el hno derecho actual, 
                                    // seguimos con una recursiva con los otros hermanos derechos
                                    elemPadre = padreAux(hd, elem);
                                    // Seteamos hd para que se corra al siguiente hermano derecho.
                                    hd = hd.getHermanoDerecho();
                                }
                            }
    
                        }
                    }
                }
            }
        }

        return elemPadre;


    }

    public Lista listarPreorden(){
        // Método que retorna una lista ordenada con preorden
        
        Lista salida = new Lista();
        
        if(this.raiz!=null){
            listarPreordenAux(this.raiz, salida);
        }

        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista ls){
        // Método privado y recursivo que recibe una lista por parametro y modifica la 
        // misma agregando los elementos en forma de preorden

        if(n!=null){
            ls.insertar(n.getElem(), ls.longitud() + 1);

            NodoGen hijo = n.getHijoIzquierdo();

            while(hijo!=null){
                listarPreordenAux(hijo, ls);
                hijo = hijo.getHermanoDerecho();
            }
        } 


    }

    public Lista listarInorden(){
        Lista salida = new Lista();
        if(this.raiz != null){
            listarInordenAux(this.raiz, salida);
        }
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls){

        if(n!=null){
            // llamado recursivo con primer hijo de n
            if(n.getHijoIzquierdo() != null){
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            // Visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);

            // Llamados recursivos con los otros hijos de n
            if(n.getHijoIzquierdo() != null){
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while(hijo!=null){
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden(){
        // Método que retorna una lista con los elementos del arbol en orden posorden.

        Lista salida = new Lista();

        if(this.raiz!=null){
            listarPosordenAux(this.raiz, salida);
        }
        return salida;

    }

    private void listarPosordenAux(NodoGen n, Lista ls){
        // Método privado y recursivo que va llenando la lista con los elementos del arbol en posorden

        if(n != null){

            if(n.getHijoIzquierdo() != null){
                listarPosordenAux(n.getHijoIzquierdo(), ls);
            }

            ls.insertar(n.getElem(), ls.longitud()+1);

            if(n.getHermanoDerecho() != null){
                listarPosordenAux(n.getHermanoDerecho(), ls);
            }


        }


    }


    public Lista listarPorNiveles(){
        // Método que retorna una lista con los elementos del arbol por nivel de izq a der

        Lista salida = new Lista();

        if(this.raiz!=null){
            // La cola servirá para ir guardando todos los nodos del mismo nivel 
            // Una vez listados, se irá sacando los hijos de cada uno de ellos para el siguiente nivel
            Cola q = new Cola();

            q.poner(this.raiz);

            int i = 1;
            while(!q.esVacia()){
                NodoGen n = (NodoGen) q.obtenerFrente();
                q.sacar();
                salida.insertar(n.getElem(), i);

                NodoGen hijo = n.getHijoIzquierdo(); 

                if(hijo!=null){

                    q.poner(hijo);
                    
                    NodoGen hd = hijo.getHermanoDerecho();
                    
                    while(hd!=null){
                    
                        q.poner(hd);
                    
                        hd = hd.getHermanoDerecho();
                    
                    }
                }
                i +=1;

            }

        }

        return salida;


    }


    public void vaciar(){
        // Para vaciar la estructura en java alcanza con cambiar la raiz a null 
        // Luego el garbage collector eliminará todos los nodos que quedaron sin apuntar

        this.raiz = null;
    }


    public int grado(){

        int g = -1;

        if(!this.esVacio()){
            g = gradoAux(this.raiz, g);

        }

        return g;
    }

    private int gradoAux(NodoGen n, int grado){

        int g=0;

        if(n!=null){
            NodoGen hi = n.getHijoIzquierdo();
            if(hi!=null){
                g += 1;
                
                NodoGen hd = hi.getHermanoDerecho();
                while(hd!=null){
                    g +=1;
                    hd = hd.getHermanoDerecho();
                }

                if(g > grado){
                    grado = g;
                }

            // Empezamos a iterar.
            hi = n.getHijoIzquierdo();
            if(hi != null){
                grado = gradoAux(hi, grado);

                hd = hi.getHermanoDerecho();
                while(hd!=null){
                    grado = gradoAux(hd, grado);
                    hd = hd.getHermanoDerecho();
                }
            }

        }
    }
        return grado;


    }

    public int gradoSubarbol(Object elem){

        int g = -1;
        if(!this.esVacio()){
            g = subGradoAux(this.raiz, elem);

        }

        return g;
    }



    private int subGradoAux(NodoGen n, Object elem){

        int grado = -1;

        if(n != null){
            if(n.getElem().equals(elem)){
                grado = 0;
                NodoGen hi = n.getHijoIzquierdo();
                if(hi != null){
                    grado +=1;
                    
                    NodoGen hd = hi.getHermanoDerecho();
                    while(hd!=null){
                        grado += 1;

                        hd = hd.getHermanoDerecho();
                    }
                }
            } else{
                // No encontramos, entonces seguimos iterando buscando el elemento
                NodoGen hi = n.getHijoIzquierdo();
                if(hi != null){
                    grado = subGradoAux(hi, elem);

                    if(grado == -1){
                        NodoGen hd = hi.getHermanoDerecho();
                        while(hd!=null && grado==-1){
                            grado = subGradoAux(hd, elem);
                            hd = hd.getHermanoDerecho();
                        }
                    }
                }
            }
            

        }



        return grado;
    }


    @Override
    public ArbolGen clone(){
        ArbolGen arbolClon = new ArbolGen();

        if(!this.esVacio()){
            arbolClon.raiz = cloneAux(this.raiz);
        }

        return arbolClon;
    }

    private NodoGen cloneAux(NodoGen n){
        // Método privado iterativo que va creando los nodos en la nueva estructura
        // Creamos un nodo nuevo y un nodo auxiliar que nos ayudarán a ir enganchando los punteros

        NodoGen nuevo = new NodoGen(null, null, null);
        NodoGen aux2 = new NodoGen(null, null, null);

        if(n != null){
            // Si el nodo no es nulo ingresamos y primero seteamos el elemento a nuevo.
            nuevo.setElem(n.getElem());

            // Generamos un objeto hi que tiene el hijo izquierdo
            NodoGen hi = n.getHijoIzquierdo();

            if(hi != null){
                // Si el hijo izquierdo NO es nulo, seteamos el hijo izquierdo con
                // llamando a cloneAux, es decir, llamando con el hijo izquierdo del nodo inicial

                nuevo.setHijoIzquierdo(cloneAux(hi));

                }

            if(hi != null){
                // Para evitar problemas de puntero nulo, volvemos a entrar si existia el hi.

                // Creamos un objeto NodoGen para el hermano derecho
                NodoGen hd = hi.getHermanoDerecho(); 

                // Asignamos el hijo izquierdo a aux2, esto es porque no tenemos que 
                // apuntar el hermano derecho al padre, sino a su hermano.
                aux2 = nuevo.getHijoIzquierdo();

                while(hd != null){
                    // Mientras el hermano derecho no sea nulo, llamamos recursivamente
                    // a cloneAux con hd y seteamos ese valor en el hermano derecho de aux2 
                    // es decir, del hijo izquierdo que teniamos
                    aux2.setHermanoDerecho(cloneAux(hd));

                    // Actualizamos el hermano derecho para que pase al
                    hd = hd.getHermanoDerecho();

                    // Actualizamos aux2 para que este parada en el hermano que recién seteo.
                    aux2 = aux2.getHermanoDerecho();
                }
    
            }
        }
        return nuevo;

    }


    @Override
    public String toString(){
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n){

        String s= "";

        if(n != null){
            // Visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();

            while(hijo!=null){
                s += hijo.getElem().toString();
                hijo = hijo.getHermanoDerecho();
            }

            // Comienza recorrido de los hijos de n llamando recursivamente para que
            // cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while(hijo!=null){
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }

        return s;
    }
    public boolean sonFrontera(Lista unaLista){
        // Método publico que recibe una lista de elementos y 
        // retorna true si TODOS los mismos forman parte de la frontera del árbol
        // y false en caso contrario

        boolean estan = false;

        // Se clona la lista para podes modificarla sin modificar el original.
        Lista lClone = unaLista.clone();

        if(!this.esVacio() && !lClone.esVacia()){
            estan = sonFronteraAux(this.raiz, lClone);
        }

        return estan;
    }

    private boolean sonFronteraAux(NodoGen n, Lista ls){
        // Método privado recursivo para ir chequeando cada valor de la lista
        // si esta en la frontera. Primero baja hasta nodos frontera (nodos que NO tienen
        // hijo izquierdo) y chequea si ese valor del nodo esta en la lista. En 
        // caso de que este, lo elimina.

        int i;

        NodoGen hi = n.getHijoIzquierdo();

        if(hi == null){
            // Estamos en un valor de la frontera
            i = ls.localizar(n.getElem());
            if(i > 0){
                ls.eliminar(i);
            }
        } else {
            // No estamos en una frontera, entonces seguimos recorriendo con preorden
            sonFronteraAux(hi, ls);

            NodoGen hd = hi.getHermanoDerecho();
            while(hd!=null){
                sonFronteraAux(hd, ls);
                hd = hd.getHermanoDerecho();
            }
        }

        // Si la lista tiene longitud 0, entonces es que todos los elementos fueron
        // encontrados y eliminados. En caso contrario es que quedaron elementos, 
        // por lo tanto no todos los elementos de la lista estaban en la frontera.
        return ls.longitud()==0;

    }





}
