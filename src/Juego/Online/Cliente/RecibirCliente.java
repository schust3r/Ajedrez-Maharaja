/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego.Online.Cliente;

import GUI.VistaTablero;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RecibirCliente implements Runnable {
    
    private final VistaTablero main;
    private String mensaje; 
    private BufferedReader entrada;
    private Socket cliente;
    
   //Inicializar chatServer y configurar GUI
   public RecibirCliente(Socket cliente, final VistaTablero main) {
       this.cliente = cliente;
       this.main    = main;
   }  
   
    @Override
    public void run() {
        try {
            
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            do {
                mensaje = (String) entrada.readLine();
                String command = mensaje.substring(0, 5);
                switch (command) {
                    
                    // Comando Seleccion Movimiento1 encontrado
                    case "!mv1:":
                        mensaje = mensaje.substring(5, mensaje.length());
                        
                        int x1 = Integer.parseInt(mensaje.split(",")[0]);
                        int y1 = Integer.parseInt(mensaje.split(",")[1]);
                        
                        main.elegirPieza(x1, y1);

                        break;
                        
                    // Comando movimiento encontrado
                    case "!mv2:":
                        mensaje = mensaje.substring(5, mensaje.length());

                        int x2 = Integer.parseInt(mensaje.split(",")[0]);
                        int y2 = Integer.parseInt(mensaje.split(",")[1]);
                        
                        main.elegirPieza(x2, y2);

                        break;                        
                        
                    // Comando Accion encontrada
                    case "!act:":
                        mensaje = mensaje.substring(5, mensaje.length());

                        int act1 = Integer.parseInt(mensaje.split(",")[0]);
                        int act2 = Integer.parseInt(mensaje.split(",")[1]);
                        
                        main.elegirPieza(act1, act2);
                        
                        break;
                        
                    case "!newc":
                        if (VistaTablero.host == true) {
                            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
                            salida.println("!pos:"+main.xMaharaja+","+main.yMaharaja);
                            salida.flush(); 
                            main.conectarCliente();
                        } 
                        break;
                        
                    case "!pos:":
                        if (VistaTablero.host != true) {
                            mensaje = mensaje.substring(5, 8);

                            String x = mensaje.split(",")[0];
                            String y = mensaje.split(",")[1];

                            main.elegirMaharaja(Integer.parseInt(x), Integer.parseInt(y));
                        }
                        break;
                        
                    // Si no se cumplen anteriores (no hay comando).
                    default:
                        main.areaChat.setText(main.areaChat.getText() + "\n" + mensaje);
                        break;
                }
            } while (true); // ejecutar por siempre
        } 
        catch (IOException | NumberFormatException e) { }
        
    }
} 
