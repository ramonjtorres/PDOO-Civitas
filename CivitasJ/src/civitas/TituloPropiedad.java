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
public class TituloPropiedad {
    
    private float alquilerBase;
    private static float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    Jugador propietario;
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){}
    
    void actualizaPropietarioPorConversion(Jugador jugador){}
    
    boolean cancelarHipoteca(Jugador jugador){return false;}
    
    int cantidadCasasHoteles(){return -1;}
    
    boolean comprar(Jugador jugador){return false;}
    
    boolean construirCasa(Jugador jugador){return false;}
    
    boolean construirHotel(Jugador jugador){return false;}
    
    boolean derruirCasas(int n, Jugador jugador){return false;}
    
    boolean esEsteElPropietario(Jugador jugador){return false;}
    
    public boolean getHipotecado(){
        return hipotecado;
    }
    
    float getImporteCancelarHipoteca(){return -1;}
    
    float getImporteHipoteca(){return -1;}
    
    String getNombre(){
        return nombre;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    private float getPrecioAlquiler(){return -1;}
    
    private float getPrecioVenta(){return -1;}
    
    float getPrecioEdificar(){return -1;}
    
    float getPrecioCompra(){return -1;}
    
    Jugador getPropietario(){
        return propietario;
    }
    
    boolean hipotecar(Jugador jugador){return false;}
    
    private boolean propietarioEncarcelado(){return false;}
    
    boolean tienePropietario(){return false;}
    
    void tramitarAlquiler(Jugador jugador){}
    
    boolean vender(Jugador jugador){return false;}
    
    public String toString(){return null;}
    
}
