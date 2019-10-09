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
public class Tablero {
    
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private boolean tieneJuez;
    
    public Tablero(int numCasillaCarcel){
        
        if(numCasillaCarcel >= 1){
            
            this.numCasillaCarcel = numCasillaCarcel;
        }
        else{
            this.numCasillaCarcel = 1;
        }
        
        casillas = new ArrayList<Casilla>();
        casillas.add(new Casilla("Salida"));
                
        porSalida = 0;
        tieneJuez = false;
        
    }
    
    private boolean correcto(){
        
        if(casillas.size() > numCasillaCarcel && tieneJuez){
            
            return true;
        }
        
        return false;
    }
    
    private boolean correcto(int numCasilla){
        
        if(correcto() && numCasilla < casillas.size()){
            
            return true;
        }
        
        return false;
    }
    
    int getCarcel(){
        
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
        
        if(porSalida > 0){
            
            porSalida--;
            
            return porSalida+1;
        }
        else{
            
            return porSalida;
        }
    }
    
    void añadeCasilla(Casilla casilla){
        
        if(casillas.size() == numCasillaCarcel){
            
            casillas.add(new Casilla("Cárcel"));
        }
        
        casillas.add(casilla);
        
        if(casillas.size() == numCasillaCarcel){
            
            casillas.add(new Casilla("Cárcel"));
        }
    }
    
    void añadeJuez(){
    
        if(!tieneJuez){
            
            casillas.add(new Casilla(this.numCasillaCarcel, "Juez"));
            tieneJuez = true;
        }
    }
    
    Casilla getCasilla(int numCasilla){
        
        if(correcto(numCasilla)){
            
            return casillas.get(numCasilla);
        }
        
        return null;
    }
    
    int nuevaPosicion(int actual, int tirada){
        
        if(!correcto()){
            
            return -1;
        }
        else{
            
            int nueva = (actual + tirada) % casillas.size();
            
            if(nueva != actual + tirada){
                
                porSalida++;
            }
            
            return nueva;
        }
    }
    
    int calcularTirada(int origen, int destino){
        
        int tirada = destino - origen;
        
        if(tirada < 0){
            
            return tirada + casillas.size();
        }
        else{
            
            return tirada;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Tablero tablero = new Tablero(4);
        MazoSorpresas mazo = new MazoSorpresas();
        
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

        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Ronda de Valencia",10, (float) 0.5,25,50,20)));
        tablero.añadeCasilla(new Casilla("Parking"));
        tablero.añadeCasilla(new Casilla(100, "Impuesto"));
        tablero.añadeCasilla(new Casilla(mazo, "Sorpresa"));
        
        System.out.println("Debe salir false al no tener juez:" + tablero.correcto());
        
        tablero.añadeJuez();
        
        System.out.println("Debe salir 4:" + tablero.calcularTirada(0, 4));
        System.out.println("Debe salir (1-3)%"+ tablero.casillas.size() + ":" + tablero.calcularTirada(3, 1));
        System.out.println("Debe salir true al tener juez:" + tablero.correcto());
        System.out.println("Debe salir true al ser una casilla valida:" + tablero.correcto(2));
        System.out.println("Debe salir false al ser una casilla valida:" + tablero.correcto(10));
        
        System.out.println("Debe salir el numero 4:" + tablero.getCarcel());
        
        System.out.println("Debe salir la casilla SALIDA:" + tablero.getCasilla(0).getNombre());
        System.out.println("Debe salir la casilla RONDA DE VALENCIA:" + tablero.getCasilla(1).getNombre());
        System.out.println("Debe salir la casilla PARKING:" + tablero.getCasilla(2).getNombre());
        System.out.println("Debe salir la casilla IMPUESTO:" + tablero.getCasilla(3).getNombre());
        System.out.println("Debe salir la casilla CARCEL:" + tablero.getCasilla(4).getNombre());
        System.out.println("Debe salir la casilla SORPRESA:" + tablero.getCasilla(5).getNombre());
        System.out.println("Debe salir la casilla JUEZ:" + tablero.getCasilla(6).getNombre());
        
        System.out.println("Debe salir que ha pasado 0 veces por salida:" + tablero.getPorSalida());
        
        System.out.println("Debe salir 5:" + tablero.nuevaPosicion(0, 5));
        System.out.println("Debe salir 4:" + tablero.nuevaPosicion(5, 6));
        System.out.println("Debe salir 3:" + tablero.nuevaPosicion(4, 6));
        System.out.println("Debe salir 2:" + tablero.nuevaPosicion(3, 6));
        
        System.out.println("Debe salir que ha pasado 3 veces por salida:" + tablero.getPorSalida());
        
        Tablero tablero2 = new Tablero(0);
        
        System.out.println("Debe salir el numero 1:" + tablero2.getCarcel());
        
    }
}
