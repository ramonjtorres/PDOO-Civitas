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
public class CasillaImpuesto extends Casilla{
    
    private String nombre;
    private float importe;
    
    CasillaImpuesto(float cantidad, String nombre){
        super(nombre);
        this.importe = cantidad;
        this.informacion = "Has caído en una casilla de Impuesto. Pagas " + this.importe;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }
    
    @Override
    public String toString(){
        String Casilla="\n";
        
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        if(importe!=-1.0)
            Casilla = Casilla +"\n  Importe: " + importe;
        
        Casilla = Casilla +"\n  Tipo Casilla: IMPUESTO";
        
        return Casilla;
    }
    
}
