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
    public T remove (T elem){
        NodoAVL <T> actual = busca(elem);
        NodoAVL <T> hijo;
        NodoAVL <T> elimina = actual.papa;
        int fact = 0;
        NodoAVL <T> aux;
        T temp;
        if (actual == null){
            throw new RuntimeException();
        }
        temp = actual.dato;
        if(actual.izquierda == null && actual.derecha == null){
            if(actual.equals(raiz)){
                raiz = null;

            }
            if (actual.dato.compareTo(actual.papa.dato)<=0){
                actual.papa.izquierda = null;
                fact = 1;
            }
            else{
                actual.papa.derecha = null;
                fact = -1;
            }
        }
        else{
            if(actual.izquierda == null || actual.derecha == null){
                if (actual.izquierda == null){
                    hijo = actual.derecha;
                }
                else{
                    hijo = actual.izquierda;
                }
                if (actual.equals(raiz)){
                    raiz = hijo;
                    elimina = raiz;
                }
                else{
                    if(actual.dato.compareTo(actual.papa.dato)<=0){
                        fact = 1;
                    }
                    else {
                        fact = -1;
                    }
                    actual.papa.cuelga(hijo);
                }
            }
            if (actual.izquierda != null &&  actual.derecha != null){
                aux = actual.derecha;
                while(aux.izquierda != null){
                    aux = aux.izquierda;
                }
                actual.dato = aux.dato;
                elimina = aux.papa;
                if (aux != actual.derecha){
                    aux.papa.izquierda = aux.derecha;
                    fact = 1;
                    if (aux.derecha != null){
                        aux.derecha.papa = aux.papa;
                    }
                }
                else{
                    fact = -1;
                    aux.papa.derecha = aux.derecha;
                    if (aux.derecha != null){
                        aux.derecha.papa = aux.papa;
                    }
                }
            }

        }
        termine(elimina, fact);
        return temp;
    }

    private void termine(NodoAVL<T> elimina, int fact) {
        if (elimina == null){
            return;
        }
        else {
            elimina.factorEquilibrio += fact;
            while(elimina != null && Math.abs(elimina.factorEquilibrio) != 1){
                if (Math.abs(elimina.factorEquilibrio)==2){
                    elimina = rotacion(elimina);
                }
                if(elimina.papa != null && elimina == elimina.papa.derecha){
                    elimina.papa.factorEquilibrio -= 1;
                }
                if(elimina.papa != null && elimina == elimina.papa.izquierda){
                    elimina.papa.factorEquilibrio += 1;
                }
                elimina = elimina.papa;
            }
        }

    }
}
