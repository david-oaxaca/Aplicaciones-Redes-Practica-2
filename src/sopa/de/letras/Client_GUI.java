/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa.de.letras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author tdwda
 */
public class Client_GUI extends JFrame implements ActionListener{
    private JLabel [][] Sopa;
    private JLabel [] PalabrasB;
    private JLabel lbl_Titulo, lbl_SubTitulo;
    private JButton btn_Terminar;
    private Client_Sopa Cliente_s = new Client_Sopa();
    
    public Client_GUI() throws IOException, ClassNotFoundException{
        iniciarVentana("Practica 2");
        setVisible(true);
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        new Client_GUI();
            
    }
    
    public void iniciarVentana(String nombre) throws IOException, ClassNotFoundException{
        this.setTitle(nombre);
        this.setSize(850, 650);
        this.setLayout(null);
        
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents();
        this.setVisible(true);
    }
    
    
    public void addComponents() throws IOException, ClassNotFoundException{
        lbl_Titulo = new JLabel("Practica 2: Sopa de letras", SwingConstants.CENTER);
        lbl_Titulo.setBounds(0, 0, 825, 70);
        lbl_Titulo.setFont(new Font("Sans", Font.PLAIN, 35));
        this.add(lbl_Titulo);
        
        lbl_SubTitulo = new JLabel("Encuentra las siguientes palabras: ", SwingConstants.CENTER);
        lbl_SubTitulo.setBounds(20, 70, 270, 28);
        lbl_SubTitulo.setFont(new Font("Sans", Font.PLAIN, 17));
        this.add(lbl_SubTitulo);
        
        iniciarSopa();
        
        btn_Terminar = new JButton("Terminar juego");
        btn_Terminar.setBounds(75, 550, 125, 40);
        btn_Terminar.addActionListener(this);
        this.add(btn_Terminar);
        
        
    }
    
    public void iniciarSopa() throws IOException, ClassNotFoundException{
        
        String Sopa_tema = seleccionSopa();
        
        //JOptionPane.showMessageDialog(null, Sopa_tema);
        
        if ((Sopa_tema != null) && (Sopa_tema.length() > 0)) {
            Cliente_s.requestSopa(Sopa_tema);
            crearPalabrasList( Cliente_s.getPalabrasB() );
            crearSopaUI(Cliente_s.getSopaDeLetras());
        }else{
            System.exit(0);
        }
        
    }
    
    
    public String seleccionSopa(){
        String [] sopa_tema = {"Animales", "Comida", "Paises", "Pokemon"};
        String s = (String)JOptionPane.showInputDialog(
                            null,"Seleccione el tema de la sopa de letras: \t\t\n\n",
                            "Practica 2: Sopa de letras", JOptionPane.QUESTION_MESSAGE,
                            null, sopa_tema, "Animales");

        return s;
        
    }
    
    public void crearPalabrasList(ArrayList <String> palabrasList) throws IOException, ClassNotFoundException{
        
        PalabrasB = new JLabel[palabrasList.size()];
        
        for (int i = 0; i < palabrasList.size(); i++) {
            PalabrasB[i] = new JLabel(palabrasList.get(i));
            PalabrasB[i].setBounds(40, 100 + (i*28), 250, 28);
            PalabrasB[i].setVisible(true);
            PalabrasB[i].setFont(new Font("Sans", Font.PLAIN, 14));
            
            this.add(PalabrasB[i]);
        }
        
     }
    
    
    public void crearSopaUI(char [][] Matrix) throws IOException, ClassNotFoundException{
       
        Sopa = new JLabel [Matrix.length][Matrix[0].length];
        
        for (int i = 0; i < Matrix.length; i++) {
            
            for (int j = 0; j < Matrix[i].length; j++) {
                
                Sopa[i][j] = new JLabel("" + Matrix[i][j], SwingConstants.CENTER);
                Sopa[i][j].setBounds(325 + (j*30), 85 + (i*30), 30, 30);
                Sopa[i][j].setVisible(true);
                Sopa[i][j].setOpaque(true);
                Sopa[i][j].setBackground(Color.white);
                Sopa[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JLabel Label = (JLabel)e.getSource();
                        Label.setBackground(Color.BLUE);
                        Label.setForeground(Color.white);
                    }

                });
                Sopa[i][j].setBorder(LineBorder.createBlackLineBorder());
                this.add(Sopa[i][j]);
                
            }
            
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();
        if(evento.equals("Terminar juego")){
            
        }
    }
}
