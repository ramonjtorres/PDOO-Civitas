#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#require_relative "Sorpresa"
#require_relative "Mazo_Sorpresas"
#require_relative "Jugador"
#require_relative "Tipo_Sorpresas"
#require_relative "Diario"

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
      @importe = cantidad
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
      Diario.instance.ocurre_evento( to_s)
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
      return(iactual>=0 && iactual<=todos.length())
    end
    
    def recibe_jugador(iactual, todos)
      
    end
    
    private
    def recibe_jugador_calle(iactual, todos)
      
    end
    
    private
    def recibe_jugador_impuesto(iactual, todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).paga_impuesto(@importe)
      end
    end
    
    private
    def recibe_jugador_juez(iactual, todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).encarcelar(@carcel)
      end
      
    end
    
    private
    def recibe_jugador_sorpresa(iactual, todos)
      
    end
    
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
        if(@carcel!=-1.0)
            casilla = casilla + "\n  Carcel: " + @carcel.to_s
        end
        if(@importe!=-1.0)
          casilla = casilla +"\n  Importe: " + @importe.to_s
        end
        if(@tipo!=nil)
            casilla = casilla +"\n  Tipo Casilla: " + @tipo.to_s
        end
        if(@tituloPropiedad!=nil)
            casilla = casilla + @tituloPropiedad.to_s   
        end
        return casilla
    end
    
  
#    def main
#      j1 = Jugador.new("David")
#      j2 = Jugador.new("Ramon")
#      Diario.instance
#      todos = Array.new
#        
#      todos.push(j1)
#      todos.push(j2)
#    
#      casilla = Casilla.new("hola")
#      casilla.casilla_cantidad(100,"impuesto")
#      casilla.recibe_jugador_impuesto(0,todos)
#      puts "Debe de tener 100 menos de saldo: \n" + j1.to_s
#      casilla.recibe_jugador_juez(0,todos)
#      puts "El jugador debe estar encarcelado: \n" + j1.to_s
#    end
#  
  end
#   casilla = Casilla.new("prueba")
#   casilla.main()

end

   
