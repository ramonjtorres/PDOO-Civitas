package civitas;

class GestorEstados {
  EstadosJuego estadoInicial() {
    return (EstadosJuego.INICIO_TURNO);
  }
  
  Operaciones_juego operacionesPermitidas(Jugador jugador, EstadosJuego estado) {
    Operaciones_juego op = null;

    switch (estado) {
      case INICIO_TURNO :
        if (jugador.encarcelado)
          op = Operaciones_juego.SALIR_CARCEL;
        else
          op = Operaciones_juego.AVANZAR;
        break;

      case DESPUES_CARCEL :
        op = Operaciones_juego.PASAR_TURNO;
        break;

      case DESPUES_AVANZAR :
        if (jugador.encarcelado)
          op = Operaciones_juego.PASAR_TURNO;
        else if (jugador.getPuedeComprar())
          op = Operaciones_juego.COMPRAR;
        else if (jugador.tieneAlgoQueGestionar())
          op = Operaciones_juego.GESTIONAR;
        else
          op = Operaciones_juego.PASAR_TURNO;
        break;

      case DESPUES_COMPRAR :
        if (jugador.tieneAlgoQueGestionar())
          op = Operaciones_juego.GESTIONAR;
        else
          op = Operaciones_juego.PASAR_TURNO;
        break;

      case DESPUES_GESTIONAR :
        op = Operaciones_juego.PASAR_TURNO;
        break;        
    }
    return op;
  }
  
  EstadosJuego siguienteEstado (Jugador jugador, EstadosJuego estado, Operaciones_juego operacion) {
    EstadosJuego siguiente = null;

    switch (estado) {
      case INICIO_TURNO :
        if (operacion==Operaciones_juego.SALIR_CARCEL)
          siguiente = EstadosJuego.DESPUES_CARCEL;
        else if (operacion==Operaciones_juego.AVANZAR)
          siguiente = EstadosJuego.DESPUES_AVANZAR;
        break;

      case DESPUES_CARCEL :
        if (operacion==Operaciones_juego.PASAR_TURNO)
          siguiente = EstadosJuego.INICIO_TURNO;
        break;

      case DESPUES_AVANZAR :
        switch (operacion) {
          case PASAR_TURNO :
            siguiente = EstadosJuego.INICIO_TURNO;
            break;
          case COMPRAR :
            siguiente = EstadosJuego.DESPUES_COMPRAR;
            break;
          case GESTIONAR :
            siguiente = EstadosJuego.DESPUES_GESTIONAR;
            break;
        }
        break;

      case DESPUES_COMPRAR :
        if (operacion==Operaciones_juego.GESTIONAR)
          siguiente = EstadosJuego.DESPUES_GESTIONAR;
        else if (operacion==Operaciones_juego.PASAR_TURNO)
          siguiente = EstadosJuego.INICIO_TURNO;
        break;

      case DESPUES_GESTIONAR :
        if (operacion==Operaciones_juego.PASAR_TURNO)
          siguiente = EstadosJuego.INICIO_TURNO;
        break;
    }

    Diario.getInstance().ocurreEvento("De: "+estado.toString()+ " con "+operacion.toString()+ " sale: "+siguiente.toString());

    return siguiente;
  }

}