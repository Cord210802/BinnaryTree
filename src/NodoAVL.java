public class NodoAVL <T extends Comparable<T>> implements PrintableNode<T>{
    int factorEquilibrio;
    T dato;
    NodoAVL<T> izquierda;
    NodoAVL<T> derecha;
    NodoAVL<T> papa;

    public NodoAVL(){
        this.factorEquilibrio = 0;
        this.dato = null;
        this.izquierda = null;
        this.derecha = null;
        this.papa = null;
    }

    public NodoAVL(T dato){
        this.factorEquilibrio = 0;
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
        this.papa = null;
    }

    @Override
    public PrintableNode getIzq() {
        return this.izquierda;
    }

    @Override
    public PrintableNode getDer() {
        return this.derecha;
    }

    @Override
    public T getElem() {
        return this.dato;
    }

    @Override
    public String toString() {
        return "" +this.dato + " " + this.factorEquilibrio;
    }

    public void cuelga(NodoAVL<T> hijo){
        if (hijo == null){
            return;
        }
        if (hijo.dato.compareTo(this.dato)<0){
            this.izquierda = hijo;
        }
        else{
            this.derecha = hijo;
        }
        hijo.papa = this;
    }
}
