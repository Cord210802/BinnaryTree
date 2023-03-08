/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jupac
 */
public class BinaryTree <T> implements BinaryTreeADT{
    
    NodoBin<T> raiz;
    int cont;

    public BinaryTree(T elemento) {
        raiz = new NodoBin(elemento);
        cont = 1;
    }

    @Override
    public boolean isEmpty() {
        return cont == 0;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private <T> void preOrden(NodoBin<T> actual, ArrayList<T> lista){
        if(actual == null){
            return;
        }
        lista.add(actual.elem);
        preOrden(actual.izquierda, lista);
        preOrden(actual.derecha, lista);
    }
    
    private Iterator<T> preOrden(){
        PilaA<NodoBin<T>> pila = new PilaA();
        ArrayList <T> lista = new ArrayList();
        pila.push(raiz);
        NodoBin<T> actual;
        while(!pila.isEmpty()){
            actual = pila.pop();
            lista.add(actual.elem);
            if (actual.derecha != null){
                pila.push(actual.derecha);
            }
            if (actual.izquierda != null){
                pila.push(actual.izquierda);
            }
        }
        return lista.iterator();
    }
    
    private Iterator<T> Nivel(){
        ColaA<NodoBin<T>> cola = new ColaA();
        ArrayList <T> lista = new ArrayList();
        cola.agrega(raiz);
        NodoBin<T> actual;
        while(!cola.estaVacia()){
            actual = cola.saca();
            lista.add(actual.elem);
            if (actual.derecha != null){
                cola.agrega(actual.derecha);
            }
            if (actual.izquierda != null){
                cola.agrega(actual.izquierda);
            }
        }
        return lista.iterator();
    }
    
    private <T> int alturaArbol(NodoBin<T> actual, int cont){
        if (actual == null){
            return cont;
        }
        int izq = alturaArbol(actual.izquierda, cont + 1);
        int der = alturaArbol(actual.derecha, cont + 1);
        if (der >= izq){
            return der;
        }
        else{
            return izq;
        }
    }
    
    public int alturaArbol(){
        return alturaArbol(raiz, 0);
    }
    
    private <T> NodoBin<T> busca(NodoBin<T> actual, T dato){
        if (actual == null){
            return null;
        }
        if(actual.elem.equals(dato)){
            return actual;
        }
        NodoBin<T> res = busca(actual.izquierda, dato);
        if (res == null){
            res = busca(actual.derecha, dato);
        }
        return res;
    }
}
