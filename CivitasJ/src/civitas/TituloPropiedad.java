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
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){
    
        nombre = nom;
        alquilerBase = ab;
        factorRevalorizacion = fr;
        hipotecaBase = hb;
        precioCompra = pc;
        precioEdificar = pe;
        hipotecado = false;
        numCasas = 0;
        numHoteles = 0;
        propietario = null;
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador){
    
        propietario = jugador;
    }
    
    boolean esEsteElPropietario(Jugador jugador){
        return propietario == jugador;
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        
        if(hipotecado && esEsteElPropietario(jugador)){
            
            float cantidadRecibida = getImporteHipoteca();
            
            jugador.paga((float) (cantidadRecibida + 0.1*cantidadRecibida));
            hipotecado = false;
            
            return true;
        }
        else{
        
            return false;
        }
    }
    
    int cantidadCasasHoteles(){
        return getNumCasas() + getNumHoteles();
    }
    
    boolean comprar(Jugador jugador){
        
        if(tienePropietario()){
        
            return false;
        }
        else{
            
            actualizaPropietarioPorConversion(jugador);
            jugador.paga(getPrecioCompra());
            return true;
        }
    }
    
    boolean construirCasa(Jugador jugador){
        
        boolean construida = false;
        
        if(esEsteElPropietario(jugador)){
        
            propietario.paga(precioEdificar);
            numCasas+=1;
            construida = true;
        }
        
        return construida;
    }
    
    boolean construirHotel(Jugador jugador){
        
        boolean construida = false;
        
        if(esEsteElPropietario(jugador)){
        
            propietario.paga(precioEdificar);
            numCasas-=4;
            numHoteles+=1;
            construida = true;
        }
        
        return construida;
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        
        if(esEsteElPropietario(jugador) && numCasas >= n){
        
            numCasas -= n;
            
            return true;
        }
        else{
        
            return false;
        }
    }
    
    public boolean getHipotecado(){
        return hipotecado;
    }
    
    float getImporteCancelarHipoteca(){
        return factorInteresesHipoteca*hipotecaBase;
    }
    
    float getImporteHipoteca(){
        return (float) (hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5)));
    }
    
    String getNombre(){
        return nombre;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    private float getPrecioAlquiler(){
        
        if(propietarioEncarcelado() || hipotecado){
            return 0;
        }
        else{
        
            return (float) (alquilerBase * (1+(numCasas*0.5)+(numHoteles*2.5)));
        }
    }
    
    private float getPrecioVenta(){
        
        return precioCompra + (precioEdificar * cantidadCasasHoteles()) * factorRevalorizacion;
    }
    
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    float getPrecioCompra(){
        return precioCompra;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    boolean hipotecar(Jugador jugador){
        
        if(!hipotecado && esEsteElPropietario(jugador)){
        
            propietario.recibe(getImporteHipoteca());
            hipotecado = true;
            
            return true;
        }
        else{
        
            return false;
        }
    }
    
    private boolean propietarioEncarcelado(){
    
        if(!tienePropietario() || !propietario.isEncarcelado()){
        
            return false;
        }
        else{
        
            return true;
        }
        
    }
    
    boolean tienePropietario(){
        return propietario != null;
    }
    
    void tramitarAlquiler(Jugador jugador){
    
        if(propietario != null && !esEsteElPropietario(jugador)){
        
            jugador.pagaAlquiler(getPrecioAlquiler());
            propietario.recibe(getPrecioAlquiler());
            
        }
    }
    
    boolean vender(Jugador jugador){
        
        if(!tienePropietario()){
        
            return false;
        }
        else{
            
            jugador.recibe(getPrecioVenta());
            actualizaPropietarioPorConversion(null);
            numCasas = 0;
            numHoteles = 0;
            return true;
        }
    }
    
    @Override
    public String toString(){
        
        String h = "No";
        
        if(hipotecado){
        
            h = "Sí";
        }
        
        String p = "Sin Propietario";
                
        if(propietario != null){
            
            p = propietario.getNombre();
        }       
        
        return "| Nombre: " + nombre +
               "\n|  Propietario: " + p +
               "\n|  Numero Casas: " + numCasas +
               "\n|  Numero Hoteles: " + numHoteles +
               "\n|  Precio Compra: " + precioCompra +
               "\n|  Precio Alquiler Base: " + alquilerBase +
               "\n|  Precio Edificar: " + precioEdificar +
               "\n|  Precio Hipoteca Base: " + hipotecaBase +
               "\n|  Factor Intereses Hipoteca: " + factorInteresesHipoteca +
               "\n|  Factor Revalorización: " + factorRevalorizacion +
               "\n|  Hipotecado: " + h;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            TituloPropiedad propiedad = new TituloPropiedad("Ronda de Valencia",10, (float) 0.5,25,50,20);
            
            Jugador jugador = new Jugador("Ramón");
            
            System.out.println("NO TIENE PROPIETARIO, NULL: " + propiedad.getPropietario());
            
            System.out.println("NO TIENE PROPIETARIO, FALSE: " + propiedad.tienePropietario());
            
            propiedad.comprar(jugador);
        
            System.out.println("TIENE PROPIETARIO, RAMÓN: " + propiedad.getPropietario());
            
            System.out.println("TIENE PROPIETARIO, TRUE: " + propiedad.tienePropietario());
        
            System.out.println("DEBE DAR FALSE, PROPIETARIO LIBRE: " + propiedad.propietarioEncarcelado());
            
            jugador.encarcelado = true;
            
            System.out.println("DEBE DAR TRUE, PROPIETARIO ENCARCELADO: " + propiedad.propietarioEncarcelado());
            
            jugador.encarcelado = false;
            
            System.out.println("DEBE DAR FALSE, NO PUEDE CANCELAR HIPOTECA: " + propiedad.cancelarHipoteca(jugador));
            
            System.out.println("DEBE DAR FALSE, NO ESTA HIPOTECADA: " + propiedad.getHipotecado());
            
            propiedad.hipotecar(jugador);
            
            System.out.println("DEBE DAR TRUE, ESTA HIPOTECADA: " + propiedad.getHipotecado());
            
            System.out.println("DEBE DAR TRUE, PUEDE CANCELAR HIPOTECA: " + propiedad.cancelarHipoteca(jugador));
            
            System.out.println("TIENE 0 CASAS Y HOTELES: " + propiedad.cantidadCasasHoteles());
            
            System.out.println("CONSTRUIDA CASA" + propiedad.construirCasa(jugador));
            System.out.println("CONSTRUIDA CASA" + propiedad.construirCasa(jugador));
            System.out.println("CONSTRUIDA CASA" + propiedad.construirCasa(jugador));
            System.out.println("CONSTRUIDA CASA" + propiedad.construirCasa(jugador));
            System.out.println("CONSTRUIDA CASA" + propiedad.construirCasa(jugador));
            System.out.println("CONSTRUIDO HOTEL" + propiedad.construirHotel(jugador));
            
            System.out.println("TIENE 2 CASAS Y HOTELES: " + propiedad.cantidadCasasHoteles());
            
            System.out.println("" + propiedad.derruirCasas(1, jugador));
            
            System.out.println("TIENE UN HOTEL: " + propiedad.cantidadCasasHoteles());
            
            System.out.println("DEBE DAR TRUE: " + propiedad.esEsteElPropietario(jugador));
            
            propiedad.vender(jugador);
            
            System.out.println("NO TIENE PROPIETARIO, NULL: " + propiedad.getPropietario());
            
            System.out.println("DATOS DE LA PROPIEDAD: " + propiedad.toString());
    }
}