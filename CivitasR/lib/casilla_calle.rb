# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Casilla_calle < Casilla
    
    attr_reader :nombre, :tituloPropiedad
    
    def initialize(titulo)
      
      super(titulo.nombre)
      @tituloPropiedad = titulo
      
    end
    
    def recibe_jugador(iactual, todos)
     
      if(jugador_correcto(iactual,todos))
         informe(iactual,todos)
         nuevo = todos.at(iactual)
        
        if(!@tituloPropiedad.tiene_propietario)
          nuevo.puede_comprar_casilla
        else
          @tituloPropiedad.tramitar_alquiler(nuevo)
        end
      end
    end
    
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
            
        casilla = casilla +"\n  Tipo Casilla: CALLE"

        if(@tituloPropiedad!=nil)
            casilla = casilla + @tituloPropiedad.to_s   
        end
        return casilla
    end
  end
end
