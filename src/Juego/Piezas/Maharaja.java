/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Maharaja extends Pieza {

    public Maharaja(Tablero board, int color, int locX, int locY){
        super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX, posicionY))
            return movimientoMaharaja(posicionX, posicionY);
        return false;
    }
    /**
     * Especifica las reglas de movimiento del Maharajá
     * El Maharajá puede moverse como el Caballero y la Reina
     * Si se salta otras piezas depende del tipo de movimiento.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoMaharaja(int posicionX, int posicionY){
        return seMueveLineal(posicionX, posicionY) ||
               seMueveDiagonal(posicionX, posicionY) ||
              (Math.abs(this.getPosicionX() - posicionX) == 2 && Math.abs(this.getPosicionY() - posicionY) == 1) ||
              (Math.abs(this.getPosicionX() - posicionX) == 1 && Math.abs(this.getPosicionY() - posicionY) == 2);
    }
}
