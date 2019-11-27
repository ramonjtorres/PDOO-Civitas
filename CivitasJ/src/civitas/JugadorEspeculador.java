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
public class JugadorEspeculador extends Jugador{
    
    private static final int FactorEspeculador = 2;
    private float fianza;

    public JugadorEspeculador(Jugador otro, float fianza) {
        super(otro);
        this.fianza = fianza;
        
        for(int i = 0; i < this.propiedades.size(); i++){
        
            this.propiedades.get(i).actualizaPropietarioPorConversion(this);
        }
    }
    
    @Override
    boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
            
            if(this.puedoGastar(fianza)){
            
                this.paga(fianza);
                Diario.getInstance().ocurreEvento("El jugador especulador ha pagado la fianza");
            }
            else{
            
                this.moverACasilla(numCasillaCarcel);
                this.encarcelado = true;
                Diario.getInstance().ocurreEvento("El jugador especulador ha sido encarcelado");
            }
        }
        return this.encarcelado;
    }
    
    @Override
    int getCasasMax(){
        return CasasMax*FactorEspeculador;
    }
    
    @Override
    int getHotelesMax(){
        return HotelesMax*FactorEspeculador;
    }
    
    @Override
    boolean pagaImpuesto(float cantidad){
        if(this.encarcelado || (cantidad/2) < 0)
            return false;
        else
            return this.paga(cantidad/2);
    }
    
    @Override
    public String toString(){
         
        return super.toString() + "\nFianza: " + this.fianza; 
    }
}
