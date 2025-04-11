package Ventanas;

import ConexionBD.ConexionBD;
import Elementos.Elementos;
import modelo.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaModificaciones extends Elementos implements ActionListener, KeyListener {


    JButton btnGuardar, btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    public VentanaModificaciones() {

        getContentPane().setLayout(null);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Modificaciones Alumnos");

        setSize(600, 600);

        setLocationRelativeTo(null);

        setVisible(true);

        //Widgets

        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("MODIFICACIONES ALUMNOS");

        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

        txtTitulo.setBounds(20, 25, 300,30);

        panelSuperior.setBackground(Color.decode("#fb9623"));

        panelSuperior.add(txtTitulo);

        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        JLabel txtNumControl = new JLabel("Número de control");

        asignarPosicion(txtNumControl, 30, 90, 120, 20);

        cajaNumControl = new JTextField();

        cajaNumControl.addKeyListener(this);

        asignarPosicion(cajaNumControl, 160, 90, 100, 20);

        ImageIcon icono = new ImageIcon("./img/search.png");

        Image imagenAjustada = icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

        ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);

        btnLogo = new JButton(iconoAjustado);

        btnLogo.addActionListener(this);

        asignarPosicion(btnLogo, 270, 80, 90, 35);

        btnBorrar = new JButton("Borrar");

        asignarPosicion(btnBorrar, 380, 90, 80, 20);

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


        for(int i = 0; i < 10; i++){

            comboSemestre.addItem(String.valueOf(i+1));

        }

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");

        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();

        comboCarrera.addItem("ISC");

        comboCarrera.addItem("IM");

        comboCarrera.addItem("LA");

        comboCarrera.addItem("IIA");

        comboCarrera.addItem("LCP");

        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnGuardar = new JButton("Guardar Cambios");

        btnGuardar.addActionListener(this);

        asignarPosicion(btnGuardar, 380, 175, 140, 20);

        btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(this);

        asignarPosicion(btnCancelar, 380, 235, 90, 20);

        String[][] rowData = null;

        String[] columnNames = {"NO DE CONTROL", "NOMBRE", "AP. PATERNO", "AP. MATERNO", "SEMESTRE", "CARRERA"};

        tabla = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(tabla);

        scrollPane.setBackground(Color.decode("#d2e2f1"));

        scrollPane.setBounds(10, 280, 580, 200);

        add(scrollPane);

        actualizarTabla(tabla);

    }

    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

    }



    public void obtnerDatos() {

        ConexionBD conexionBD = new ConexionBD();

        String sql = "SELECT * FROM Alumnos WHERE Nun_Control='"+cajaNumControl.getText()+"'";

        ResultSet rs = conexionBD.ejecutarIstruccionSQL(sql);

        try {

            rs.next();

            cajaNombre.setText(rs.getString("Nombre"));

            cajaApePat.setText(rs.getString(3));

            cajApeMat.setText(rs.getString("Segundo_Ap"));

            // byte e = rs.getByte(5);

            comboSemestre.setSelectedItem(String.valueOf(rs.getByte(6)));

            comboCarrera.setSelectedItem(rs.getString(7));

        } catch (SQLException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(this,"El registro no fue encontrado");

            restablecer(cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object componente = e.getSource();

        if(componente == btnLogo){

            obtnerDatos();

        }

        if(componente == btnGuardar){

            if(validacion(cajaNombre, cajaApePat, cajApeMat)){

                int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres guardar los cambios?");

                if(respuesta == JOptionPane.YES_OPTION){

                    Alumno a1 = new Alumno(cajaNumControl.getText(),cajaNombre.getText(),cajaApePat.getText(),cajApeMat.getText(),(byte)0, Byte.parseByte(String.valueOf(comboSemestre.getSelectedItem())),String.valueOf(comboCarrera.getSelectedItem()));

                    if(alumnoDAO.editarAlumno(a1) == true){

                        actualizarTabla(tabla);

                        System.out.println("Registro Modificado correctamente");

                        JOptionPane.showMessageDialog(this, "Se ha actualizado el registro correctamente");

                    }else{

                        System.out.println("Error en la Modificacion");

                        JOptionPane.showMessageDialog(this, "Ocurrio un error en la actualización del registro");

                    }

                }

            }else{

                JOptionPane.showMessageDialog(this, "Los datos son incorrectos, verificalos");

            }

        }

        if(componente == btnBorrar){

            restablecer(cajaNumControl,cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);

        }

        if(componente == btnCancelar){

            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres salir?, si no has guardado los cambios se perderan");

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

