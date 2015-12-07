/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego.Piezas;

import Juego.Tablero;
import Juego.Pieza;

public class Peon extends Pieza {

    public Peon(Tablero board, int color, int locX, int locY){
        super(board, color, locX, locY);
    }

    public boolean puedeMoverA(int posicionX, int posicionY){
        if (puedeMoverAuxiliar(posicionX,posicionY))
            return movimientoPeon(posicionX, posicionY);
        return false;
    }

    /**
     * Especifica las reglas de movimiento del Peón
     * Los peones solo pueden avanzar 1 o 2 espacios según el turno,
     * pueden avanzar un espacio diagonal si van a capturar una pieza.
     * 
     * @param posicionX - la posición en X a donde se quiere mover.
     * @param posicionY - la posición en Y a donde se quiere mover.
     * @return - True - si el destino es una ubicación válida.
     */
    private boolean movimientoPeon(int posicionX, int posicionY){
        int unPaso = 1;
        int dosPasos = 2;
        Pieza objetivo = tablero.piezaEn(posicionX, posicionY);

        // Se mueve un espacio hacia adelante
        if (posicionX - this.getPosicionX() == unPaso){
            // En línea recta
            if (posicionY == this.getPosicionY() && objetivo == null)
                return true;

            // Diagonalmente
            if (Math.abs(this.getPosicionY() - posicionY) == 1 && objetivo != null)
                return true;
        }
        
        // Avanza dos espacios
        else if (!haMovido){
            if (posicionX - this.getPosicionX() == dosPasos){
                if (posicionY == this.getPosicionY() && objetivo == null)
                    return true;
            }
        }
        return false;
    }
}
