/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.*;
import java.util.ArrayList;

/**
 *
 * @author ramonjtorres
 */
public class TestP5 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        CivitasView vista = new CivitasView();
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(true);
        
        CapturaNombres capturaNombres = new CapturaNombres(vista, true);
        ArrayList<String> nombres = new ArrayList<String>();
        
        nombres = capturaNombres.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(juego, vista);
        
        controlador.juega();
        
    }
}
