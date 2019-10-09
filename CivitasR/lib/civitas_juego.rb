# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "Tipo_Sorpresas"

module Civitas
  class Civitas_Juego
    
    @mazo = Mazo_Sorpresas.new
    @tablero = Tablero.new(20)
    @jugadores = Array.new()
    
    public
    def initialize(nombres)
      var = 0
      while (var < nombres.length())
        j = Jugador.new(nombres.at(var))
        @jugadores.push(j)
        var = var + 1
      end
      
      @gestorEstados.estado_inicial
      @indice_jugador_actual = Dado.quien_empieza(jugadores.length())
      @mazo = Mazo_Sorpresas.new
      inicializar_mazo_sorpresas(@tablero)
      inicializar_tablero(@mazo)
    end
    
    private
    def avanza_jugador()
      
    end
    
    private
    def actualizar_info()
      puts "Estado: " + @estado
      puts "Información Jugador: " + info_jugador_texto()
    end
    
    public
    def cancelar_hipoteca(ip)
      @jugadores.at(@indice_jugador_actual).cancelar_hipoteca(ip)
    end
    
    public
    def comprar()
      
    end
  
    public
    def construir_casa(ip)
      @jugadores.at(@indice_jugador_actual).construir_casa(ip)
    end
    
    public
    def construir_hotel(ip)
      @jugadores.at(@indice_jugador_actual).construir_hotel(ip)
    end
    
    private
    def contabilizar_pasos_por_salida(jugador_actual)
      while(@tablero.get_por_salida > 0)
        jugador_actual.pasa_por_salida
      end
    end
    
    public
    def final_del_juego()
      var = 0
      while(var<@jugadores.length)
        if(@jugadores.at(var).en_bancarrota)
          return true
        end
        var = var + 1
      end
      return false
    end
    
    public
    def get_casilla_actual()
      var i = @jugadores.at(@indice_jugador_actual).num_casilla_actual
      return @tablero.get_casilla(i)
    end
    
    public
    def get_jugador_actual()
      return @jugadores.at(@indice_jugador_actual)
    end
    
    public
    def hipotecar(ip)
      return @jugadores.at(@indice_jugador_actual).hipotecar(ip)
      
    end
    
    public
    def info_jugador_texto()
       @jugadores.at(@indice_jugador_actual).to_string
    end
    
    private
    def inicializar_mazo_sorpresas(tablero)
      @mazo = Mazo_Sorpresas.new
      @mazo.al_mazo(Sorpresa.new(Tipo_Sorpresas::IRCARCEL, tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IRCASILLA,4,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IRCASILLA,5,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IRCASILLA,10,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIRCARCEL,@mazo))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresas::PORJUGADOR,tablero,-50,"El jugador debe pagar a cada uno de los demas jugadores 50€"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PORJUGADOR,tablero,50,"Cada jugador te debe pagar 50€"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PORCASAHOTEL,tablero,30,"Recibes 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PORCASAHOTEL,tablero,-30,"Cobras 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PAGARCOBRAR,tablero,-100,"Pagas 100€ por gastos de limpieza"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PAGARCOBRAR,tablero,100,"Has ganado un premio al hotel más limpio recibe 100€"))

    end
    
    private
    def inicializar_tablero(mazo)
        
        @tablero = Tablero.new(4);#la carcel

        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Ronda de Valencia",10, 0.5,25,50,20)))
 
        @tablero.añade_casilla(Casilla.casilla_cantidad(0, "Impuesto"));
        
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Lavapies",10,0.5,25,50,20)));
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Cuatro Caminos",20,0.6,30,70,40)));
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Reina Victoria",20,0.6,30,70,40)));
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Bravo Murillo",30,0.7,35,90,60)));
        
        @tablero.añade_casilla(Casilla.casilla_mazo(mazo, "Sorpresa"));
        
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Alberto Aguilera",40,0.7,35,90,80)));
        
        @tablero.añade_casilla(Casilla.new("Parking"));
        
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Fuencarral",40,0.8,40,110,80)));
        
        @tablero.añade_casilla(Casilla.casilla_mazo(mazo, "Sorpresa"));
        
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Felipe II",50,0.8,40,110,100)));
        @tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Velázquez",50,0.8,45,130,100)));
        
        @tablero.añade_juez;
        
        tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Puerta del Sol",70,0.8,45,160,100)));
        tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Alcalá",70,0.8,50,160,100)));
        
        tablero.añade_casilla(Casilla.casilla_mazo(mazo, "Sorpresa"));
        
        tablero.añade_casilla(Casilla.casilla_titulo(Titulo_Propiedad.new("Paseo del Prado",100,0.8,60,250,120)));
        
    end
    
    private
    def pasar_turno()
      @indice_jugador_actual = (@indice_jugador_actual + 1) % @jugadores.length
    end
    
    private
    def ranking()
      ranking = Array.new
      aux = Jugador.new("aux")
      ranking = @jugadores
      i = 0
      j = 1
      while(j<ranking.length)
        if(0>ranking.at(i).compare_to(ranking.at(j)))
          aux = ranking.at(i)
          ranking.insert(i, ranking.at(j))
          ranking.delete_at(j)
          ranking.insert(j,aux)
          ranking.delete_at(j+1)
          i = i + 1
          j = j + 1
        end
      end
      
      return ranking
    end
    
    public
    def salir_carcel_pagando()
      return @jugadores.at(@indice_jugador_actual).salir_carcel_pagando
    end
    
    public
    def salir_carcel_tirando()
      return @jugadores.at(@indice_jugador_actual).salir_carcel_tirando
    end
    
    public
    def siguiente_paso()
      
    end
    
    public
    def siguiente_paso_completado(operacion)
      @estado = @gestor_estados.siguiente_estado(@jugadores.at(@indice_jugador_actual), @estado, operacion)
    end
    
    public
    def vender(ip)
      return @jugadores.at(@indice_jugador_actual).vender(ip)
    end
    
  end
end
