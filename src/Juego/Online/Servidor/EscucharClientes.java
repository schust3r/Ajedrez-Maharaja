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
import java.io.*;
import java.net.*;
 
public class EscucharClientes extends Thread
{
    private ColaServidor handlerServidor;
    private Clientes infoClientes;
    private BufferedReader entrada;
 
    public EscucharClientes(Clientes aClientInfo, ColaServidor aServerDispatcher)
    throws IOException {
        
        infoClientes = aClientInfo;
        handlerServidor = aServerDispatcher;
        Socket socket = aClientInfo.getSocketCliente();
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void run()
    {
        try {
           while (!isInterrupted()) {
               String mensaje = entrada.readLine();
                if (mensaje == null)
                   break;
                handlerServidor.dispatchMessage(infoClientes, mensaje);
                System.out.println("Nuevo mensaje recibido y despachado.");
           }
        } catch (IOException e) { }
 
        infoClientes.gethiloSalida().interrupt();
        
    }
 
}