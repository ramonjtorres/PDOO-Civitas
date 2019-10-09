/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ramonjtorres
 */
public class TestP1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Diario diario = Diario.getInstance();
        
        /* 1 Llama 100 veces al método quienEmpieza() de Dado considerando que hay 4 jugadores, y
        calcula cuantas veces se obtiene cada uno de los valores posibles. Comprueba si se cumplen
        a nivel práctico las probabilidades de cada valor.*/
        
        Dado dado = Dado.getInstance();
        int jugador1=0,jugador2=0,jugador3=0,jugador4=0,jugador=0;
        
        for(int i = 0; i<100; i++){
            
            jugador=dado.quienEmpieza(4);
        
            if(jugador==0)
                jugador1++;
            if(jugador==1)
                jugador2++;
            if(jugador==2)
                jugador3++;
            if(jugador==3)
                jugador4++;
        }
        
        System.out.println("Jugador 1 = "+jugador1);
        System.out.println("Jugador 2 = "+jugador2);
        System.out.println("Jugador 3 = "+jugador3);
        System.out.println("Jugador 4 = "+jugador4);
        
        /* 2 Asegúrate de que funciona el modo debug del dado activando y desactivando ese modo, y
        realizando varias tiradas en cada modo.*/
        
        for(int i = 0; i<20 ; i++)
            System.out.print(dado.tirar());

        dado.setDebug(true);

        for(int i = 0; i<20 ; i++)
            System.out.print(dado.tirar());
            System.out.println();
        
        /* 3 Prueba al menos una vez los métodos getUltimoResultado() y salgoDeLaCarcel() de Dado.*/
        
        System.out.println(dado.getUltimoResultado());
        System.out.println(dado.salgoDeLaCarcel());
        
        /* 4 Muestra al menos un valor de cada tipo enumerado.*/
        
        System.out.println(EstadosJuego.DESPUES_AVANZAR);
        System.out.println(Operaciones_juego.AVANZAR);
        System.out.println(TipoCasilla.CALLE);
        System.out.println(TipoSorpresa.IRCARCEL);
        
        /* 5 Crea un objeto MazoSorpresas y haz las siguientes pruebas: añade dos sorpresas al mazo,
        obtén la siguiente sorpresa en juego, inhabilita y habilita la segunda carta añadida.*/
        
        MazoSorpresas mazo = new MazoSorpresas();
        Sorpresa sorpresa1 = new Sorpresa(TipoSorpresa.IRCARCEL, new Tablero(3));
        Sorpresa sorpresa2 = new Sorpresa(TipoSorpresa.IRCASILLA, new Tablero(3));
        mazo.alMazo(sorpresa1);
        diario.ocurreEvento("añadimos sorpresa 1");
        mazo.alMazo(sorpresa2);
        diario.ocurreEvento("añadimos sorpresa 2");
        Sorpresa sorpresa3 = mazo.siguiente();
        diario.ocurreEvento("cargamos siguiente sorpresa");
        mazo.inhabilitarCartaEspecial(sorpresa2);
        diario.ocurreEvento("inhabiltamos carta especial sorpresa2");
        mazo.habilitarCartaEspecial(sorpresa2);
        diario.ocurreEvento("habilitamos carta especial sorpresa2");
        
        /* 6 Dado que MazoSorpresas usa la clase Diario, aprovecha y prueba todos los métodos de Diario*/
        
        System.out.println(diario.eventosPendientes());
        System.out.println(diario.leerEvento());
        
        /* 7 Crea un tablero, añádele varias casillas y comprueba con el depurador que efectivamente la
            estructura del mismo es la que esperabas. Intenta provocar las situaciones erróneas
            controladas en la clase Tablero (por ejemplo, que la posición de la cárcel sea mayor que el
            tamaño del tablero) y comprueba que la gestión de las mismas es la correcta. Finalmente,
            realiza distintas tiradas con el dado y asegúrate de que se calcula correctamente la posición
            de destino en el tablero.*/
        
        Tablero tablero = new Tablero(3);
        Casilla casilla1 = new Casilla("casilla1");
        Casilla casilla2 = new Casilla("casilla2");
        tablero.añadeCasilla(casilla1);
        tablero.añadeCasilla(casilla2);
        tablero.añadeJuez();
        
        System.out.println(tablero.nuevaPosicion(3, 4));
        
    }
}
