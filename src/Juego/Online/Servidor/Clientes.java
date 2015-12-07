/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego.Online.Servidor;

/**
 *
 * @author Joel
 */
import java.net.Socket;
 
public class Clientes {
    
    private Socket socketCliente = null;
    private EscucharClientes hiloEntrada = null;
    private EnviarClientes hiloSalida = null;
    
    public Socket getSocketCliente() {
        return socketCliente;
    }

    public void setSocketCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public EscucharClientes gethiloEntrada() {
        return hiloEntrada;
    }

    public void sethiloEntrada(EscucharClientes hiloEntrada) {
        this.hiloEntrada = hiloEntrada;
    }

    public EnviarClientes gethiloSalida() {
        return hiloSalida;
    }

    public void sethiloSalida(EnviarClientes hiloSalida) {
        this.hiloSalida = hiloSalida;
    }

}