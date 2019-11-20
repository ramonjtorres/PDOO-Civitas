# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Casilla_juez < Casilla
    
    attr_reader :nombre
    
    def initialize(nombre, num_casilla_carcel)
      
      super(nombre)
      @@carcel = num_casilla_carcel
      
    end
    
    def recibe_jugador(iactual, todos)
     
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).encarcelar(@@carcel)
      end
    end
    
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
        if(@@carcel!=-1.0)
            casilla = casilla + "\n  Carcel: " + @@carcel.to_s
        end
        
        casilla = casilla +"\n  Tipo Casilla: JUEZ"
        
        return casilla
    end
  end
end
