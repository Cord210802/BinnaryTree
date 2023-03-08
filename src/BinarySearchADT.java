/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author jupac
 */
public interface BinarySearchADT <T extends Comparable<T>>{
    public void add(T dato);
    public T remove(T dato);
    public T findMin();
    public T findMax();
}
