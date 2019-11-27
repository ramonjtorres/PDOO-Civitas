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
public class TestP4 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Jugador jugador = new Jugador("Ramon");
        
        TituloPropiedad propiedad = new TituloPropiedad("Ronda de Valencia", 10, (float) 0.5,25,50,20);
        
        propiedad.actualizaPropietarioPorConversion(jugador);
        jugador.propiedades.add(propiedad);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        jugador.construirCasa(0);
        
        jugador.construirHotel(0);
        
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        
        jugadores.add(jugador);
        
        System.out.println(jugador.toString());
        
        System.out.println("\n================================= HACEMOS LA CONVERSIÓN ==================================\n");
        
        SorpresaEspeculador sorpresa = new SorpresaEspeculador(100, "Te conviertes en un especulador con 100€ de fianza");
        
        sorpresa.aplicarAJugador(0, jugadores);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        jugadores.get(0).construirCasa(0);
        
        jugadores.get(0).construirHotel(0);
        
        System.out.println(jugadores.get(0).toString());
        
    }
    
}
