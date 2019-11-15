#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "civitas_juego"
require_relative "vista_textual"
require_relative "controlador"

module Civitas
  class Juego_texto
    
    def self.main

      jugadores = Array.new
      num_jugadores = 4
      
      loop do
        
        print "Indica el número de jugadores (de 2 a 4 jugadores): "
        num_jugadores = gets.chomp
        
        break if(num_jugadores.to_i >= 2 && num_jugadores.to_i <= 4)
      end
      
      i = 0
      
      print "Indica el nombre de los jugadores:"
      
      nombre = ""
      
      loop do
        
        loop do
          print "Nombre jugador " + i.to_s + ":"
          nombre = gets.chomp
          
          break if(!nombre.empty?)
        end
        
        jugadores.push(nombre)
        
        i = i + 1
        
        break if (i >= num_jugadores.to_i)
      end
      
      puts("================================= COMENZAMOS ==================================")
      
      juego = Civitas_Juego.new(jugadores)
      vista = Vista_textual.new()
      dado = Dado.instance
      dado.set_debug(true)
      controlador = Controlador.new(juego, vista)
      controlador.juega()
      
    end
  end
  
  Juego_texto.main

end