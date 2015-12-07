/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Alfil extends Pieza {


    public Alfil(Tablero board, int color, int locX, int locY){
        super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX,posicionY))
            return movimientoAlfil(posicionX, posicionY);
        return false;
    }

    /**
     * Especifica las reglas de movimiento del Alfil
     * Los Alfiles pueden moverse en líneas diagonales,
     * siempre y cuando no haya una pieza en su camino.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoAlfil(int posicionX, int posicionY){
        return seMueveDiagonal(posicionX, posicionY);
    }
}