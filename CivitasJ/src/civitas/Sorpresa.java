/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ramonjtorres
 * @author david
 */
abstract class Sorpresa {
    
    private int valor;
    MazoSorpresas mazo;
    String informacion;
    
    public String getInformacion(){
    
        return informacion;
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    abstract void informe(int actual, ArrayList<Jugador> todos);
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        
        return (actual < todos.size());
    }
    
    void salirDelMazo(){
    
        if(this.valor == 1){
        
            mazo.inhabilitarCartaEspecial(this);
        }
    }
    
    void usada(){
    
        if(this.valor == 1){
        
            mazo.habilitarCartaEspecial(this);
        }
    }
    
    public abstract String toString();
}