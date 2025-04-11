package vista;

import Ventanas.VentanaAltas;
import Ventanas.VentanaBajas;
import Ventanas.VentanaConsultas;
import Ventanas.VentanaModificaciones;

import controlador.AlumnoDAO;
import modelo.Alumno;
import modelo.ResultSetTableModel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaInicio extends JFrame implements ActionListener, KeyListener {

    JMenu menuAlumnos, menuAsignaturas;

    JMenuItem altas, bajas, cambios, consultas;


    public VentanaInicio() {

        getContentPane().setLayout(new BorderLayout());;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Proyecto ABCC");

        setSize(700, 700);

        setVisible(true);

        setLocationRelativeTo(null);

        //Widgets

        //---------------Menú---------------

        JMenuBar menuBar = new JMenuBar();

        menuAlumnos = new JMenu("Alumnos");

        menuAlumnos.setMnemonic(KeyEvent.VK_A);

        //menuAlumnos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        altas = new JMenuItem("Agregar");

        altas.setMnemonic(KeyEvent.VK_E);

        altas.addActionListener(this);

        //altas.setIcon(new ImageIcon("./img/java.png"));

        altas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        bajas = new JMenuItem("Eliminar");

        bajas.addActionListener(this);

        cambios = new JMenuItem("Combios");

        cambios.addActionListener(this);

        consultas = new JMenuItem("Consultas");

        consultas.addActionListener(this);

        menuAlumnos.add(altas);

        menuAlumnos.add(bajas);

        menuAlumnos.add(cambios);

        menuAlumnos.add(consultas);


        menuBar.add(menuAlumnos);

        menuAsignaturas = new JMenu("Asignaturas");

        menuBar.add(menuAsignaturas);

        setJMenuBar(menuBar);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == altas) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new VentanaAltas();

                }
            });

        }

        if(e.getSource() == bajas) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new VentanaBajas();

                }
            });

        }

        if(e.getSource() == cambios) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new VentanaModificaciones();

                }
            });

        }

        if(e.getSource() == consultas) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new VentanaConsultas();

                }
            });

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

            @Override
            public void run() {

                new VentanaInicio();

            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actualizarTabla(JTable tabla){

        final String CONTROLADOR_JDBC = "com.mysql.cj.jdbc.Driver";

        final String URL = "jdbc:mysql://localhost:3306/bd_Topicos_2025";

        final String CONSULTA = "SELECT * FROM Alumnos";

        try {
            ResultSetTableModel modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL, CONSULTA);

            tabla.setModel(modelo);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }


}
