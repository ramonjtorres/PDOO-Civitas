#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa
   
    public
    def initialize(tipo, tablero)
      
      init()
      
      @tipo = tipo
      @tablero = tablero
      @mazo = Mazo_Sorpresas.new
      @texto = "Esta sorpresa te lleva a la cárcel"
      
    end
    
    public
    def sorpresa_valor(tipo, tablero, valor, texto)
      
      init()
      
      @tipo = tipo
      @tablero = tablero
      @valor = valor
      @texto = texto
      @mazo = Mazo_Sorpresas.new
      
    end
    
    public
    def sorpresa_tablero(tipo, valor, tablero)
      
      init()
      
      @tipo = tipo
      @tablero = tablero
      @valor = valor
      @mazo = Mazo_Sorpresas.new
      @texto = "Esta sorpresa te lleva a otra casilla"
      
    end
    
    public
    def sorpresa_mazo(tipo, mazo)
      
      init()
      
      @tipo = tipo
      @mazo = mazo
      @texto = "Esta sorpresa evita que caigas en la cárcel"
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
      if(@tipo == Tipo_Sorpresas::IR_CARCEL)
        
        aplicar_a_jugador_ir_carcel(actual, todos)
      
      elsif(@tipo == Tipo_Sorpresas::SALIR_CARCEL)
            
        aplicar_a_jugador_salir_carcel(actual, todos)
       
      elsif(@tipo == Tipo_Sorpresas::PAGAR_COBRAR)
        
        aplicar_a_jugador_pagar_cobrar(actual, todos)
       
      elsif(@tipo == Tipo_Sorpresas::POR_CASA_HOTEL)
        
        aplicar_a_jugador_por_casa_hotel(actual, todos)
       
      elsif(@tipo == Tipo_Sorpresas::POR_JUGADOR)
        
        aplicar_a_jugador_por_jugador(actual, todos)
       
      else
            
        aplicar_a_jugador_ir_a_casilla(actual, todos)
      end
      
    end
    
    private
    def aplicar_a_jugador_ir_a_casilla(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        casilla_actual = todos.find(actual).num_casilla_actual
        tirada = @tablero.calcular_tirada(casilla_actual, @valor)
            
        nuevaPosicion = @tablero.nueva_posicion(casilla_actual, tirada)
        todos.find(actual).mover_a_casilla(nueva_posicion)
        @tablero.getCasilla(@valor).recibe_jugador(actual, todos)
      end     
      
    end
    
    private
    def aplicar_a_jugador_ir_carcel(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        todos.at(actual).encarcelar(@tablero.num_casilla_carcel)
      end
      
    end
    
    private
    def aplicar_a_jugador_pagar_cobrar(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        todos.at(actual).modificar_saldo(@valor)
      end
    end
    
    private
    def aplicar_a_jugador_por_casa_hotel(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        todos.at(actual).modificar_saldo(@valor*todos.at(actual).cantidad_casas_hoteles())
      end
      
    end
    
    private
    def aplicar_a_jugador_por_jugador(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
            
        pagar = Sorpresa.new(Tipo_Sorpresas::PAGAR_COBRAR, @tablero)
        pagar.sorpresa_tablero(Tipo_Sorpresas::PAGAR_COBRAR, @valor*-1, @tablero)    
        i = 0
        while(i<todos.length())
            
          if(i != actual)
            pagar.aplicar_a_jugador(i, todos)
          end
          i= i+1
        end
          cobrar = Sorpresa.new(Tipo_Sorpresas::PAGAR_COBRAR, @tablero)
          cobrar.sorpresa_tablero(Tipo_Sorpresas::PAGAR_COBRAR, @valor*(todos.length()-1), @tablero)
          cobrar.aplicar_a_jugador(actual, todos)
      end
      
    end
    
    private
    def aplicar_a_jugador_salir_carcel(actual, todos)
      
      if(jugador_correcto(actual, todos))
        
        informe(actual, todos)
        i = 0
        tiene = false
                    
        while(i<todos.length())
                
          tiene = todos.at(i).tiene_salvoconducto
          i=i+1
        end
            
        if(!tiene)
            
          todos.at(actual).obtener_salvoconducto(self)
          salir_del_mazo()
        end
      end
      
    end
    
    private
    def informe(actual, todos)
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa " + @tipo.to_s + " al jugador " + todos.find(actual).to_s);
      
    end
    
    private
    def init()
      
      @valor = -1
      @mazo = nil
      @tablero = nil
      
    end
    
    public
    def jugador_correcto(actual, todos)
      
      return (actual < todos.length())
      
    end
    
    public
    def salir_del_mazo()
      
      if(@valor == 1)
        
        @mazo.inhabilitar_carta_especial(self)
      end
      
    end
    
    public
    def usada()
      
      if(@valor == 1)
        
        @mazo.habilitar_carta_especial(self)
      end
      
    end
    
    public
    def to_string()
      
      return @tipo.name()

    end
  

  end

end
