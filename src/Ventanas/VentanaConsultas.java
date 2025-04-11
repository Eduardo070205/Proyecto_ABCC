package Ventanas;


import Elementos.Elementos;
import modelo.ResultSetTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

public class VentanaConsultas extends Elementos implements ActionListener {

    JRadioButton radioTodos, radioNombre, radioApePat, radioApeMat, radioSemestre, radioCarrera;

    JButton btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    ButtonGroup bg;


    public VentanaConsultas() {

        bg = new ButtonGroup();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Consultas Alumnos");

        setSize(600, 600);

        setLocationRelativeTo(null);

        setVisible(true);

        //Widgets

        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("CONSULTAS ALUMNOS");

        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

        txtTitulo.setBounds(20, 25, 250,30);

        panelSuperior.setBackground(Color.decode("#3c64f8"));

        panelSuperior.add(txtTitulo);

        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        radioTodos = new JRadioButton("Todos");

        radioTodos.setSelected(true);

        bg.add(radioTodos);

        radioTodos.addActionListener(this);

        asignarPosicion(radioTodos, 10, 100, 100,20);

        JLabel txtCriterios = new JLabel("Selecciona criterio de busqueda:");

        asignarPosicion(txtCriterios, 30, 80, 180, 20);

        ImageIcon icono = new ImageIcon("./img/search.png");

        Image imagenAjustada = icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

        ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);

        btnLogo = new JButton(iconoAjustado);

        btnLogo.addActionListener(this);

        asignarPosicion(btnLogo, 380, 140, 90, 35);

        btnBorrar = new JButton("Borrar");

        btnBorrar.addActionListener(this);

        asignarPosicion(btnBorrar, 380, 200, 80, 20);

        radioNombre = new JRadioButton("Nombre");

        bg.add(radioNombre);

        radioNombre.addActionListener(this);

        asignarPosicion(radioNombre, 20, 130, 80,20);

        cajaNombre = new JTextField();

        cajaNombre.setEnabled(false);

        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        radioApePat = new JRadioButton("Apellido Paterno");

        bg.add(radioApePat);

        radioApePat.addActionListener(this);

        asignarPosicion(radioApePat, 20, 160, 120,20);

        cajaApePat = new JTextField();

        cajaApePat.setEnabled(false);

        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        radioApeMat = new JRadioButton("Apellido Materno");

        bg.add(radioApeMat);

        radioApeMat.addActionListener(this);

        asignarPosicion(radioApeMat, 20, 190, 120,20);

        cajApeMat = new JTextField();

        cajApeMat.setEnabled(false);

        asignarPosicion(cajApeMat, 160, 190, 200,20);

        radioSemestre = new JRadioButton("Semestre");

        bg.add(radioSemestre);

        radioSemestre.addActionListener(this);

        asignarPosicion(radioSemestre, 20, 220, 80,20);

        comboSemestre = new JComboBox<>();


        for(int i = 0; i < 10; i++){

            comboSemestre.addItem(String.valueOf(i+1));

        }

        comboSemestre.setEnabled(false);

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        radioCarrera = new JRadioButton("Carrera");

        bg.add(radioCarrera);

        radioCarrera.addActionListener(this);


        asignarPosicion(radioCarrera, 20, 250, 100,20);

        comboCarrera = new JComboBox<>();

        comboCarrera.addItem("ISC");

        comboCarrera.addItem("IM");

        comboCarrera.addItem("LA");

        comboCarrera.addItem("IIA");

        comboCarrera.addItem("LCP");

        comboCarrera.setEnabled(false);

        asignarPosicion(comboCarrera, 160, 250, 150, 20);

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

    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

    }


    public void actualizarTablaFiltro(JTable tabla) {

        final String CONTROLADOR_JDBC = "com.mysql.cj.jdbc.Driver";

        final String URL = "jdbc:mysql://localhost:3306/bd_Topicos_2025";

        String CONSULTA = "SELECT * FROM Alumnos";

        if(radioNombre.isSelected()){

            CONSULTA = "SELECT * FROM Alumnos WHERE Nombre='"+cajaNombre.getText()+"'";

        }

        if(radioApePat.isSelected()){

            CONSULTA = "SELECT * FROM Alumnos WHERE Primer_Ap='"+cajaApePat.getText()+"'";

        }

        if(radioApeMat.isSelected()){

            CONSULTA = "SELECT * FROM Alumnos WHERE Segundo_Ap='"+cajApeMat.getText()+"'";

        }

        if(radioSemestre.isSelected()){

            CONSULTA = "SELECT * FROM Alumnos WHERE Semestre="+comboSemestre.getSelectedItem()+"";

        }

        if(radioCarrera.isSelected()){

            CONSULTA = "SELECT * FROM Alumnos WHERE Carrera='"+comboCarrera.getSelectedItem()+"'";

        }

        try {
            ResultSetTableModel modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL, CONSULTA);

            tabla.setModel(modelo);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);

        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        Object componente = e.getSource();

        if(componente == btnBorrar){

            restablecer(cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);

        }

        if(componente == btnCancelar){

            this.dispose();

        }

        if(componente == btnLogo){

            actualizarTablaFiltro(tabla);

        }

        if (componente == radioTodos){

            actualizarTablaFiltro(tabla);

        }

        if(radioNombre.isSelected()){

            cajaNombre.setEnabled(true);

        }else{

            cajaNombre.setEnabled(false);

        }

        if(radioApePat.isSelected()){

            cajaApePat.setEnabled(true);

        }else{

            cajaApePat.setEnabled(false);

        }

        if(radioApeMat.isSelected()){

            cajApeMat.setEnabled(true);

        }else{

            cajApeMat.setEnabled(false);

        }

        if(radioSemestre.isSelected()){

            comboSemestre.setEnabled(true);

        }else{

            comboSemestre.setEnabled(false);

        }

        if(radioCarrera.isSelected()){

            comboCarrera.setEnabled(true);

        }else{

            comboCarrera.setEnabled(false);

        }

    }
}
