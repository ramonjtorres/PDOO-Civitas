#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "Casilla"
require_relative "Mazo_Sorpresas"
require_relative "Tipo_Sorpresas"
require_relative "Sorpresa"
require_relative "Titulo_Propiedad"
#require_relative "Jugador"


module Civitas
  class Tablero
    
    attr_reader :num_casilla_carcel, :casillas
    
    public
    def initialize(numCasillaCarcel)
      
      if(numCasillaCarcel >= 1)
        
        @num_casilla_carcel = numCasillaCarcel
        
      else
        
        @num_casilla_carcel = 1
        
      end
      
      @casillas = Array.new
      @casillas.push(Casilla.new("Salida"))
      
      @por_salida = 0
      @tiene_juez = false
      
    end
    
    
    def correcto2()
    
      if(@casillas.length > @num_casilla_carcel && @tiene_juez)
        
        return true
        
      end
      
      return false
      
    end
    
    
    def correcto(num_casilla)
      
      if(correcto2() && num_casilla < @casillas.length)
        
        return true
        
      end
      
      return false
      
    end
    
    public
    def get_por_salida()
      
      if(@por_salida > 0)
        
        @por_salida = @por_salida-1
        
        return @por_salida+1
        
      else
        
        return @por_salida
        
      end
      
    end
    
    public
    def añade_casilla(casilla)
      
      if(@casillas.length == @num_casilla_carcel)
        
        @casillas.push(Casilla.new("Cárcel"))
        
      end
      
      @casillas.push(casilla)      
      
      if(@casillas.length == @num_casilla_carcel)
        
        @casillas.push(Casilla.new("Cárcel"))
        
      end
      
    end
    
    public
    def añade_juez()
      
      if(!@tiene_juez)
        
        @tiene_juez = true
        casilla = Casilla.new("Auxiliar")
        @casillas.push(casilla.casilla_carcel(@num_casilla_carcel, "Cárcel"))
      end
      
    end
    
    public
    def get_casilla(num_casilla)
      
      if(correcto(num_casilla))
        
        return @casillas.find(num_casilla)
        
      end
      
      return nil
      
    end
    
    public
    def nueva_posicion(actual, tirada)
      
      if(!correcto2())
        
        return -1
        
      else
        
        nueva = (actual + tirada) % @casillas.length
        
        if(nueva != (actual + tirada))
          
          @por_salida = @por_salida + 1
          
        end
        
        return nueva
        
      end
      
    end
    
    public
    def calcular_tirada(origen, destino)
      
      tirada = destino - origen
      
      if(tirada < 0)
        
        return tirada + @casillas.length
        
      else
        
        return tirada
        
      end
      
    end
    
#    def main
#    
#      tablero = Tablero.new(4)
#      mazo = Mazo_Sorpresas.new()
#      sorpresa = Sorpresa.new(Tipo_Sorpresas::IR_CARCEL, tablero)      
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
#      casilla = Casilla.new("Auxiliar")
#      
#      tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Ronda de Valencia" , 10, 0.5, 25, 50, 20)))
#      tablero.añade_casilla(Casilla.new("Parking"))
#      tablero.añade_casilla(casilla.casilla_cantidad(100, "Impuesto"))
#      tablero.añade_casilla(casilla.casilla_mazo(mazo, "Sorpresa"))
#        
#      puts("Debe salir false al no tener juez:" + tablero.correcto2().to_s)
#        
#      tablero.añade_juez();
#        
#      puts("Debe salir 4:" + tablero.calcular_tirada(0, 4).to_s)
#      puts("Debe salir (1-3)%"+ tablero.casillas.length().to_s + ":" + tablero.calcular_tirada(3, 1).to_s)
#      puts("Debe salir true al tener juez:" + tablero.correcto2().to_s)
#      puts("Debe salir true al ser una casilla valida:" + tablero.correcto(2).to_s)
#      puts("Debe salir false al ser una casilla valida:" + tablero.correcto(10).to_s)
#      
#      puts("Debe salir el numero 4:" + tablero.num_casilla_carcel.to_s)
#        
#      puts("Debe salir que ha pasado 0 veces por salida:" + tablero.get_por_salida().to_s)
#        
#      puts("Debe salir 5:" + tablero.nueva_posicion(0, 5).to_s)
#      puts("Debe salir 4:" + tablero.nueva_posicion(5, 6).to_s)
#      puts("Debe salir 3:" + tablero.nueva_posicion(4, 6).to_s)
#      puts("Debe salir 2:" + tablero.nueva_posicion(3, 6).to_s)
#        
#      puts("Debe salir que ha pasado 3 veces por salida:" + tablero.get_por_salida().to_s)
#       
#      tablero2 = Tablero.new(0)
#        
#      puts("Debe salir el numero 1:" + tablero2.num_casilla_carcel.to_s)
#    
#    end
    
  end
  
  #Test_tablero = Tablero.new(4)
  #Test_tablero.main()
  
end
     