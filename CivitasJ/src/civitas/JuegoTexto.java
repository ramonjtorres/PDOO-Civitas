/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Scanner;
import juegoTexto.*;

/**
 *
 * @author ramonjtorres
 */
public class JuegoTexto {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Indique el número de jugadores: ");
        int num_jugadores = sc.nextInt();
        ArrayList<String> jugadores = new ArrayList();
        int i = 0;
        
        System.out.println("Indique el nombre de los jugadores: ");
        
        do{
        
            System.out.println("Nombre jugador " + i + ": ");
            jugadores.add(sc.next());
            
            i++;
        }while( i < num_jugadores);

        CivitasJuego juego = new CivitasJuego(jugadores);
        VistaTextual vista = new VistaTextual();
        Dado dado = Dado.getInstance();
        dado.setDebug(true);
        Controlador controlador = new Controlador(juego, vista);
        controlador.juega();
    }
}
