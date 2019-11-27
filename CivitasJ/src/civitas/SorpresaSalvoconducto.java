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
public class SorpresaSalvoconducto extends Sorpresa{
    
    private String texto;
    MazoSorpresas mazo;
    
    SorpresaSalvoconducto(MazoSorpresas mazo){
    
        this.mazo = mazo;
        this.texto = "Esta sorpresa evita que caigas en la cárcel";
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    
        if(this.jugadorCorrecto(actual, todos)){
        
            this.informe(actual, todos);
            int i = 0;
            boolean tiene = false;
                    
            while(i<todos.size()){
                
                tiene = todos.get(i).tieneSalvoconducto();
                i++;
            }
            
            if(!tiene){
            
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }
        }
        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
    
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa SALIR DE LA CÁRCEL al jugador " + todos.get(actual).getNombre());
    }
    @Override
    public String toString(){
        
        String sorpresa = "Tipo: Sorpresa Salvoconducto\n";
        
        sorpresa += this.texto;
        
        return sorpresa;
    }
}
