#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa"

module Civitas
  class Sorpresa_especulador < Sorpresa
    
    public
    def initialize(valor, texto)
      
      @valor = valor
      @mazo = Mazo_Sorpresas.new
      @texto = texto
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
            informe(actual, todos)
            
            especulador = Jugador_especulador.new(todos.at(actual), @valor)
            
            todos.delete_at(actual)
            todos.push(especulador)
            
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa CONVERSIÓN ESPECULADOR al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Conversión Especulador\n" + @texto

    end
    
    public_class_method :new

  end
end
