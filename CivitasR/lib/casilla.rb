# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "Sorpresa"
require_relative "Mazo_Sorpresas"

module Civitas
  class Casilla
    
    attr_reader :nombre, :titulo
    
    def initialize(nombre)
      
      @nombre = nombre
      
    end
    
    def casilla_titulo(titulo)
      
      @titulo = titulo
      
    end
    
    def casilla_cantidad(cantidad, nombre)
      
      @cantidad = cantidad
      @nombre = nombre
      
    end
    
    def casilla_carcel(num_casilla_carcel, nombre)
      
      @num_casilla_carcel = num_casilla_carcel
      @nombre = nombre
      
    end
    
    def casilla_mazo(mazo_sorpresas, nombre)
      
      @mazo = mazo_sorpresas
      @nombre = nombre
      
    end
    
    private
    def informe(iactual, todos)
      
      
    end
    
    private
    def init()
      
      
    end
      
    public
    def jugador_correcto(iactual, todos)
      
    end
    
    def recibe_jugador(iactual, todos)
      
    end
    
    private
    def recibe_jugador_calle(iactual, todos)
      
    end
    
    private
    def recibe_jugador_impuesto(iactual, todos)
      
    end
    
    private
    def recibe_jugador_juez(iactual, todos)
      
    end
    
    private
    def recibe_jugador_sorpresa(iactual, todos)
      
    end
    
    public
    def to_string()
      
    end
    
  end
end
