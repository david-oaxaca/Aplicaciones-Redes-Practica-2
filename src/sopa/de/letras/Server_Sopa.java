/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa.de.letras;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tdwda
 */
public class Server_Sopa {
    
    public static void main(String[] args) {
        
        Operaciones_Sopa operaciones = new Operaciones_Sopa();
        char [][] sopa = new char[16][16];
        int puerto = 4000;
        
        String [] Animales = {"PERRO", "GATO", "RATON", "AVESTRUZ", "PATO", "ORNITORRINCO", 
                                "MONO", "ZORRO", "LOBO", "MARIPOSA", "CABALLO", "CAMELLO", "JABALI",
                                "ZURICATA", "HURON", "TLACUACHE", "BALLENA", "TIBURON", "DINGO",
                                "CARPINCHO"};
        
        String [] Comida = {"PIZZA", "HAMBURGUESA", "SANDWICH", "SUSHI", "MILANESA", "TACOS",
                            "SPAGHETTI", "LASAGNA", "TLAYUDAS", "CEREAL", "BROWNIES", "GALLETAS",
                            "BOLILLO", "TORTA", "ENSALADA", "FILETE", "SOPA", "ALITAS", "NACHOS",
                            "ATUN"};
        
        sopa = operaciones.llenarSopa(sopa, Animales);
        operaciones.imprimirSopa(sopa);
        
        ArrayList <String> palabras = operaciones.getPalabras();
        
        for (int i = 0; i < 10; i++) {
            System.out.println(palabras.get(i));
        }
        
        try {
            ServerSocket ss = new ServerSocket(puerto);
            ss.setReuseAddress(true);
            
            System.out.println("Esperando a un cliente...");
            
            Socket sc = ss.accept();
            
            System.out.println("Cliente conectado desde:"+sc.getInetAddress()+":"+sc.getPort());
        
            ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
                    
            oos.writeObject(sopa);
            
            oos.flush();
            
            oos.writeObject(palabras);
            
        } catch (IOException ex) {
            Logger.getLogger(Server_Sopa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
