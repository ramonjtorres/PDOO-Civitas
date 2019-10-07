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
    
    public CivitasJuego(ArrayList<String> nombres){
    int i = 0;
    while (i<nombres.size()){
        Jugador j = new Jugador(nombres.get(i));
        jugadores.add(j);
        i++;
        }
    gestorEstados.estadoInicial();
    indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
    this.inicializarTablero(mazo);  
    this.inicializarMazoSorpresas(tablero);

    
    }
    
    private void avanzaJugador(){}
    
    private void actualizarInfo(){
    
        System.out.println("Estado: "+this.estado);
        System.out.println("Información Jugador: "+this.infoJugadorTexto());
    
    }
    
    public boolean cancelarHipoteca(int ip){
    
        return jugadores.get(this.indiceJugadorActual).cancelarHipoteca(ip);
    }
    
    public boolean comprar(){ return false;}
    
    public boolean construirCasa(int ip){
        
        return jugadores.get(this.indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
    
        return jugadores.get(this.indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
    
        while(tablero.getPorSalida() > 0){
        
            jugadorActual.pasaPorSalida();
        }
    }
    
    public boolean finalDelJuego(){
        
        for(Jugador j:jugadores){
        
            if(j.enBancarrota()){
            
                return true;
            }
        }
        
        return false;
        
    }
    
    public Casilla getCasillaActual(){
        int i = this.jugadores.get(indiceJugadorActual).getNumCasillaActual();
        return this.tablero.getCasilla(i);
    }
    
    
    public Jugador getJugadorActual(){
        
        return jugadores.get(indiceJugadorActual);
    }
    
    public boolean hipotecar(int ip){
        
        return jugadores.get(this.indiceJugadorActual).hipotecar(ip);
    }
    
    public String infoJugadorTexto(){
        return jugadores.get(indiceJugadorActual).toString();
    }
    
    private void inicializarMazoSorpresas(Tablero tablero){
    
        
        
    }
    
    private void inicializarTablero(MazoSorpresas mazo){
        
        tablero = new Tablero(4);//la carcel

        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
 
        tablero.añadeCasilla(new Casilla(0, "Impuesto"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        tablero.añadeCasilla(new Casilla(mazo, ""));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        tablero.añadeCasilla(new Casilla("Parking"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        tablero.añadeCasilla(new Casilla(mazo, ""));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        tablero.añadeJuez();
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        tablero.añadeCasilla(new Casilla(mazo, ""));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad()));
        
        }
    
    private void pasarTurno(){
    
        this.indiceJugadorActual = (this.indiceJugadorActual + 1) % jugadores.size();
    }
    
    private ArrayList<Jugador> ranking(){
        
        ArrayList<Jugador> ranking = new ArrayList();
        
        for(Jugador j : jugadores){
            if(j.s)
            
        }
        
        return ranking;//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    }
    
    public boolean salirCarcelPagando(){
        
        return jugadores.get(this.indiceJugadorActual).salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        
        return jugadores.get(this.indiceJugadorActual).salirCarcelTirando();        
    }
    
    public Operaciones_juego siguientePaso(){return null;}
    
    public void siguientePasoCompletado(Operaciones_juego operacion){
    
        this.estado = this.gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), this.estado, operacion);
    }
    
    public boolean vender(int ip){
        
        return jugadores.get(this.indiceJugadorActual).vender(ip);
    }
}
