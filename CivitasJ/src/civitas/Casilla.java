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
public class Casilla {
    
    private String nombre;
    String informacion;
    
    Casilla(String cadena){
        
        this.nombre = cadena;
        this.informacion = "Es una casilla de " + this.nombre;
    }
    
    public String getNombre(){
    
        return nombre;
    }
    
    public String getInformacion(){
    
        return informacion;
    }
    
    void informe(int actual, ArrayList<Jugador> todos){
              Diario.getInstance().ocurreEvento("Jugador actual: "+todos.get(actual).getNombre()+this.toString());
    
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){
        if(iactual>=0 && iactual<todos.size())
            return true;
        else return false;
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
            this.informe(iactual, todos);
    }
    
    public String toString(){
        
        String Casilla="\n";
        
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        
        Casilla = Casilla +"\n  Tipo Casilla: DESCANSO";
        
        return Casilla;
    }
}
