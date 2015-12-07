/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego.Online.Cliente;

import GUI.VistaTablero;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.SocketException;
        
public class EnviarCliente implements Runnable {
    
    private final VistaTablero main;
    private PrintWriter salida;
    private String mensaje;
    private Socket conexion;
   
    public EnviarCliente(Socket conexion, final VistaTablero main) throws IOException {
        this.conexion = conexion;
        this.main     = main;
        
        //Evento que ocurre al escribir en el campo de texto
        main.fieldMensaje.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                                
                if (!"".equals(main.fieldMensaje.getText())) {
                    if (main.multiplayer == true && main.host == true) {
                        salida.println("Maharajá: " + main.fieldMensaje.getText());
                        salida.flush();
                        main.fieldMensaje.setText("");
                    } else {
                        salida.println("Cipayos: " + main.fieldMensaje.getText());
                        salida.flush();
                        main.fieldMensaje.setText("");                        
                    }
                }
            }
        });
    } 
       
    @Override
    public void run() {
        
        try {
            salida = new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));
            
            if (main.host == false) {
                salida.println("!newc");
            }
            
            salida.flush(); 
            
        } catch (SocketException ex) {
        } catch (IOException | NullPointerException ioException) {
        }
    }     
}
