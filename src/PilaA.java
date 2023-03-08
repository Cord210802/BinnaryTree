/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;

/**
 *
 * @author jupac
 */
public class PilaA <T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private final int MAXIMO = 20;
    
    public PilaA(){
        pila = (T[]) new Object[MAXIMO];
        tope = -1;
    }
    
    public PilaA(int max){
        pila = (T[]) new Object[max];
        tope = -1;
    }
    
    public boolean isEmpty(){
        return tope == -1;
    }
    
    public void push(T dato){
        if (tope == pila.length - 1){
            expande();
        }
        tope++;
        pila[tope] = dato;
    }
    
    private void expande(){
        T[] masGrande = (T[]) new Object [pila.length * 2];
        
        for(int i = 0; i <= tope; i++){
            masGrande[i] = pila[i];
        }
        pila = masGrande;
    }

    @Override
    public T pop() {
        T resultado;
        if (this.isEmpty()){
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        }
        resultado = pila[tope];
        pila[tope] = null;
        tope--;
        return resultado;
    }

    @Override
    public T peek() {
        if (this.isEmpty()){
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        }
        return pila[tope];
    }
    
    public T[] multiPop(int n){
        T[] arre = null;
        
        if(n >=0 && n<=tope + 1){
            arre = (T[]) new Object[n];
            for(int i = 0; i < n ; i++){
                arre[i] = pila[tope];
                pila[tope] = null;
                tope--;
            }
        }
        return arre;
    }
    
    public String toString(){
        StringBuilder cad = new StringBuilder();
        
        for (int i = 0; i< tope+1; i++){
            cad.append("\n" + pila[i]);
        }
        return cad.toString();
    }
    
    
    public boolean equals(Object obj){
        boolean res = true;
        int i = 0;
        
        if (obj == null){
            res = false;
        }
        else{
            if (!(obj instanceof PilaA)){
                res = false;
            }
            else{
                if (((PilaA)obj).tope!= this.tope){
                    res = false;
                }
                else{
                    while (i <= tope && res){
                        
                        if (!this.pila[i].equals(((PilaA)obj).pila[i])){
                            res = false;
                        }
                        i++;
                    }
                }
            }
        }
        return res;
    }
}
