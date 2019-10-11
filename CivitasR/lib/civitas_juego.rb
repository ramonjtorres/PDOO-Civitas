#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "Tipo_Sorpresas"
require_relative "Mazo_Sorpresas"
require_relative "Sorpresa"
require_relative "Tablero"
require_relative "Jugador"
require_relative "Titulo_Propiedad"
require_relative "Gestor_estados"
require_relative "Estados_juego"
require_relative "Dado"

module Civitas
  class Civitas_Juego
    
    attr_writer :indice_jugador_actual #Para probar el main
    attr_reader :tablero, :estado #Para probar el main
    
    @mazo = Mazo_Sorpresas.new
    @tablero = Tablero.new(20)
    
    public
    def initialize(nombres)
      
      @jugadores = Array.new
      var = 0
      while (var < nombres.length())
        j = Jugador.new(nombres.at(var))
        @jugadores.push(j)
        var = var + 1
      end
      
      @gestor_estados = Gestor_estados.new
      @estado = @gestor_estados.estado_inicial
      @indice_jugador_actual = Dado.instance.quien_empieza(@jugadores.length())
      @mazo = Mazo_Sorpresas.new
      inicializar_mazo_sorpresas(@tablero)
      inicializar_tablero(@mazo)
    end
    
    private
    def avanza_jugador()
      
    end
    
    public #Puesto a public para probar el main
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
    
    public #Puesto en public para probar el main
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
      i = @jugadores.at(@indice_jugador_actual).num_casilla_actual
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
      
      sorpresa = Sorpresa.new(Tipo_Sorpresas::SALIR_CARCEL, Tablero.new(20))
      
      @mazo.al_mazo(Sorpresa.new(Tipo_Sorpresas::IR_CARCEL, tablero))
      @mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,4,tablero))
      @mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,5,tablero))
      @mazo.al_mazo(sorpresa.sorpresa_tablero(Tipo_Sorpresas::IR_CASILLA,10,tablero))
      @mazo.al_mazo(sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIR_CARCEL,@mazo))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_JUGADOR,tablero,-50,"El jugador debe pagar a cada uno de los demas jugadores 50€"))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_JUGADOR,tablero,50,"Cada jugador te debe pagar 50€"))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_CASA_HOTEL,tablero,30,"Recibes 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::POR_CASA_HOTEL,tablero,-30,"Cobras 30€ por cada casa y hotel en propiedad"))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::PAGAR_COBRAR,tablero,-100,"Pagas 100€ por gastos de limpieza"))
      @mazo.al_mazo(sorpresa.sorpresa_valor(Tipo_Sorpresas::PAGAR_COBRAR,tablero,100,"Has ganado un premio al hotel más limpio recibe 100€"))

    end
    
    private
    def inicializar_tablero(mazo)
        
        @tablero = Tablero.new(4);#la carcel
        
        casilla = Casilla.new("Auxiliar")

        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Ronda de Valencia",10, 0.5,25,50,20)))
 
        @tablero.añade_casilla(casilla.casilla_cantidad(0, "Impuesto"));
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Lavapies",10,0.5,25,50,20)));
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Cuatro Caminos",20,0.6,30,70,40)));
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Reina Victoria",20,0.6,30,70,40)));
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Bravo Murillo",30,0.7,35,90,60)));
        
        @tablero.añade_casilla(casilla.casilla_mazo(mazo, "Sorpresa"));
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Alberto Aguilera",40,0.7,35,90,80)));
        
        @tablero.añade_casilla(Casilla.new("Parking"));
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Fuencarral",40,0.8,40,110,80)));
        
        @tablero.añade_casilla(casilla.casilla_mazo(mazo, "Sorpresa"));
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Felipe II",50,0.8,40,110,100)));
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Velázquez",50,0.8,45,130,100)));
        
        @tablero.añade_juez;
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Puerta del Sol",70,0.8,45,160,100)));
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Alcalá",70,0.8,50,160,100)));
        
        @tablero.añade_casilla(casilla.casilla_mazo(mazo, "Sorpresa"));
        
        @tablero.añade_casilla(casilla.casilla_titulo(Titulo_Propiedad.new("Paseo del Prado",100,0.8,60,250,120)));
        
    end
    
    public #Puesto en public para probar el main
    def pasar_turno()
      @indice_jugador_actual = (@indice_jugador_actual + 1) % @jugadores.length
    end
    
    public #Puesto en public para probar el main
    def ranking()
      ranking = Array.new
      aux = Jugador.new("aux")
      ranking = @jugadores
      i = 1
      
      while(i<ranking.length-1)
        
        if(ranking.at(i-1).compare_to(ranking.at(i)) < 0)
        
          aux = ranking.at(i-1)
          ranking.delete_at(i-1)
          ranking.insert(i, aux)

        end

        i = i + 1
        
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
    
#    def main
#      
#      j1 = "David"
#      j2 = "Ramón"
#        
#      todos = Array.new
#        
#      todos.push(j1)
#      todos.push(j2)
#       
#      cj = Civitas_Juego.new(todos)
#        
#      propiedad = Titulo_Propiedad.new("Lavapies",10,0.5,25,50,20)
#        
#      cj.indice_jugador_actual = 1
#        
#      cj.get_jugador_actual().propiedades.push(propiedad)
#        
#      propiedad.comprar(cj.get_jugador_actual())
#        
#      cj.actualizar_info()
#        
#      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades[0].construir_casa(cj.get_jugador_actual()).to_s)
#      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades[0].construir_casa(cj.get_jugador_actual()).to_s)
#      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades[0].construir_casa(cj.get_jugador_actual()).to_s)
#      puts("Debe devolver true, al construir una casa: " + cj.get_jugador_actual().propiedades[0].construir_casa(cj.get_jugador_actual()).to_s)
#      puts("Debe devolver true, al construir un hotel: " + cj.get_jugador_actual().propiedades[0].construir_hotel(cj.get_jugador_actual()).to_s)
#        
#      puts("Debe devolver informacion del jugador actual: " + cj.info_jugador_texto())
#        
#      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
#      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
#      puts("Debe salir 6:" + cj.tablero.nueva_posicion(20, 6).to_s)
#      puts("Debe salir 10:" + cj.tablero.nueva_posicion(0, 10).to_s)
#        
#      cj.contabilizar_pasos_por_salida(cj.get_jugador_actual())
#        
#      puts("Debe dar 10350, al pasar 3 veces por salida (7350 + 3*1000): " + cj.get_jugador_actual().saldo.to_s)
#        
#      puts("Debe dar que estamos en la casilla 0, Salida: " + cj.get_casilla_actual().to_s)
#        
#      puts("Debe dar Ramon: " + cj.get_jugador_actual().to_s)
#        
#      cj.pasar_turno()
#        
#      puts("Debe dar David: " + cj.get_jugador_actual().to_s)
#        
#      puts("Debe dar en primera posicion Ramón, en segunda David: " + cj.ranking().to_s)
#      
#      cj.get_jugador_actual().modificar_saldo(20000)
#       
#      cj.pasar_turno()
#        
#      puts("Debe dar en primera posicion David, en segunda Ramón: " + cj.ranking().to_s)
#        
#      cj.siguiente_paso_completado(Operaciones_juego::AVANZAR)
#        
#      puts("Debe dar DESPUES_AVANZAR: " + cj.estado.to_s)
#        
#      cj.siguiente_paso_completado(Operaciones_juego::COMPRAR)
#        
#      puts("Debe dar DESPUES_COMPRAR: " + cj.estado.to_s)
#        
#      puts("Debe dar false, no hay fin del juego: " + cj.final_del_juego().to_s)
#        
#      cj.get_jugador_actual().modificar_saldo(-20000)
#        
#      puts("Debe dar true, hay fin del juego: " + cj.final_del_juego().to_s)
#    
#    end
    
  end
  
#  j1 = "David"
#  j2 = "Ramón"
#        
#  todos = Array.new
#        
#  todos.push(j1)
#  todos.push(j2)
#  
#  Test_civitas = Civitas_Juego.new(todos)
#  Test_civitas.main()
  
end
