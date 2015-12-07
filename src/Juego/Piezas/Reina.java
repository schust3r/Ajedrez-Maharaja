/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Reina extends Pieza {

    public Reina(Tablero board, int color, int locX, int locY){
            super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX, posicionY))
                return movimientoReina(posicionX, posicionY);
        return false;
    }

    /**
     * Especifica las reglas de movimiento de la Reina
     * La Reina puede moverse en 8 direcciones (en línea recta y diagonal)
     * siempre que no haya piezas intermedias.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoReina(int posicionX, int posicionY){
        return seMueveLineal(posicionX, posicionY) || seMueveDiagonal(posicionX, posicionY);
    }
}
