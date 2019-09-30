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
public class CivitasJuego {
    
    private int indiceJugadorActual;
    GestorEstados gestorEstados;
    EstadosJuego estado;
    Tablero tablero;
    MazoSorpresas mazo;
    ArrayList<Jugador> jugadores;
    
    public CivitasJuego(ArrayList<String> nombres){}
    
    private void avanzaJugador(){}
    
    public boolean cancelarHipoteca(int ip){return false;}
    
    public boolean comprar(){return false;}
    
    public boolean construirCasa(int ip){return false;}
    
    public boolean construirHotel(int ip){return false;}
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){}
    
    public boolean finalDelJuego(){return false;}
    
    public Casilla getCasillaActual(){return null;}
    
    public Jugador getJugadorActual(){return null;}
    
    public boolean hipotecar(int ip){return false;}
    
    public String infoJugadorTexto(){return null;}
    
    private void inicializarMazoSorpresas(Tablero tablero){}
    
    private void inicializarTablero(MazoSorpresas mazo){}
    
    private void pasarTurno(){}
    
    private ArrayList<Jugador> ranking(){return null;}
    
    public boolean salirCarcelPagando(){return false;}
    
    public boolean salirCarcelTirando(){return false;}
    
    public Operaciones_juego siguientePaso(){return null;}
    
    public void siguientePasoCompletado(Operaciones_juego operacion){}
    
    public boolean vender(int ip){return false;}
}
