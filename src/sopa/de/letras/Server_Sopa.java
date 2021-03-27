/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa.de.letras;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        Operaciones_Sopa operaciones = new Operaciones_Sopa();
        
        int puerto = 4000;
        
        String [] Animales = {"PERRO", "GATO", "RATON", "AVESTRUZ", "PATO", "ORNITORRINCO", 
                                "MONO", "ZORRO", "LOBO", "MARIPOSA", "CABALLO", "CAMELLO", "JABALI",
                                "ZURICATA", "HURON", "TLACUACHE", "BALLENA", "TIBURON", "DINGO",
                                "CARPINCHO"};
        
        String [] Comida = {"PIZZA", "HAMBURGUESA", "SANDWICH", "SUSHI", "MILANESA", "TACOS",
                            "SPAGHETTI", "LASAGNA", "TLAYUDAS", "CEREAL", "BROWNIES", "GALLETAS",
                            "BOLILLO", "TORTA", "ENSALADA", "FILETE", "SOPA", "ALITAS", "NACHOS",
                            "ATUN"};
        
        String [] Paises = {"MEXICO", "CHILE", "BRAZIL", "FRANCIA", "ALEMANIA", "JAPON",
                            "ARGENTINA", "CHINA", "INDIA", "AUSTRALIA", "RUSIA", "ITALIA",
                            "IRLANDA", "SUECIA", "SUIZA", "HONDURAS", "BOLIVIA", "MADAGASCAR", 
                            "CANADA","POLONIA"};
        
        String [] Pokemon = {"CHARIZARD", "SQUIRTLE", "BULBASAUR", "BLAZIKEN", "MEWTWO", "SNORLAX",
                            "EEVE", "SYLVEON", "POOCHYENA", "METAPOD", "BIDOOF", "SCIZOR",
                            "LOTAD", "LUDICULO", "MEOWTH", "ARTICUNO", "ZAPDOS", "MOLTRES", 
                            "PIKACHU","ARCEUS"};
        
        
        
        try {
            ServerSocket ss = new ServerSocket(puerto);
            ss.setReuseAddress(true);
            
            for(;;){
                System.out.println("Esperando a un cliente...");

                Socket sc = ss.accept();

                System.out.println("Cliente conectado desde:"+sc.getInetAddress()+":"+sc.getPort());

                ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
        
                String tema  = (String)ois.readObject();

                System.out.println(tema);
                
                char [][] sopa = new char[16][16];
                
                switch(tema){
                    
                    case "Animales":
                        sopa = operaciones.llenarSopa(sopa, Animales);
                        break;
                    case "Comida":
                        sopa = operaciones.llenarSopa(sopa, Comida);
                        break;
                    case "Paises":
                        sopa = operaciones.llenarSopa(sopa, Paises);
                        break;
                    case "Pokemon":
                        sopa = operaciones.llenarSopa(sopa, Pokemon);
                        break;
                    default:
                        sopa = operaciones.llenarSopa(sopa, Animales);
                        break;
                }
                
                ArrayList <Coordenadas> palabras = operaciones.getPalabras();

                System.out.println("\nPalabras y coordenadas de la sopa: \n");
                
                for (int i = 0; i < palabras.size(); i++) {
                    System.out.print(palabras.get(i).getPalabra() + "  ");
                    System.out.print(palabras.get(i).getInicio_x()+ " , " + palabras.get(i).getInicio_y() + "  ");
                    System.out.print(palabras.get(i).getFinal_x() + " , " + palabras.get(i).getFinal_y());
                    System.out.println("");
                }
                
                oos.writeObject(sopa);

                oos.flush();

                oos.writeObject(palabras);

                oos.flush();
                
                ois.close();
                oos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server_Sopa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
