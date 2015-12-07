/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego.Online.Servidor;

import java.net.*;
import java.io.*;

/**
 * @author Joel
 */

public class Servidor implements Runnable {
    
    public static final int Port = 9729;
 
    public void run() {
        
        ServerSocket socketServidor = null;
        try {
           socketServidor = new ServerSocket(Port);
           System.out.println("Inicializando servidor en  el puerto " + Port +".");
        } catch (IOException se) {
           System.err.println("No se pudo iniciar el servidor en el puerto " + Port);
           System.exit(-1);
        }
 
        ColaServidor serverDispatcher = new ColaServidor();
        System.out.println("Inicializando servicio de dispatch de mensajes...");
        serverDispatcher.start();
        System.out.println("Servidor operacional.\n");
        
        while (true) {
           try {
               
               Socket socket = socketServidor.accept();
               Clientes infoDeCliente = new Clientes();
               infoDeCliente.setSocketCliente(socket);
               EscucharClientes clientListener = new EscucharClientes(infoDeCliente, serverDispatcher);
               EnviarClientes clientSender = new EnviarClientes(infoDeCliente, serverDispatcher);
               infoDeCliente.sethiloEntrada(clientListener);
               infoDeCliente.sethiloSalida(clientSender);
               clientListener.start();
               clientSender.start();
               serverDispatcher.agregarCliente(infoDeCliente);
               
           } catch (IOException ioe) { }
        }
    }
}