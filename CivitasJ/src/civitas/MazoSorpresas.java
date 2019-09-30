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
public class MazoSorpresas {
    
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    private void init(){
        sorpresas = new ArrayList<Sorpresa>();
        cartasEspeciales = new ArrayList<Sorpresa>();
        barajada = false;
        usadas = 0;
    }
    
    
    
    MazoSorpresas(Boolean dbg){
        debug = dbg;
        init();
        if(debug)
            Diario.getInstance().ocurreEvento("Modo debug activado");
    }
    
    MazoSorpresas(){
        debug = false;
        init();
    }
    
    Sorpresa getUltimaSorpresa(){
        return ultimaSorpresa;
    }
    
    void alMazo(Sorpresa s){
        if(!(barajada))
            sorpresas.add(s);
    }
    
    Sorpresa siguiente(){        
        if(!(barajada)||usadas==sorpresas.size()){ 
            if(!(debug))
                barajada = true;
                usadas = 0;
        }
        usadas++;
        ultimaSorpresa = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(ultimaSorpresa);
        return ultimaSorpresa;
    }
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        for(int i = 0; i < sorpresas.size(); i++){
            if(sorpresa == sorpresas.get(i)){
                cartasEspeciales.add(sorpresa);
                sorpresas.remove(i);
                Diario.getInstance().ocurreEvento("Se ha inhabilitado una carta especial");
            }
        }
    }
    void habilitarCartaEspecial(Sorpresa sorpresa){
        for(int i = 0; i < cartasEspeciales.size(); i++){
            if(sorpresa == cartasEspeciales.get(i)){
                cartasEspeciales.remove(i);
                sorpresas.add(sorpresa);
                Diario.getInstance().ocurreEvento("Se ha habilitado esta sorpresa");
            }
        }
    }
}
