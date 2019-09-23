/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ramonjtorres
 */
public class TestP1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Diario di = Diario.getInstance();
        // TODO code application logic here
        /* 1 Llama 100 veces al método quienEmpieza() de Dado considerando que hay 4 jugadores, y
        calcula cuantas veces se obtiene cada uno de los valores posibles. Comprueba si se cumplen
        a nivel práctico las probabilidades de cada valor.*/
        Dado d = Dado.getInstance();
        int a=0,b=0,c=0,e=0,f=0;
        for(int i = 0; i<100; i++){
            f=d.quienEmpieza(4);
            if(f==0)
                a++;
            if(f==1)
                b++;
            if(f==2)
                c++;
            if(f==3)
                e++;
        }
        System.out.println("0 = "+a);
        System.out.println("1 = "+b);
        System.out.println("2 = "+c);
        System.out.println("3 = "+e);
        /* 2 Asegúrate de que funciona el modo debug del dado activando y desactivando ese modo, y
        realizando varias tiradas en cada modo.*/
        for(int i = 0; i<20 ; i++)
            System.out.print(d.tirar());
        d.setDebug(true);
        for(int i = 0; i<20 ; i++)
            System.out.print(d.tirar());
            System.out.println();
        /* 3 Prueba al menos una vez los métodos getUltimoResultado() y salgoDeLaCarcel() de Dado.*/
        int n = 0;
        System.out.println(d.getUltimoResultado());
        System.out.println(d.salgoDeLaCarcel());
        /* 4 Muestra al menos un valor de cada tipo enumerado.*/
        System.out.println(EstadosJuego.DESPUES_AVANZAR);
        System.out.println(Operaciones_juego.AVANZAR);
        System.out.println(TipoCasilla.CALLE);
        System.out.println(TipoSorpresa.IRCARCEL);
        /* 5 Crea un objeto MazoSorpresas y haz las siguientes pruebas: añade dos sorpresas al mazo,
        obtén la siguiente sorpresa en juego, inhabilita y habilita la segunda carta añadida.*/
        MazoSorpresas ms = new MazoSorpresas();
        Sorpresa s1 = new Sorpresa();
        Sorpresa s2 = new Sorpresa();
        ms.alMazo(s1);
        di.ocurreEvento("añadimos sorpresa 1");
        ms.alMazo(s2);
        di.ocurreEvento("añadimos sorpresa 2");
        Sorpresa s3 = ms.siguiente();
        di.ocurreEvento("cargamos siguiente sorpresa");
        ms.inhabilitarCartaEspecial(s2);
        di.ocurreEvento("inhabiltamos carta especial s2");
        ms.habilitarCartaEspecial(s2);
        di.ocurreEvento("habilitamos carta especial s2");
        /* 6 Dado que MazoSorpresas usa la clase Diario, aprovecha y prueba todos los métodos de Diario*/
        System.out.println(di.eventosPendientes());
        System.out.println(di.leerEvento());
        /* 7 Crea un tablero, añádele varias casillas y comprueba con el depurador que efectivamente la
            estructura del mismo es la que esperabas. Intenta provocar las situaciones erróneas
            controladas en la clase Tablero (por ejemplo, que la posición de la cárcel sea mayor que el
            tamaño del tablero) y comprueba que la gestión de las mismas es la correcta. Finalmente,
            realiza distintas tiradas con el dado y asegúrate de que se calcula correctamente la posición
            de destino en el tablero.*/
        Tablero tb = new Tablero(3);
        
    }
}
