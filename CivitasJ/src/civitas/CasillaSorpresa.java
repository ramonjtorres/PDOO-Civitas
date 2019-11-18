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
public class CasillaSorpresa extends Casilla{
    
    private String nombre;
    Sorpresa sorpresa;
    MazoSorpresas mazo;

    CasillaSorpresa(MazoSorpresas mazo, String nombre){
        super(nombre);
        this.mazo = mazo;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(iactual, todos)){
            this.sorpresa = mazo.siguiente();
            this.informe(iactual, todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    
    @Override
    public String toString(){
        String Casilla="\n";
        
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        
        Casilla = Casilla +"\n  Tipo Casilla: SORPRESA";
        
        return Casilla;
    }
    
}
