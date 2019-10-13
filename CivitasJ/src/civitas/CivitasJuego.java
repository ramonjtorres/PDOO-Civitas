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
    
    jugadores = new ArrayList<Jugador>();
    gestorEstados = new GestorEstados();
    for (String s:nombres){
        Jugador j = new Jugador(s);
        jugadores.add(j);
    }    
    
    estado = gestorEstados.estadoInicial();
    indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
    mazo = new MazoSorpresas();
    this.inicializarMazoSorpresas(tablero);
    this.inicializarTablero(mazo);  
    

    
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
        
        return jugadores.get(this.indiceJugadorActual);
    }
    
    public boolean hipotecar(int ip){
        
        return jugadores.get(this.indiceJugadorActual).hipotecar(ip);
    }
    
    public String infoJugadorTexto(){
        return jugadores.get(this.indiceJugadorActual).toString();
    }
    
    private void inicializarMazoSorpresas(Tablero tablero){
        this.mazo = new MazoSorpresas();
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCARCEL,tablero));
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,4,tablero));//a la carcel
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,5,tablero));
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA,10,tablero));
        mazo.alMazo(new Sorpresa(TipoSorpresa.SALIRCARCEL,mazo));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR,tablero,-50,"El jugador debe pagar a cada uno de los demas jugadores 50€"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR,tablero,50,"Cada jugador te debe pagar 50€"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,tablero,30,"Recibes 30€ por cada casa y hotel en propiedad"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,tablero,-30,"Cobras 30€ por cada casa y hotel en propiedad"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,-100,"Pagas 100€ por gastos de limpieza"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,100,"Has ganado un premio al hotel más limpio recibe 100€"));

        
    }
    
    private void inicializarTablero(MazoSorpresas mazo){
        
        tablero = new Tablero(4);//la carcel

        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Ronda de Valencia", 10, (float) 0.5,25,50,20)));
 
        tablero.añadeCasilla(new Casilla((float)100, "Impuesto"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Lavapies",10, (float) 0.5,25,50,20)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Cuatro Caminos",20, (float) 0.6,30,70,40)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Reina Victoria",20, (float) 0.6,30,70,40)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Bravo Murillo",30, (float) 0.7,35,90,60)));
        
        tablero.añadeCasilla(new Casilla(mazo, "Sorpresa"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Alberto Aguilera",40, (float) 0.7,35,90,80)));
        
        tablero.añadeCasilla(new Casilla("Parking"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Fuencarral",40, (float) 0.8,40,110,80)));
        
        tablero.añadeCasilla(new Casilla(mazo, "Sorpresa"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Felipe II",50, (float) 0.8,40,110,100)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Velázquez",50, (float) 0.8,45,130,100)));
        
        tablero.añadeJuez();
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Puerta del Sol",70, (float) 0.8,45,160,100)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Alcalá",70, (float) 0.8,50,160,100)));
        
        tablero.añadeCasilla(new Casilla(mazo, "Sorpresa"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Paseo del Prado",100, (float) 0.8,60,250,120)));
        
        }
    
    private void pasarTurno(){
    
        this.indiceJugadorActual = (this.indiceJugadorActual + 1) % this.jugadores.size();
    }

    private ArrayList<Jugador> ranking(){
        
        ArrayList<Jugador> ranking = new ArrayList();
        Jugador aux = new Jugador("AUXILIAR");
        ranking = this.jugadores;
        int i = 1;
        
        while(i < ranking.size()-1){
            
            if(ranking.get(i-1).compareTo(ranking.get(i)) < 0){
               
                aux = ranking.get(i-1);
                ranking.remove(i-1);
                ranking.add(i, aux);                
           }
            
           i++;
        }
        
        return ranking;
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String j1 = "David";
        String j2 = "Ramón";
        
        ArrayList <String> todos = new ArrayList();
        
        todos.add(j1);
        todos.add(j2);
        
        CivitasJuego cj = new CivitasJuego(todos);
        
        TituloPropiedad propiedad = new TituloPropiedad("Ronda de Valencia",10, (float) 0.5,25,50,20);
        
        cj.indiceJugadorActual = 1;
        
        cj.getJugadorActual().propiedades.add(propiedad);
        
        propiedad.comprar(cj.getJugadorActual());
        
        cj.actualizarInfo();
        
        System.out.println("Debe devolver true, al construir una casa: " + cj.getJugadorActual().propiedades.get(0).construirCasa(cj.getJugadorActual()));
        System.out.println("Debe devolver true, al construir una casa: " + cj.getJugadorActual().propiedades.get(0).construirCasa(cj.getJugadorActual()));
        System.out.println("Debe devolver true, al construir una casa: " + cj.getJugadorActual().propiedades.get(0).construirCasa(cj.getJugadorActual()));
        System.out.println("Debe devolver true, al construir una casa: " + cj.getJugadorActual().propiedades.get(0).construirCasa(cj.getJugadorActual()));
        System.out.println("Debe devolver true, al construir un hotel: " + cj.getJugadorActual().propiedades.get(0).construirHotel(cj.getJugadorActual()));
        
        System.out.println("Debe devolver informacion del jugador actual: " + cj.infoJugadorTexto());
        
        System.out.println("Debe salir 6:" + cj.tablero.nuevaPosicion(20, 6));
        System.out.println("Debe salir 6:" + cj.tablero.nuevaPosicion(20, 6));
        System.out.println("Debe salir 6:" + cj.tablero.nuevaPosicion(20, 6));
        System.out.println("Debe salir 10:" + cj.tablero.nuevaPosicion(0, 10));
        
        cj.contabilizarPasosPorSalida(cj.getJugadorActual());
        
        System.out.println("Debe dar 10350, al pasar 3 veces por salida (7350 + 3*1000): " + cj.getJugadorActual().getSaldo());
        
        System.out.println("Debe dar que estamos en la casilla 0, Salida: " + cj.getCasillaActual());
        
        System.out.println("Debe dar Ramon: " + cj.getJugadorActual());
        
        cj.pasarTurno();
        
        System.out.println("Debe dar David: " + cj.getJugadorActual());
        
        System.out.println("Debe dar en primera posicion Ramón, en segunda David: " + cj.ranking());
        
        cj.getJugadorActual().modificarSaldo(20000);
        
        cj.pasarTurno();
        
        System.out.println("Debe dar en primera posicion David, en segunda Ramón: " + cj.ranking());
        
        cj.siguientePasoCompletado(Operaciones_juego.AVANZAR);
        
        System.out.println("Debe dar DESPUES_AVANZAR: " + cj.estado);
        
        cj.siguientePasoCompletado(Operaciones_juego.COMPRAR);
        
        System.out.println("Debe dar DESPUES_COMPRAR: " + cj.estado);
        
        System.out.println("Debe dar true al vender: " + cj.vender(0));
        
        System.out.println("Debe dar false, no hay fin del juego: " + cj.finalDelJuego());
        
        cj.getJugadorActual().modificarSaldo(-20000);
        
        System.out.println("Debe dar true, hay fin del juego: " + cj.finalDelJuego());

    }
}
