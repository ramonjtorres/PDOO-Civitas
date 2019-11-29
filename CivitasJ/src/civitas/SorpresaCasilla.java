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
 */
public class SorpresaCasilla extends Sorpresa{
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    Tablero tablero;

    SorpresaCasilla(int valor, Tablero tablero){
    
        this.valor = valor;
        this.tablero = tablero;
        this.texto = "Esta sorpresa te lleva a otra casilla";
        this.informacion = this.texto;
        this.mazo = new MazoSorpresas();
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            int casillaActual = todos.get(actual).getNumCasillaActual();
            int tirada = tablero.calcularTirada(casillaActual, this.valor);
            
            int nuevaPosicion = tablero.nuevaPosicion(casillaActual, tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            tablero.getCasilla(this.valor).recibeJugador(actual, todos);
        }        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa IR A CASILLA al jugador " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Casilla\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
}
