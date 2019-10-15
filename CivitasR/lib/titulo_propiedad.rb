#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Titulo_Propiedad
    
    attr_reader :hipotecado, :nombre, :num_casas, :num_hoteles, :precio_compra, :precio_edificar, :propietario
    
    @@factor_intereses_hipoteca = 1.1
    
    def initialize(nom, ab, fr, hb, pc, pe)
      
      @nombre = nom
      @alquiler_base = ab
      @factor_revalorizacion = fr
      @hipoteca_base = hb
      @precio_compra = pc
      @precio_edificar = pe
      @num_casas = 0
      @num_hoteles = 0
      @hipotecado = false
      @propietario = nil
      
    end
    
    public
    def actualizar_propietario_por_conversion(jugador)
      
      @propietario = jugador
    end
    
    public
    def cancelar_hipoteca(jugador)
      
      if(@hipotecado && es_este_el_propietario(jugador))
            
        cantidad_recibida = get_importe_hipoteca()
            
        jugador.paga((cantidad_recibida + 0.1*cantidad_recibida))
        @hipotecado = false
            
        return true
      else
        
        return false
      end
      
    end
    
    public
    def cantidad_casas_hoteles()
      
      return @num_casas + @num_hoteles
    end
    
    public
    def comprar(jugador)
      
      if(tiene_propietario())
        
        return false
      
      else         
        actualizar_propietario_por_conversion(jugador)
        jugador.paga(@precio_compra)
        return true
      end
      
    end
    
    public
    def construir_casa(jugador)
      
      construida = false
        
      if(es_este_el_propietario(jugador))
        
        @propietario.paga(@precio_edificar)
        @num_casas+=1
        construida = true
      end
        
      return construida
    end
    
    public
    def construir_hotel(jugador)
      
      construida = false
        
      if(es_este_el_propietario(jugador))
        
        @propietario.paga(@precio_edificar)
        @num_casas-=4
        @num_hoteles+=1
        construida = true
      end
        
      return construida
    end
    
    public
    def derruir_casas(n, jugador)
      
      if(es_este_el_propietario(jugador) && @num_casas >= n)
        
        @num_casas -= n
            
        return true
      else
        
        return false
      end
    end
    
    private
    def es_este_el_propietario(jugador)
      
      return @propietario == jugador      
    end
    
    public
    def get_importe_cancelar_hipoteca()
      return @factor_intereses_hipoteca*@hipoteca_base
    end
    
    private
    def get_importe_hipoteca()
      return @hipoteca_base*(1+(@num_casas*0.5)+(@numhoteles*2.5))
      
    end
    
    private
    def get_precio_alquiler()
      
      if(propietario_encarcelado() || @hipotecado)
     
        return 0
      else
        
        return @alquiler_base * (1+(@num_casas*0.5)+(@numhoteles*2.5))
      end
    end
    
    private
    def get_precio_venta()
      
      return @precio_compra + (@precio_edificar * cantidad_casas_hoteles()) * @factor_revalorizacion
    end
    
    public
    def hipotecar(jugador)
      
      if(!@hipotecado && es_este_el_propietario(jugador))
        
        @propietario.recibe(get_importe_hipoteca())
        @hipotecado = true
           
        return true
      else
        
        return false
      end
    end
    
    private
    def propietario_encarcelado()
      
      if(!tiene_propietario() || !@propietario.is_encarcelado())
        
        return false
        
      else
        
        return true
      end
    end
    
    public
    def tiene_propietario()
      if(@propietario == nil)
        return false
      else
        return true
      end
    end
    
    public
    def tramitar_alquiler(jugador)
      
      if(@propietario != nil && es_este_el_propietario(jugador))
        
            jugador.paga_alquiler(get_precio_alquiler())
            @propietario.recibe(get_precio_alquiler())
            
      end
    end
    
    public
    def vender(jugador)
      
      if(!tiene_propietario())
        
        return false
        
      else
            
        jugador.recibe(get_precio_venta())
        actualiza_propietario_por_conversion(nil)
        @num_casas = 0
        @num_hoteles = 0
        return true
      end
    end
    
    public
    def to_s
      
      h = "No";
        
      if(@hipotecado)
        
        h = "Sí"
      end
        
      p = "Sin Propietario"
                
      if(@propietario != nil)
            
        p = @propietario
      end     
        
      return "| Nombre: " + @nombre +
             "\n|  Propietario: " + p.to_s +
             "\n|  Numero Casas: " + @num_casas.to_s +
             "\n|  Numero Hoteles: " + @num_hoteles.to_s +
             "\n|  Precio Compra: " + @precio_compra.to_s +
             "\n|  Precio Alquiler Base: " + @alquiler_base.to_s +
             "\n|  Precio Edificar: " + @precio_edificar.to_s +
             "\n|  Precio Hipoteca Base: " + @hipoteca_base.to_s +
             "\n|  Factor Intereses Hipoteca: " + @factor_intereses_hipoteca.to_s +
             "\n|  Factor Revalorización: " + @factor_revalorizacion.to_s +
             "\n|  Hipotecado: " + h.to_s;
    end
    
  end
    
end
