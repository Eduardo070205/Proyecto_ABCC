package Ventanas;
import Elementos.Elementos;
import controlador.AlumnoDAO;
import modelo.Alumno;
import modelo.ResultSetTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class VentanaAltas extends Elementos implements ActionListener, KeyListener {

    JButton btnAgregar, btnBorrar, btnCancelar;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    public VentanaAltas() {

        getContentPane().setLayout(null);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Altas Alumnos");

        setSize(600, 600);

        setLocationRelativeTo(null);

        setVisible(true);

        //Widgets

        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("ALTAS ALUMNOS");

        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

        txtTitulo.setBounds(20, 25, 200,30);

        panelSuperior.setBackground(Color.decode("#24c248"));

        panelSuperior.add(txtTitulo);

        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        JLabel txtNumControl = new JLabel("Número de control");

        asignarPosicion(txtNumControl, 30, 100, 120, 20);

        cajaNumControl = new JTextField();

        cajaNumControl.addKeyListener(this);

        asignarPosicion(cajaNumControl, 160, 100, 200, 20);

        JLabel txtNombre = new JLabel("Nombre");

        asignarPosicion(txtNombre, 30, 130, 120, 20);

        cajaNombre = new JTextField();

        cajaNombre.addKeyListener(this);

        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        JLabel txtApePat = new JLabel("Apellido Paterno");

        asignarPosicion(txtApePat, 30, 160, 120, 20);

        cajaApePat = new JTextField();

        cajaApePat.addKeyListener(this);

        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        JLabel txtApeMat = new JLabel("Apellido Materno");

        asignarPosicion(txtApeMat, 30, 190, 120, 20);

        cajApeMat = new JTextField();

        cajApeMat.addKeyListener(this);

        asignarPosicion(cajApeMat, 160, 190, 200,20);

        JLabel txtSemestre = new JLabel("Semestre");

        asignarPosicion(txtSemestre, 30, 220,120, 20);

        comboSemestre = new JComboBox<>();

        comboSemestre.addItem("Elegir Semestre...");

        for(int i = 0; i < 10; i++){

            comboSemestre.addItem(String.valueOf(i+1));

        }

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");

        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();

        comboCarrera.addItem("Elegir Carrera...");

        comboCarrera.addItem("ISC");

        comboCarrera.addItem("IM");

        comboCarrera.addItem("LA");

        comboCarrera.addItem("IIA");

        comboCarrera.addItem("LCP");

        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnAgregar = new JButton("Agregar");

        btnAgregar.addActionListener(this);

        asignarPosicion(btnAgregar, 380, 115, 80, 20);

        btnBorrar = new JButton("Borrar");

        btnBorrar.addActionListener(this);

        asignarPosicion(btnBorrar, 380, 175, 80, 20);

        btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(this);

        asignarPosicion(btnCancelar, 380, 235, 90, 20);

        String[][] rowData = {{"1","1","1","1","1","1"}};

        String[] columnNames = {"NO DE CONTROL", "NOMBRE", "AP. PATERNO", "AP. MATERNO", "SEMESTRE", "CARRERA"};

        tabla = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(tabla);

        scrollPane.setBackground(Color.decode("#d2e2f1"));

        scrollPane.setBounds(10, 280, 580, 200);

        add(scrollPane);

        actualizarTabla(tabla);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        Object componente = e.getSource();

        if(componente == btnAgregar){


            if(validacion(cajaNumControl, cajaNombre, cajaApePat, cajApeMat, comboSemestre, comboCarrera)){



                Alumno a = new Alumno(cajaNumControl.getText(),cajaNombre.getText(),cajaApePat.getText(),cajApeMat.getText(),(byte)0, Byte.parseByte(String.valueOf(comboSemestre.getSelectedItem())),String.valueOf(comboCarrera.getSelectedItem()));

                if(alumnoDAO.agregarAlumno(a) == true){

                    actualizarTabla(tabla);

                    JOptionPane.showMessageDialog(this, "Registro agregado correctamente");

                    System.out.println("Registro agregado correctamente");

                }else{

                    JOptionPane.showMessageDialog(this, "Error en la Insercción");

                    System.out.println("Error en la Insercción");

                }

            }else{

                JOptionPane.showMessageDialog(this, "Tus datos no son correctos, verificalos");

            }

        }

        if(componente == btnBorrar){

            restablecer(cajaNumControl, cajaNombre, cajaApePat, cajApeMat, comboSemestre, comboCarrera);

        }

        if(componente == btnCancelar){

            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres Salir sin guardar el registro?");

            if(respuesta == JOptionPane.YES_OPTION){

                this.dispose();

            }

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getSource() == cajaNumControl){

            if((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || e.getKeyCode() == 8 || e.getKeyCode() == 20 || e.getKeyCode() == 16 || e.getKeyCode() == 17 || e.getKeyCode() == 18){




            }else{

                JOptionPane.showMessageDialog(this, "Solo puedes ingresar numeros");

                cajaNumControl.setText("");

            }

        }

        if(e.getSource() == cajaNombre || e.getSource() == cajaApePat || e.getSource() == cajApeMat){

            if(e.getKeyCode() == 32 && cajaNombre.getText() == ""){



            }


            if((e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() >= 97 && e.getKeyCode() <= 122) || e.getKeyCode() == 32 || e.getKeyCode() == 8 || e.getKeyCode() == 20 || e.getKeyCode() == 16 || e.getKeyCode() == 17 || e.getKeyCode() == 18){



            }else{

                JOptionPane.showMessageDialog(this, "Solo puedes ingresar letras: " + e.getKeyCode());

                if(e.getSource() == cajaNombre){

                    cajaNombre.setText("");

                }

                if(e.getSource() == cajaApePat){

                    cajaApePat.setText("");

                }

                if(e.getSource() == cajApeMat){

                    cajApeMat.setText("");

                }

            }

        }

    }
}
