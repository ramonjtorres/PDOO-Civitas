/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ramonjtorres
 */
public class Casilla {
    
    private String nombre;
    private static int carcel;
    private float importe;
    TipoCasilla tipo;
    TituloPropiedad tituloPropiedad;
    Sorpresa sorpresa;
    MazoSorpresas mazo;
    
    
    Casilla(String cadena){
        
        nombre = cadena;
    }
    
    Casilla(TituloPropiedad titulo){}
    
    Casilla(float cantidad, String nombre){}
    
    Casilla(int numCasillaCarcel, String nombre){}
    
    Casilla(MazoSorpresas mazo, String nombre){} 
    
    public String getNombre(){
    
        return nombre;
    }
    
    TituloPropiedad getTituloPropiedad(){}
    
    private void informe(int iactual, ArrayList<Jugador> todos){}
    
    private void init(){}
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){}
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){}
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){}

    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){}

    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){}

    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){}
    
    public String toString(){}

}
