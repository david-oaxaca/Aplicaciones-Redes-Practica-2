/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa.de.letras;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tdwda
 */
public class Client_Sopa {
    
    private ArrayList <Coordenadas> palabras;//Palabras dentro de la sopa de letras
    private char [][] SopaDeLetras;
    
    public Client_Sopa(){
        
    }
    
    public void requestSopa( String opc ) throws IOException, ClassNotFoundException {
        
        Socket cl = new Socket("localhost", 4000);
        
        //Operaciones_Sopa operaciones = new Operaciones_Sopa();
        
        ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
        
        ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());

        oos.writeObject(opc);
        oos.flush();
        
        this.SopaDeLetras  = (char[][])ois.readObject();
        
        this.palabras = (ArrayList <Coordenadas>)ois.readObject();
        
        oos.close();
        ois.close();
        
    }
    
    public ArrayList <String> getPalabrasB(){
        
        ArrayList <String> temp = new ArrayList<String>();
        
        for (Coordenadas palabra : this.palabras) {
            temp.add(palabra.getPalabra());
        }
        
        return temp;
    }
    
    public char [][] getSopaDeLetras(){
        
        return this.SopaDeLetras;
    }
}
