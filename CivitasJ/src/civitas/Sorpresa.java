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
public abstract class Sorpresa {
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    Tablero tablero;
    
    Sorpresa(Tablero tablero){
    
        this.tablero = tablero;
        this.mazo = new MazoSorpresas();
        this.texto = "Esta sorpresa te lleva a la cárcel";
    
    }
    
    Sorpresa(Tablero tablero, int valor, String texto){
    
        this.tablero = tablero;
        this.texto = texto;
        this.valor = valor;
        this.mazo = new MazoSorpresas();
    }
    
    Sorpresa(int valor, Tablero tablero){
    
        this.valor = valor;
        this.tablero = tablero;
        this.texto = "Esta sorpresa te lleva a otra casilla";
        this.mazo = new MazoSorpresas();
    }
    
    Sorpresa(MazoSorpresas mazo){
    
        this.mazo = mazo;
        this.texto = "Esta sorpresa evita que caigas en la cárcel";
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){}
    
    private void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa abstracta al jugador " + todos.get(actual).getNombre());
    }
    
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
    
    public String toString(){
        
        return "Clase Sorpresa Abstracta";
    }
}