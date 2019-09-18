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
    
    Tablero(int indice){
        
        if(indice >= 1){
            
            numCasillaCarcel = indice;
        }
        else{
            numCasillaCarcel = 1;
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
}
