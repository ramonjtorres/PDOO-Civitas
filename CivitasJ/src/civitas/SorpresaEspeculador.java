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
public class SorpresaEspeculador extends Sorpresa{
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    
    SorpresaEspeculador(int valor, String texto){
    
        this.texto = texto;
        this.informacion = this.texto;
        this.valor = valor;
        this.mazo = new MazoSorpresas();
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            
            JugadorEspeculador especulador = new JugadorEspeculador(todos.get(actual), this.valor);
            
            todos.remove(actual);
            todos.add(especulador);
            
        }
        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa CONVERSIÓN ESPECULADOR al jugador " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Conversión Especulador\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
    
}
