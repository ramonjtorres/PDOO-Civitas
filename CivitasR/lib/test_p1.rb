# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "Dado"
require_relative "Mazo_Sorpresas"
require_relative "Sorpresa"

module Civitas
  class Test_P1
    def main()
      #1
      d = Dado.instance
      puts d.quien_empieza(4)
      ms = Mazo_Sorpresas.new
      s = Sorpresa.new
      puts ms.alMazo(s)
    end  
  end
    P1 = Test_P1.new
    P1.main()
end
