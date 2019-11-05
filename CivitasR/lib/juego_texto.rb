#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "civitas_juego"
require_relative "vista_textual"
require_relative "controlador"

module Civitas
  class Juego_texto
    
    def main
      
      jugador1 = "David"
      jugador2 = "Ramón"

      jugadores = Array.new

      jugadores.push(jugador1)
      jugadores.push(jugador2)

      juego = Civitas_Juego.new(jugadores)
      vista = Vista_textual.new()
      dado = Dado.instance()
      dado.set_debug(true)
      controlador = Controlador.new(juego, vista)
      controlador.juega()
      
    end
  end
  
  juego = Juego_texto.new
  juego.main

end