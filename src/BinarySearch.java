
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

    public BinarySearch() {
        this.raiz = null;
    }

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

    public boolean esDeBusqueda(NodoBST<T> actual){
        if(actual == null){
            return true;
        }
        else{
            if(actual.izquierda != null){
                if(actual.izquierda.dato.compareTo(actual.dato)>0){
                    return false;
                }
            }
            if(actual.derecha != null){
                if(actual.derecha.dato.compareTo(actual.dato)<0){
                    return false;
                }
            }
            return esDeBusqueda(actual.izquierda) && esDeBusqueda(actual.derecha);
        }
    }

    public boolean esDeBusqueda(){
        return esDeBusqueda(raiz);
    }

    public int [] encontrarMinimo(int[] arreglo1, int[] arreglo2, int[] arreglo3) {
        int minimo = arreglo1[0] + arreglo1[1];
        int [] min = arreglo1;
        if ((arreglo2[0] + arreglo2[1]) < minimo) {
            min = arreglo2;
        }
        if ((arreglo3[0] + arreglo3[1]) < minimo) {
            min = arreglo3;
        }
        return min;
    }
    public int [] buscaPapaMin(NodoBST<T> primero, NodoBST<T> segundo, int p, int s, int[] arr){
        arr[0] = p;
        arr[1] = s;
        if(primero == null || segundo == null){
            return null;
        }
        if(primero.papa != null && segundo.papa!= null && primero.papa.equals(segundo.papa)){
            System.out.println(arr[0] + " "+ arr[1]);
            int [] arrAux1 = new int[2];
            arrAux1[0] = arr[0];
            arrAux1[1] = arr[1];
            return arrAux1;
        }
        if(primero.papa == null && segundo.papa == null){
            int [] arrAux = new int[2];
            arrAux[0] = arr[0];
            arrAux[1] = arr[1];
            return arrAux;
        }
        int [] arrP = buscaPapaMin(primero.papa, segundo, p+1, s, arr);
        System.out.println(arrP);
        int [] arrS = buscaPapaMin(primero, segundo.papa, p, s+1, arr);
        System.out.println(arrS);
        int [] arrPS = buscaPapaMin(primero.papa, segundo.papa, p+1, s+1, arr);
        return minimo(arrP, arrS, arrPS);
    }

    public int[] minimo(int[] arrP, int[] arrS, int[] arrPS) {
        int [] min;
        if(arrP != null && arrS != null && arrPS!= null){
            System.out.println("Hola");
            min = encontrarMinimo(arrP, arrS, arrPS);
            return min;
        }
        if(arrP != null && arrS == null && arrPS== null){
            return arrP;
        }
        if(arrS != null && arrS == null && arrP== null){
            return arrS;
        }
        if(arrPS != null && arrS == null && arrP== null){
            return arrPS;
        }
        if(arrP != null && arrS != null && arrPS == null){
            if(arrP[0] + arrP[1] > arrS[0] + arrS[1]){
                return arrS;
            }
            else {
                return arrP;
            }
        }
        if(arrPS != null && arrS != null && arrP == null){
            if(arrPS[0] + arrPS[1] > arrS[0] + arrS[1]){
                return arrS;
            }
            else {
                return arrPS;
            }
        }
        if(arrP != null && arrPS != null && arrS == null){
            if(arrP[0] + arrP[1] > arrPS[0] + arrPS[1]){
                return arrPS;
            }
            else {
                return arrP;
            }
        }
        return null;
    }

    public NodoBST<T> buscaPapaMin(NodoBST<T> primero, NodoBST<T> segundo){
        int [] a = {0,0};
        int [] arr = buscaPapaMin(primero, segundo, 0, 0, a);
        NodoBST<T> aux = primero;
        for(int i = 0; i <= arr[0]; i++){
            aux = aux.papa;
        }
        return aux;
    }
}
