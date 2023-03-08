/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author jupac
 */
public class PruebaArbolBusqueda {

    
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        bs.add(10);
        bs.add(1);
        bs.add(5);
        bs.add(2);
        bs.add(6);
        bs.add(7);
        bs.add(11);
        bs.add(12);
        bs.add(13);
        System.out.println(bs.toStringInorden());
    }
}
