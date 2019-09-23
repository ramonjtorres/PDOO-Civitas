# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require 'singleton'

module Civitas
  class Dado
    include Singleton
    
    attr_reader :ultimo_resultado
    
    @@salida_carcel = 5
    
    def initialize
      
      @random = rand()
      @ultimo_resultado = 0
      @debug = false
      
    end
    
    public 
    def tirar()
      
      if(@debug)
       
        @ultimo_resultado = rand(5) + 1
        
      else
      
        @ultimo_resultado = 1
      
      end
      
      return @ultimo_resultado
      
    end
    
    public 
    def salgo_de_la_carcel()
      
      if(tirar() >= @salida_carcel)
        
        return true
        
      end
      
      return false
      
    end
    
    public 
    def quien_empieza(n)
      
      return rand(n-1)
      
    end
    
    public 
    def set_debug(d)
      @debug = d
      Diario.instance.ocurre_evento("Modo debug activado")
    end
    
  end
end
