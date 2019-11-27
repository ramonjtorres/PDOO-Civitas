#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa_jugador < Sorpresa
    public
    def initialize(tablero, valor, texto)
      
      @valor = valor
      @tablero = tablero
      @mazo = Mazo_Sorpresas.new
      @texto = texto
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
            
        pagar = Sorpresa.sorpresa_tablero(Tipo_Sorpresas::PAGAR_COBRAR, @valor*-1, @tablero)    
        i = 0
        while(i<todos.length())
            
          if(i != actual)
            pagar.aplicar_a_jugador(i, todos)
          end
          i= i+1
        end
          cobrar = Sorpresa.sorpresa_tablero(Tipo_Sorpresas::PAGAR_COBRAR, @valor*(todos.length()-1), @tablero)
          cobrar.aplicar_a_jugador(actual, todos)
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa JUGADOR POR JUGADOR al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Jugador por Jugador\n" + @texto

    end

    public_class_method :new
    
  end
end
