/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ramonjtorres
 */
public class Casilla {
    
    private String nombre;
    
    Casilla(String cadena){
        
        nombre = cadena;
    }
    
    public String getNombre(){
    
        return nombre;
    }
}
