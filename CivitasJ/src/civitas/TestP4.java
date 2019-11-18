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
public class TestP4 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Jugador jugador = new Jugador("Ramon");
        
        TituloPropiedad propiedad = new TituloPropiedad("Ronda de Valencia", 10, (float) 0.5,25,50,20);
        
        propiedad.actualizaPropietarioPorConversion(jugador);
        jugador.propiedades.add(propiedad);
        
        System.out.println(jugador.toString());
        
        System.out.println("================================= HACEMOS LA CONVERSIÓN ==================================");
        
        JugadorEspeculador jugadorEspeculador = new JugadorEspeculador(jugador, 500);
        
        System.out.println(jugadorEspeculador.toString());
        
    }
    
}
