package lineales.dinamicas;

public class Lista{

        private Nodo cabecera;

        public Lista (){
                // Creación de la lista con la cabecera en null.
                this.cabecera = null;
        }

        // Modificadores

        public boolean insertar(Object elemento, int pos){
                // Método insertar que inserta un objeto en la posición ingresada como parametro.
                boolean exito = true;
                // Chequea si la posición ingresada es correcta. Caso contrario devuelve false.
                if(pos>=1 & pos <= this.longitud()+1){

                        if(pos == 1){
                                // Si la posición es la primera se ubica el nuevo elemento en el primer lugar
                                this.cabecera = new Nodo(elemento, this.cabecera);
                        } else {
                                // Si la posición es distinta de la primera, encuentra la posición pos-1 y ubica
                                // el nuevo nodo en la posición especificada.
                                Nodo aux = this.cabecera;
                                int i = 1;
                                while(i < pos-1){
                                        aux = aux.getEnlace();
                                        i++;
                                }
                                Nodo nuevoNodo = new Nodo(elemento, aux.getEnlace());
                                aux.setEnlace(nuevoNodo);

                        }       
        
                } else {
                        exito = false;
                }
                return exito;

        }

        public boolean eliminar(int pos){
                // Método eliminar, elimina un elemento de la posición dada por parametro.
                boolean exito = true;

                if(pos >= 1 & pos <= this.longitud()){
                        // Dadp que la posición solicitada es una de las posibles, se 
                        // chequea si es la primera (caso especial) u otra.
                        if(pos == 1){
                                // En caso de ser la primera se cambia la cabecera al siguiente enlace
                                // y luego el recolector elimina el nodo que quedo sin apuntar.
                                this.cabecera = this.cabecera.getEnlace();
                        } else {
                                // En caso de ser otra posición, se recorre mediante un while
                                // hasta la posición anterior y le establece el enlace
                                // del siguiente a la posición solicitada, dejando así
                                // sin apuntar al elemento, que luego será eliminado por el recolector.
                                Nodo aux = this.cabecera;
                                int i = 1;
                                while(i < pos-1){
                                        aux = aux.getEnlace();
                                        i++;
                                }
                                aux.setEnlace(aux.getEnlace().getEnlace());
                        }
                } else {
                        exito = false;
                }

                return exito;


        }

        public Object recuperar(int pos){
                // Método recuperar, permite que dada una posición, se devuelve el elemento
                // de la misma, o null en caso de que la posición NO sea válida.
                
                int i = 1;
                Object ret = null;

                if(pos>=1 & pos <= this.longitud()){
                        // La posición es válida
                        Nodo aux = this.cabecera;
                        while(i <= pos){
                                // Se recorre el arreglo mediante los enlaces hasta llegar 
                                // a la posición solicitada y se guarda el elemento en ret
                                // para ser retornado.
                                ret = aux.getElem();
                                aux = aux.getEnlace();
                                i++;
                        }
                }
                return ret;


        }

        public int localizar(Object elemento){
                // Método localizar, permite ingresar un elemento, y se retorna la posición
                // en la que se encuentra, o -1 si no esta en la lista.
                
                boolean exito = false;
                int i = 1;
                Nodo aux = this.cabecera;


                while(i <= this.longitud() & !exito){
                        // Se recorre la lista hasta que se termine la misma, o 
                        // se haya encontrado el elemento
                        if(aux.getElem()==elemento){
                                // Se encontró el elemento, se corta el whie.
                                exito = true;
                        } else {
                                // No se encontró, se avanza al siguiente nodo.
                                aux = aux.getEnlace();
                                i++;
                        }

                }
                if(!exito){
                        // Si exito sigue falsa, el elemento nunca se encontró
                        // por lo que hay que retornar -1
                        i = -1;
                }
                return i;

        }

        public void vaciar(){
                // Se pone el puntero inicial a null, rompiendo la cadena que luego será limpiada por
                // el recolector de basura.

                this.cabecera = null;

        }

        public boolean esVacia(){
                // Si la cabecera es vacía, no hay nodos conectados.
                return this.cabecera==null;
        }

        @Override
        public Lista clone(){

                // Método clone, para clonar una lista.
                // Crea una nueva lista vacía
                Lista nuevaLista = new Lista();

                if(!this.esVacia()){
                        // Si la lista original NO esta vacía, se hace un nodo auxiliar
                        // que apunta a la cabecera (servirá para ir generando copias de los nodos)
                        // que se iran anexando en la nueva lista.
                        Nodo aux = this.cabecera;
                        // Se establece un nodo nuevo, con el elemento del primer nodo y enlace null.
                        Nodo nuevo = new Nodo(aux.getElem(), null);
                        // La cabecera de la nueva lista apunta al nodo nuevo, de forma tal que 
                        // apunta al primer elemento de la lista original.
                        nuevaLista.cabecera=nuevo;

                        // Otro nodo auxiliar que apunta a la cabecera de la nueva lista.
                        Nodo auxClon = nuevaLista.cabecera;
                        // Aux pasa al segundo nodo de la lista original.
                        aux = aux.getEnlace();

                        while(aux != null){
                                // Mientras el nodo de la lista original, no apunte a null 
                                // Es decir, no sea el último de la lista. 
                                // Se repite el proceso de crear un nuevo nodo, 
                                // enlazarlo auxClon al mismo, pasar auxClon al nuevo nodo
                                // y pasar aux al siguiente nodo de la lista original.
                                nuevo = new Nodo(aux.getElem(), null);
                                auxClon.setEnlace(nuevo);
                                auxClon = auxClon.getEnlace();
                                aux = aux.getEnlace();
                        }
                }
                return nuevaLista;

        }


        public int longitud(){
                // Método para calcular la longitud.
                // Se inicializa un contador i y luego se recorre los enlaces aumentando
                // el contador, cuando no hay más enlaces, se retorna el valor del contador

                int i = 0;
                Nodo aux = this.cabecera;
                while(aux!=null){
                        aux = aux.getEnlace();
                        i++;
                }
                return i;
        }

        @Override
        public String toString(){

                // Método toString, sirve para controlar que los métodos de la clase funcionan
                // como queremos. Se arma un string y luego se van agregando cada uno
                // de los elementos de la lista. En caso de que no haya elementos, se
                // considera "Lista vacía". Se retorna el string.

                String s ="[";

                if(this.esVacia()){
                        s = "Lista vacía";
                } else {
                        int i = 1;
                        Nodo aux = this.cabecera;
                        while(i <= this.longitud()){
                                s += aux.getElem().toString();
                                aux = aux.getEnlace();
                                i++;
                                if(aux!=null){
                                        s+=", ";
                                }
                        }
                s +="]";
                }
                
                return s;

        }


}