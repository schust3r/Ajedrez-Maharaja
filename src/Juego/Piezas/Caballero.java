/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Caballero extends Pieza {

    public Caballero(Tablero board, int color, int locX, int locY){
            super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
            if(puedeMoverAuxiliar(posicionX,posicionY)){
                    return movimientoCaballo(posicionX, posicionY);
            }
            return false;
    }

    /**
     * Especifica las reglas de movimiento del Caballero
     * El Caballero puede moverse en forma de "L"
     * También puede saltarse otras piezas, así que no
     * hay que revisar piezas intermedias.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoCaballo(int posicionX, int posicionY){
        if (Math.abs(this.getPosicionX() - posicionX) == 2 && Math.abs(this.getPosicionY() - posicionY) == 1)
            return true;
        if (Math.abs(this.getPosicionX() - posicionX) == 1 && Math.abs(this.getPosicionY() - posicionY) == 2)
            return true;
        return false;
    }
}
