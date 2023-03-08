/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author jupac
 */
public class NodoBin <T> {
    T elem;
    NodoBin<T> izquierda;
    NodoBin<T> derecha;
    NodoBin<T> papa;

    public NodoBin() {
        this.elem = null;
        this.izquierda = null;
        this.derecha = null;
        this.derecha = null;
    }
    
    public NodoBin(T elem) {
        this.elem = elem;
        this.izquierda = null;
        this.derecha = null;
        this.derecha = null;
    }
    
    public int numDecendientes(){
        int cont = 0;
        
        if (this.izquierda != null){
            cont += izquierda.numDecendientes() + 1;
        }
        if (this.derecha != null){
            cont += derecha.numDecendientes() + 1;
        }
        return cont;
    }
    
    public String toString(){
        return this.elem.toString();
    }
}
