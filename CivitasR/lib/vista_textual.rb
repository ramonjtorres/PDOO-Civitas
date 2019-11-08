#encoding:utf-8
require_relative 'operaciones_juego'
require 'io/console'

module Civitas

  class Vista_textual

    def mostrar_estado(estado)
      puts estado
    end
    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end



    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end

    def salirCarcel()
    
      opcion = menu("Elige la forma para intentar salir de la carcel", ["Pagando","Tirando el dado"])
    
      return lista_salir_carcel[opcion]
    end
    
    def comprar
      lista_respuestas = [Respuestas::NO,Respuestas::SI]
      
      opcion = menu("¿Desea comprar la calle a la que se ha llegado?", ["SI","NO"])
    
      return lista_respuestas[opcion]
    end

    def gestionar
      
      opcion = menu("Indique el número de gestión inmobiliaria elegida",
      ["Vender", "Hipotecar", "Cancelar hipoteca", "Construir casa", "Construir hotel", "Terminar"])
      
      @ipropiedad = @juegoModel.get_jugador_actual().get_casilla_actual()
      @igestion = opcion
    end

    def getGestion
      return @igestion
    end

    def getPropiedad
      return @ipropiedad
    end

    def mostrarSiguienteOperacion(operacion)

      puts "\nSiguiente operación: " + operacion.to_s      
    end

    def mostrarEventos
      
      while(Diario.instance.eventos_pendientes())
        
        puts "\nEvento pendiente: " + Diario.instance.leer_evento()
      end
    end

    def setCivitasJuego(civitas)
         @juegoModel=civitas
         self.actualizarVista
    end

    def actualizarVista
      
      puts "\nJugador Actual: " + @juegoModel.get_jugador_actual().to_s
      puts "\nCasilla Actual: " + @juegoModel.get_casilla_actual().to_s
      
    end

    
  end

end
