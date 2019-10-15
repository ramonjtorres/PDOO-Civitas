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
 * @author david
 */
public class Sorpresa {
    
    private String texto;
    private int valor;
    MazoSorpresas mazo;
    TipoSorpresa tipo;
    Tablero tablero;
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
    
        init();
        
        this.tipo = tipo;
        this.tablero = tablero;
        this.mazo = new MazoSorpresas();
        this.texto = "Esta sorpresa te lleva a la cárcel";
    
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
    
        init();
        
        this.tipo = tipo;
        this.tablero = tablero;
        this.texto = texto;
        this.valor = valor;
        this.mazo = new MazoSorpresas();
    }
    
    Sorpresa(TipoSorpresa tipo, int valor, Tablero tablero){
    
        init();
        
        this.tipo = tipo;
        this.valor = valor;
        this.tablero = tablero;
        this.texto = "Esta sorpresa te lleva a otra casilla";
        this.mazo = new MazoSorpresas();
    }
    
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
    
        init();
        
        this.tipo = tipo;
        this.mazo = mazo;
        this.texto = "Esta sorpresa evita que caigas en la cárcel";
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.tipo == TipoSorpresa.IRCARCEL){
        
            this.aplicarAJugador_irCarcel(actual, todos);
        }
        else if(this.tipo == TipoSorpresa.SALIRCARCEL){
            
            this.aplicarAJugador_salirCarcel(actual, todos);
        }
        else if(this.tipo == TipoSorpresa.PAGARCOBRAR){
        
            this.aplicarAJugador_pagarCobrar(actual, todos);
        }
        else if(this.tipo == TipoSorpresa.PORCASAHOTEL){
        
            this.aplicarAJugador_porCasaHotel(actual, todos);
        }
        else if(this.tipo == TipoSorpresa.PORJUGADOR){
        
            this.aplicarAJugador_porJugador(actual, todos);
        }
        else{
            
            this.aplicarAJugador_irACasilla(actual, todos);
        }
        
    }
    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            int casillaActual = todos.get(actual).getNumCasillaActual();
            int tirada = tablero.calcularTirada(casillaActual, this.valor);
            
            int nuevaPosicion = tablero.nuevaPosicion(casillaActual, tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            tablero.getCasilla(this.valor).recibeJugador(actual, todos);
        }        
    }

    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }

    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            todos.get(actual).modificarSaldo(this.valor);
        }
    }

    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            todos.get(actual).modificarSaldo(this.valor*todos.get(actual).cantidadCasasHoteles());
        }
    }

    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            
            Sorpresa pagar = new Sorpresa(TipoSorpresa.PAGARCOBRAR, this.valor*-1, this.tablero);
            
            int i = 0;
            while(i<todos.size()){
            
                if(i != actual){
                    pagar.aplicarAJugador(i, todos);
                }
            i++;
            }
            Sorpresa cobrar = new Sorpresa(TipoSorpresa.PAGARCOBRAR, this.valor*(todos.size()-1), this.tablero);
            cobrar.aplicarAJugador(actual, todos);
        }
    }

    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            int i = 0;
            boolean tiene = false;
                    
            while(i<todos.size()){
                
                tiene = todos.get(i).tieneSalvoconducto();
                i++;
            }
            
            if(!tiene){
            
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }
        }
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa " + this.tipo.name() + " al jugador " + todos.get(actual).getNombre());
    }
    
    private void init(){
    
        valor = -1;
        mazo = null;
        tablero = null;
    }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        
        return (actual < todos.size());
    }
    
    void salirDelMazo(){
    
        if(this.valor == 1){
        
            mazo.inhabilitarCartaEspecial(this);
        }
    }
    
    void usada(){
    
        if(this.valor == 1){
        
            mazo.habilitarCartaEspecial(this);
        }
    }
    
    public String toString(){
        
        return tipo.name();
    }
}