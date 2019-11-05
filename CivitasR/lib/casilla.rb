#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_casilla"

module Civitas
  class Casilla
    
    attr_reader :nombre, :titulo, :importe, :mazo
    
    def initialize(nombre, titulo, cantidad, num_casilla_carcel, mazo_sorpresas, tipo)
      
      @nombre = nombre
      @tituloPropiedad = titulo
      @importe = cantidad
      @@carcel = num_casilla_carcel
      @mazo = mazo_sorpresas
      @tipo = tipo
      
    end
    
    def self.casilla_descanso(nombre)
      
      new(nombre, nil, nil, nil, nil, Tipo_Casilla::DESCANSO)
    end
    
    def self.casilla_titulo(nombre, titulo)
      
      new(nombre, titulo, nil, nil, nil, Tipo_Casilla::CALLE)
      
    end
    
    def self.casilla_cantidad(cantidad, nombre)
      
      new(nombre, nil, cantidad, nil, nil, Tipo_Casilla::IMPUESTO)
      
    end
    
    def self.casilla_carcel(num_casilla_carcel, nombre)
      
      new(nombre, nil, nil, num_casilla_carcel, nil, Tipo_Casilla::JUEZ)
      
    end
    
    def self.casilla_mazo(mazo_sorpresas, nombre)
      
      new(nombre, nil, nil, nil, mazo_sorpresas, Tipo_Casilla::SORPRESA)
      
    end
    
    private
    def informe(iactual, todos)
      Diario.instance.ocurre_evento(todos.at(iactual).nombre + to_s)
    end
      
    public
    def jugador_correcto(iactual, todos)
      return(iactual>=0 && iactual<=todos.length())
    end
    
    def recibe_jugador(iactual, todos)
     
        if(@tipo == Tipo_Casilla::CALLE)
          recibe_jugador_calle(iactual,todos)
        elsif(@tipo == Tipo_Casilla::IMPUESTO)
          recibe_jugador_impuesto(iactual,todos)
        elsif(@tipo == Tipo_Casilla::JUEZ)
          recibe_jugador_juez(iactual,todos)
        elsif(@tipo == Tipo_Casilla::SORPRESA)
          recibe_jugador_sorpresa(iactual,todos)
        else
          informe(iactual,todos)
        end
    end
    
    private
    def recibe_jugador_calle(iactual, todos)
      if(jugador_correcto(iactual,todos))
         informe(iactual,todos)
         nuevo = todos.at(iactual)
        
        if(!@tituloPropiedad.tiene_propietario)
          nuevo.puede_comprar_casilla
        else
          @tituloPropiedad.tramitar_alquiler(nuevo)
        end
      end
    end
    
    private
    def recibe_jugador_impuesto(iactual, todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).paga_impuesto(@importe)
      end
    end
    
    private
    def recibe_jugador_juez(iactual, todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos.at(iactual).encarcelar(@@carcel)
      end
      
    end
    
    private
    def recibe_jugador_sorpresa(iactual, todos)
      if(jugador_correcto(iactual,todos))
        @sorpresa = @mazo.siguiente
        informe(iactual,todos)
        sorpresa.aplicar_a_jugador(iactual, todos)
      end
      
    end
    
    public
    def to_s
      casilla="\n"
        if(@nombre!=nil)
          casilla = casilla + "\n  Nombre casilla: "+@nombre.to_s
        end
        if(@@carcel!=-1.0)
            casilla = casilla + "\n  Carcel: " + @@carcel.to_s
        end
        if(@importe!=-1.0)
          casilla = casilla +"\n  Importe: " + @importe.to_s
        end
        if(@tipo!=nil)
            casilla = casilla +"\n  Tipo Casilla: " + @tipo.to_s
        end
        if(@tituloPropiedad!=nil)
            casilla = casilla + @tituloPropiedad.to_s   
        end
        return casilla
    end

  end

end

   
