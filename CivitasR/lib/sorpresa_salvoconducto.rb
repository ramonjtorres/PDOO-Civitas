# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class Sorpresa_salvoconducto
    public
    def initialize(mazo)
      
      @mazo = mazo
      @texto = "Esta sorpresa evita que caigas en la cárcel"
      
    end
    
    public
    def aplicar_a_jugador(actual, todos)
      
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
      
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa SALIR DE LA CÁRCEL al jugador " + todos.at(actual).to_s);
      
    end
    
    public
    def to_s()
      
      return "Tipo: Sorpresa Salvoconducto\n" + @texto

    end

    public_class_method :new
    
  end
end
