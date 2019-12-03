#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa_casilla < Sorpresa
    public
    def initialize(valor, tablero)
      
      @valor = valor
      @tablero = tablero
      @mazo = Mazo_Sorpresas.new
      @texto = "Esta sorpresa te lleva a otra casilla"
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        casilla_actual = todos.at(actual).num_casilla_actual
        tirada = @tablero.calcular_tirada(casilla_actual, @valor)
            
        nueva_posicion = @tablero.nueva_posicion(casilla_actual, tirada)
        todos.at(actual).mover_a_casilla(nueva_posicion)
        @tablero.get_casilla(@valor).recibe_jugador(actual, todos)
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa IR A CASILLA al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Casilla\n" + @texto

    end
    
    public_class_method :new
  end
end
