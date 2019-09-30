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
public class Jugador {
    
    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;
    protected boolean encarcelado;
    protected static int HotelesMax = 4;
    private String nombre;
    private int numCasillaActual;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 200;
    private boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial = 7500;
    ArrayList<TituloPropiedad> propiedades;
    Sorpresa salvoconducto;
    
    Jugador(String nombre){}
    
    protected Jugador(Jugador otro){}
    
    boolean cancelarHipoteca(int ip){return false;}
    
    int cantidadCasasHoteles(){return -1;}
    
    public int compareTo(Jugador otro){return -1;}
    
    boolean comprar(TituloPropiedad titulo){return false;}
    
    boolean construirCasa(int ip){return false;}
    
    boolean construirHotel(int ip){return false;}
    
    protected boolean debeSerEncarcelado(){return false;}
    
    boolean enBancarrota(){return false;}
    
    boolean encarcelar(int numCasillaCarcel){return false;}
    
    private boolean existeLaPropiedad(int ip){return false;}
    
    private int getCasasMax(){
        return CasasMax;
    }
    
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    private int getHotelesMax(){
        return HotelesMax;
    }
    
    protected String getNombre(){
        return nombre;
    }
    
    int getNumCasillaActual(){
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    protected ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    protected float getSaldo(){
        return saldo;
    }
    
    boolean hipotecar(int ip){return false;}
    
    public boolean isEncarcelado(){
        return encarcelado;
    }
    
    boolean modificarSaldo(float cantidad){return false;}
    
    boolean moverACasilla(int numCasilla){return false;}
    
    boolean obtenerSalvoconducto(Sorpresa sorpresa){return false;}
    
    boolean paga(float cantidad){return false;}
    
    boolean pagaAlquiler(float cantidad){return false;}
    
    boolean pagaImpuesto(float cantidad){return false;}
    
    boolean pasaPorSalida(){return false;}
    
    private void perderSalvoConducto(){}
    
    boolean puedeComprarCasilla(){return false;}
    
    private boolean puedeSalirCarcelPagando(){return false;}
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){return false;}
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){return false;}
    
    private boolean puedoGastar(){return false;}
    
    boolean recibe(float cantidad){return false;}
    
    boolean salirCarcelPagando(){return false;}
    
    boolean salirCarcelTirando(){return false;}
    
    boolean tieneAlgoQueGestionar(){return false;}
    
    boolean tieneSalvoconducto(){return false;}
    
    boolean vender(int ip){return false;}
    
    public String toString(){return null;}
}
