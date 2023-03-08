import java.util.concurrent.ConcurrentMap;

public class ArbolAVL <T extends Comparable<T>> {
    NodoAVL<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public void inserta(T dato){
        NodoAVL<T> actual = raiz;
        NodoAVL<T> papa = actual;
        NodoAVL<T> nuevo = new NodoAVL<T>(dato);
        boolean termine = false;

        if(raiz == null){
            raiz = nuevo;
        }
        else{
            while (actual != null){
                papa = actual;
                if (dato.compareTo(actual.dato)<=0){
                    actual = actual.izquierda;
                }
                else {
                    actual = actual.derecha;
                }
            }
            actual = nuevo;
            if (dato.compareTo(papa.dato)<=0){
                papa.izquierda = actual;
                actual.papa = papa;
            }
            else{
                papa.derecha = nuevo;
                actual.papa = papa;
            }
            //Colocar nuevos valores de equilibrio
            while (!termine && papa!=null){
                if(actual.equals(papa.derecha)){
                    papa.factorEquilibrio +=1;
                }
                else{
                    papa.factorEquilibrio -=1;
                }
                if(papa.factorEquilibrio == 0){
                    termine = true;
                }
                if(Math.abs(papa.factorEquilibrio) == 2){
                    papa = rotacion(papa);
                    termine = true;
                }
                actual = papa;
                papa = actual.papa;
            }
        }
    }

    private NodoAVL<T> rotacion(NodoAVL<T> papa) {
        NodoAVL<T> nuevoPapa = new NodoAVL<>();
        if (papa.factorEquilibrio == 2 && papa.derecha.factorEquilibrio >= 0){
            nuevoPapa = rotacionDD(papa);
        }
        if (papa.factorEquilibrio == -2 && papa.izquierda.factorEquilibrio <= 0){
            nuevoPapa = rotacionII(papa);
        }
        if (papa.factorEquilibrio == 2 && papa.derecha.factorEquilibrio == -1){
            nuevoPapa = rotacionDI(papa);
        }
        if (papa.factorEquilibrio == -2 && papa.izquierda.factorEquilibrio == 1){
            nuevoPapa = rotacionID(papa);
        }
        return nuevoPapa;
    }

    private NodoAVL<T> rotacionDD(NodoAVL<T> papa) {
        NodoAVL<T> abuelo = papa.papa;
        NodoAVL<T> alpha = papa;
        NodoAVL<T> beta = papa.derecha;
        NodoAVL<T> gama = beta.derecha;
        NodoAVL<T> b = beta.izquierda;
        beta.izquierda = alpha;
        beta.derecha = gama;
        alpha.papa = beta;
        gama.papa = beta;
        alpha.derecha = b;
        if (b != null){
            b.papa = alpha;
        }
        if (abuelo == null){
            raiz = beta;
            beta.papa = null;
        }
        else{
            abuelo.cuelga(beta);
        }
        actualizaFactor(beta);
        actualizaFactor(alpha);
        actualizaFactor(gama);
        return beta;
    }

    private NodoAVL<T> rotacionII(NodoAVL<T> papa) {
        NodoAVL<T> abuelo = papa.papa;
        NodoAVL<T> alpha = papa;
        NodoAVL<T> beta = papa.izquierda;
        NodoAVL<T> gama = beta.izquierda;
        NodoAVL<T> c = beta.derecha;
        beta.izquierda = gama;
        beta.derecha = alpha;
        alpha.papa = beta;
        gama.papa = beta;
        alpha.izquierda = c;
        if (c != null){
            c.papa = alpha;
        }
        if (abuelo == null){
            raiz = beta;
            beta.papa = null;
        }
        else{
            abuelo.cuelga(beta);
        }
        actualizaFactor(beta);
        actualizaFactor(alpha);
        actualizaFactor(gama);
        return beta;
    }

    private NodoAVL<T> rotacionDI(NodoAVL<T> papa) {
        NodoAVL<T> abuelo = papa.papa;
        NodoAVL<T> alpha = papa;
        NodoAVL<T> beta = papa.derecha;
        NodoAVL<T> gama = beta.izquierda;
        NodoAVL<T> b = gama.izquierda;
        NodoAVL<T> c = gama.derecha;
        gama.izquierda = alpha;
        alpha.papa = gama;
        gama.derecha = beta;
        beta.papa = gama;
        alpha.derecha = b;
        beta.izquierda = c;
        if (c != null){
            c.papa = beta;
        }
        if (b != null){
            b.papa = alpha;
        }
        if (abuelo == null){
            raiz = gama;
            gama.papa = null;
        }
        else{
            abuelo.cuelga(gama);
        }
        actualizaFactor(beta);
        actualizaFactor(alpha);
        actualizaFactor(gama);
        return gama;
    }

    private NodoAVL<T> rotacionID(NodoAVL<T> papa) {
        NodoAVL<T> abuelo = papa.papa;
        NodoAVL<T> alpha = papa;
        NodoAVL<T> beta = papa.izquierda;
        NodoAVL<T> gama = beta.derecha;
        NodoAVL<T> b = gama.izquierda;
        NodoAVL<T> c = gama.derecha;
        gama.derecha = alpha;
        alpha.papa = gama;
        gama.izquierda = beta;
        beta.papa = gama;
        alpha.izquierda = c;
        beta.derecha = b;
        if (c != null){
            c.papa = alpha;
        }
        if (b != null){
            b.papa = beta;
        }
        if (abuelo == null){
            raiz = gama;
            gama.papa = null;
        }
        else{
            abuelo.cuelga(gama);
        }
        actualizaFactor(beta);
        actualizaFactor(alpha);
        actualizaFactor(gama);
        return gama;
    }
    public void actualizaFactor(NodoAVL<T> nodo){
        int nodoI = calculaAltura(nodo.izquierda);
        int nodoD = calculaAltura(nodo.derecha);
        int fe = nodoD - nodoI;
        nodo.factorEquilibrio = fe;
    }
    private String toStringInorden(NodoAVL <T> actual, StringBuilder cad){
        if (actual == null){
            return "";
        }
        else{
            toStringInorden(actual.izquierda, cad);
            cad.append(actual.dato + " ");
            toStringInorden(actual.derecha, cad);
            return cad.toString();
        }
    }

    public String toStringInorden(){
        return toStringInorden(raiz, new StringBuilder());
    }

    public int max(int a, int b){
        int res;
        if (a>=b){
            res = a;
        }
        else{
            res = b;
        }
        return res;
    }

    private int calculaAltura(NodoAVL<T> actual, int cont){
        if (actual==null){
            return cont;
        }
        else{
            int izq = calculaAltura(actual.izquierda, cont+1);
            int der = calculaAltura(actual.derecha, cont+1);
            return max(izq, der);
        }
    }

    public int calculaAltura(NodoAVL<T> actual){
        return calculaAltura(actual, 0);
    }

    private NodoAVL <T> busca(T elem){
        NodoAVL <T> actual = raiz;

        while(actual != null && actual.dato.compareTo(elem)!= 0){
            if (elem.compareTo(actual.dato)<0){
                actual = actual.izquierda;
            }
            else{
                actual = actual.derecha;
            }
        }
        return actual;
    }

    public T borra (T elem){
        NodoAVL <T> actual = busca(elem);
        NodoAVL <T> hijo;
        NodoAVL <T> aux;
        NodoAVL <T> eliminado = null;
        NodoAVL <T> papa = null;
        boolean termine = false;
        T temp;
        if (actual == null){
            throw new RuntimeException();
        }
        temp = actual.dato;
        eliminado = actual;
        papa = eliminado.papa;
        if(actual.izquierda == null && actual.derecha == null){
            if(actual.equals(raiz)){
                raiz = null;
            }
            if (actual.dato.compareTo(actual.papa.dato)<=0){
                actual.papa.izquierda = null;
            }
            else{
                actual.papa.derecha = null;
            }
        }
        if (actual.izquierda == null || actual.derecha == null){
            if (actual.izquierda == null){
                hijo = actual.derecha;
                eliminado = hijo;
            }
            else{
                hijo = actual.izquierda;
                eliminado = hijo;
            }
            if (actual.equals(raiz)){
                raiz = hijo;
            }
            else{
                actual.papa.cuelga(hijo);
            }
        }
        if (actual.izquierda != null &&  actual.derecha != null){
            aux = actual.derecha;
            while(aux.izquierda != null){
                aux = aux.izquierda;
            }
            actual.dato = aux.dato;
            if (aux != actual.derecha){
                papa = aux.papa;
                aux.papa.izquierda = aux.derecha;
                if (aux.derecha != null){
                    eliminado = aux.papa.izquierda;
                    aux.derecha.papa = aux.papa;
                }
            }
            else{
                papa = aux.papa;
                aux.papa.derecha = aux.derecha;
                if (aux.derecha != null){
                    eliminado = aux.papa.derecha;
                    aux.derecha.papa = aux.papa;
                }
            }
        }
        while (!termine && papa != null){
            if(eliminado == papa.derecha){
                papa.factorEquilibrio -= 1;
            }
            else{
                papa.factorEquilibrio +=1;
            }
            if(Math.abs(papa.factorEquilibrio) == 1){
                termine = true;
            }
            if(Math.abs(papa.factorEquilibrio)== 2){
                papa= rotacion(papa);
            }
            eliminado = papa;
            papa = eliminado.papa;
        }
        return temp;
    }
}
