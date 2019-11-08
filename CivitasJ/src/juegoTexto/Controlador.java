/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoTexto;

import civitas.CivitasJuego;
import civitas.GestionesInmobiliarias;
import civitas.Jugador;
import civitas.OperacionInmobiliaria;
import civitas.Operaciones_juego;
import civitas.Respuestas;
import civitas.SalidasCarcel;
import java.util.ArrayList;

/**
 *
 * @author ramonjtorres
 */
public class Controlador {
    
    private CivitasJuego juego;
    private VistaTextual vista;
    
    public Controlador(CivitasJuego juego, VistaTextual vista){
    
        this.juego = juego;
        this.vista = vista;
    }
    
    public void juega(){
    
        vista.setCivitasJuego(juego);
        
        while(!juego.finalDelJuego()){
        
            vista.actualizarVista();
            
            vista.pausa();
            
            Operaciones_juego operacion = juego.siguientePaso();
            
            vista.mostrarSiguienteOperacion(operacion);
            
            if(operacion != Operaciones_juego.PASAR_TURNO){
            
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
            
                if(operacion == Operaciones_juego.COMPRAR){
                
                    Respuestas respuesta = vista.comprar();
                    
                    if(respuesta == Respuestas.SI){
                    
                        juego.comprar();
                    }
                    
                    juego.siguientePasoCompletado(Operaciones_juego.COMPRAR);
                }
                
                if(operacion == Operaciones_juego.GESTIONAR){
                
                    vista.gestionar();
                    
                    int ig = vista.getGestion();
                    int ip = vista.getPropiedad();
                    
                    OperacionInmobiliaria oi = new OperacionInmobiliaria(GestionesInmobiliarias.values()[ig], ip);
                    
                    if(oi.getGestion() == GestionesInmobiliarias.CANCELAR_HIPOTECA){
                    
                        juego.cancelarHipoteca(ip);
                    }
                    else if(oi.getGestion() == GestionesInmobiliarias.CONSTRUIR_CASA){
                    
                        juego.construirCasa(ip);
                    }
                    else if(oi.getGestion() == GestionesInmobiliarias.CONSTRUIR_HOTEL){
                    
                        juego.construirHotel(ip);
                    }
                    else if(oi.getGestion() == GestionesInmobiliarias.HIPOTECAR){

                        juego.hipotecar(ip);
                    }
                    else if(oi.getGestion() == GestionesInmobiliarias.VENDER){
                    
                        juego.vender(ip);
                    }
                    else{
                    
                        juego.siguientePasoCompletado(operacion);
                    }
                }
                
                if(operacion == Operaciones_juego.SALIR_CARCEL){
                
                    SalidasCarcel salida = vista.salirCarcel();
                    
                    if(salida == SalidasCarcel.PAGANDO){
                    
                        juego.salirCarcelPagando();
                    }
                    else{
                    
                        juego.salirCarcelTirando();
                    }
                    
                    juego.siguientePasoCompletado(operacion);
                }
            }
        }
        
        ArrayList<Jugador> ranking = juego.ranking();
        
        System.out.println("Ranking:\n\n");
        
        for(int i = 0; i < ranking.size(); i++){
        
            System.out.println(ranking.get(i).toString() + "\n");
        }
        
    }
}
