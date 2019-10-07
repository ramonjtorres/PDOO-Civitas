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
public class Jugador implements Comparable<Jugador>{
    
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
    
    Jugador(String nombre){
        this.nombre = nombre;
    }
    
    protected Jugador(Jugador otro){
        this.nombre = otro.nombre;
        this.numCasillaActual = otro.numCasillaActual;
        this.encarcelado = otro.encarcelado;
        this.propiedades = otro.propiedades;
        this.puedeComprar = otro.puedeComprar;
        this.salvoconducto = otro.salvoconducto;
        this.saldo = otro.saldo;
    }
    
    boolean cancelarHipoteca(int ip){return false;}
    
    int cantidadCasasHoteles(){
        
        int cantidad = 0;
        
        for(TituloPropiedad p:propiedades){
        
            cantidad += p.cantidadCasasHoteles();
        }
        
        return cantidad;
    }
    
    @Override
    public int compareTo(Jugador otro){
    
        return Float.compare(this.saldo, otro.saldo);
    }
    
    boolean comprar(TituloPropiedad titulo){return false;}
    
    boolean construirCasa(int ip){return false;}
    
    boolean construirHotel(int ip){return false;}
    
    protected boolean debeSerEncarcelado(){
    if(this.encarcelado)
        return false;
    else if(!this.tieneSalvoconducto())
            return true;
            else{
                this.perderSalvoConducto();
                Diario.getInstance().ocurreEvento("El jugador se libra de la carcel");
                return false;
            }
        
    }
    
    boolean enBancarrota(){
        return this.saldo < 0;
    }
    
    boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado = true;
            Diario.getInstance().ocurreEvento("El jugador ha sido encarcelado");
        }
        return this.encarcelado;
    }
    
    private boolean existeLaPropiedad(int ip){
        
        return ip < this.propiedades.size();
    }
    
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
    
    boolean modificarSaldo(float cantidad){
        this.saldo = this.saldo + cantidad;
        Diario.getInstance().ocurreEvento("Se ha modificado el saldo ahora tienes " + this.saldo);
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        if(this.encarcelado)
            return false;
        else{
            this.numCasillaActual = numCasilla;
            this.puedeComprar = false;
            Diario.getInstance().ocurreEvento("El jugador ha sido movido de casilla a la numero " + this.numCasillaActual);
            return true;
        }
    }
    
    boolean obtenerSalvoconducto(Sorpresa sorpresa){
        if(this.encarcelado)
            return false;
        this.salvoconducto = sorpresa;
        return true;
    }
    
    boolean paga(float cantidad){
        return this.modificarSaldo(-1*cantidad);
    }
    
    boolean pagaAlquiler(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.paga(cantidad);
    }
    
    boolean pagaImpuesto(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        this.modificarSaldo(1000);
        Diario.getInstance().ocurreEvento("El jugador ha pasado por salida");
        return true;
    }
    
    private void perderSalvoConducto(){
        this.salvoconducto.usada();
        this.salvoconducto = null;
    }
    
    boolean puedeComprarCasilla(){
        if(this.encarcelado)
            this.puedeComprar = false;
        else
            this.puedeComprar = true;
        return puedeComprar;
    }
    
    private boolean puedeSalirCarcelPagando(){
        return (this.saldo>this.getPrecioLibertad());
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){
        
        if(this.propiedades.contains(propiedad) && this.saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() < 4){
        
            return true;
        }
        else{
        
            return false;
        }
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){
        
        if(this.propiedades.contains(propiedad) && this.saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() == 4 && propiedad.getNumHoteles() < 4){
        
            return true;
        }
        else{
        
            return false;
        }
    }
    
    private boolean puedoGastar(float precio){
        if(this.encarcelado)
            return false;
        else
            return(this.saldo>precio);
    }
    
    boolean recibe(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.modificarSaldo(cantidad);
    }
    
    boolean salirCarcelPagando(){
        if(this.encarcelado)
            this.paga(this.getPrecioLibertad());
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador ha salido de la carcel pagando");
            return true;   
    }
    
    boolean salirCarcelTirando(){
        if(Dado.getInstance().salgoDeLaCarcel()){
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador ha salido de la carcel tirando el dado");
            return true;
        }else return false;
    }
    
    boolean tieneAlgoQueGestionar(){
        if(this.propiedades.isEmpty())
            return false;
        return true;
    }
    
    boolean tieneSalvoconducto(){
        return (this.salvoconducto != null);
    }
    
    boolean vender(int ip){
        if(this.encarcelado)
            return false;
        else{
            if(this.existeLaPropiedad(ip))
                if(propiedades.get(ip).vender(this));
                    propiedades.remove(ip);
                    Diario.getInstance().ocurreEvento("Se ha vendido la propiedad: "+propiedades.get(ip).toString());
                    return true;
                
        }}
    
    public String toString(){
     
        String s = "No";
        
        if(this.tieneSalvoconducto()){
        
            s = "Sí";
        }
        
        return "Nombre: " + this.getNombre() +
               "Saldo: " + this.getSaldo() +
               "Casilla Actual: " + this.getNumCasillaActual() +
               "Salvoconducto: " + s +
               "Encarcelado: " + this.isEncarcelado() +
               "Puede comprar: " + this.getPuedeComprar() +
               "Propiedades: " + "\n" +
                propiedades.toString(); 

    }
}
