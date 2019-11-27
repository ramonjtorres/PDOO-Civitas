#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa_carcel < Sorpresa
    public
    def initialize(tablero)
      
      @tablero = tablero
      @mazo = Mazo_Sorpresas.new
      @texto = "Esta sorpresa te lleva a la cárcel"
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        todos.at(actual).encarcelar(@tablero.num_casilla_carcel)
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa IR A CÁRCEL al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Cárcel\n" + @texto

    end
  
    public_class_method :new

  end
end
