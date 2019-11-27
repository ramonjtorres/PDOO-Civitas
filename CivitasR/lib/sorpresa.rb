#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa
   
    public
    def initialize()
      
      @valor = -1
      
    end
    
    public
    def jugador_correcto(actual, todos)
      
      return (actual < todos.length())
      
    end
    
    public
    def salir_del_mazo()
      
      if(@valor == 1)
        
        @mazo.inhabilitar_carta_especial(self)
      end
      
    end
    
    public
    def usada()
      
      if(@valor == 1)
        
        @mazo.habilitar_carta_especial(self)
      end
      
    end

    private_class_method :new
    
  end

end
