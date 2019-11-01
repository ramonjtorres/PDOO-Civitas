/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import juegoTexto.*;

/**
 *
 * @author ramonjtorres
 */
public class Prueba {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        String jugador1 = "David";
        String jugador2 = "Ramón";

        ArrayList<String> jugadores = new ArrayList();

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        CivitasJuego juego = new CivitasJuego(jugadores);
        VistaTextual vista = new VistaTextual();
        Dado dado = Dado.getInstance();
        dado.setDebug(true);
        Controlador controlador = new Controlador(juego, vista);
        controlador.juega();
    }
}
