

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author jupac
 */
public interface ColaADT<T>{
    public void agrega(T dato);
    public T saca();
    public boolean estaVacia();
    public T consultaPrimero();
    public int cuantosElementos();
    public T consultaUltimo();
}
