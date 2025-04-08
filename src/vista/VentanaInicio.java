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

    JInternalFrame IF_Altas;

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



        //--------------IternalFrames-------------


        JDesktopPane deskTopPane = new JDesktopPane();

        IF_Altas =  new JInternalFrame();

        IF_Altas.setSize(500, 200);

        //IF_Altas.setVisible(true);

        IF_Altas.setClosable(true);

        IF_Altas.setMaximizable(true);

        IF_Altas.setClosable(true);

        IF_Altas.setMaximizable(true);

        IF_Altas.setIconifiable(true);

        IF_Altas.setResizable(true);

        IF_Altas.setDefaultCloseOperation(HIDE_ON_CLOSE);

        IF_Altas.getContentPane().setLayout(new FlowLayout());

        JPanel panel1 = new JPanel();

        panel1.setLayout(new FlowLayout());

        panel1.setBackground(Color.CYAN);

        panel1.add(new JLabel("-------- Datos personales --------"));

        panel1.add(new JLabel("-------- Nombre --------"));

        panel1.add(new JTextField(5));

        IF_Altas.add(panel1, BorderLayout.NORTH);


        JPanel panel2 = new JPanel();

        panel2.setLayout(new FlowLayout());

        panel2.setBackground(Color.GREEN);

        panel2.add(new JLabel("-------- Datos Academicos--------"));

        panel2.add(new JLabel("-------- Numero de control --------"));

        panel2.add(new JTextField(5));

        String[][] rowData = {{"23", "Pepe", "Perez", "19", "ISC"},{"23", "Pepe", "Perez", "19", "ISC"},{"23", "Pepe", "Perez", "19", "ISC"},{"23", "Pepe", "Perez", "19", "ISC"}};

        String[] columnNames = {"num, control" , "nombre", "Primer ape", "Edad", "Carrera"};

        JTable tabla = new JTable(rowData, columnNames);

        panel2.add(tabla);

        IF_Altas.add(panel2, BorderLayout.CENTER);

        IF_Altas.add(new JButton("Guardadr"), BorderLayout.SOUTH);

        deskTopPane.add(IF_Altas);

        add(deskTopPane, BorderLayout.CENTER);

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

        AlumnoDAO alumnoDAO = new AlumnoDAO();


        //============ Prueba bajas ==========




        //============ Prueba Combios ==========

        Alumno a1 = new Alumno("2","Luke","Skywalker","-", (byte)100, (byte)10, "ISC" );

        if(alumnoDAO.editarAlumno(a1) == true){

            System.out.println("Registro Modificado correctamente");

        }else{

            System.out.println("Error en la Modificacion");

        }

        //============ Prueba Consultas ==========

        ArrayList<Alumno> lista = alumnoDAO.mostrarAlumnos("");

        for(Alumno alumno: lista){

            System.out.println(alumno);

        }

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
