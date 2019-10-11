#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#require_relative "Titulo_Propiedad"
#require_relative "Diario"
#require_relative "Dado"
#require_relative "Mazo_Sorpresas"
#require_relative "Sorpresa"
#require_relative "Tipo_Sorpresas"
#require_relative "Tablero"

module Civitas
  class Jugador
    include Comparable
    
    attr_reader :casas_max, :hoteles_max, :casas_por_hotel, :nombre, :num_casilla_actual, :precio_libertad, :paso_por_salida, :propiedades, :puede_comprar, :saldo
    attr_writer :encarcelado, :puede_comprar, :num_casilla_actual #Puesto para probar el main de jugador
    
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
      elsif(!tiene_salvoconducto())
        return true
      else
        perder_salvoconducto()
        Diario.instance.ocurre_event("El jugador se libra de la carcel")
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
    
    public #se pone a public para probar el main
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
        diario.ocurre_evento("El jugador a sido movido de casilla a la numero "+ @num_casilla_actual.to_s)
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
      if(@encarcelado)
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
    
    public #Puesto a public para probar el main
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
    
    public #puesto en public para probar el main de jugador
    def puedo_edificar_casa(propiedad)
      
      if(@propiedades.include?(propiedad) && @saldo >= propiedad.precio_edificar && propiedad.num_casas < 4)
       
        return true
        
      else
        
        return false
        
      end
      
    end
    
    public #puesto en public para probar el main de jugador
    def puedo_edificar_hotel(propiedad)
      
      if(@propiedades.include?(propiedad) && @saldo >= propiedad.precio_edificar && propiedad.num_casas == 4 && propiedad.num_hoteles < 4)
       
        return true
        
      else
        
        return false
        
      end
      
    end
    
    public #Puesto a public para probar el main
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
    
#    def main
#      
#      jugador = Jugador.new("Ramón")
#      jugador2 = Jugador.new("David")
#      jugador3 = Jugador.new(jugador)
#        
#      puts(jugador3.to_s)
#        
#      puts("No tiene algo que gestionar. Debe dar false: " + jugador.tiene_algo_que_gestionar().to_s)
#        
#      propiedad = Titulo_Propiedad.new("Ronda de Valencia",10, 0.5,25,50,20)
#
#      jugador.propiedades.push(propiedad)
#        
#      propiedad.comprar(jugador)
#        
#      puts("Precio compra: " + propiedad.precio_compra.to_s)
#        
#      puts("Precio edificación: " + propiedad.precio_edificar.to_s)
#        
#      puts("Tiene algo que gestionar. Debe dar true: " + jugador.tiene_algo_que_gestionar().to_s)
#        
#      puts("Debe dar 0, no tiene casas ni hoteles: " + jugador.cantidad_casas_hoteles().to_s)
#        
#      puts("Debe dar true, puede edificar casas: " + jugador.puedo_edificar_casa(propiedad).to_s)
#    
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#        
#      puts("Debe dar false, no puede edificar casas: " + jugador.puedo_edificar_casa(propiedad).to_s)
#        
#      puts("Debe dar 4, tiene 4 casas: " + jugador.cantidad_casas_hoteles().to_s)
#        
#      puts("Debe dar true, puede edificar hoteles: " + jugador.puedo_edificar_hotel(propiedad).to_s)
#        
#      puts("HOTEL CONSTRUIDO: " + jugador.propiedades[0].construir_hotel(jugador).to_s)
#        
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#        
#      puts("Debe dar 2, tiene 1 hotel y 1 casa: " + jugador.cantidad_casas_hoteles().to_s)
#        
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#        
#      puts("HOTEL CONSTRUIDO: " + jugador.propiedades[0].construir_hotel(jugador).to_s)
#        
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s) 
#        
#      puts("HOTEL CONSTRUIDO: " + jugador.propiedades[0].construir_hotel(jugador).to_s)
#        
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s)
#      puts("CASA CONSTRUIDA: " + jugador.propiedades[0].construir_casa(jugador).to_s) 
#        
#      puts("HOTEL CONSTRUIDO: " + jugador.propiedades[0].construir_hotel(jugador).to_s)
#        
#      puts("Debe dar false, no puede edificar hoteles: " + jugador.puedo_edificar_hotel(propiedad).to_s)
#        
#      puts("Debe dar 7050: " + jugador.saldo.to_s)
#        
#      jugador.modificar_saldo(-200)
#        
#      puts("Debe dar 6850: " + jugador.saldo.to_s)
#        
#      puts("Debe dar -1 ya que jugador tiene menos saldo: " + jugador.compare_to(jugador2).to_s)
#      puts("Debe dar 1 ya que jugador2 tiene mas saldo: " + jugador2.compare_to(jugador).to_s)
#        
#      puts("Debe dar false, no tiene salvoconducto: " + jugador.tiene_salvoconducto().to_s)
#        
#      puts("Debe dar true, debe ser encarcelado: " + jugador.debe_ser_encarcelado().to_s)
#        
#      jugador.encarcelado = true;
#        
#      puts("Debe dar true, esta encarcelado: " + jugador.is_encarcelado().to_s)
#        
#      puts("Debe dar false, no puede comprar si esta encarcelado: " + jugador.puede_comprar_casilla().to_s)
#        
#      jugador.encarcelado = false;
#        
#      puts("Debe dar true, no esta encarcelado, puede comprar: " + jugador.puede_comprar_casilla().to_s)
#        
#      puts("Debe dar false, no esta encarcelado: " + jugador.is_encarcelado().to_s)
#        
#      mazo = Mazo_Sorpresas.new
#        
#      sorpresa = Sorpresa.new(Tipo_Sorpresas::SALIR_CARCEL, Tablero.new(20))
#      
#      jugador.obtener_salvoconducto(sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIR_CARCEL , mazo))
#        
#      puts("Debe dar false, no esta en bancarrota: " + jugador.en_bancarrota().to_s)
#        
#      puts("Debe dar true, tiene salvoconducto: " + jugador.tiene_salvoconducto().to_s)
#       
#      puts("Debe dar true, existe la propiedad: " + jugador.existe_la_propiedad(0).to_s)
#      puts("Debe dar false, no existe la propiedad: " + jugador.existe_la_propiedad(5).to_s)
#        
#      puts("Debe dar 4 casas max: " + jugador.casas_max.to_s)
#      puts("Debe dar 4 casas por hotel: " + jugador.casas_por_hotel.to_s)
#      puts("Debe dar 4 hoteles max: " + jugador.hoteles_max.to_s)
#        
#      puts("Debe dar 0, estamos en la salida: " + jugador.num_casilla_actual.to_s)
#        
#      puts("Debe dar 200 de precio de libertad: " + jugador.precio_libertad.to_s)
#        
#      puts("Debe dar 1000 de premio de salida: " + jugador.paso_por_salida.to_s)
#        
#      puts("Debe dar los datos de Ronda de Valencia: " + jugador.propiedades.to_s)
#        
#      puts("Debe dar false, no puede comprar: " + jugador.puede_comprar.to_s)
#        
#      jugador.puede_comprar = true
#        
#      puts("Debe dar true, puede comprar: " + jugador.puede_comprar.to_s)
#        
#      jugador.encarcelado = false
#      jugador.num_casilla_actual = 0
#        
#      puts("Debe dar true, se puede mover a la carcel: " + jugador.mover_a_casilla(4).to_s)
#        
#      jugador.encarcelado = true;
#        
#      puts("Debe dar false, no se puede mover a la carcel: " + jugador.mover_a_casilla(4).to_s)
#        
#      puts("Debe dar true, puede salir pagando: " + jugador.salir_carcel_pagando().to_s)
#        
#      puts("Debe dar true, paga 200: " + jugador.paga(200).to_s)
#        
#      puts("Debe dar true, paga 200 de alquiler: " + jugador.paga_alquiler(200).to_s)
#        
#      puts("Debe dar true, paga 200 de impuesto: " + jugador.paga_impuesto(200).to_s)
#        
#      puts("Debe dar 6050 de saldo: " + jugador.saldo.to_s)
#        
#      puts("Debe dar true al pasar por la salida: " + jugador.pasa_por_salida().to_s)
#        
#      puts("Debe dar 7050 de saldo: " + jugador.saldo.to_s)
#      
#      jugador.obtener_salvoconducto(sorpresa.sorpresa_mazo(Tipo_Sorpresas::SALIR_CARCEL , mazo))
#        
#        
#      puts("Debe dar true, puede gastar 7000: " + jugador.puedo_gastar(7000).to_s)
#       
#      puts("Debe dar true, pierde 7000: " + jugador.modificar_saldo(-7000).to_s)
#        
#      puts("Debe dar false, no puede gastar 7000: " + jugador.puedo_gastar(7000).to_s)
#        
#      puts("Debe dar 50 de saldo: " + jugador.saldo.to_s)
#        
#      puts("Debe dar true al recibir 500: " + jugador.recibe(500).to_s)
#        
#      puts("Debe dar 550 de saldo: " + jugador.saldo.to_s)
#        
#      jugador.encarcelado = false
#        
#      puts("Debe dar true al vender: " + jugador.vender(0).to_s)
#        
#      puts("Debe dar null: " + jugador.propiedades.to_s);
#        
#      puts("Debe dar false al vender: " + jugador.vender(0).to_s)
#        
#      puts("Debe dar los datos del jugador: " + jugador.to_s)
#        
#      puts("Debe dar true al modificar -10000 de saldo: " + jugador.modificar_saldo(-10000).to_s)
#        
#      puts("Debe dar true, esta en bancarrota: " + jugador.en_bancarrota().to_s)
#
#    end
    
  end
  
  #Test_jugador = Jugador.new("Prueba")
  #Test_jugador.main()
  
end
