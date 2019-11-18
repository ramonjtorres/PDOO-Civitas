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
public class CasillaJuez extends Casilla{
    
    private String nombre;
    private static int carcel;
    
    CasillaJuez(int numCasillaCarcel, String nombre){
        super(nombre);
        this.carcel = numCasillaCarcel;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    
    @Override
    public String toString(){
        
        String Casilla="\n";
        
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        if(carcel!=-1.0)
            Casilla =Casilla + "\n  Carcel: " + carcel;
        
        Casilla = Casilla +"\n  Tipo Casilla: JUEZ";
        
        return Casilla;
    }
    
}
