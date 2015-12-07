/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juego;

import GUI.VistaTablero;
import Juego.Piezas.Alfil;
import Juego.Piezas.Rey;
import Juego.Piezas.Reina;
import Juego.Piezas.Torre;
import Juego.Piezas.Peon;
import Juego.Piezas.Caballero;
import Juego.Piezas.Maharaja;
import java.awt.Color;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/**
 * Clase Juego implementada como un Runnable
 * 
 * @author Joel
 */
public class Juego implements Runnable {
    
    // Parámetros del juego
    
    public static final int BLACK = 0;
    private final String nombreCipayos;
    
    public static final int WHITE = 1;
    private final String nombreMaharaja;
    
    public final int xReyNegro = 0;
    public final int yReyNegro = 4;

    public int xMaharaja;
    public int yMaharaja;
    
    private final boolean multiplayer;
    private final boolean host;

    private int jugadorActual;
    private boolean continuarJuego = true;
    
    private final Tablero tablero;
    private final VistaTablero tableroGUI;

    private LinkedList<Pieza> piezasNegras;
    private final LinkedList<Pieza> piezasBlancas;

    private final Rey reyNegro;
    private final Maharaja reyBlanco;
    
    /**
     * 
     * @param mp  - determina si la partida es en línea
     * @param host - determina si es el host de una partida online
     * @param cip - nombre del jugador de los Cipayos
     * @param mah - nombre del jugador Maharajá
     * @param xMaharaja - posición inicial en X del Maharajá
     * @param yMaharaja - posición inicial en Y del Maharajá
     */
    public Juego(boolean mp, boolean host, String cip, String mah, int xMaharaja, int yMaharaja) {

        this.nombreCipayos  = cip;
        this.nombreMaharaja = mah;
        this.multiplayer    = mp;
        this.host           = host;
        
        tablero    = new Tablero();
        tableroGUI = new VistaTablero(mp, host, xMaharaja, yMaharaja);

        jugadorActual = WHITE;
        piezasNegras = new LinkedList<>();
        piezasBlancas = new LinkedList<>();

        reyNegro = new Rey(tablero, BLACK, xReyNegro, yReyNegro);
        piezasNegras.add(reyNegro);

        this.xMaharaja = xMaharaja;
        this.yMaharaja = yMaharaja;
        
        reyBlanco = new Maharaja(tablero, WHITE, xMaharaja, yMaharaja);
        piezasBlancas.add(reyBlanco);
    }

    /**
     *  Método para comenzar un juego desde el inicio
     *  Se coloca a los Cipayos en sus posiciones por defecto.
     */
    public void nuevoJuego() {

        this.ponerPeon(1, 0);
        this.ponerPeon(1, 1);
        this.ponerPeon(1, 2);
        this.ponerPeon(1, 3);
        this.ponerPeon(1, 4);
        this.ponerPeon(1, 5);
        this.ponerPeon(1, 6);
        this.ponerPeon(1, 7);

        this.ponerTorre(0, 0);
        this.ponerTorre(0, 7);

        this.ponerCaballo(0, 1);
        this.ponerCaballo(0, 6);

        this.ponerAlfil(0, 2);
        this.ponerAlfil(0, 5);

        this.ponerReina(0, 3);
        this.ponerPeon(1, 0);
    }
    
    /**
     * Método para actualizar el GUI
     * Se muestran las posiciones nuevas después de cada jugada
     */
    public void displayTablero() {
        Enumeration<AbstractButton> elementos = tableroGUI.getCuadros().getElements();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AbstractButton boton = elementos.nextElement();
                boton.setText(convertirPieza(tablero.getTableroAjedrez()[i][j]));
            }
        }
    }
    
    /**
     * Verifica una pieza cualquiera y la transforma a su representación ASCII
     * @param e - Pieza cualquiera para ser analizada
     * @return 
     */
    public String convertirPieza(Pieza e) {
        if (e instanceof Peon)
            return "♟";
        else if (e instanceof Caballero)
            return "♞";
        else if (e instanceof Reina)
            return "♛";
        else if (e instanceof Rey)
            return "♚";
        else if (e instanceof Torre)
            return "♜";
        else if (e instanceof Alfil)
            return "♝";
        else if (e instanceof Maharaja)
            return "♕";
        else
            return "";
    }        
    
    /**
     * Método dinámico para mostrar (en verde) los posibles movimientos de 
     * alguna pieza seleccionada.
     * @param x - posición en X de la selección 
     * @param y - posición en Y de la selección
     */
    public void displayMovimientos(int x, int y) {
        Enumeration<AbstractButton> elementos = tableroGUI.getCuadros().getElements();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AbstractButton boton = elementos.nextElement();
                if (tablero.piezaEn(x, y).puedeMoverA(i, j))
                    boton.setBackground(Color.green);
            }
        }
    }
    
    /**
     * Metodo para obtener IP IPv4 de la maquina local
     * @return String con IP de la maquina en red
     */
    public String getMyIP() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.out.println("Error al iniciar juego online.");
        }
        return null;
    }
    
    /**
     * Loop hasta que el juego termine, contiene todas las verificaciones
     * pertinentes para cada turno
     */
    public synchronized void comenzarJuego() {
        
        if (multiplayer == true && host == false) {
            tableroGUI.esperarMaharaja();
            this.xMaharaja = tableroGUI.xMaharaja;
            this.yMaharaja = tableroGUI.yMaharaja;
            reyBlanco.moverA(tableroGUI.xMaharaja, tableroGUI.yMaharaja);
            displayTablero();
        }
        
        tableroGUI.mostrarProgreso("\n ¡Que comience el juego!");
        nuevoJuego();
        
        while (continuarJuego) {
            
            tableroGUI.mostrarProgreso("\n Es el turno de " + getNombreJugador() + ".");
            
            displayTablero();
            tableroGUI.resetPiezas();

            tableroGUI.esperarPieza();
            
            if (tableroGUI.getNextX() == -2 && tableroGUI.getNextY() == -2) {
                tableroGUI.enviarUpdate("-2,-2", 1);
                rendirse(); break;
            }
            
            if (tableroGUI.getNextX() == -3 && tableroGUI.getNextY() == -3) {
                tableroGUI.enviarUpdate("-3,-3", 1);
                if (solicitarTablas()) break;
            }
            
            if (tableroGUI.getNextX() == -4 && tableroGUI.getNextY() == -4) {
                reiniciar();
            }            
            
            Pieza objetivo = tablero.piezaEn(tableroGUI.getNextX(), tableroGUI.getNextY());
            
            if (objetivo == null) {
                tableroGUI.mostrarProgreso(" Esa posición no es válida.");
            }
                
            else if (objetivo.getColor() == jugadorActual) {

                displayMovimientos(tableroGUI.getNextX(), tableroGUI.getNextY());
                
                int anteriorX = tableroGUI.getNextX();
                int anteriorY = tableroGUI.getNextY();
                
                tableroGUI.resetPiezas();
                tableroGUI.esperarPieza();
                
                tableroGUI.resetBackgroundColor();

                if (objetivo.puedeMoverA(tableroGUI.getNextX(), tableroGUI.getNextY()) ) {
                    
                    int nuevoX = tableroGUI.getNextX();
                    int nuevoY = tableroGUI.getNextY();
                    
                    Pieza piezaEnDestino = tablero.piezaEn(tableroGUI.getNextX(), tableroGUI.getNextY());
                    
                    if (objetivo == reyNegro || objetivo == reyBlanco) {
                        
                        objetivo.moverA(tableroGUI.getNextX(), tableroGUI.getNextY());
                        cambiarTurno(jugadorActual);
                        
                        if (piezaEnDestino != null) {
                            removerPieza(piezaEnDestino);
                        }
                        
                        if (checkStatus(jugadorActual) == true) {
                            objetivo.moverA(anteriorX, anteriorY);
                            tablero.colocarPieza(piezaEnDestino, tableroGUI.getNextX(), tableroGUI.getNextY());
                            cambiarTurno(jugadorActual);
                        }
                        
                        else {
                            tableroGUI.mostrarProgreso(" Movimiento: " +
                                cambiarNotacion(anteriorX, anteriorY) +
                                " → " +
                                cambiarNotacion(tableroGUI.getNextX(), tableroGUI.getNextY()) );  
                            
                            // Agrega la pieza al panel de capturas del Maharaja
                            tableroGUI.areaCapturas.setText(tableroGUI.areaCapturas.getText()
                                                            + convertirPieza(piezaEnDestino));
                            // Comunica movimientos o acciones en modo multijugador
                            if (multiplayer) {
                                try {
                                    tableroGUI.enviarUpdate(anteriorX+","+anteriorY, 1);
                                    this.wait(500);
                                    tableroGUI.enviarUpdate(nuevoX+","+nuevoY, 2);
                                } catch (Exception e) { }
                            }
                        }
                    }
                    
                    else if (objetivo != reyNegro || objetivo != reyBlanco)  {
                        
                        objetivo.moverA(tableroGUI.getNextX(), tableroGUI.getNextY());
                        cambiarTurno(jugadorActual);
                        
                        if (piezaEnDestino != null) {
                            removerPieza(piezaEnDestino);
                        }
                        
                        if (checkStatus(jugadorActual) == true) {
                            objetivo.moverA(anteriorX, anteriorY);
                            tablero.colocarPieza(piezaEnDestino, tableroGUI.getNextX(), tableroGUI.getNextY());
                            
                            if (objetivo instanceof Peon && anteriorX == 1)
                                objetivo.haMovido = false;
                            
                            cambiarTurno(jugadorActual);
                            
                        } 
                        
                        else {
                            // Muestra las coordenadas (x, y) -> (x2, y2) del movimiento
                            tableroGUI.mostrarProgreso(" Movimiento: " +
                                                       cambiarNotacion(anteriorX, anteriorY) +
                                                       " → " +
                                                       cambiarNotacion(tableroGUI.getNextX(), tableroGUI.getNextY()));
                            
                            // Agrega la pieza al panel de capturas del Maharaja
                            tableroGUI.areaCapturas.setText(tableroGUI.areaCapturas.getText()
                                                            + convertirPieza(piezaEnDestino));
                            
                           // Comunica movimientos o acciones en modo multijugador
                            if (multiplayer) {
                                try {
                                    tableroGUI.enviarUpdate(anteriorX+","+anteriorY, 1);
                                    this.wait(500);
                                    tableroGUI.enviarUpdate(nuevoX+","+nuevoY, 2);
                                } catch (Exception e) { }
                            }                            
                            
                            if (checkStatus(jugadorActual)) break;
                        }
                    }
                }
                else { 
                    tableroGUI.mostrarProgreso(" No se puede mover ahí."); }
            }
            else {
                tableroGUI.mostrarProgreso(" Esa no es tu pieza.");
            }
        }
    }
    
    /**
     * Método para reiniciar el tablero a su estado original
     */
    public void reiniciar() {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
        getNombreJugador()+", ¿Estás seguro de que quieres reiniciar el juego?","Reinicio", 1);
        
        if (dialogResult == JOptionPane.YES_OPTION) { 

            Pieza[][] miTab = tablero.getTableroAjedrez();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (miTab[i][j] != reyNegro || miTab[i][j] != reyBlanco) {
                        miTab[i][j] = null;
                    }
                }
            }
            reyNegro.moverA(0, 4);
            reyBlanco.moverA(xMaharaja, yMaharaja);
            tableroGUI.historial.setText(" ¡Que comience el juego!");
            tableroGUI.areaCapturas.setText("");
            nuevoJuego();
        }
    }
    
    /**
     * Método para declarar rendición de una de las partes.
     * El jugador debe confirmar que desea rendirse durante el juego.
     */
    public void rendirse() {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
        getNombreJugador()+", ¿Estás seguro de que quieres rendirte?","Rendición", 1);
        
        if (dialogResult == JOptionPane.YES_OPTION) { 
            cambiarTurno(jugadorActual);
            tableroGUI.mostrarProgreso( "\n ¡Felicidades "+ getNombreJugador()
                                      + ", tu enemigo ha cedido y has ganado!");
        } 
    }
    
    /**
     * Método para declarar tablas o empate si ambas partes están de acuerdo
     * @return True - si el enemigo acepta la proposición de Tablas
     */
    public boolean solicitarTablas(){
        
        tableroGUI.mostrarProgreso(" " + getNombreJugador() + " ha solicitado tablas.");
        
        int dialogResult = JOptionPane.showConfirmDialog (null, 
        getNombreJugador() + " ha solicitado tablas, ¿Aceptas?","Solicitud", 1);
        
        if (dialogResult == JOptionPane.YES_OPTION) { 
            tableroGUI.mostrarProgreso("\n El juego ha terminado en tablas.");
            return true;
        } 
        
        else {
            tableroGUI.mostrarProgreso("\n Tu enemigo te ha rechazado. ¡La batalla continúa!");
            return false;
        }
    }
    
    /**
     * Método para verificar si hay JAQUE o JAQUE MATE tras un movimiento.
     * @param color - color del jugador en curso.
     * @return True - si el juego ha terminado por JAQUE MATE
     */
    public boolean checkStatus(int color) {
        int jugActual = color;
        int jugContrario;
        if (jugActual == WHITE)
            jugContrario = BLACK;
        else
            jugContrario = WHITE;
        
        // Maharajá o Rey de los Cipayos en JAQUE MATE - NO se indica.
        if (reyEnJaque(jugContrario) && jugadorActual == jugActual) {
            tableroGUI.mostrarProgreso("\n Esa posición no es válida.");
            displayTablero();
            return true;
        }
        
        // Movimiento causa un JAQUE
        if (reyEnJaque(jugActual) && jugadorActual == jugActual) {
            tableroGUI.mostrarProgreso("\n\n Posición de JAQUE");
        }
        return false;
    }

    /**
     * Función auxiliar para cambiar el turno después de una jugada válida.
     * @param jugador - jugador actual que cederá el turno.
     */
    public void cambiarTurno(int jugador) {
        if (jugador == 1) { setPlayer(0); }
        if (jugador == 0) { setPlayer(1); }
    }
    
    public String cambiarNotacion(int x, int y) {
        int conversionX = 7 - x;
        if (y == 0) return (conversionX+1)+"A";
        if (y == 1) return (conversionX+1)+"B";
        if (y == 2) return (conversionX+1)+"C";
        if (y == 3) return (conversionX+1)+"D";
        if (y == 4) return (conversionX+1)+"E";
        if (y == 5) return (conversionX+1)+"F";
        if (y == 6) return (conversionX+1)+"G";
        if (y == 7) return (conversionX+1)+"H";
        return null;
    }

    /**
     * Verifica si el rey de uno de los jugadores está en Jaque
     * @param color - el color del jugador que es revisado.
     * @return - True - si el rey del jugador está en JAQUE
     */
    public boolean reyEnJaque(int color) {

        if (color == 0) {
            
            int x = reyNegro.getPosicionX();
            int y = reyNegro.getPosicionY();

            if (reyBlanco.puedeMoverA(x, y))
                return true;
        }

        else {
            int x = reyBlanco.getPosicionX();
            int y = reyBlanco.getPosicionY();
            
            for (Pieza e : piezasNegras) {
                if (e.puedeMoverA(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Elimina una pieza del tablero de juego.
     * @param remueveEstaPieza - la pieza que será removida.
     */
    public void removerPieza(Pieza remueveEstaPieza){
        piezasNegras.remove(remueveEstaPieza);
    }
    
    /**
     * Método para agregar una Reina al juego
     * @param posX - posición en X de la pieza
     * @param posY - posición en Y de la pieza
     * @return Pieza - reina
     */
    public Reina ponerReina(int posX, int posY){
        Reina reina = new Reina(tablero, BLACK, posX, posY);
        piezasNegras.add(reina);
        return reina;
    }

    /**
     * Método para agregar un Caballero al juego
     * @param posX - posición en X de la pieza
     * @param posY - posición en Y de la pieza
     * @return Pieza - caballero
     */
    public Caballero ponerCaballo(int posX, int posY){
        Caballero caballero = new Caballero(tablero, BLACK, posX, posY);
        piezasNegras.add(caballero);
        return caballero;
    }
    
    /**
     * Método para agregar una Torre al juego
     * @param posX - posición en X de la pieza
     * @param posY - posición en Y de la pieza
     * @return Pieza - torre
     */
    public Torre ponerTorre(int posX, int posY){
        Torre torre = new Torre(tablero, BLACK, posX, posY);
        piezasNegras.add(torre);
        return torre;
    }
    
    /**
     * Método para agregar un Alfil al juego
     * @param posX - posición en X de la pieza
     * @param posY - posición en Y de la pieza
     * @return Pieza - alfil
     */
    public Alfil ponerAlfil(int posX, int posY){
        Alfil alfil = new Alfil(tablero, BLACK, posX, posY);
        piezasNegras.add(alfil);
        return alfil;
    }
    
    /**
     * Método para agregar un Peón al juego
     * @param posX - posición en X de la pieza
     * @param posY - posición en Y de la pieza
     * @return Pieza - peón
     */
    public Peon ponerPeon(int posX, int posY){
        Peon peon = new Peon(tablero, BLACK, posX, posY);
        piezasNegras.add(peon);
        return peon;
    }

    /**
     * Getters & Setters
     */
    public void setPlayer(int jugador) {
        jugadorActual = jugador;
    }
    
    public void setContinuarJuego(boolean continuarJuego) {
        this.continuarJuego = continuarJuego;
    }

    public Tablero getTablero() {
        return tablero;
    }        
    
    public String getNombreJugador() {
        if (jugadorActual == WHITE) return nombreMaharaja;
        else return nombreCipayos;
    }
    
    /**
     * Método para correr la instancia desde un hilo.
     */
    @Override
    public void run() {
        tableroGUI.correGUI();
        if (multiplayer == true && host == true) {
            tableroGUI.mostrarProgreso("Bienvenido al campo de batalla.");
            tableroGUI.mostrarProgreso("Por favor, comparte tu dirección IP con tu adversario para jugar.");
            tableroGUI.mostrarProgreso("\nTu dirección IP es: "+getMyIP());
            tableroGUI.mostrarProgreso("(La puedes copiar y pegar para envíarsela)");
            tableroGUI.esperarCliente();
            tableroGUI.mostrarProgreso("\n¡Tu adversario se ha conectado!");
            comenzarJuego();
        }
        comenzarJuego();
    }
}
