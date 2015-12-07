/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import Juego.Online.Cliente.*;
import java.awt.Desktop;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author Joel
 */
public final class VistaTablero extends javax.swing.JFrame {
    
    public static VistaTablero main;
    
    public static boolean multiplayer;
    public static boolean host;
    public boolean online = false;
    
    public int xMaharaja;
    public int yMaharaja;
    
    private static ServerSocket servidor; //
    private static Socket cliente; //Socket para conectarse con el cliente
    private static Socket conexion; //Socket para conectarse con el cliente
    private static String ip; //ip a la cual se conecta    
    
    private SoundClip sonido;
    
    private int nextX;
    private int nextY;

    /**
     * Creates new form VistaTablero
     * @param mp
     * @param host
     * @param xMaharaja
     * @param yMaharaja
     * @param menu
     */
    public VistaTablero(boolean mp, boolean host, int xMaharaja, int yMaharaja) {
        
        VistaTablero.multiplayer = mp;
        VistaTablero.host        = host;
        
        this.xMaharaja = xMaharaja;
        this.yMaharaja = yMaharaja;
        
        sonido = new SoundClip("intro.wav");
        
        nextY = -1;
        nextX = -1;

        setIP();
        initComponents();
        initMaharaja();
        getContentPane().setBackground(Color.white);
    }
    
    public void initMaharaja() {
        if (multiplayer == true && host == false) {
            this.xMaharaja = 3;
            this.yMaharaja = 3;
        }
    }
    
    public void setIP() {
        if (multiplayer == true && host == false ) {
            this.ip = JOptionPane.showInputDialog("Ingresa la dirección IP del host: ");
        }
    }

    public ButtonGroup getCuadros() {
        return cuadros;
    }
    
    public int getNextX() {
        return nextX; 
    }
    
    public int getNextY() {
        return nextY;
    }
    
    public void setNextX(int x) {
        this.nextX = x; 
    }
    
    public void setNextY(int y) {
        this.nextY = y;
    }    
    
    public void resetPiezas() {
        setNextX(-1); setNextY(-1);
    }
    
    public void resetBackgroundColor() {
        Enumeration<AbstractButton> elementos = getCuadros().getElements();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AbstractButton boton = elementos.nextElement();
                if ( (i + j) % 2 == 0 )
                    boton.setBackground(Color.white);
                else
                    boton.setBackground(new Color(130,130,130));
            }
        }
    }
    
    public void convertirNotacion(String notacion) {
        int x = Integer.parseInt(notacion.split(",")[0]);
        int y = Integer.parseInt(notacion.split(",")[1]);
        elegirPieza(x, y);
    }
    
    public void elegirPieza(int x, int y) {
        synchronized (this) {
            setNextX(x); setNextY(y);
            this.notifyAll();
        }
    }    
    
    public void enviarUpdate(String pos, int num) {
        try {
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
            if (num == 1) {
                salida.println("!mv1:"+pos);
                salida.flush();
            }
            
            else if (num == 2) {
                salida.println("!mv2:"+pos);
                salida.flush();            
            }
            
            else {
                salida.println("!act:"+pos);
                salida.flush();
            }
            
        } catch (Exception e) { }
    }
    
    public void elegirMaharaja(int x, int y) {
        synchronized (this) {
            this.xMaharaja = x; 
            this.yMaharaja = y;
            this.notifyAll();
        }
    }        
    
    public void esperarMaharaja() {
        synchronized (this) {
            while (this.xMaharaja == 3 && 
                   this.yMaharaja == 3 ) {
                try {
                    this.wait();
                } catch (Exception e) { }
            }
        }
    }
    
    public void esperarPieza() {
        synchronized (this) {
            while (getNextX() == -1 && 
                   getNextY() == -1    ) {
                try {
                    this.wait();
                } catch (Exception e) { }
            }
        }
    }
    
    public void esperarCliente() {
        synchronized (this) {
            while (online == false) {
                try {
                    this.wait();
                } catch (Exception e) { }
            }
        }
    }    
    
    public void conectarCliente() {
        synchronized (this) {
            this.online = true;
            this.notifyAll();
        }
    }        
    
    public void mostrarMensaje(String mensaje) {
        areaChat.setText(areaChat.getText()+ "\n" + mensaje);
    }
    
    public void mostrarProgreso(String mensaje) {
        historial.setText(historial.getText()+ "\n" + mensaje);
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton114 = new javax.swing.JButton();
        cuadros = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        pos8a = new javax.swing.JButton();
        pos8b = new javax.swing.JButton();
        pos8c = new javax.swing.JButton();
        pos8d = new javax.swing.JButton();
        pos8e = new javax.swing.JButton();
        pos8f = new javax.swing.JButton();
        pos8g = new javax.swing.JButton();
        pos8h = new javax.swing.JButton();
        jButton83 = new javax.swing.JButton();
        jButton94 = new javax.swing.JButton();
        pos7a = new javax.swing.JButton();
        pos7b = new javax.swing.JButton();
        pos7c = new javax.swing.JButton();
        pos7d = new javax.swing.JButton();
        pos7e = new javax.swing.JButton();
        pos7f = new javax.swing.JButton();
        pos7g = new javax.swing.JButton();
        pos7h = new javax.swing.JButton();
        jButton112 = new javax.swing.JButton();
        jButton115 = new javax.swing.JButton();
        pos6a = new javax.swing.JButton();
        pos6b = new javax.swing.JButton();
        pos6c = new javax.swing.JButton();
        pos6d = new javax.swing.JButton();
        pos6e = new javax.swing.JButton();
        pos6f = new javax.swing.JButton();
        pos6g = new javax.swing.JButton();
        pos6h = new javax.swing.JButton();
        jButton124 = new javax.swing.JButton();
        jButton126 = new javax.swing.JButton();
        pos5a = new javax.swing.JButton();
        pos5b = new javax.swing.JButton();
        pos5c = new javax.swing.JButton();
        pos5d = new javax.swing.JButton();
        pos5e = new javax.swing.JButton();
        pos5f = new javax.swing.JButton();
        pos5g = new javax.swing.JButton();
        pos5h = new javax.swing.JButton();
        jButton135 = new javax.swing.JButton();
        jButton137 = new javax.swing.JButton();
        pos4a = new javax.swing.JButton();
        pos4b = new javax.swing.JButton();
        pos4c = new javax.swing.JButton();
        pos4d = new javax.swing.JButton();
        pos4e = new javax.swing.JButton();
        pos4f = new javax.swing.JButton();
        pos4g = new javax.swing.JButton();
        pos4h = new javax.swing.JButton();
        jButton146 = new javax.swing.JButton();
        jButton148 = new javax.swing.JButton();
        pos3a = new javax.swing.JButton();
        pos3b = new javax.swing.JButton();
        pos3c = new javax.swing.JButton();
        pos3d = new javax.swing.JButton();
        pos3e = new javax.swing.JButton();
        pos3f = new javax.swing.JButton();
        pos3g = new javax.swing.JButton();
        pos3h = new javax.swing.JButton();
        jButton157 = new javax.swing.JButton();
        jButton159 = new javax.swing.JButton();
        pos2a = new javax.swing.JButton();
        pos2b = new javax.swing.JButton();
        pos2c = new javax.swing.JButton();
        pos2d = new javax.swing.JButton();
        pos2e = new javax.swing.JButton();
        pos2f = new javax.swing.JButton();
        pos2g = new javax.swing.JButton();
        pos2h = new javax.swing.JButton();
        jButton168 = new javax.swing.JButton();
        jButton170 = new javax.swing.JButton();
        pos1a = new javax.swing.JButton();
        pos1b = new javax.swing.JButton();
        pos1c = new javax.swing.JButton();
        pos1d = new javax.swing.JButton();
        pos1e = new javax.swing.JButton();
        pos1f = new javax.swing.JButton();
        pos1g = new javax.swing.JButton();
        pos1h = new javax.swing.JButton();
        jButton179 = new javax.swing.JButton();
        jButton181 = new javax.swing.JButton();
        jButton182 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton84 = new javax.swing.JButton();
        jButton113 = new javax.swing.JButton();
        jButton125 = new javax.swing.JButton();
        jButton136 = new javax.swing.JButton();
        jButton147 = new javax.swing.JButton();
        jButton158 = new javax.swing.JButton();
        jButton169 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historial = new javax.swing.JTextArea();
        fieldMensaje = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new RoundButton("");
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaCapturas = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaChat = new javax.swing.JTextArea();
        btnMusica = new RoundButton("");
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jButton114.setBackground(new java.awt.Color(255, 255, 255));
        jButton114.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton114.setText("8");
        jButton114.setBorder(null);
        jButton114.setBorderPainted(false);
        jButton114.setPreferredSize(new java.awt.Dimension(35, 35));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Campo de batalla");
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setFocusable(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(520, 520));
        jPanel1.setLayout(new java.awt.GridLayout(10, 10));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setEnabled(false);
        jButton9.setFocusPainted(false);
        jButton9.setFocusable(false);
        jButton9.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton9);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton10.setText("a");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setEnabled(false);
        jButton10.setFocusPainted(false);
        jButton10.setFocusable(false);
        jButton10.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton10);

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton11.setText("b");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setEnabled(false);
        jButton11.setFocusPainted(false);
        jButton11.setFocusable(false);
        jButton11.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton11);

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton12.setText("c");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setEnabled(false);
        jButton12.setFocusPainted(false);
        jButton12.setFocusable(false);
        jButton12.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton12);

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton22.setText("d");
        jButton22.setBorder(null);
        jButton22.setBorderPainted(false);
        jButton22.setEnabled(false);
        jButton22.setFocusPainted(false);
        jButton22.setFocusable(false);
        jButton22.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton22);

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton23.setText("e");
        jButton23.setBorder(null);
        jButton23.setBorderPainted(false);
        jButton23.setEnabled(false);
        jButton23.setFocusPainted(false);
        jButton23.setFocusable(false);
        jButton23.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton23);

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton24.setText("f");
        jButton24.setBorder(null);
        jButton24.setBorderPainted(false);
        jButton24.setEnabled(false);
        jButton24.setFocusPainted(false);
        jButton24.setFocusable(false);
        jButton24.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton24);

        jButton34.setBackground(new java.awt.Color(255, 255, 255));
        jButton34.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton34.setText("g");
        jButton34.setBorder(null);
        jButton34.setBorderPainted(false);
        jButton34.setEnabled(false);
        jButton34.setFocusPainted(false);
        jButton34.setFocusable(false);
        jButton34.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton34);

        jButton35.setBackground(new java.awt.Color(255, 255, 255));
        jButton35.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton35.setText("h");
        jButton35.setBorder(null);
        jButton35.setBorderPainted(false);
        jButton35.setEnabled(false);
        jButton35.setFocusPainted(false);
        jButton35.setFocusable(false);
        jButton35.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton35);

        jButton36.setBackground(new java.awt.Color(255, 255, 255));
        jButton36.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton36.setBorder(null);
        jButton36.setBorderPainted(false);
        jButton36.setEnabled(false);
        jButton36.setFocusPainted(false);
        jButton36.setFocusable(false);
        jButton36.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton36);

        jButton47.setBackground(new java.awt.Color(255, 255, 255));
        jButton47.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton47.setText("8");
        jButton47.setBorder(null);
        jButton47.setBorderPainted(false);
        jButton47.setEnabled(false);
        jButton47.setFocusPainted(false);
        jButton47.setFocusable(false);
        jButton47.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton47);

        pos8a.setBackground(new java.awt.Color(255, 255, 255));
        pos8a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8a);
        pos8a.setFocusPainted(false);
        pos8a.setFocusable(false);
        pos8a.setName("0,0"); // NOI18N
        pos8a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8a);

        pos8b.setBackground(new java.awt.Color(130, 130, 130));
        pos8b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8b);
        pos8b.setFocusPainted(false);
        pos8b.setFocusable(false);
        pos8b.setName("0,1"); // NOI18N
        pos8b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8b);

        pos8c.setBackground(new java.awt.Color(255, 255, 255));
        pos8c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8c);
        pos8c.setFocusPainted(false);
        pos8c.setFocusable(false);
        pos8c.setName("0,2"); // NOI18N
        pos8c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8c);

        pos8d.setBackground(new java.awt.Color(130, 130, 130));
        pos8d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8d);
        pos8d.setFocusPainted(false);
        pos8d.setFocusable(false);
        pos8d.setName("0,3"); // NOI18N
        pos8d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8d);

        pos8e.setBackground(new java.awt.Color(255, 255, 255));
        pos8e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8e);
        pos8e.setFocusPainted(false);
        pos8e.setFocusable(false);
        pos8e.setName("0,4"); // NOI18N
        pos8e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8e);

        pos8f.setBackground(new java.awt.Color(130, 130, 130));
        pos8f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8f);
        pos8f.setFocusPainted(false);
        pos8f.setFocusable(false);
        pos8f.setName("0,5"); // NOI18N
        pos8f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8f);

        pos8g.setBackground(new java.awt.Color(255, 255, 255));
        pos8g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8g);
        pos8g.setFocusPainted(false);
        pos8g.setFocusable(false);
        pos8g.setName("0,6"); // NOI18N
        pos8g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8g);

        pos8h.setBackground(new java.awt.Color(130, 130, 130));
        pos8h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos8h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos8h);
        pos8h.setFocusPainted(false);
        pos8h.setFocusable(false);
        pos8h.setName("0,7"); // NOI18N
        pos8h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos8h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos8h);

        jButton83.setBackground(new java.awt.Color(255, 255, 255));
        jButton83.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton83.setText("8");
        jButton83.setBorder(null);
        jButton83.setBorderPainted(false);
        jButton83.setEnabled(false);
        jButton83.setFocusPainted(false);
        jButton83.setFocusable(false);
        jButton83.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton83);

        jButton94.setBackground(new java.awt.Color(255, 255, 255));
        jButton94.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton94.setText("7");
        jButton94.setBorder(null);
        jButton94.setBorderPainted(false);
        jButton94.setEnabled(false);
        jButton94.setFocusPainted(false);
        jButton94.setFocusable(false);
        jButton94.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton94);

        pos7a.setBackground(new java.awt.Color(130, 130, 130));
        pos7a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7a);
        pos7a.setFocusPainted(false);
        pos7a.setFocusable(false);
        pos7a.setName("1,0"); // NOI18N
        pos7a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7a);

        pos7b.setBackground(new java.awt.Color(255, 255, 255));
        pos7b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7b);
        pos7b.setFocusPainted(false);
        pos7b.setFocusable(false);
        pos7b.setName("1,1"); // NOI18N
        pos7b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7b);

        pos7c.setBackground(new java.awt.Color(130, 130, 130));
        pos7c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7c);
        pos7c.setFocusPainted(false);
        pos7c.setFocusable(false);
        pos7c.setName("1,2"); // NOI18N
        pos7c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7c);

        pos7d.setBackground(new java.awt.Color(255, 255, 255));
        pos7d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7d);
        pos7d.setFocusPainted(false);
        pos7d.setFocusable(false);
        pos7d.setName("1,3"); // NOI18N
        pos7d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7d);

        pos7e.setBackground(new java.awt.Color(130, 130, 130));
        pos7e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7e);
        pos7e.setFocusPainted(false);
        pos7e.setFocusable(false);
        pos7e.setName("1,4"); // NOI18N
        pos7e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7e);

        pos7f.setBackground(new java.awt.Color(255, 255, 255));
        pos7f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7f);
        pos7f.setFocusPainted(false);
        pos7f.setFocusable(false);
        pos7f.setName("1,5"); // NOI18N
        pos7f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7f);

        pos7g.setBackground(new java.awt.Color(130, 130, 130));
        pos7g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7g);
        pos7g.setFocusPainted(false);
        pos7g.setFocusable(false);
        pos7g.setName("1,6"); // NOI18N
        pos7g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7g);

        pos7h.setBackground(new java.awt.Color(255, 255, 255));
        pos7h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos7h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos7h);
        pos7h.setFocusPainted(false);
        pos7h.setFocusable(false);
        pos7h.setName("1,7"); // NOI18N
        pos7h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos7h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos7h);

        jButton112.setBackground(new java.awt.Color(255, 255, 255));
        jButton112.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton112.setText("7");
        jButton112.setBorder(null);
        jButton112.setBorderPainted(false);
        jButton112.setEnabled(false);
        jButton112.setFocusPainted(false);
        jButton112.setFocusable(false);
        jButton112.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton112);

        jButton115.setBackground(new java.awt.Color(255, 255, 255));
        jButton115.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton115.setText("6");
        jButton115.setBorder(null);
        jButton115.setBorderPainted(false);
        jButton115.setEnabled(false);
        jButton115.setFocusPainted(false);
        jButton115.setFocusable(false);
        jButton115.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton115.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton115);

        pos6a.setBackground(new java.awt.Color(255, 255, 255));
        pos6a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6a);
        pos6a.setFocusPainted(false);
        pos6a.setFocusable(false);
        pos6a.setName("2,0"); // NOI18N
        pos6a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6a);

        pos6b.setBackground(new java.awt.Color(130, 130, 130));
        pos6b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6b);
        pos6b.setFocusPainted(false);
        pos6b.setFocusable(false);
        pos6b.setName("2,1"); // NOI18N
        pos6b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6b);

        pos6c.setBackground(new java.awt.Color(255, 255, 255));
        pos6c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6c);
        pos6c.setFocusPainted(false);
        pos6c.setFocusable(false);
        pos6c.setName("2,2"); // NOI18N
        pos6c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6c);

        pos6d.setBackground(new java.awt.Color(130, 130, 130));
        pos6d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6d);
        pos6d.setFocusPainted(false);
        pos6d.setFocusable(false);
        pos6d.setName("2,3"); // NOI18N
        pos6d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6d);

        pos6e.setBackground(new java.awt.Color(255, 255, 255));
        pos6e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6e);
        pos6e.setFocusPainted(false);
        pos6e.setFocusable(false);
        pos6e.setName("2,4"); // NOI18N
        pos6e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6e);

        pos6f.setBackground(new java.awt.Color(130, 130, 130));
        pos6f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6f);
        pos6f.setFocusPainted(false);
        pos6f.setFocusable(false);
        pos6f.setName("2,5"); // NOI18N
        pos6f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6f);

        pos6g.setBackground(new java.awt.Color(255, 255, 255));
        pos6g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6g);
        pos6g.setFocusPainted(false);
        pos6g.setFocusable(false);
        pos6g.setName("2,6"); // NOI18N
        pos6g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6g);

        pos6h.setBackground(new java.awt.Color(130, 130, 130));
        pos6h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos6h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos6h);
        pos6h.setFocusPainted(false);
        pos6h.setFocusable(false);
        pos6h.setName("2,7"); // NOI18N
        pos6h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos6h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos6h);

        jButton124.setBackground(new java.awt.Color(255, 255, 255));
        jButton124.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton124.setText("6");
        jButton124.setBorder(null);
        jButton124.setBorderPainted(false);
        jButton124.setEnabled(false);
        jButton124.setFocusPainted(false);
        jButton124.setFocusable(false);
        jButton124.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton124);

        jButton126.setBackground(new java.awt.Color(255, 255, 255));
        jButton126.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton126.setText("5");
        jButton126.setBorder(null);
        jButton126.setBorderPainted(false);
        jButton126.setEnabled(false);
        jButton126.setFocusPainted(false);
        jButton126.setFocusable(false);
        jButton126.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton126.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton126);

        pos5a.setBackground(new java.awt.Color(130, 130, 130));
        pos5a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5a);
        pos5a.setFocusPainted(false);
        pos5a.setFocusable(false);
        pos5a.setName("3,0"); // NOI18N
        pos5a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5a);

        pos5b.setBackground(new java.awt.Color(255, 255, 255));
        pos5b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5b);
        pos5b.setFocusPainted(false);
        pos5b.setFocusable(false);
        pos5b.setName("3,1"); // NOI18N
        pos5b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5b);

        pos5c.setBackground(new java.awt.Color(130, 130, 130));
        pos5c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5c);
        pos5c.setFocusPainted(false);
        pos5c.setFocusable(false);
        pos5c.setName("3,2"); // NOI18N
        pos5c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5c);

        pos5d.setBackground(new java.awt.Color(255, 255, 255));
        pos5d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5d);
        pos5d.setFocusPainted(false);
        pos5d.setFocusable(false);
        pos5d.setName("3,3"); // NOI18N
        pos5d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5d);

        pos5e.setBackground(new java.awt.Color(130, 130, 130));
        pos5e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5e);
        pos5e.setFocusPainted(false);
        pos5e.setFocusable(false);
        pos5e.setName("3,4"); // NOI18N
        pos5e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5e);

        pos5f.setBackground(new java.awt.Color(255, 255, 255));
        pos5f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5f);
        pos5f.setFocusPainted(false);
        pos5f.setFocusable(false);
        pos5f.setName("3,5"); // NOI18N
        pos5f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5f);

        pos5g.setBackground(new java.awt.Color(130, 130, 130));
        pos5g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5g);
        pos5g.setFocusPainted(false);
        pos5g.setFocusable(false);
        pos5g.setName("3,6"); // NOI18N
        pos5g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5g);

        pos5h.setBackground(new java.awt.Color(255, 255, 255));
        pos5h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos5h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos5h);
        pos5h.setFocusPainted(false);
        pos5h.setFocusable(false);
        pos5h.setName("3,7"); // NOI18N
        pos5h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos5h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos5h);

        jButton135.setBackground(new java.awt.Color(255, 255, 255));
        jButton135.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton135.setText("5");
        jButton135.setBorder(null);
        jButton135.setBorderPainted(false);
        jButton135.setEnabled(false);
        jButton135.setFocusPainted(false);
        jButton135.setFocusable(false);
        jButton135.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton135.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton135);

        jButton137.setBackground(new java.awt.Color(255, 255, 255));
        jButton137.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton137.setText("4");
        jButton137.setBorder(null);
        jButton137.setBorderPainted(false);
        jButton137.setEnabled(false);
        jButton137.setFocusPainted(false);
        jButton137.setFocusable(false);
        jButton137.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton137.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton137);

        pos4a.setBackground(new java.awt.Color(255, 255, 255));
        pos4a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4a);
        pos4a.setFocusPainted(false);
        pos4a.setFocusable(false);
        pos4a.setName("4,0"); // NOI18N
        pos4a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4a);

        pos4b.setBackground(new java.awt.Color(130, 130, 130));
        pos4b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4b);
        pos4b.setFocusPainted(false);
        pos4b.setFocusable(false);
        pos4b.setName("4,1"); // NOI18N
        pos4b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4b);

        pos4c.setBackground(new java.awt.Color(255, 255, 255));
        pos4c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4c);
        pos4c.setFocusPainted(false);
        pos4c.setFocusable(false);
        pos4c.setName("4,2"); // NOI18N
        pos4c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4c);

        pos4d.setBackground(new java.awt.Color(130, 130, 130));
        pos4d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4d);
        pos4d.setFocusPainted(false);
        pos4d.setFocusable(false);
        pos4d.setName("4,3"); // NOI18N
        pos4d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4d);

        pos4e.setBackground(new java.awt.Color(255, 255, 255));
        pos4e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4e);
        pos4e.setFocusPainted(false);
        pos4e.setFocusable(false);
        pos4e.setName("4,4"); // NOI18N
        pos4e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4e);

        pos4f.setBackground(new java.awt.Color(130, 130, 130));
        pos4f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4f);
        pos4f.setFocusPainted(false);
        pos4f.setFocusable(false);
        pos4f.setName("4,5"); // NOI18N
        pos4f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4f);

        pos4g.setBackground(new java.awt.Color(255, 255, 255));
        pos4g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4g);
        pos4g.setFocusPainted(false);
        pos4g.setFocusable(false);
        pos4g.setName("4,6"); // NOI18N
        pos4g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4g);

        pos4h.setBackground(new java.awt.Color(130, 130, 130));
        pos4h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos4h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos4h);
        pos4h.setFocusPainted(false);
        pos4h.setFocusable(false);
        pos4h.setName("4,7"); // NOI18N
        pos4h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos4h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos4h);

        jButton146.setBackground(new java.awt.Color(255, 255, 255));
        jButton146.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton146.setText("4");
        jButton146.setBorder(null);
        jButton146.setBorderPainted(false);
        jButton146.setEnabled(false);
        jButton146.setFocusPainted(false);
        jButton146.setFocusable(false);
        jButton146.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton146.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton146);

        jButton148.setBackground(new java.awt.Color(255, 255, 255));
        jButton148.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton148.setText("3");
        jButton148.setBorder(null);
        jButton148.setBorderPainted(false);
        jButton148.setEnabled(false);
        jButton148.setFocusPainted(false);
        jButton148.setFocusable(false);
        jButton148.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton148.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton148);

        pos3a.setBackground(new java.awt.Color(130, 130, 130));
        pos3a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3a);
        pos3a.setFocusPainted(false);
        pos3a.setFocusable(false);
        pos3a.setName("5,0"); // NOI18N
        pos3a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3a);

        pos3b.setBackground(new java.awt.Color(255, 255, 255));
        pos3b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3b);
        pos3b.setFocusPainted(false);
        pos3b.setFocusable(false);
        pos3b.setName("5,1"); // NOI18N
        pos3b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3b);

        pos3c.setBackground(new java.awt.Color(130, 130, 130));
        pos3c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3c);
        pos3c.setFocusPainted(false);
        pos3c.setFocusable(false);
        pos3c.setName("5,2"); // NOI18N
        pos3c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3c);

        pos3d.setBackground(new java.awt.Color(255, 255, 255));
        pos3d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3d);
        pos3d.setFocusPainted(false);
        pos3d.setFocusable(false);
        pos3d.setName("5,3"); // NOI18N
        pos3d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3d);

        pos3e.setBackground(new java.awt.Color(130, 130, 130));
        pos3e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3e);
        pos3e.setFocusPainted(false);
        pos3e.setFocusable(false);
        pos3e.setName("5,4"); // NOI18N
        pos3e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3e);

        pos3f.setBackground(new java.awt.Color(255, 255, 255));
        pos3f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3f);
        pos3f.setFocusPainted(false);
        pos3f.setFocusable(false);
        pos3f.setName("5,5"); // NOI18N
        pos3f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3f);

        pos3g.setBackground(new java.awt.Color(130, 130, 130));
        pos3g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3g);
        pos3g.setFocusPainted(false);
        pos3g.setFocusable(false);
        pos3g.setName("5,6"); // NOI18N
        pos3g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3g);

        pos3h.setBackground(new java.awt.Color(255, 255, 255));
        pos3h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos3h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos3h);
        pos3h.setFocusPainted(false);
        pos3h.setFocusable(false);
        pos3h.setName("5,7"); // NOI18N
        pos3h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos3h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos3h);

        jButton157.setBackground(new java.awt.Color(255, 255, 255));
        jButton157.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton157.setText("3");
        jButton157.setBorder(null);
        jButton157.setBorderPainted(false);
        jButton157.setEnabled(false);
        jButton157.setFocusPainted(false);
        jButton157.setFocusable(false);
        jButton157.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton157.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton157);

        jButton159.setBackground(new java.awt.Color(255, 255, 255));
        jButton159.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton159.setText("2");
        jButton159.setBorder(null);
        jButton159.setBorderPainted(false);
        jButton159.setEnabled(false);
        jButton159.setFocusPainted(false);
        jButton159.setFocusable(false);
        jButton159.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton159.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton159);

        pos2a.setBackground(new java.awt.Color(255, 255, 255));
        pos2a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2a);
        pos2a.setFocusPainted(false);
        pos2a.setFocusable(false);
        pos2a.setName("6,0"); // NOI18N
        pos2a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2a);

        pos2b.setBackground(new java.awt.Color(130, 130, 130));
        pos2b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2b);
        pos2b.setFocusPainted(false);
        pos2b.setFocusable(false);
        pos2b.setName("6,1"); // NOI18N
        pos2b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2b);

        pos2c.setBackground(new java.awt.Color(255, 255, 255));
        pos2c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2c);
        pos2c.setFocusPainted(false);
        pos2c.setFocusable(false);
        pos2c.setName("6,2"); // NOI18N
        pos2c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2c);

        pos2d.setBackground(new java.awt.Color(130, 130, 130));
        pos2d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2d);
        pos2d.setFocusPainted(false);
        pos2d.setFocusable(false);
        pos2d.setName("6,3"); // NOI18N
        pos2d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2d);

        pos2e.setBackground(new java.awt.Color(255, 255, 255));
        pos2e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2e);
        pos2e.setFocusPainted(false);
        pos2e.setFocusable(false);
        pos2e.setName("6,4"); // NOI18N
        pos2e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2e);

        pos2f.setBackground(new java.awt.Color(130, 130, 130));
        pos2f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2f);
        pos2f.setFocusPainted(false);
        pos2f.setFocusable(false);
        pos2f.setName("6,5"); // NOI18N
        pos2f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2f);

        pos2g.setBackground(new java.awt.Color(255, 255, 255));
        pos2g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2g);
        pos2g.setFocusPainted(false);
        pos2g.setFocusable(false);
        pos2g.setName("6,6"); // NOI18N
        pos2g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2g);

        pos2h.setBackground(new java.awt.Color(130, 130, 130));
        pos2h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos2h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos2h);
        pos2h.setFocusPainted(false);
        pos2h.setFocusable(false);
        pos2h.setName("6,7"); // NOI18N
        pos2h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos2h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos2h);

        jButton168.setBackground(new java.awt.Color(255, 255, 255));
        jButton168.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton168.setText("2");
        jButton168.setBorder(null);
        jButton168.setBorderPainted(false);
        jButton168.setEnabled(false);
        jButton168.setFocusPainted(false);
        jButton168.setFocusable(false);
        jButton168.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton168.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton168);

        jButton170.setBackground(new java.awt.Color(255, 255, 255));
        jButton170.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton170.setText("1");
        jButton170.setBorder(null);
        jButton170.setBorderPainted(false);
        jButton170.setEnabled(false);
        jButton170.setFocusPainted(false);
        jButton170.setFocusable(false);
        jButton170.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton170.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton170);

        pos1a.setBackground(new java.awt.Color(130, 130, 130));
        pos1a.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1a);
        pos1a.setFocusPainted(false);
        pos1a.setFocusable(false);
        pos1a.setName("7,0"); // NOI18N
        pos1a.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1a);

        pos1b.setBackground(new java.awt.Color(255, 255, 255));
        pos1b.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1b);
        pos1b.setFocusPainted(false);
        pos1b.setFocusable(false);
        pos1b.setName("7,1"); // NOI18N
        pos1b.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1b);

        pos1c.setBackground(new java.awt.Color(130, 130, 130));
        pos1c.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1c.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1c);
        pos1c.setFocusPainted(false);
        pos1c.setFocusable(false);
        pos1c.setName("7,2"); // NOI18N
        pos1c.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1c);

        pos1d.setBackground(new java.awt.Color(255, 255, 255));
        pos1d.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1d.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1d);
        pos1d.setFocusPainted(false);
        pos1d.setFocusable(false);
        pos1d.setName("7,3"); // NOI18N
        pos1d.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1d);

        pos1e.setBackground(new java.awt.Color(130, 130, 130));
        pos1e.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1e.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1e);
        pos1e.setFocusPainted(false);
        pos1e.setFocusable(false);
        pos1e.setName("7,4"); // NOI18N
        pos1e.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1e);

        pos1f.setBackground(new java.awt.Color(255, 255, 255));
        pos1f.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1f.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1f);
        pos1f.setFocusPainted(false);
        pos1f.setFocusable(false);
        pos1f.setName("7,5"); // NOI18N
        pos1f.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1f);

        pos1g.setBackground(new java.awt.Color(130, 130, 130));
        pos1g.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1g.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1g);
        pos1g.setFocusPainted(false);
        pos1g.setFocusable(false);
        pos1g.setName("7,6"); // NOI18N
        pos1g.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1g);

        pos1h.setBackground(new java.awt.Color(255, 255, 255));
        pos1h.setFont(new java.awt.Font("Arial Unicode MS", 0, 28)); // NOI18N
        pos1h.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cuadros.add(pos1h);
        pos1h.setFocusPainted(false);
        pos1h.setFocusable(false);
        pos1h.setName("7,7"); // NOI18N
        pos1h.setPreferredSize(new java.awt.Dimension(35, 35));
        pos1h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(pos1h);

        jButton179.setBackground(new java.awt.Color(255, 255, 255));
        jButton179.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton179.setText("1");
        jButton179.setBorder(null);
        jButton179.setBorderPainted(false);
        jButton179.setEnabled(false);
        jButton179.setFocusPainted(false);
        jButton179.setFocusable(false);
        jButton179.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton179.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton179);

        jButton181.setBackground(new java.awt.Color(255, 255, 255));
        jButton181.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton181.setBorder(null);
        jButton181.setBorderPainted(false);
        jButton181.setEnabled(false);
        jButton181.setFocusPainted(false);
        jButton181.setFocusable(false);
        jButton181.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton181.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton181);

        jButton182.setBackground(new java.awt.Color(255, 255, 255));
        jButton182.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton182.setText("a");
        jButton182.setBorder(null);
        jButton182.setBorderPainted(false);
        jButton182.setEnabled(false);
        jButton182.setFocusPainted(false);
        jButton182.setFocusable(false);
        jButton182.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton182.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton182);

        jButton46.setBackground(new java.awt.Color(255, 255, 255));
        jButton46.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton46.setText("b");
        jButton46.setBorder(null);
        jButton46.setBorderPainted(false);
        jButton46.setEnabled(false);
        jButton46.setFocusPainted(false);
        jButton46.setFocusable(false);
        jButton46.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton46);

        jButton84.setBackground(new java.awt.Color(255, 255, 255));
        jButton84.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton84.setText("c");
        jButton84.setBorder(null);
        jButton84.setBorderPainted(false);
        jButton84.setEnabled(false);
        jButton84.setFocusPainted(false);
        jButton84.setFocusable(false);
        jButton84.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton84);

        jButton113.setBackground(new java.awt.Color(255, 255, 255));
        jButton113.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton113.setText("d");
        jButton113.setBorder(null);
        jButton113.setBorderPainted(false);
        jButton113.setEnabled(false);
        jButton113.setFocusPainted(false);
        jButton113.setFocusable(false);
        jButton113.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton113);

        jButton125.setBackground(new java.awt.Color(255, 255, 255));
        jButton125.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton125.setText("e");
        jButton125.setBorder(null);
        jButton125.setBorderPainted(false);
        jButton125.setEnabled(false);
        jButton125.setFocusPainted(false);
        jButton125.setFocusable(false);
        jButton125.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton125);

        jButton136.setBackground(new java.awt.Color(255, 255, 255));
        jButton136.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton136.setText("f");
        jButton136.setBorder(null);
        jButton136.setBorderPainted(false);
        jButton136.setEnabled(false);
        jButton136.setFocusPainted(false);
        jButton136.setFocusable(false);
        jButton136.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton136.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton136);

        jButton147.setBackground(new java.awt.Color(255, 255, 255));
        jButton147.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton147.setText("g");
        jButton147.setBorder(null);
        jButton147.setBorderPainted(false);
        jButton147.setEnabled(false);
        jButton147.setFocusPainted(false);
        jButton147.setFocusable(false);
        jButton147.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton147.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton147);

        jButton158.setBackground(new java.awt.Color(255, 255, 255));
        jButton158.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton158.setText("h");
        jButton158.setBorder(null);
        jButton158.setBorderPainted(false);
        jButton158.setEnabled(false);
        jButton158.setFocusPainted(false);
        jButton158.setFocusable(false);
        jButton158.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton158.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton158);

        jButton169.setBackground(new java.awt.Color(255, 255, 255));
        jButton169.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButton169.setBorder(null);
        jButton169.setBorderPainted(false);
        jButton169.setEnabled(false);
        jButton169.setFocusPainted(false);
        jButton169.setFocusable(false);
        jButton169.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton169.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalAction(evt);
            }
        });
        jPanel1.add(jButton169);

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chat");

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Progreso del juego");

        DefaultCaret caret = (DefaultCaret) historial.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        historial.setEditable(false);
        historial.setColumns(20);
        historial.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        historial.setLineWrap(true);
        historial.setRows(5);
        historial.setWrapStyleWord(true);
        historial.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane1.setViewportView(historial);

        fieldMensaje.setText("Escriba aquí para chatear...");
        fieldMensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldMensajeMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("Solicitar Tablas");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Rendirse");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("?");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Salir del juego");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Capturas del Maharajá");

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        areaCapturas.setEditable(false);
        areaCapturas.setColumns(20);
        areaCapturas.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        areaCapturas.setLineWrap(true);
        areaCapturas.setRows(5);
        areaCapturas.setWrapStyleWord(true);
        areaCapturas.setAutoscrolls(false);
        jScrollPane3.setViewportView(areaCapturas);

        DefaultCaret chatCaret = (DefaultCaret) areaChat.getCaret();
        chatCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        areaChat.setEditable(false);
        areaChat.setColumns(20);
        areaChat.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        areaChat.setLineWrap(true);
        areaChat.setRows(5);
        areaChat.setWrapStyleWord(true);
        areaChat.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane4.setViewportView(areaChat);

        btnMusica.setBackground(new java.awt.Color(0, 0, 0));
        btnMusica.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        btnMusica.setForeground(new java.awt.Color(255, 255, 255));
        btnMusica.setText("O");
        btnMusica.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMusica.setBorderPainted(false);
        btnMusica.setFocusable(false);
        btnMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusicaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("¿Música?");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("Reiniciar");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fieldMensaje)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldMensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldMensajeMouseClicked
        // TODO add your handling code here:
        fieldMensaje.setText("");
    }//GEN-LAST:event_fieldMensajeMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Estás seguro de que quieres salir del juego?","Atención", 1);
        if (dialogResult == JOptionPane.YES_OPTION) { System.exit(0); } 
        else {}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void generalAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalAction
        Object source = evt.getSource();
        if (source instanceof JButton) {
            JButton btn = (JButton) source;
            convertirNotacion(btn.getName());
        }
    }//GEN-LAST:event_generalAction

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        elegirPieza(-3, -3);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        elegirPieza(-2, -2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        elegirPieza(-4, -4);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("src/GUI/resources/manual.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (Exception ex) {
                // no application registered for PDFs
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusicaActionPerformed
        // TODO add your handling code here:
        if (sonido.isAlive()) {
            sonido.stop();
        } else {
            sonido = new SoundClip("intro.wav");
            sonido.start();
        }
    }//GEN-LAST:event_btnMusicaActionPerformed
    

    /**
     */
    public void correGUI() {
        
        main = this;
        main.setLocationRelativeTo(null);
        main.setVisible(true);
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        if (main.multiplayer != true) {
            main.areaChat.setEnabled(false);
            main.fieldMensaje.setEnabled(false);
            main.fieldMensaje.setText("Chat inhabilitado.");
        }
        
        if (main.multiplayer == true && main.host == true) {
            try {
                jButton2.setEnabled(false);
                cliente = new Socket(InetAddress.getByName(ip), 9729); // Comunicarme con el servidor
                main.mostrarMensaje("Cliente en línea.\n");

                // Ejecucion de los Threads
                executor.execute(new RecibirCliente(cliente, main));
                executor.execute(new EnviarCliente(cliente, main));

            } catch (IOException ex) { //Fin del catch

            }
            
            finally {
                executor.shutdown();
            }
        }

        if (main.multiplayer == true && main.host == false) {
              try {
                jButton2.setEnabled(false);
                cliente = new Socket(InetAddress.getByName(ip), 9729); // Comunicarme con el servidor
                main.mostrarMensaje("Cliente en línea.\n");

                // Ejecucion de los Threads
                executor.execute(new RecibirCliente(cliente, main));
                executor.execute(new EnviarCliente(cliente, main));

            } catch (IOException ex) { //Fin del catch

            }
              
            finally {
                executor.shutdown();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea areaCapturas;
    public javax.swing.JTextArea areaChat;
    private javax.swing.JButton btnMusica;
    private javax.swing.ButtonGroup cuadros;
    public javax.swing.JTextField fieldMensaje;
    public javax.swing.JTextArea historial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton112;
    private javax.swing.JButton jButton113;
    private javax.swing.JButton jButton114;
    private javax.swing.JButton jButton115;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton124;
    private javax.swing.JButton jButton125;
    private javax.swing.JButton jButton126;
    private javax.swing.JButton jButton135;
    private javax.swing.JButton jButton136;
    private javax.swing.JButton jButton137;
    private javax.swing.JButton jButton146;
    private javax.swing.JButton jButton147;
    private javax.swing.JButton jButton148;
    private javax.swing.JButton jButton157;
    private javax.swing.JButton jButton158;
    private javax.swing.JButton jButton159;
    private javax.swing.JButton jButton168;
    private javax.swing.JButton jButton169;
    private javax.swing.JButton jButton170;
    private javax.swing.JButton jButton179;
    private javax.swing.JButton jButton181;
    private javax.swing.JButton jButton182;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton83;
    private javax.swing.JButton jButton84;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton94;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton pos1a;
    private javax.swing.JButton pos1b;
    private javax.swing.JButton pos1c;
    private javax.swing.JButton pos1d;
    private javax.swing.JButton pos1e;
    private javax.swing.JButton pos1f;
    private javax.swing.JButton pos1g;
    private javax.swing.JButton pos1h;
    private javax.swing.JButton pos2a;
    private javax.swing.JButton pos2b;
    private javax.swing.JButton pos2c;
    private javax.swing.JButton pos2d;
    private javax.swing.JButton pos2e;
    private javax.swing.JButton pos2f;
    private javax.swing.JButton pos2g;
    private javax.swing.JButton pos2h;
    private javax.swing.JButton pos3a;
    private javax.swing.JButton pos3b;
    private javax.swing.JButton pos3c;
    private javax.swing.JButton pos3d;
    private javax.swing.JButton pos3e;
    private javax.swing.JButton pos3f;
    private javax.swing.JButton pos3g;
    private javax.swing.JButton pos3h;
    private javax.swing.JButton pos4a;
    private javax.swing.JButton pos4b;
    private javax.swing.JButton pos4c;
    private javax.swing.JButton pos4d;
    private javax.swing.JButton pos4e;
    private javax.swing.JButton pos4f;
    private javax.swing.JButton pos4g;
    private javax.swing.JButton pos4h;
    private javax.swing.JButton pos5a;
    private javax.swing.JButton pos5b;
    private javax.swing.JButton pos5c;
    private javax.swing.JButton pos5d;
    private javax.swing.JButton pos5e;
    private javax.swing.JButton pos5f;
    private javax.swing.JButton pos5g;
    private javax.swing.JButton pos5h;
    private javax.swing.JButton pos6a;
    private javax.swing.JButton pos6b;
    private javax.swing.JButton pos6c;
    private javax.swing.JButton pos6d;
    private javax.swing.JButton pos6e;
    private javax.swing.JButton pos6f;
    private javax.swing.JButton pos6g;
    private javax.swing.JButton pos6h;
    private javax.swing.JButton pos7a;
    private javax.swing.JButton pos7b;
    private javax.swing.JButton pos7c;
    private javax.swing.JButton pos7d;
    private javax.swing.JButton pos7e;
    private javax.swing.JButton pos7f;
    private javax.swing.JButton pos7g;
    private javax.swing.JButton pos7h;
    private javax.swing.JButton pos8a;
    private javax.swing.JButton pos8b;
    private javax.swing.JButton pos8c;
    private javax.swing.JButton pos8d;
    private javax.swing.JButton pos8e;
    private javax.swing.JButton pos8f;
    private javax.swing.JButton pos8g;
    private javax.swing.JButton pos8h;
    // End of variables declaration//GEN-END:variables
}
