#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Mazo_Sorpresas
    
    
    attr_reader :usadas, :ultimaSorpesa
    
    @degb = false

    private
    def init()
    
      @sorpresas = Array.new()
      @cartas_especiales = Array.new()
      @barajada = false
      @usadas = 0
    
    end
    
    def initialize_with_arguments(debg)
      
      @debug = debg
      init()
      
      if(@debug)
        Diario.instance.ocurre_evento("Modo debug activado")
      end
      
    end

    def initialize() 
    
      init()
      @debug = false
    
    end
    
    public  #Puesto a public para poder probar el main de la clase Tablero
    def al_mazo(s)
    
      if(!@barajada)
    
        @sorpresas.push(s)
      end
    
    end
    
    def siguiente()
    
      if(!@barajada||@usadas == @sorpresas.length())
        if(!@debug)
          @barajada=true
          @usadas = 0 
        end
      end
      
      @usadas = @usadas+1
      @ultimaSorpesa = @sorpresas[0]
      @sorpresas.delete_at(0)
      @sorpresas.push(@ultimaSorpresa)
      
      return @ultimaSorpresa
    
    end
    
    def inhabilitar_carta_especial(sorpresa)
    
      @var = 0
      
      while @var < @sorpresas.length()
        if(sorpresa == @sorpresas[@var])
          @cartas_especiales.push(sorpresa)
          @sorpresas.delete_at(@var)
          Diario.instance.ocurre_evento("Se ha inhabilitado una carte especial")
        end
        @var = @var + 1
      end
    
    end
    
    def habilitar_carta_especial(sorpresa)
    
      @var = 0
      
      while @var < @cartas_especiales.length()
        if(sorpresa == @cartas_especiales[@var])
            @cartas_especiales.delete_at(@var)
            @sorpresas.push(sorpresa)
            Diario.instance.ocurre_evento("Se ha habilitado esta sorpresa")
        end
        @var = @var + 1
      end   
    end
  end
end
