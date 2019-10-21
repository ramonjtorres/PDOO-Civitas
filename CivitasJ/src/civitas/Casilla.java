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
public class Casilla {
    
    private String nombre;
    private static int carcel=0;
    private float importe;
    TipoCasilla tipo;
    TituloPropiedad tituloPropiedad;
    Sorpresa sorpresa;
    MazoSorpresas mazo;
    
    
    Casilla(String cadena){
        
        nombre = cadena;
    }
    
    Casilla(TituloPropiedad titulo){
        init();
        tituloPropiedad = titulo;
     
        
    }
    
    Casilla(float cantidad, String nombre){
        init();
        this.nombre = nombre;
        this.importe = cantidad;
    }
    
    Casilla(int numCasillaCarcel, String nombre){
        init();
        this.nombre = nombre;
        carcel = numCasillaCarcel;
    }
    
    Casilla(MazoSorpresas mazo, String nombre){
        init();
        this.nombre = nombre;
        this.mazo = mazo;
    } 
    
    public String getNombre(){
    
        return nombre;
    }
    
    TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
              Diario.getInstance().ocurreEvento("Jugador actual: "+todos.get(actual).getNombre()+this.toString());
    
    }
    
    private void init(){
        this.nombre = null;
        this.carcel = -1;
        this.importe = -1;
        this.tituloPropiedad = null;
        this.sorpresa = null;
        this.mazo = null;
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){
        if(iactual>=0 && iactual<todos.size())
            return true;
        else return false;
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
    
        if(null==this.tipo) 
            this.informe(iactual, todos);
        else switch (this.tipo) {
            case CALLE:
                this.recibeJugador(iactual, todos);
                break;
            case IMPUESTO:
                this.recibeJugador_impuesto(iactual, todos);
                break;
            case JUEZ:
                this.recibeJugador_juez(iactual, todos);
                break;
            case SORPRESA:
                this.recibeJugador_sorpresa(iactual, todos);
                break;
            default:
                this.informe(iactual, todos);
                break;
        }
    }
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual,todos)){
            this.informe(iactual, todos);
            Jugador nuevo = todos.get(iactual);
            
            if(!this.tituloPropiedad.tienePropietario()){
                nuevo.puedeComprarCasilla();
            }
            else this.tituloPropiedad.tramitarAlquiler(nuevo);  
        }
    }

    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(this.importe);
        }
    }

    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }

    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            this.sorpresa = mazo.siguiente();
            this.informe(iactual, todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
        
    
    }
    
    public String toString(){
        String Casilla="\n";
        if(nombre!=null)
            Casilla = "\n  Nombre casilla: "+nombre;
        if(carcel!=-1.0)
            Casilla =Casilla + "\n  Carcel: " + carcel;
        if(importe!=-1.0)
            Casilla = Casilla +"\n  Importe: " + importe;
        if(tipo!=null)
            Casilla = Casilla +"\n  Tipo Casilla: " + tipo.toString();
        if(tituloPropiedad!=null)
            Casilla = Casilla + tituloPropiedad.toString();
        
        return Casilla;
    }
}
