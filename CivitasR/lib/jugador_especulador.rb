#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Jugador_especulador < Jugador
    
    @@Factor_especulador = 2
    
    public
    def initialize(otro, fianza)
    
      super(otro)
      @fianza = fianza * 1.0
      
      var = 0
      while(var < @propiedades.length())
        
        self.propiedades.at(i).actualizar_propietario_por_conversion(self)
        var = var + 1
      end
      
    end
    
    public
    def encarcelar(num_casilla_carcel)
      if(debe_ser_encarcelado())
        
        if(self.puedo_gastar(@fianza))
          
          self.paga(@fianza)
          Diario.instance.ocurre_evento("El jugador especulador ha pagado la fianza")
          
        else
          mover_a_casilla(num_casilla_carcel)
          @encarcelado = true

          Diario.instance.ocurre_evento("El jugador especulador ha sido encarcelado")
        end
      end
      
      return @encarcelado
      
    end
    
    public
    def self.get_casas_max
      
      return @@casas_max * @@Factor_especulador
    end
    
    public
    def self.get_hoteles_max
      
      return @@hoteles_max * @@Factor_especulador
    end
    
    public
    def paga_impuesto(cantidad)
      if(@encarcelado || (cantidad/2) < 0)
        return false
      else
        return paga(cantidad/2)
      end
    end
  
    public
    def to_s
      
      return super + "\nFianza: " + @fianza.to_s
      
    end
  end
end
