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
public class SorpresaPagarCobrar extends Sorpresa{
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    Tablero tablero;
    
    SorpresaPagarCobrar(Tablero tablero, int valor, String texto){
    
        this.tablero = tablero;
        this.texto = texto;
        this.informacion = this.texto;
        this.valor = valor;
        this.mazo = new MazoSorpresas();
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            todos.get(actual).modificarSaldo(this.valor);
        }
        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa COBRAR-PAGAR al jugador " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Pagar Cobrar\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
    
}
