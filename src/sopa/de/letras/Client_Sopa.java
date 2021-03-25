/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa.de.letras;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tdwda
 */
public class Client_Sopa {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Socket cl = new Socket("localhost", 4000);
        Operaciones_Sopa operaciones = new Operaciones_Sopa();
        ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
        char [][] Sopa  = (char[][])ois.readObject();
        
        operaciones.imprimirSopa(Sopa);
        
        ArrayList <String> palabras = (ArrayList <String>)ois.readObject();
        
        for (int i = 0; i < 10; i++) {
            System.out.println(palabras.get(i));
        }
        
        
    }
}
