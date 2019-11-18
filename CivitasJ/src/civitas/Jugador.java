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
        this.saldo = SaldoInicial;
        this.numCasillaActual = 0;
        this.propiedades = new ArrayList();
        this.puedeComprar = true;
        this.encarcelado = false;
        this.salvoconducto = null;
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
    
    boolean cancelarHipoteca(int ip){
        
        boolean result = false;
        
        if(this.encarcelado){
            
            return result;
        }

        if(this.existeLaPropiedad(ip)){
        
            TituloPropiedad propiedad = this.propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            boolean puedoGastar = this.puedoGastar(cantidad);
            
            if(puedoGastar){
            
                result = propiedad.cancelarHipoteca(this);
                
                if(result){
                
                    Diario.getInstance().ocurreEvento("El jugador" + this.nombre + "cancela la hipoteca de la propiedad" + ip);
                }
            }
        }
        
        return result;
    }
    
    int cantidadCasasHoteles(){
        
        int cantidad = 0;
        
        for(TituloPropiedad p:propiedades){
        
            cantidad += p.cantidadCasasHoteles();
        }
        
        return cantidad;
    }
    
    public int compareTo(Jugador otro){
    
        return new Float(otro.saldo).compareTo(this.saldo);
    }
    
    boolean comprar(TituloPropiedad titulo){
        
        boolean result = false;
        
        if(this.encarcelado){
        
            return result;
        }
        
        if(this.puedeComprar){
    
            float precio = titulo.getPrecioCompra();
            
            if(this.puedoGastar(precio)){
            
                result = titulo.comprar(this);
                
                if(result){
                
                    this.propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador " + this.nombre + " compra la propiedad " + titulo.getNombre());
                }
            }
        }
        
        return result;
    }
    
    boolean construirCasa(int ip){
        
        boolean puedoEdificarCasa = false;
        boolean result = false;
        float precio = 0;
        
        if(this.encarcelado){
         
            return result;
        }
        
        if(this.existeLaPropiedad(ip)){
            
            TituloPropiedad propiedad = propiedades.get(ip);
            puedoEdificarCasa = this.puedoEdificarCasa(propiedad);
            precio = propiedad.getPrecioEdificar();
            
            if((this.puedoGastar(precio))&&(propiedad.getNumCasas()<Jugador.CasasMax)){
                
                puedoEdificarCasa = true;
            }
            
            if(puedoEdificarCasa){
                
                result = propiedad.construirCasa(this);
                Diario.getInstance().ocurreEvento("El jugador " + this.nombre + " construye casa en la propiedad "+ ip);
            }
        }
        
        return result;
        
    }
    
    boolean construirHotel(int ip){
        
        boolean result = false;
        
        if(this.encarcelado){
        
            return result;
        }
        
        if(this.existeLaPropiedad(ip)){
            
            TituloPropiedad propiedad = this.propiedades.get(ip);
            boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad);
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoEdificarHotel){
            
                result = propiedad.construirHotel(this);
                int casasPorHotel = this.getCasasPorHotel();
                
                propiedad.derruirCasas(casasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye hotel en la propiedad " + ip);
            }
            
        }
        
        return result;
    }
    
    protected boolean debeSerEncarcelado(){
    
        if(this.encarcelado){
     
            return false;
        }
        else if(!this.tieneSalvoconducto()){
            return true;
        }
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
    
    int getCasasMax(){
        return CasasMax;
    }
    
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    int getHotelesMax(){
        return HotelesMax;
    }
    
    protected String getNombre(){
        return nombre;
    }
    
    public int getNumCasillaActual(){
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    protected float getSaldo(){
        return saldo;
    }
    
    boolean hipotecar(int ip){
        
        boolean result = false;
        
        if(this.encarcelado){
        
            return result;
        }
        
        if(this.existeLaPropiedad(ip)){
        
            TituloPropiedad propiedad = this.propiedades.get(ip);
            result = propiedad.hipotecar(this);
            
            if(result){
                Diario.getInstance().ocurreEvento("El jugador" + this.nombre + "hipoteca la propiedad" + ip);
            }
        }
        
        return result;
    }
    
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
        if(this.encarcelado || cantidad == -1)
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
        if(this.encarcelado){
            
            this.puedeComprar = false;
        }
        else{
          
            this.puedeComprar = true;
        }
        
        return puedeComprar;
    }
    
    private boolean puedeSalirCarcelPagando(){
        return (this.saldo>this.getPrecioLibertad());
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){
        
        if(this.propiedades.contains(propiedad) && this.saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() < this.getCasasMax()){
        
            return true;
        }
        else{
        
            return false;
        }
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){
        
        if(this.propiedades.contains(propiedad) && this.saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() == this.getCasasPorHotel() && propiedad.getNumHoteles() < this.getHotelesMax()){
        
            return true;
        }
        else{
        
            return false;
        }
    }
    
    boolean puedoGastar(float precio){
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
        if(this.encarcelado){
         
            this.paga(this.getPrecioLibertad());
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador ha salido de la carcel pagando");
            return true;   
        }
        else{
        
            return false;
        }
    }
    
    boolean salirCarcelTirando(){
        
        if(Dado.getInstance().salgoDeLaCarcel()){
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador ha salido de la carcel tirando el dado");
            return true;
        }
        else return false;
    }
    
    boolean tieneAlgoQueGestionar(){
        
        if(this.propiedades.isEmpty()){
         
            return false;
        }
        else{
            
            return true;
        }
    }
    
    boolean tieneSalvoconducto(){
        return (this.salvoconducto != null);
    }
    
    boolean vender(int ip){
        
        if(this.encarcelado){
            
            return false;
        }
        else{
            
            if(this.existeLaPropiedad(ip)){
                
                if(propiedades.get(ip).vender(this)){
                 
                    Diario.getInstance().ocurreEvento("Se ha vendido la propiedad: " + propiedades.get(ip).toString());
                    propiedades.remove(ip);
                    return true;
                }
            }
            
            return false;
        }
    }
    
    @Override
    public String toString(){
     
        String s = "No";
        
        if(this.tieneSalvoconducto()){
        
            s = "Sí";
        }
        
        return "Nombre Jugador: " + this.getNombre() +
               "\nSaldo: " + this.getSaldo() +
               "\nCasilla Actual: " + this.getNumCasillaActual() +
               "\nSalvoconducto: " + s +
               "\nEncarcelado: " + this.isEncarcelado() +
               "\nPuede comprar: " + this.getPuedeComprar() +
               "\nPropiedades: " + "\n" +
                propiedades.toString(); 

    }
}