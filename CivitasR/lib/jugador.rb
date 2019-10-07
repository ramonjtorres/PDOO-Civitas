#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador
    
    include Comparable
    
    attr_reader :casas_max, :hoteles_max, :casas_por_hotel, :nombre, :num_casilla_actual, :precio_libertad, :paso_por_salida, :propiedades, :puede_comprar, :saldo
    
    @@casas_max = 4
    @@hoteles_max = 4
    @@casas_por_hotel = 4
    @@paso_por_salida = 1000
    @@precio_libertad = 200
    @@saldo_inicial = 7500
    
    public
    def initialize(nombre)
    
      @encarcelado = false
      @num_casilla_actual = 0
      @puede_comprar = true
      @saldo = @@saldo_inicial
      @nombre = nombre
      @propiedades = Array.new
      
    end
    
    protected
    def jugador(otro)
      
      @nombre = otro.nombre
      @num_casilla_actual = otro.numCasillaActual
      @encarcelado = otro.encarcelado
      @propiedades = otro.propiedades
      @puede_pomprar = otro.puedeComprar
      @salvoconducto = otro.salvoconducto
      @saldo = otro.saldo
      
    end
    
    public
    def cancelar_hipoteca(ip)
      
    end
    
    public
    def cantidad_casas_hoteles()
      
      cantidad = 0
      
      @propiedades.each do |p|
        
            cantidad += p.cantidad_casas_hoteles();
      end
      
      return cantidad
      
    end
    
    public
    def compare_to(otro)
      
      return (@saldo <=> otro.saldo)
    end
    
    public
    def comprar(titulo)
      
    end
    
    public
    def construir_casa(ip)
      
    end
    
    public
    def construir_hotel(ip)
      
    end
    
    protected
    def debe_ser_encarcelado()
      if(@encarcelado)
        return false
      elsif(!tieneSalvoconducto())
        return true
      else
        perderSalvoConducto()
        Diario.ocurre_event("El jugador se libra de la carcel")
        return false
      end
      
    end
    
    public
    def en_bancarrota()
      
      return @saldo < 0      
    end
    
    public
    def encarcelar(num_casilla_carcel)
      if(debe_ser_encarcelado())
        mover_a_casilla(num_casilla_carcel)
        @encarcelado = true
        Diario.ocurre_evento("El jugador ha sido encarcelado")
      end
      return @encarcelado
      
    end
    
    private
    def existe_la_propiedad(ip)
      
      return ip < @propiedades.length()
      
    end
    
    public
    def hipotecar(ip)
      
    end
    
    public
    def is_encarcelado()
      
      return @encarcelado
    end
    
    public
    def modificar_saldo(cantidad)
      @saldo = @saldo + cantidad
      Diario.ocurre_evento("Se ha modificado el saldo ahora tienes "+@saldo)
      return true
      
    end
    
    public
    def mover_a_casilla(num_casilla)
      if(@encarcelado)
        return false
      else
        @num_casilla_actual = num_casilla
        @puede_comprar = false
        Diario.ocurre_evento("El jugador a sido movido de casilla a la numero "+ @num_casilla_actual)
        return true
      end
      
    end
    
    public
    def obtener_salvoconducto(sorpresa)
      if(@encarcelado)
        return false
      end
      @salvoconducto = sorpresa
      return true
    end
    
    public
    def paga(cantidad)
      modificarSaldo(-1*cantidad)
    end
    
    public
    def paga_alquiler(cantidad)
      if(@encarcelado)
        return false
      else
        return paga(cantidad)
      end
    end
    
    public
    def paga_impuesto(cantidad)
      if(@encarcelado)
        return false
      else
        return paga(cantidad)
      end
    end
    
    public
    def pasa_por_salida()
      modificar_saldo(1000)
      Diario.ocurre_evento("El jugador ha pasado por salida")
      return true
      
    end
    
    private
    def perder_salvoconducto
      @salvoconducto.usada
      @salvoconducto = nil
      
    end
    
    public
    def puede_comprar_casilla()
      if(@encarcelado)
        @puede_comprar = false
      else
        @puede_comprar = true
      end
      return @puede_comprar
      
    end
    
    private
    def puede_salir_carcel_pagando()
      return @saldo>get_precio_libertad()
      
    end
    
    private
    def puedo_edificar_casa(propiedad)
      
      if(@propiedades.include?(propiedad) && @saldo >= propiedad.precio_edificar && propiedad.num_casas < 4)
       
        return true
        
      else
        
        return false
        
      end
      
    end
    
    private
    def puedo_edificar_hotel(propiedad)
      
      if(@propiedades.include?(propiedad) && @saldo >= propiedad.precio_edificar && propiedad.num_casas == 4 && propiedad.num_hoteles < 4)
       
        return true
        
      else
        
        return false
        
      end
      
    end
    
    private
    def puedo_gastar(precio)
        if(@encarcelado)
          return false
        else
          return (@saldo>precio)
        end
      
    end
    
    public
    def recibe(cantidad)
      if(@encarcelado)
        return false
      else
        return modificar_saldo(cantidad)
      end
      
    end
    
    public
    def salir_carcel_pagando()
      if(@encarcelado)
        paga(get_precio_libertad())
        @encarcelado = false
        Diario.ocurre_evento("El jugado ha salido de la carcel pagando")
      end
      
    end
    
    public
    def salir_carcel_tirando()
      if(Dado.salgo_de_la_carcel())
        @encarcelado = false
        Diario.ocurre_evento("El jugador ha salido de la carcel tirando el dado")
        return true
      end
    end
    
    public
    def tiene_algo_que_gestionar()
      if(@propiedades.empty?)
        return flase
      end
      return true
      
    end
    
    public
    def tiene_salvoconducto()
      return (@salvoconducto != nil)
    end
    
    public
    def vender(ip)
      if(@encarcelado)
        return false
      else
        if(existe_la_propiedad())
          if(@propiedades.at(ip).vender(self))
            @propiedades.delete(ip)
            Diario.ocurre_evento("Se ha vendido la propiedad: "+@propiedades.at(ip).to_string())
            return true
          end
        end
      end
      
    end
    
    public
    def to_string()
      
      s = "No"
        
        if(tiene_salvoconducto())
        
            s = "Sí"
        end
        
        return "Nombre: " + @nombre +
               "Saldo: " + @saldo +
               "Casilla Actual: " + @num_casilla_actual +
               "Salvoconducto: " + s +
               "Encarcelado: " + @encarcelado +
               "Puede comprar: " + @puede_comprar +
               "Propiedades: " + "\n" +
                @propiedades.to_string()
      
    end
    
  end
end
