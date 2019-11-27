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
public class SorpresaCarcel extends Sorpresa{
    
    private String texto;
    MazoSorpresas mazo;
    Tablero tablero;
    
    SorpresaCarcel(Tablero tablero){
    
        this.tablero = tablero;
        this.mazo = new MazoSorpresas();
        this.texto = "Esta sorpresa te lleva a la cárcel";
    
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
        
    }
    
    @Override
     void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa IR A CÄRCEL al jugador " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Cárcel\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
    
}
