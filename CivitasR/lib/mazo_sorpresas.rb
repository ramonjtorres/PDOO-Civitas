# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Mazo_Sorpresas
    
    
    attr_reader :usadas
    @degb = false

    
    def init()
      @sorpresas = Array.new()
      @cartasEspeciaes = Array.new()
      @barajada = false
      @usadas = 0
    end
    
    def initialize(debg)
      @debug = debg
      init()
#      if debug se avisa a diario
    end
#no se si esto esta bien ya que hay dos constructores (funciones con el mismo metodo)
    def initialize() 
      init()
      @debug = false
    end
    
    def alMazo(s)
      if(!@barajada)
        @sorpresas.push(s)
      end
    end
    def siguiente()
      if(!@barajada||@usadas ==@sorpresas.length())
        if(!@debug)
          @barajada=true
        end
        @usadas = 0 
      end
      @usadas +1
      @ultimaSorpesa = @sorpresas.gets(0)
      @sorpresas.delete_at(0)
      @sorpresas.push(@ultimaSorpresa)
      return @ultimaSorpresa
    end
    def inhabilitarCartaEspecial(sorpresa)
      @var = 0
      while @var < @sorpresas.length()
        if(sorpresa == @sorpresas.gets(var))
          @cartasEspeciales.push(sorpresa)
          @sorpresas.delete_at(@var)
          #se deja constancia en el diario falta aqui
        end
        @var +1
      end
    end
    def habilitarCartaEspecial(sorpresa)
      @var = 0
      while @var < @cartasEspeciales.length()
        if(sorpresa == @cartasEspeciales.gets(i))
            @cartasEspeciales.deleta_at(@var)
            @sorpresas.push(sorpresa)
            #se deja constancia en el diario que no se como es
        end
        @var +1
      end   
    end
  end
end
