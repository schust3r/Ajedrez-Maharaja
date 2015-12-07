/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego.Online.Servidor;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Joel
 */
public class EnviarClientes extends Thread
{
    private Vector colaMensajes;
    private Clientes infoCliente;
    private PrintWriter salida;
 
    public EnviarClientes(Clientes aClientInfo, ColaServidor aServerDispatcher)
    throws IOException {
        
        this.colaMensajes = new Vector();
        infoCliente = aClientInfo;
        Socket socket = aClientInfo.getSocketCliente();
        salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        
    }
 
    /**
     * añade los mensajes a una lista para luego ser enviados
     */
    public synchronized void enviarMensaje(String mensaje) {
        colaMensajes.add(mensaje);
        notify();
    }
 
    /**
     * borra el mensaje enviado y luego obtiene uno nuevo para enviar
     */
    private synchronized String getNextMessageFromQueue() throws InterruptedException
    {
        while (colaMensajes.isEmpty())
           wait();
        String message = (String) colaMensajes.get(0);
        colaMensajes.removeElementAt(0);
        return message;
    }
 
    /**
     * envia un mensaje al cliente
     */
    private void enviarMensajeToClient(String mensaje) {
        salida.println(mensaje);
        salida.flush();
    }
 
    /**
     * lee los mensajes y los envia
     */
    public void run()
    {
        try {
           while (!isInterrupted()) {
               String message = getNextMessageFromQueue();
               enviarMensajeToClient(message);
           }
        } catch (Exception e) { }
 
        infoCliente.gethiloEntrada().interrupt();
    }
 
}