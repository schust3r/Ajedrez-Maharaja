/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego;

/**
 * Clase Pieza del juego de Ajedrez Maharajá
 * Aquí se definen las bases genéricas del movimiento de las piezas.
 * 
 * @author Joel
 */
public class Pieza {

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    private int posX;
    private int posY;
    private final int color;
    protected boolean haMovido;
    protected Tablero tablero;

    /**
     * Constructor
     * @param board - el tablero que contiene a las piezas
     * @param color - si la pieza en Negra o Blanca
     */
    public Pieza(Tablero board, int color){
        this.tablero = board;
        this.color = color;
        haMovido = false;
        posX = -1;
        posY = -1;
    }

    public Pieza(Tablero board, int color, int posX, int posY){
        this.tablero = board;
        this.color = color;
        this.haMovido = false;
        this.posX = posX;
        this.posY = posY;

        tablero.colocarPieza(this, posX, posY);
    }

    /**
     * Verifica si una pieza se puede mover a una posición.
     * Para moverse a una posición: 
     * - La posición debe pertenecer al tablero.
     * - Debe estar vacía o ocupada por un enemigo.
     * - Dependiendo de la pieza, el movimiento debe ser un estilo válido.
     * 
     * @param posicionX - la coordenada X a donde mover.
     * @param posicionY - la coordenada Y a donde mover.
     * @return True - si el movimiento es posible para la pieza.
     */
    public boolean puedeMoverA(int posicionX, int posicionY){
        return puedeMoverAuxiliar(posicionX, posicionY);
    }

    /**
     * Método auxiliar para comprobar si es posible efectuar un
     * movimiento en la mayoría de los casos.
     * No se considera el estilo de movimiento de una pieza.
     * 
     * @param posicionX - ubicación en X del movimiento.
     * @param posicionY - ubicación en Y del movimiento.
     * @return True - si el movimiento es posible
     */
    protected boolean puedeMoverAuxiliar(int posicionX, int posicionY){
        if (tablero.estaEnLimites(posicionX, posicionY)) {

            Pieza location = tablero.piezaEn(posicionX, posicionY);

            if (location == null) 
                return true;

            if (location.getColor() != this.color) 
                return true; 
        }
        return false;
    }

    /**
     * Mueve la pieza de ajedrez actual a la posición indicada
     * @param posicionX - la ubicación X a donde mover.
     * @param posicionY - la ubicación Y a donde mover.
     */
    public void moverA(int posicionX, int posicionY){	
        if (tablero.piezaEn(posX, posY) == this)
            tablero.eliminarDeTablero(this);

        Pieza target = tablero.piezaEn(posicionX, posicionY);

        if (target != null)
            tablero.eliminarDeTablero(target);
    

        this.posX = posicionX;
        this.posY = posicionY;

        tablero.colocarPieza(this, posicionX, posicionY);
        this.haMovido = true;
    }

    /**
     * Remueve la pieza actual del tablero
     */
    public void removerPieza() {
        tablero.eliminarDeTablero(this);
    }

    /**
     * Captura una pieza enemiga en el tablero.
     * @param pieza - la pieza que será capturada.
     */
    public void capturarPieza(Pieza pieza){
        pieza.removerPieza();
    }

    /**
     * Función auxiliar para determinar si una pieza se mueve en
     * línea recta, en cualquier dirección
     * 
     * @param posicionX - la posición X especificada
     * @param posicionY - la posición Y especificada
     * @return True - si se cumple el tipo de movimiento.
     */
    protected boolean seMueveLineal(int posicionX, int posicionY) {

        int actualX = this.getPosicionX();
        int actualY = this.getPosicionY();

        int valorMin;
        int valorMax;

        // Posición X fija
        if (actualX == posicionX) {
            if (actualY > posicionY){
                valorMin = posicionY;
                valorMax = actualY;
            }
            else if (posicionY > actualY){
                valorMin = actualY;
                valorMax = posicionY;
            }
            else return false;

            // Ciclo para verificar si hay una pieza intermedia.
            valorMin++;
            for(; valorMin < valorMax; valorMin++){
                    if (tablero.piezaEn(actualX, valorMin) != null){
                            return false;
                    }
            }
            return true;
        }

        // Posición Y fija
        if (actualY == posicionY){
                if (actualX > posicionX){
                    valorMin = posicionX;
                    valorMax = actualX;
                }
                else if (posicionX > actualX){
                    valorMin = actualX;
                    valorMax = posicionX;
                }
                else return false;

                // Ciclo para verificar si hay una pieza intermedia.
                valorMin++;
                for(; valorMin < valorMax; valorMin++){
                    if (tablero.piezaEn(valorMin, actualY) != null) {
                        return false;
                    }
                }
                return true;
        }
        return false;
    }

    /**
     * Función auxiliar para determinar si una pieza se mueve en
     * forma diagonal, en cualquier dirección
     * 
     * @param posicionX - la posición X especificada
     * @param posicionY - la posición Y especificada
     * @return True - si se cumple el tipo de movimiento.
     */
    protected boolean seMueveDiagonal(int posicionX, int posicionY) {

        // Verificar si el movimiento es diagonal
        int totalX = Math.abs(posicionX - this.getPosicionX());
        int totalY = Math.abs(posicionY - this.getPosicionY());

        if (totalX == totalY) {
            
            // Verificar si no hay obstrucciones
            int dirX = posicionX > this.getPosicionX() ? 1 : -1;
            int dirY = posicionY > this.getPosicionY() ? 1 : -1;
            
            for (int i = 1; i < (Math.abs(posicionX - this.getPosicionX())); i++) {
                if (tablero.piezaEn(this.getPosicionX() + i*dirX, this.getPosicionY() + i*dirY) != null)
                    return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Getters & Setters para los atributos privados
     */
    public int getPosicionX(){
        return posX;
    }

    public int getPosicionY(){
        return posY;
    }

    public int getColor() {
        return color;
    }

    public Tablero getBoard(){
        return tablero;
    }    
    
}
