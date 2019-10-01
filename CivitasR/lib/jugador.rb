#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador
    
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
      
    end
    
    public
    def cancelar_hipoteca(ip)
      
    end
    
    public
    def cantidad_casas_hoteles()
      
    end
    
    public
    def compare_to(otro)
      
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
      
    end
    
    public
    def en_bancarrota()
      
    end
    
    public
    def encarcelar(num_casilla_carcel)
      
    end
    
    private
    def existe_la_propiedad(ip)
      
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
      
    end
    
    public
    def mover_a_casilla(num_casilla)
      
    end
    
    public
    def obtener_salvoconducto(sorpresa)
      
    end
    
    public
    def paga(cantidad)
      
    end
    
    public
    def paga_alquiler(cantidad)
      
    end
    
    public
    def paga_impuesto(cantidad)
      
    end
    
    public
    def pasa_por_salida()
      
    end
    
    private
    def perder_salvoconducto()
      
    end
    
    public
    def puede_comprar_casilla()
      
    end
    
    private
    def puede_salir_carcel_pagando()
      
    end
    
    private
    def puedo_edificar_casa(propiedad)
      
    end
    
    private
    def puedo_edificar_hotel(propiedad)
      
    end
    
    private
    def puedo_gastar(precio)
      
    end
    
    public
    def recibe(cantidad)
      
    end
    
    public
    def salir_carcel_pagando()
      
    end
    
    public
    def salir_carcel_tirando()
      
    end
    
    public
    def tiene_algo_que_gestionar()
      
    end
    
    public
    def tiene_salvoconducto()
      
    end
    
    public
    def vender(ip)
      
    end
    
    public
    def to_string()
      
    end
    
  end
end
