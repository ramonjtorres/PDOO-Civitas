# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Casilla_impuesto < Casilla
    
    attr_reader :nombre, :importe
    
    def initialize(nombre, cantidad)
      
      super(nombre)
      @importe = cantidad
      
    end
    
    def recibe_jugador(iactual, todos)
     
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).paga_impuesto(@importe)
      end
    end
    
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
        if(@importe!=-1.0)
          casilla = casilla +"\n  Importe: " + @importe.to_s
        end
        
        casilla = casilla +"\n  Tipo Casilla: IMPUESTO"
        
        return casilla
    end
  end
end
