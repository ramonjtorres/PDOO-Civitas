#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa_edificacion < Sorpresa
    
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
        todos.at(actual).modificar_saldo(@valor*todos.at(actual).cantidad_casas_hoteles())
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa POR CASA-HOTEL al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Por CasabHotel\n" + @texto

    end
  
    public_class_method :new
  
  end
end
