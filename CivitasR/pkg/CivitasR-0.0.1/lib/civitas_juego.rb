#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "Tipo_Sorpresas"
require_relative "Mazo_Sorpresas"
require_relative "Sorpresa"
require_relative "Tablero"
require_relative "Jugador"

module Civitas
  class Civitas_Juego
    
    @mazo = Mazo_Sorpresas.new
    @tablero = Tablero.new(20)
    @jugadores = Array.new()
    
    public
    def initialize(nombres)
#      var = 0
#      while (var < nombres.length())
#        j = Jugador.new(nombres.at(var))
#        @jugadores.push(j)
#        var = var + 1
#      end
#      
#      @gestorEstados.estado_inicial
#      @indice_jugador_actual = Dado.quien_empieza(@jugadores.length())
#      @mazo = Mazo_Sorpresas.new
#      inicializar_mazo_sorpresas(@tablero)
#      inicializar_tablero(@mazo)
    end
    
    private
    def avanza_jugador()
      
    end
    
    private
    def actualizar_info()
      puts "Estado: " + @estado.to_s + "\nInformación Jugador: " + info_jugador_texto()
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
       @jugadores.at(@indice_jugador_actual).to_s
    end
    
    private
    def inicializar_mazo_sorpresas(tablero)
      @mazo = Mazo_Sorpresas.new
      @mazo.al_mazo(Sorpresa.new(Tipo_Sorpresas::IR_CARCEL, tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,4,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,5,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,10,tablero))
      @mazo.al_mazo(Sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIR_CARCEL,@mazo))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_JUGADOR,tablero,-50,"El jugador debe pagar a cada uno de los demas jugadores 50€"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::POR_JUGADOR,tablero,50,"Cada jugador te debe pagar 50€"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::POR_CASA_HOTEL,tablero,30,"Recibes 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::POR_CASA_HOTEL,tablero,-30,"Cobras 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PAGAR_COBRAR,tablero,-100,"Pagas 100€ por gastos de limpieza"))
      @mazo.al_mazo(Sorpresa.sorpresa_valor(Tipo_Sorpresa::PAGAR_COBRAR,tablero,100,"Has ganado un premio al hotel más limpio recibe 100€"))

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
    
    def main
      
      j1 = "David"
      j2 = "Ramón"
        
      todos = Array.new
        
      todos.push(j1)
      todos.push(j2)
       
      cj = Civitas_Juego.new(todos)
        
      propiedad = Titulo_Propiedad.new("Ronda de Valencia",10, 0.5,25,50,20)
        
      cj.indice_jugador_actual = 1
        
      cj.get_jugador_actual().propiedades.push(propiedad)
        
      propiedad.comprar(cj.get_jugador_actual())
        
      cj.actualizar_info()
        
      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades.get(0).construir_casa(cj.get_jugador_actual()))
      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades.get(0).construir_casa(cj.get_jugador_actual()))
      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades.get(0).construir_casa(cj.get_jugador_actual()))
      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades.get(0).construir_casa(cj.get_jugador_actual()))
      puts("Debe devolver true, al construir un hotel: " + cj.get_jugador_actual().propiedades.get(0).construir_hotel(cj.get_jugador_actual()))
        
      puts("Debe devolver informacion del jugador actual: " + cj.info_jugador_texto())
        
      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
      puts("Debe salir 10:" + cj.tablero.nueva_posicion(0, 10).to_s)
        
      cj.contabilizar_pasos_por_salida(cj.get_jugador_actual())
        
      puts("Debe dar 10350, al pasar 3 veces por salida (7350 + 3*1000): " + cj.get_jugador_actual().saldo.to_s)
        
      puts("Debe dar que estamos en la casilla 0, Salida: " + cj.get_casilla_actual())
        
      puts("Debe dar Ramon: " + cj.get_jugador_actual())
        
      cj.pasar_turno()
        
      puts("Debe dar David: " + cj.get_jugador_actual())
        
      puts("Debe dar en primera posicion Ramón, en segunda David: " + cj.ranking())
        
      cj.get_jugador_actual().modificar_saldo(20000)
       
      cj.pasar_turno()
        
      puts("Debe dar en primera posicion David, en segunda Ramón: " + cj.ranking())
        
      cj.siguiente_paso_completado(Operaciones_juego.AVANZAR)
        
      puts("Debe dar DESPUES_AVANZAR: " + cj.estado)
        
      cj.siguiente_paso_completado(Operaciones_juego.COMPRAR)
        
      puts("Debe dar DESPUES_COMPRAR: " + cj.estado)
        
      puts("Debe dar true al vender: " + cj.vender(0))
        
      puts("Debe dar false, no hay fin del juego: " + cj.final_del_juego())
        
      cj.get_jugador_actual().modificar_saldo(-20000)
        
      puts("Debe dar true, hay fin del juego: " + cj.final_del_juego())
    
    end
    
  end
  
  j1 = "David"
  j2 = "Ramón"
        
  todos = Array.new
        
  todos.push(j1)
  todos.push(j2)
  
  Test_civitas = Civitas_Juego.new(todos)
  #Test_civitas.main()
  
end
