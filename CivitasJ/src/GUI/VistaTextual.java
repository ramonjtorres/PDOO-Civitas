package GUI;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.Operaciones_juego;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.GestionesInmobiliarias;
import civitas.Jugador;
import civitas.SalidasCarcel;
import civitas.TituloPropiedad;

public class VistaTextual{
  
  CivitasJuego juegoModel; 
  private int iGestion=-1;
  private int iPropiedad=-1;
  private static String separador = "===================================================================";
  
  private Scanner in;
  
  public VistaTextual() {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar(){
  
    int opcion = menu ("¿Desea comprar la calle a la que se ha llegado?",
      new ArrayList<> (Arrays.asList("SI","NO")));
    
    return (Respuestas.values()[opcion]);
  }

  void gestionar(){
  
    int opcion = menu ("Indique el número de gestión inmobiliaria elegida",
      new ArrayList<> (Arrays.asList("Vender", "Hipotecar", "Cancelar hipoteca", "Construir casa", "Construir hotel", "Terminar")));
    
    Jugador ja = juegoModel.getJugadorActual();
    
    ArrayList<TituloPropiedad> propiedades = ja.getPropiedades();
    ArrayList<String> nombrePropiedades = new ArrayList<String>(); 
    
    for(int i = 0; i < propiedades.size(); i++){
        
        nombrePropiedades.add(propiedades.get(i).getNombre());
    }
    
    int ip = -1;
    
    if(opcion != 5){
    
        ip = menu ("Indique el número de la propiedad a gestionar",
      nombrePropiedades);
    }
    
    this.iPropiedad = ip;
    this.iGestion = opcion;
  }
  
  public int getGestion(){
  
      return this.iGestion;
  }
  
  public int getPropiedad(){
  
      return this.iPropiedad;
  }
    

  void mostrarSiguienteOperacion(Operaciones_juego operacion) {
  
      System.out.println("\nSiguiente operación: " + operacion.name());
  }


  void mostrarEventos(){
  
      while(Diario.getInstance().eventosPendientes()){
      
          System.out.println("\nEvento pendiente: " + Diario.getInstance().leerEvento());
      }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();

    }
  
  void actualizarVista(){
  
      System.out.println("\nJugador Actual: " + juegoModel.getJugadorActual().toString());
      System.out.println(separador);
      System.out.println("\nCasilla Actual: " + juegoModel.getCasillaActual().toString());
      System.out.println(separador);
      System.out.println(separador);
  } 
}
