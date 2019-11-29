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
public class CasillaCalle extends Casilla{
    
    private String nombre;
    TituloPropiedad tituloPropiedad;
    
    
    CasillaCalle(TituloPropiedad titulo){
        super(titulo.getNombre());
        this.tituloPropiedad = titulo;
        this.informacion = "Es una propiedad";
    }
    
    TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
        if(jugadorCorrecto(iactual,todos)){
            this.informe(iactual, todos);
            Jugador nuevo = todos.get(iactual);
            if(!this.tituloPropiedad.tienePropietario()){
                nuevo.puedeComprarCasilla();
            }
            else this.tituloPropiedad.tramitarAlquiler(nuevo);  
        }
    }
    
    @Override
    public String toString(){
        
        String Casilla="\n";
        
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        
        Casilla = Casilla +"\n  Tipo Casilla: CALLE";
        
        if(tituloPropiedad!=null)
            Casilla = Casilla + tituloPropiedad.toString();
        
        return Casilla;
    }
    
}
