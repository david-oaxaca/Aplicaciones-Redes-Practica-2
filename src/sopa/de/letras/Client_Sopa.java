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
 * @author David Madrigal Buendía
 * @author David Arturo Oaxaca Pérez
 */
public class Client_Sopa {
    private ArrayList <Coordenadas> palabras;//Palabras dentro de la sopa de letras
    private char [][] SopaDeLetras;
    private boolean seleccionar;
    private int[] punto1, punto2;
    private String palabra_encontrada;
    private int palabras_encontradas;
    private long tiempo_inicio;
    
    public Client_Sopa(){
        seleccionar= false;
        punto1= new int[2];
        punto2= new int[2];
        palabra_encontrada= "";
        palabras_encontradas= 0;
    }
    
    public int guardarSeleccion(int x, int y) {
        if(!seleccionar) {
            seleccionar= true;
            punto1[0]= x;
            punto1[1]= y;
            return 1;
        }else {
            seleccionar= false;
            punto2[0]= x;
            punto2[1]= y;
            return verificarSeleccion();
        }
    }
    //Devuleve verdadero si la palabra fue correcta
    private int verificarSeleccion() {
        System.out.println("Puntos: " + punto1[0] + ", " + punto1[1] + ": " + punto2[0] + ", " + punto2[1]);
        for(Coordenadas palabra: palabras) {
            System.out.println("Puntos Palabra: " + palabra.getInicio_x() + ", " + palabra.getInicio_y() + ": " + palabra.getFinal_x() + ", " + palabra.getFinal_y());
            if(punto1[0] == palabra.getInicio_x() &&
                    punto1[1] == palabra.getInicio_y() &&
                    punto2[0] == palabra.getFinal_x() &&
                    punto2[1] == palabra.getFinal_y()) {
                System.out.println("Encontro: " + palabra.getPalabra());
                palabra_encontrada= palabra.getPalabra();
                palabras_encontradas++;
                return 2;
            }else if(punto1[0] == palabra.getFinal_x() &&
                        punto1[1] == palabra.getFinal_y() &&
                        punto2[0] == palabra.getInicio_x() &&
                        punto2[1] == palabra.getInicio_y()) {
                System.out.println("Encontro: " + palabra.getPalabra());
                palabra_encontrada= palabra.getPalabra();
                palabras_encontradas++;
                return 2;
            }
        }
        return 0;
    }
    
    public int getxPos1() { return punto1[0]; }
    public int getyPos1() { return punto1[1]; }
    public int getxPos2() { return punto2[0]; }
    public int getyPos2() { return punto2[1]; }
    public String getPalabraEncontrada() { return palabra_encontrada; }
    public int getPalabrasEncontradas() { return palabras_encontradas; }
    
    public void inicializarTiempo() {
        tiempo_inicio= System.currentTimeMillis();
    }
    
    public long terminarTiempo() {
        long tiempo_final= System.currentTimeMillis();
        return tiempo_final - tiempo_inicio;
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
