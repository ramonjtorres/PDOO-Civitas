# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Casilla_sorpresa < Casilla
    
    attr_reader :nombre, :mazo
    
    def initialize(nombre, mazo_sorpresas)
      
      super(nombre)
      @mazo = mazo_sorpresas
      
    end
  
    def recibe_jugador(iactual, todos)
     
      if(jugador_correcto(iactual,todos))
        @sorpresa = @mazo.siguiente()
        informe(iactual,todos)
        @sorpresa.aplicar_a_jugador(iactual, todos)
      end
    end
   
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
        
        casilla = casilla +"\n  Tipo Casilla: SORPRESA"
            
        return casilla
    end
  end
end
