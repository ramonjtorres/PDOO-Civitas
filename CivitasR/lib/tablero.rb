#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


module Civitas
  class Tablero
    
    attr_reader :num_casilla_carcel
    
    public
    def initialize(numCasillaCarcel)
      
      if(numCasillaCarcel >= 1)
        
        @num_casilla_carcel = numCasillaCarcel
        
      else
        
        @num_casilla_carcel = 1
        
      end
      
      @casillas = Array.new()
      @casillas.push(Casilla.new("Salida"))
      
      @por_salida = 0
      @tiene_juez = false
      
    end
    
    private
    def correcto2()
    
      if(@casillas.length > @num_casilla_carcel && @tiene_juez)
        
        return true
        
      end
      
      return false
      
    end
    
    private
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
        
        if(nueva == (actual + tirada))
          
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
    
  end
end
     