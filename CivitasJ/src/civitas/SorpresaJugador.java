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
public class SorpresaJugador extends Sorpresa{
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    Tablero tablero;
    
    SorpresaJugador(Tablero tablero, int valor, String texto){
    
        this.tablero = tablero;
        this.texto = texto;
        this.valor = valor;
        this.mazo = new MazoSorpresas();
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            
            SorpresaPagarCobrar pagar = new SorpresaPagarCobrar(this.tablero, this.valor*-1, "Esta sorpresa hace al jugador tener que pagar");
            
            int i = 0;
            while(i<todos.size()){
            
                if(i != actual){
                    pagar.aplicarAJugador(i, todos);
                }
            i++;
            }
            SorpresaPagarCobrar cobrar = new SorpresaPagarCobrar(this.tablero, this.valor*(todos.size()-1), "Esta sorpresa hace al jugador cobrar");
            cobrar.aplicarAJugador(actual, todos);
        }
        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa JUGADOR POR JUGADOR al jugador " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Jugador por Jugador\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
    
}
