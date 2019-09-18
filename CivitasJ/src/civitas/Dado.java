/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Random;

/**
 *
 * @author ramonjtorres
 */
class Dado {
  
  private Random random;
  private int ultimoResultado;
  private boolean debug;
  
  static final private Dado instance = new Dado();
  private int SalidaCarcel = 5;
  
  private Dado() {
    
      random = new Random();
      ultimoResultado = 0;
      debug = false;
  }
  
  static Dado getInstance() {
    return instance;
  }
  
  int tirar(){
      
      if(!debug){
          
          ultimoResultado = random.nextInt(6)+1;
      }
      else{
          
          ultimoResultado = 1;
      }
      
      return ultimoResultado;
  }
  
  boolean salgoDeLaCarcel(){
  
      if(tirar() == SalidaCarcel){
      
          return true;
      }
      
      return false;
  }
  
  int quienEmpieza(int n){
  
      return random.nextInt(n);
  }
  
  void setDebug(boolean d){
  
      debug = d;
      Diario.getInstance().ocurreEvento("Debug");
  }
  
  int getUltimoResultado(){
  
      return ultimoResultado;
  }

}
