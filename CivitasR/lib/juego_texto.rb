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

      print "Indica el número de jugadores: "
      num_jugadores = gets.chomp
      
      i = 0
      
      print "Indica el nombre de los jugadores:\n"
      
      loop do
        
        print "Nombre jugador " + i.to_s + ":"
        jugadores.push(gets.chomp)
        
        i = i + 1
        
        break if (i >= num_jugadores.to_i)
      end
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