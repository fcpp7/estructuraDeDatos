package conjuntistas.dinamicas;

public class CeldaHash {

    private Object elem;
    private int estado;

    public CeldaHash(Object e, int est){
        this.elem = e;
        this.estado = est;
    } 

    public Object getElem(){
        return this.elem;
    }

    public int getEstado(){
        return this.estado;
    }

    public void setElem(Object el){
        this.elem = el;
    }

    public void setEstado(int est){
        this.estado = est;
    }


    
}
