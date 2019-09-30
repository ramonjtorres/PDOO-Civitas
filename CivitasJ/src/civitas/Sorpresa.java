/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
/**
 *
 * @author david
 */
public class Sorpresa {
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    TipoSorpresa tipo;
    Tablero tablero;
    
    Sorpresa(TipoSorpresa Tipo, Tablero tablero){}
    
    Sorpresa(TipoSorpresa Tipo, Tablero tablero, String texto){}
    
    Sorpresa(TipoSorpresa Tipo, int valor, String texto){}
    
    Sorpresa(TipoSorpresa Tipo, MazoSorpresas mazo){}
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){}
    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){}

    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){}

    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){}

    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){}

    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){}

    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){}
    
    private void informe(int actual, ArrayList<Jugador> todos){}
    
    private void init(){}
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){}
    
    void salirDelMazo(){}
    
    public String toString(){}
    
    void usada(){}
    
    
}
