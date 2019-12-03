#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Casilla
    
    attr_reader :nombre
    
    def initialize(nombre)
      
      @nombre = nombre
      
    end
    
    public
    def informe(iactual, todos)
      Diario.instance.ocurre_evento("El jugador " + todos.at(iactual).nombre.to_s + "está en la casilla " + to_s())
    end
      
    public
    def jugador_correcto(iactual, todos)
      return(iactual>=0 && iactual<=todos.length())
    end
    
    def recibe_jugador(iactual, todos)
     
          informe(iactual,todos)
    end
    
    public
    def to_s
      
      casilla="\n"
      
      if(@nombre!=nil)
        casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
      end
      
      casilla = casilla + "\n Tipo Casilla: DESCANSO"  
      
      return casilla
    end

  end

end

   
