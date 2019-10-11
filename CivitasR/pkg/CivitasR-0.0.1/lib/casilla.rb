# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "Sorpresa"
require_relative "Mazo_Sorpresas"

module Civitas
  class Casilla
    
    attr_reader :nombre, :titulo
    
    def initialize(nombre)
      
      init()
      @nombre = nombre
      
    end
    
    def casilla_titulo(titulo)
      
      init()
      @titulo = titulo
      
    end
    
    def casilla_cantidad(cantidad, nombre)
      
      init()
      @cantidad = cantidad
      @nombre = nombre
      
    end
    
    def casilla_carcel(num_casilla_carcel, nombre)
      
      init()
      @carcel = num_casilla_carcel
      @nombre = nombre
      
    end
    
    def casilla_mazo(mazo_sorpresas, nombre)
      
      init()
      @mazo = mazo_sorpresas
      @nombre = nombre
      
    end
    
    private
    def informe(iactual, todos)
      Diario.ocurre_evento(todos.at(iactual).num_casilla_actual+to_string())
    end
    
    private
    def init()
      @nombre = nil
      @carcel = -1
      @importe = -1
      @tituloPropiedad = nil
      @sorpresa = nil
      @mazo = nil
      
    end
      
    public
    def jugador_correcto(iactual, todos)
      return(iactual>0 && iactual<todos.length())
    end
    
    def recibe_jugador(iactual, todos)
      
    end
    
    private
    def recibe_jugador_calle(iactual, todos)
      
    end
    
    private
    def recibe_jugador_impuesto(iactual, todos)
      if(jugador_correcto())
        informe(iactual,todos)
        todos.at(iactual).paga_impuesto(@importe)
      end
    end
    
    private
    def recibe_jugador_juez(iactual, todos)
      if(jugador_correcto())
        informe(iactual,todos)
        todos.at(iactual).encarcelar(@carcel)
      end
      
    end
    
    private
    def recibe_jugador_sorpresa(iactual, todos)
      
    end
    
    public
    def to_string()
      casilla = "Nombre casilla = "+@nombre+" Carcel = "+@carcel+" Importe = "+@importe+" Tipo Casilla = "+ @tipo.to_string() + " Titulo Propiedad = "+@tituloPropiedad.to_string()+ " Sorpresa = " + @sorpresa.to_string() +" Mazo Sorpresas = "+@mazo.to_string();
    end
    
  end
end
