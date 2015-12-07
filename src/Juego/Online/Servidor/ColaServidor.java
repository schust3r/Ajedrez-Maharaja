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
import java.util.*;
 
public class ColaServidor extends Thread {
    
    private Vector colaMensajes;
    public  Vector listaClientes;
    
    public ColaServidor() {
        this.listaClientes   = new Vector();
        this.colaMensajes    = new Vector();    
    }
 
    
    /**
     * Añade un cliente a la lista
     * @param cliente
     */
    public synchronized void agregarCliente(Clientes cliente) {
        if (listaClientes.size() != 2) {
            listaClientes.add(cliente);
            System.out.println("Agregado a la lista de clientes conectados.");   
        }
    }
 
    /**
     * Añade un mensaje a la lista de espera, para luego ser enviado
     * @param cliente
     * @param mensaje
     */
    public synchronized void dispatchMessage(Clientes cliente, String mensaje) {
        colaMensajes.add(mensaje);
        notify();
    }
 
    /**
     * retorna el mensaje obtenido y luego lo borra, luego espera hasta que
     * llegue un mensaje nuevo
     */
    private synchronized String obtenerSiguienteMensaje() throws InterruptedException {
        while (colaMensajes.isEmpty())
           wait();
        String mensaje = (String) colaMensajes.get(0);
        colaMensajes.removeElementAt(0);
        return mensaje;
    }
 
    /**
     * envia el mensaje a todos los clientes conectados
     */
    private synchronized void envioMasivo(String mensaje) {
        for (Object listaCliente : listaClientes) {
            Clientes cliente = (Clientes) listaCliente;
            cliente.gethiloSalida().enviarMensaje(mensaje);
        }
    }
 
    /**
     * lee los mensajes hasta que se cierre el programa
     */
    public void run()
    {
        try {
           while (true) {
               String mensaje = obtenerSiguienteMensaje();
               envioMasivo(mensaje);
           }
        } catch (InterruptedException ie) { }
    }
 
}