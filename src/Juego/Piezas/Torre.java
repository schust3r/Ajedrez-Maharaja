/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Torre extends Pieza {

    public Torre(Tablero board, int color, int locX, int locY){
            super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX,posicionY))
            return movimientoTorre(posicionX, posicionY);
        return false;
    }

    /**
     * Especifica las reglas de movimiento de la Torre
     * Las Torres pueden moverse en línea recta,
     * mientras no hay una pieza en su camino.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoTorre(int posicionX, int posicionY){
        return seMueveLineal(posicionX, posicionY);
    }
}