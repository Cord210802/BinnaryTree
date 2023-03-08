


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jupac
 */
public class ColaA<T> implements ColaADT<T>{
    private T[]cola;
    private int ultimo;
    private int primero;
    private final int MAXIMO=20;
   
    public ColaA(){
        cola = (T[]) new Object[MAXIMO];
        primero = -1;
        ultimo = -1;
    }
    
    public ColaA(int max){
        cola = (T[]) new Object[max];
        primero = -1;
        ultimo = -1;
    }
    
    public void agrega (T dato){
        if (this.estaVacia()){
            primero = 0;
        }
        else{
            if ((ultimo + 1) % cola.length == primero){// cola llena
                expande();
            }
        }
        ultimo = (ultimo + 1) % cola.length;
        cola[ultimo] = dato;
    }

    private void expande(){
        int tam = cola.length;
        T[] masGrande = (T[]) new Object[tam*2];
        
        for (int i = 0; i < tam; i++){
            masGrande[i] = cola[(primero + i) % tam];
        }
        primero = 0;
        ultimo = tam-1;
        cola = masGrande;
    }
    @Override
    public T saca() {
        if (estaVacia()){
            throw new RuntimeException();
        }
        T eliminado = cola[primero];
        cola[primero] = null;
        if (primero == ultimo){
            primero = -1;
            ultimo = -1;
        }
        else{
            primero = (primero + 1) % cola.length;
        }
        return eliminado;
    }

    @Override
    public boolean estaVacia() {
        return primero ==-1;
    }

    @Override
    public T consultaPrimero() {
        if (estaVacia()){
            throw new RuntimeException();
        }
        return cola[primero];
    }
    
    public String toString(){
        String respuesta = " ";
        if (!this.estaVacia()){
            respuesta = toString(primero, new StringBuilder());
        }
        return respuesta;
    }
    
    private String toString(int actual, StringBuilder cad){
        if (actual == ultimo){
            cad.append(cola[ultimo]);
            return cad.toString();
        }
        else{
            cad.append(cola[actual] + " ");
            actual = (actual + 1) % cola.length;
            return toString(actual, cad);
        }
    }
    
    public int cuantosElementos(){
        int res = 0;
        
        if (primero != -1){
            if (primero < ultimo){
                res = ultimo - primero + 1;
            }
            else{
                res = cola.length - primero + ultimo + 1;
            }
        }
        return res;
    }
    
    public T consultaUltimo() {
        if (estaVacia()){
            throw new RuntimeException();
        }
        return cola[ultimo];
    } 
    
    public ArrayList<T> multiQuita(int n){
        ArrayList<T> arr = new ArrayList();
        
        if (n < this.cuantosElementos()){
            for (int i = 0; i < n; i++){
                arr.add(this.saca());
            } 
        }
        return arr;
    }
    
    public void agregaInicio(T dato){
        int ultimoOriginal;
        
        if (this.estaVacia()){
            this.agrega(dato);
        }
        else{
            //Si esta llena
            if ((ultimo + 1) % cola.length == primero){
                System.out.println("Hola");
                System.out.println(ultimo);
                expande();
                System.out.println(ultimo);
                System.out.println(primero);
                for (int i = ultimo; i>= primero; i--){
                    System.out.println(cola[i]);
                    cola[i+1] = cola[i];
                }
                cola[primero] = dato;
                ultimo++;
            }
            else{
                if (primero == 0){
                    cola[cola.length-1] = dato;
                    primero = cola.length -1;
                }
                else{
                    cola[primero - 1] = dato;
                    primero = primero - 1;
                }
            }
        }
    }
    
    public T sacaUltimo(){
        T dato;
        
        if (this.estaVacia()){
            throw new RuntimeException();
        }
        else{
            if((ultimo + 1) % cola.length == primero){
                expande();
                dato = cola[ultimo];
                cola[ultimo] = null;
                ultimo = ultimo--;
            }
            else{
                if (ultimo == 0){
                    dato = cola[ultimo];
                    cola[ultimo] = null;
                    ultimo = cola.length - 1;
                }
                else{
                    dato = cola[ultimo];
                    cola[ultimo] = null;
                    ultimo = ultimo-1;
                }
            }
        }
        return dato;
    }
}
