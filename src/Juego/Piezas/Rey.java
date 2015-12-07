/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Rey extends Pieza {

    public Rey(Tablero board, int color, int locX, int locY){
        super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX,posicionY))
            return movimientoRey(posicionX, posicionY);
        return false;
    }

    /**
     * Especifica las reglas de movimiento del Rey
     * El Rey puede moverse un espacio en toda dirección,
     * siempre que no esté ocupado por otra pieza. 
     * 
     * El Rey debe evitar moverse a una casilla que lo ponga en riesgo,
     * si el jugador hace esto, el juego termina inmediatamente.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoRey(int posicionX, int posicionY){
        int absoluteX = Math.abs(posicionX - this.getPosicionX());
        int absoluteY = Math.abs(posicionY - this.getPosicionY());

        if (absoluteX <= 1 && absoluteY <= 1)
            return !(absoluteX == 0 && absoluteY == 0);
        return false;
    }
}
