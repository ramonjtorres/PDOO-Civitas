#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "jugador"
require_relative "jugador_especulador"
require_relative "titulo_propiedad"
require_relative "sorpresa_especulador"
require_relative "sorpresa"
require_relative "diario"
require_relative "mazo_sorpresas"

module Civitas
  class TestP4
    
    def self.main()
      
      jugador = Jugador.new("Ramon", nil)
        
      propiedad = Titulo_Propiedad.new("Ronda de Valencia", 10, 0.5, 25, 50, 20)
        
      propiedad.actualizar_propietario_por_conversion(jugador)
      jugador.propiedades.push(propiedad)
      
      jugadores = Array.new
      
      jugadores.push(jugador)

      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      puts jugadores.at(0).to_s

      puts "\n================================= HACEMOS LA CONVERSIÓN ==================================\n"

      sorpresa = Sorpresa_especulador.new(100, "Te conviertes en un especulador con 100€ de fianza")

      sorpresa.aplicar_a_jugador(0, jugadores)
        
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
        
      jugadores.at(0).construir_hotel(0)
      
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      jugadores.at(0).construir_casa(0)
      
      puts jugadores.at(0).to_s
    end
  end
  
  TestP4.main()
  
end
