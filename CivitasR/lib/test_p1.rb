# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#encoding: utf-8

require_relative "Diario"
require_relative "Dado"
require_relative "Mazo_Sorpresas"
require_relative "Sorpresa"

module Civitas
  class Test_P1
    def main()
      
      diario = Diario.instance
      
      #1
      
      dado = Dado.instance
      
      jugador1 = 0
      jugador2 = 0
      jugador3 = 0
      jugador4 = 0
      jugador = 0
      
      var = 0
      
      while(var < 100)
        
        jugador = dado.quien_empieza(4)
        
        if(jugador == 0)
            jugador1 = jugador1 + 1
        end
        if(jugador == 1)
            jugador2 = jugador2 + 1
        end
        if(jugador == 2)
            jugador3 = jugador3 + 1
        end
        if(jugador == 3)
            jugador4 = jugador4 + 1
        end    
        
        var = var + 1
        
      end 
   
      puts "Jugador 1 = " + jugador1;
      puts "Jugador 2 = " + jugador2;
      puts "Jugador 3 = " + jugador3;
      puts "Jugador 4 = " + jugador4;
      
      #2
      
      var = 0
      
      while(var < 20)
        puts dado.tirar() + " | "
      end
      
      dado.set_debug(true)
      
      var = 0
      
      while(var < 20)
        puts dado.tirar() + " | "
      end
      
      #3
      
      puts dado.ultimo_resultado
      puts dado.salgo_de_la_carcel()
      
      #4
      
      puts estados_juego:DESPUES_AVANZAR
      puts operaciones_juego:AVANZAR
      puts tipo_casilla:CALLE
      puts tipo_sorpresa:IRCARCEL
      
      #5
      
      mazo = Mazo_Sorpresas.new
      sorpresa1 = Sorpresa.new
      sorpresa2 = Sorpresa.new
      
      puts mazo.al_mazo(sorpresa1)
      diario.ocurre_evento("Añadimos sorpresa 1")
      puts mazo.alMazo(sorpresa2)
      diario.ocurre_evento("Añadimos sorpresa 2")
      sorpresa3 = mazo.siguiente()
      diario.ocurre_evento("Cargamos siguiente sorpresa")
      mazo.inhabilitar_carta_especial(sorpresa2)
      diario.ocurre_evento("inhabilitamos carta especial sorpresa2")
      mazo.habilitar_carta_especial(sorpresa2)
      diario.ocurre_evento("habilitamos carta especial sorpresa2")
      
      #6
      
      puts diario.eventos_pendientes()
      puts diario.leer_evento()
      
      #7
      
      tablero = Tablero.new(3)
      casilla1 = Casilla.new("casilla1")
      casilla2 = Casilla.new("casilla2")
      tablero.añade_casilla(casilla1)
      tablero.añade_casilla(casilla2)
      tablero.añade_juez()
      
      puts tablero.nueva_posicion(3, 4)
      
    end  
  end
  
    P1 = Test_P1.new
    P1.main()

end