#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#require_relative "Tipo_Sorpresas"
#require_relative "Tablero"
#require_relative "Sorpresa"
module Civitas
  class Mazo_Sorpresas
    
    
    attr_accessor :usadas, :ultima_sorpresa, :sorpresas
    
    @degb = false

    private
    def init()
    
      @sorpresas = Array.new()
      @cartas_especiales = Array.new()
      @barajada = false
      @usadas = 0
    
    end
    
    def initialize_with_arguments(debg)
      
      @debug = debg
      init()
      
      if(@debug)
        Diario.instance.ocurre_evento("Modo debug activado")
      end
      
    end

    def initialize() 
    
      init()
      @debug = false
    
    end
    
    public  #Puesto a public para poder probar el main de la clase Tablero
    def al_mazo(s)
    
      if(!@barajada)
    
        @sorpresas.push(s)
      end
      return s
    
    end
    
    def siguiente()
    
      if(!@barajada||@usadas == @sorpresas.length())
        if(!@debug)
          @barajada=true
          @usadas = 0 
        end
      end
      
      @usadas = @usadas+1
      @ultima_sorpesa = @sorpresas.at(0)
      @sorpresas.delete_at(0)
      @sorpresas.push(@ultima_sorpresa)
      
      return @ultima_sorpresa
    
    end
    
    def inhabilitar_carta_especial(sorpresa)
    
      @var = 0
      
      while @var < @sorpresas.length()
        if(sorpresa == @sorpresas[@var])
          @cartas_especiales.push(sorpresa)
          @sorpresas.delete_at(@var)
          Diario.instance.ocurre_evento("Se ha inhabilitado una carte especial")
        end
        @var = @var + 1
      end
    
    end
    
    def habilitar_carta_especial(sorpresa)
    
      @var = 0
      
      while @var < @cartas_especiales.length()
        if(sorpresa == @cartas_especiales[@var])
            @cartas_especiales.delete_at(@var)
            @sorpresas.push(sorpresa)
            Diario.instance.ocurre_evento("Se ha habilitado esta sorpresa")
        end
        @var = @var + 1
      end   
    end
    
#    def main
#      mazo = Mazo_Sorpresas.new
#      tablero = Tablero.new(4)
#      sorpresa = Sorpresa.new(Tipo_Sorpresas::SALIR_CARCEL, Tablero.new(20))
#      
#      mazo.al_mazo(Sorpresa.new(Tipo_Sorpresas::IR_CARCEL, tablero))
#      mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,4,tablero))
#      mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,5,tablero))
#      mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,10,tablero))
#      mazo.al_mazo(sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIR_CARCEL,mazo))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_JUGADOR,tablero,-50,"El jugador debe pagar a cada uno de los demas jugadores 50€"))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_JUGADOR,tablero,50,"Cada jugador te debe pagar 50€"))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_CASA_HOTEL,tablero,30,"Recibes 30€ por cada casa y hotel en propiedad"))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_CASA_HOTEL,tablero,-30,"Cobras 30€ por cada casa y hotel en propiedad"))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::PAGAR_COBRAR,tablero,-100,"Pagas 100€ por gastos de limpieza"))
#      mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::PAGAR_COBRAR,tablero,100,"Has ganado un premio al hotel más limpio recibe 100€"))
#      
#      mazo.siguiente
#             
#      mazo.inhabilitar_carta_especial(mazo.ultima_sorpresa)
#      puts ("10: "+ mazo.sorpresas.length.to_s)
#      mazo.habilitar_carta_especial(mazo.ultima_sorpresa)
#      puts ("11: "+ mazo.sorpresas.length.to_s)
#    end
  end
#  
#  ms = Mazo_Sorpresas.new
#  ms.main
end
