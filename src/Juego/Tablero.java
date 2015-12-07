/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego;

/**
 * Clase Tablero
 * Array bidimensional de Piezas, contiene lógica del tablero de Juego
 * 
 * @author Joel
 */
public class Tablero {

    private final Pieza[][] tablero;

    public Tablero(){
        tablero = new Pieza[8][8];
    }

    /**
     * Revisar si la ubicación está dentro de los límites del tablero
     * @param posicionX - posición en X del objetivo 
     * @param posicionY - posición en Y del objetivo
     * @return True - si está dentro de los límites
     */
    public boolean estaEnLimites(int posicionX, int posicionY){
        return posicionX < 8 && posicionX >= 0 &&
               posicionY < 8 && posicionY >= 0;
    }

    /**
     * Retorna la pieza de ajedrez en un punto específico
     * @param posicionX - posición en X del objetivo 
     * @param posicionY - posición en Y del objetivo
     * @return pieza en un punto determinado, null si no hay.
     */
    public Pieza piezaEn(int posicionX, int posicionY){
        if (estaEnLimites(posicionX, posicionY))
            return tablero[posicionX][posicionY];
        return null;
    }

    public void eliminarDeTablero(Pieza removePiece) {        
        int oldX = removePiece.getPosicionX();
        int oldY = removePiece.getPosicionY();
        tablero[oldX][oldY] = null;
    }

    public void colocarPieza(Pieza chessPiece, int posicionX, int posicionY){
        if (estaEnLimites(posicionX, posicionY))
            tablero[posicionX][posicionY] = chessPiece;
    }
    
    /**
     * Getters & Setters
     * @return tablero (array de Piezas)
     */
    public Pieza[][] getTableroAjedrez(){
        return tablero;
    }
    
    
}
