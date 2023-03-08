/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jupac
 */
public class NodoBST <T extends Comparable<T>>{
    T dato;
    NodoBST<T> izquierda;
    NodoBST<T> derecha;
    NodoBST<T> papa;

    public NodoBST() {
        this.dato = null;
        this.izquierda = null;
        this.derecha = null;
        this.papa = null;
    }
    
    public NodoBST(T elem) {
        this.dato = elem;
        this.izquierda = null;
        this.derecha = null;
        this.papa = null;
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
        return this.dato.toString();
    }
    
    public void cuelga(NodoBST<T> hijo){
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
