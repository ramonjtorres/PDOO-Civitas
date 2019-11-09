#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador
    include Comparable
    
    attr_reader :encarcelado, :nombre, :num_casilla_actual, :propiedades, :puede_comprar, :saldo
    
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
      @salvoconducto = nil
      
    end
    
    protected
    def self.jugador(otro)
      
      @nombre = otro.nombre
      @num_casilla_actual = otro.numCasillaActual
      @encarcelado = otro.encarcelado
      @propiedades = otro.propiedades
      @puede_pomprar = otro.puedeComprar
      @salvoconducto = otro.salvoconducto
      @saldo = otro.saldo
      
    end
    public
    def self.get_casas_max
      
      return @@casas_max
    end
    
    public
    def self.get_hoteles_max
      
      return @@hoteles_max
    end
    
    public
    def self.get_casas_por_hotel
      
      return @@casas_por_hotel
    end
    
    public
    def self.get_paso_por_salida
      
      return @@paso_por_salida
    end
    
    public
    def self.get_precio_libertad
      
      return @@precio_libertad
    end
    
    public
    def cancelar_hipoteca(ip)
      
      result = false
        
      if(@encarcelado)

          return result
      end

      if(existe_la_propiedad(ip))

          propiedad = @propiedades.at(ip)
          cantidad = propiedad.get_importe_cancelar_hipoteca()
          puedogastar = puedo_gastar(cantidad)

          if(puedogastar)

              result = propiedad.cancelar_hipoteca(self)

              if(result)

                  Diario.instance.ocurre_evento("El jugador " + @nombre + " cancela la hipoteca de la propiedad " + ip.to_s)
              end
          end
      end

      return result
      
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
    def <=>(otro)
      
      return (otro.saldo <=> @saldo)
    end
    
    public
    def comprar(titulo)
      
      result = false

      if(@encarcelado)

          return result
      end

      if(@puede_comprar)

          precio = titulo.precio_compra

          if(puedo_gastar(precio))

              result = titulo.comprar(self)

              if(result)

                  @propiedades.push(titulo)
                  Diario.instance.ocurre_evento("El jugador " + self.nombre + " compra la propiedad " + titulo.to_s)
              end
          end
      end

      return result
    end
    
    public
    def construir_casa(ip)
      result = false
      puedo_edificar_casa = false
      precio = 0
      if(@encarcelado)
        return result
      end
      
      if(existe_la_propiedad(ip))
        
        propiedad = @propiedades.at(ip)
        result = puedo_edificar_casa(propiedad)
        precio = propiedad.precio_edificar
        
        if((puedo_gastar(precio)) && (propiedad.num_casas < @@casas_max))
          puedo_edificar_casa = true
        end
        
        if(puedo_edificar_casa)
        
          result = propiedad.construir_casa(self)
          Diario.instance.ocurre_evento("El jugador "+ @nombre + " construye casa en la propiedad "+ ip.to_s)    
        end
      end
      
      return result;
    end
    
    public
    def construir_hotel(ip)
      
      result = false
        
      if(@encarcelado)
        
        return result
      end
        
        if(existe_la_propiedad(ip))
        
            propiedad = @propiedades.at(ip)
            puedoEdificarHotel = puedo_edificar_hotel(propiedad)
            precio = propiedad.precio_edificar
            
            if(puedoEdificarHotel)
            
                result = propiedad.construir_hotel(self)
                casasPorHotel = Jugador.get_casas_por_hotel()
                
                propiedad.derruir_casas(casasPorHotel, self)
                
                Diario.instance.ocurre_evento("El jugador " + @nombre + " construye hotel en la propiedad " + ip.to_s)
            end
            
        end
        
        return result
      
    end
    
    protected
    def debe_ser_encarcelado()
      if(@encarcelado)
        return false
      elsif(!tiene_salvoconducto())
        return true
      else
        perder_salvoconducto()
        Diario.instance.ocurre_evento("El jugador se libra de la carcel")
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
        
        Diario.instance.ocurre_evento("El jugador ha sido encarcelado")
      end
      return @encarcelado
      
    end
    
    private
    def existe_la_propiedad(ip)
      
      return ip < @propiedades.length()
      
    end
    
    public
    def hipotecar(ip)
      
      result = false

      if(@encarcelado)

          return result
      end

      if(existe_la_propiedad(ip))

          propiedad = @propiedades.at(ip)
          result = propiedad.hipotecar(self)

          if(result)

              Diario.instance.ocurre_evento("El jugador " + @nombre + " hipoteca la propiedad " + ip.to_s)
          end
      end

      return result
    end
    
    public
    def is_encarcelado()
      
      return @encarcelado
    end
    
    public
    def modificar_saldo(cantidad)
      
      diario = Diario.instance
      
      @saldo = @saldo + cantidad
      diario.ocurre_evento("Se ha modificado el saldo ahora tienes "+@saldo.to_s)
      return true
      
    end
    
    public
    def mover_a_casilla(num_casilla)
      if(@encarcelado)
        return false
      else
        diario = Diario.instance
        
        @num_casilla_actual = num_casilla
        @puede_comprar = false
        diario.ocurre_evento("El jugador ha sido movido de casilla a la numero "+ @num_casilla_actual.to_s)
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
      modificar_saldo(-1*cantidad)
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
      if(@encarcelado || cantidad == -1)
        return false
      else
        return paga(cantidad)
      end
    end
    
    public
    def pasa_por_salida()
      modificar_saldo(1000)
      Diario.instance.ocurre_evento("El jugador ha pasado por salida")
      return true
      
    end
    
    private
    def perder_salvoconducto()
      
      @salvoconducto.usada()
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
        paga(@@precio_libertad)
        @encarcelado = false
        Diario.instance.ocurre_evento("El jugado ha salido de la carcel pagando")
      end
      
    end
    
    public
    def salir_carcel_tirando()
      if(Dado.instance.salgo_de_la_carcel())
        @encarcelado = false
        Diario.instance.ocurre_evento("El jugador ha salido de la carcel tirando el dado")
        return true
      end
    end
    
    public
    def tiene_algo_que_gestionar()
      if(@propiedades.empty?)
        return false
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
        if(existe_la_propiedad(ip))
          if(@propiedades.at(ip).vender(self))
            @propiedades.delete(ip)
            Diario.instance.ocurre_evento("Se ha vendido la propiedad: "+@propiedades.at(ip).to_s)
            return true
          end
        end
      end
      
    end
    
    public
    def to_s()
      
      s = "No"
        
      if(tiene_salvoconducto())
        
        s = "Sí"
      end
        
      return "Nombre: " + @nombre.to_s +
             "\nSaldo: " + @saldo.to_s +
             "\nCasilla Actual: " + @num_casilla_actual.to_s +
             "\nSalvoconducto: " + s +
             "\nEncarcelado: " + @encarcelado.to_s +
             "\nPuede comprar: " + @puede_comprar.to_s +
             "\nPropiedades: " + "\n" +
             @propiedades.to_s
      
    end
 
  end
  
end
