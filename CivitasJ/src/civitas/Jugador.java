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
    
    boolean cancelarHipoteca(int ip){return false;}
    
    int cantidadCasasHoteles(){
        
        int cantidad = 0;
        
        for(TituloPropiedad p:propiedades){
        
            cantidad += p.cantidadCasasHoteles();
        }
        
        return cantidad;
    }
    
    
    public int compareTo(Jugador otro){
    
        return Float.compare(this.saldo, otro.saldo);
    }
    
    boolean comprar(TituloPropiedad titulo){return false;}
    
    boolean construirCasa(int ip){return false;}
    
    boolean construirHotel(int ip){return false;}
    
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
    
    public String toString(){
     
        String s = "No";
        
        if(this.tieneSalvoconducto()){
        
            s = "Sí";
        }
        
        return "Nombre: " + this.getNombre() +
               "\nSaldo: " + this.getSaldo() +
               "\nCasilla Actual: " + this.getNumCasillaActual() +
               "\nSalvoconducto: " + s +
               "\nEncarcelado: " + this.isEncarcelado() +
               "\nPuede comprar: " + this.getPuedeComprar() +
               "\nPropiedades: " + "\n" +
                propiedades.toString(); 

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Jugador jugador = new Jugador("Ramón");
        Jugador jugador2 = new Jugador("David");
        Jugador jugador3 = new Jugador(jugador);
        
        System.out.println(jugador3.toString());
        
        System.out.println("No tiene algo que gestionar. Debe dar false: " + jugador.tieneAlgoQueGestionar());
        
        TituloPropiedad propiedad = new TituloPropiedad("Ronda de Valencia",10, (float) 0.5,25,50,20);
        
        jugador.propiedades.add(propiedad);
        
        propiedad.comprar(jugador);
        
        System.out.println("Precio compra: " + propiedad.getPrecioCompra());
        
        System.out.println("Precio edificación: " + propiedad.getPrecioEdificar());
        
        System.out.println("Tiene algo que gestionar. Debe dar true: " + jugador.tieneAlgoQueGestionar());
        
        System.out.println("Debe dar 0, no tiene casas ni hoteles: " + jugador.cantidadCasasHoteles());
        
        System.out.println("Debe dar true, puede edificar casas: " + jugador.puedoEdificarCasa(propiedad));
        
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        
        System.out.println("Debe dar false, no puede edificar casas: " + jugador.puedoEdificarCasa(propiedad));
        
        System.out.println("Debe dar 4, tiene 4 casas: " + jugador.cantidadCasasHoteles());
        
        System.out.println("Debe dar true, puede edificar hoteles: " + jugador.puedoEdificarHotel(propiedad));
        
        System.out.println("HOTEL CONSTRUIDO: " + jugador.propiedades.get(0).construirHotel(jugador));
        
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        
        System.out.println("Debe dar 2, tiene 1 hotel y 1 casa: " + jugador.cantidadCasasHoteles());
        
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        
        System.out.println("HOTEL CONSTRUIDO: " + jugador.propiedades.get(0).construirHotel(jugador));
        
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        
        System.out.println("HOTEL CONSTRUIDO: " + jugador.propiedades.get(0).construirHotel(jugador));
        
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        System.out.println("CASA CONSTRUIDA: " + jugador.propiedades.get(0).construirCasa(jugador));
        
        System.out.println("HOTEL CONSTRUIDO: " + jugador.propiedades.get(0).construirHotel(jugador));
        
        System.out.println("Debe dar false, no puede edificar hoteles: " + jugador.puedoEdificarHotel(propiedad));
        
        System.out.println("Debe dar 7050: " + jugador.getSaldo());
        
        jugador.modificarSaldo(-200);
        
        System.out.println("Debe dar 6850: " + jugador.getSaldo());
        
        System.out.println("Debe dar -1 ya que jugador tiene menos saldo: " + jugador.compareTo(jugador2));
        System.out.println("Debe dar 1 ya que jugador2 tiene mas saldo: " + jugador2.compareTo(jugador));
        
        System.out.println("Debe dar false, no tiene salvoconducto: " + jugador.tieneSalvoconducto());
        
        System.out.println("Debe dar true, debe ser encarcelado: " + jugador.debeSerEncarcelado());
        
        jugador.encarcelado = true;
        
        System.out.println("Debe dar true, esta encarcelado: " + jugador.isEncarcelado());
        
        System.out.println("Debe dar false, no puede comprar si esta encarcelado: " + jugador.puedeComprarCasilla());
        
        jugador.encarcelado = false;
        
        System.out.println("Debe dar true, no esta encarcelado, puede comprar: " + jugador.puedeComprarCasilla());
        
        System.out.println("Debe dar false, no esta encarcelado: " + jugador.isEncarcelado());
        
        MazoSorpresas mazo = new MazoSorpresas();
        
        jugador.obtenerSalvoconducto(new Sorpresa(TipoSorpresa.SALIRCARCEL , mazo));
        
        System.out.println("Debe dar false, no esta en bancarrota: " + jugador.enBancarrota());
        
        System.out.println("Debe dar true, tiene salvoconducto: " + jugador.tieneSalvoconducto());
        
        System.out.println("Debe dar false, no debe ser encarcelado: " + jugador.debeSerEncarcelado());
        
        jugador.encarcelar(4);
        
        System.out.println("Debe dar true, existe la propiedad: " + jugador.existeLaPropiedad(0));
        System.out.println("Debe dar false, no existe la propiedad: " + jugador.existeLaPropiedad(5));
        
        System.out.println("Debe dar 4 casas max: " + jugador.getCasasMax());
        System.out.println("Debe dar 4 casas por hotel: " + jugador.getCasasPorHotel());
        System.out.println("Debe dar 4 hoteles max: " + jugador.getHotelesMax());
        
        System.out.println("Debe dar 4, estamos en la carcel: " + jugador.getNumCasillaActual());
        
        System.out.println("Debe dar 200 de precio de libertad: " + jugador.getPrecioLibertad());
        
        System.out.println("Debe dar 1000 de premio de salida: " + jugador.getPremioPasoSalida());
        
        System.out.println("Debe dar los datos de Ronda de Valencia: " + jugador.getPropiedades().toString());
        
        System.out.println("Debe dar false, no puede comprar: " + jugador.getPuedeComprar());
        
        jugador.puedeComprar = true;
        
        System.out.println("Debe dar true, puede comprar: " + jugador.getPuedeComprar());
        
        jugador.encarcelado = false;
        jugador.numCasillaActual = 0;
        
        System.out.println("Debe dar true, se puede mover a la carcel: " + jugador.moverACasilla(4));
        
        jugador.encarcelado = true;
        
        System.out.println("Debe dar false, no se puede mover a la carcel: " + jugador.moverACasilla(4));
        
        System.out.println("Debe dar true, puede salir pagando: " + jugador.salirCarcelPagando());
        
        System.out.println("Debe dar false, no puede salir pagando si no esta en la carcel: " + jugador.salirCarcelPagando());
        
        System.out.println("Debe dar true, paga 200: " + jugador.paga(200));
        
        System.out.println("Debe dar true, paga 200 de alquiler: " + jugador.pagaAlquiler(200));
        
        System.out.println("Debe dar true, paga 200 de impuesto: " + jugador.pagaImpuesto(200));
        
        System.out.println("Debe dar 6050 de saldo: " + jugador.getSaldo());
        
        System.out.println("Debe dar true al pasar por la salida: " + jugador.pasaPorSalida());
        
        System.out.println("Debe dar 7050 de saldo: " + jugador.getSaldo());
        
        jugador.obtenerSalvoconducto(new Sorpresa(TipoSorpresa.SALIRCARCEL , mazo));
        
        System.out.println("Debe dar true, tiene salvoconducto: " + jugador.tieneSalvoconducto());
        
        jugador.perderSalvoConducto();
        
        System.out.println("Debe dar false, no tiene salvoconducto: " + jugador.tieneSalvoconducto());
        
        System.out.println("Debe dar true, puede salir pagando: " + jugador.puedeSalirCarcelPagando());
        
        System.out.println("Debe dar true, puede gastar 7000: " + jugador.puedoGastar(7000));
        
        System.out.println("Debe dar true, pierde 7000: " + jugador.modificarSaldo(-7000));
        
        System.out.println("Debe dar false, no puede salir pagando: " + jugador.puedeSalirCarcelPagando());
        
        System.out.println("Debe dar false, no puede gastar 7000: " + jugador.puedoGastar(7000));
        
        System.out.println("Debe dar 50 de saldo: " + jugador.getSaldo());
        
        System.out.println("Debe dar true al recibir 500: " + jugador.recibe(500));
        
        System.out.println("Debe dar 550 de saldo: " + jugador.getSaldo());
        
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        System.out.println("Debe dar true en algun momento: " + jugador.salirCarcelTirando());
        
        jugador.encarcelado = false;
        
        System.out.println("Debe dar true al vender: " + jugador.vender(0));
        
        System.out.println("Debe dar null: " + jugador.getPropiedades().toString());
        
        
        System.out.println("Debe dar false al vender: " + jugador.vender(0));
        
        System.out.println("Debe dar los datos del jugador: " + jugador.toString());
        
        System.out.println("Debe dar true al modificar -10000 de saldo: " + jugador.modificarSaldo(-10000));
        
        System.out.println("Debe dar true, esta en bancarrota: " + jugador.enBancarrota());
        
    }
}
