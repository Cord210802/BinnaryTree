
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jupac
 */
public class BinarySearch <T extends Comparable<T>> implements BinarySearchADT<T>{
    NodoBST<T> raiz;
    int cont;
    
    public void add(T elem) {
        NodoBST <T> actual = raiz;
        NodoBST <T> papa = actual;
        NodoBST <T> nuevo = new NodoBST<T>(elem);
        if (raiz == null){
            raiz = nuevo;
            cont++;
        }
        else{
            while(actual != null){
                papa = actual;
                if (elem.compareTo(actual.dato)<=0){
                    actual = actual.izquierda;
                }
                else{
                    actual = actual.derecha;
                }
            }
            cont++;
            if (elem.compareTo(papa.dato)<=0){
                papa.izquierda = nuevo;
                papa.izquierda.papa = papa;
            }
            else{
                papa.derecha = nuevo;
                papa.derecha.papa = papa;
            }
        }
    }
    
    @Override
    public T findMin() {
        NodoBST <T> actual = raiz;
        if (raiz == null){
            throw new RuntimeException();
        }
        else{
            while(actual.izquierda != null){
                actual = actual.izquierda;
            }
        }
        return actual.dato;
    }

    public boolean isEmpty() {
        return cont == 0;
    }

    @Override
    public T findMax() {
    NodoBST <T> actual = raiz;
        if (raiz == null){
            throw new RuntimeException();
        }
        else{
            while(actual.derecha != null){
                actual = actual.derecha;
            }
        }
        return actual.dato;
    }
    
    private String toStringInorden(NodoBST <T> actual, StringBuilder cad){
        if (actual == null){
            return "";
        }
        else{
            toStringInorden(actual.izquierda, cad);
            cad.append(actual.dato);
            toStringInorden(actual.derecha, cad);
            return cad.toString();
        }
    }
    
    public String toStringInorden(){
        return toStringInorden(raiz, new StringBuilder());
    }
    
    private NodoBST <T> busca(T elem){
        NodoBST <T> actual = raiz;
        
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
    
    @Override
    public T remove (T elem){
        NodoBST <T> actual = busca(elem);
        NodoBST <T> hijo = new NodoBST();
        NodoBST <T> aux;
        T temp;
        if (actual == null){
            throw new RuntimeException();
        }
        cont--;
        temp = actual.dato;
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
            }
            else{
                hijo = actual.izquierda;
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
                aux.papa.izquierda = aux.derecha;
                if (aux.derecha != null){
                    aux.derecha.papa = aux.papa;
                }
            }
            else{
                aux.papa.derecha = aux.derecha;
                if (aux.derecha != null){
                    aux.derecha.papa = aux.papa;
                }
            }
        }
        return temp;
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
    
    private int calculaAltura(NodoBST<T> actual, int cont){
        if (actual==null){
            return cont;
        }
        else{
            int izq = calculaAltura(actual.izquierda, cont+1);
            int der = calculaAltura(actual.derecha, cont+1);
            return max(izq, der);
        }
    }
    
    public int calculaAltura(){
        return calculaAltura(this.raiz, 0);
    }
    
    public int promedioArbol(int n, int m){
        Random random = new Random();
        int contador = 0;
        for (int i = 0; i<m;i++){
            this.raiz = null;
            for (int j = 0; j<n;j++){
                Integer randomNum = random.nextInt(0, n+1);
                this.add((T)randomNum);
            }
            contador += this.calculaAltura();
        }

        return contador/m;
    }
}
