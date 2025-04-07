package Ventanas;

import modelo.ResultSetTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaBajas extends JFrame{

    JButton btnEliminar, btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;



    public VentanaBajas() {

        getContentPane().setLayout(null);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Bajas Alumnos");

        setSize(600, 600);

        setLocationRelativeTo(null);

        setVisible(true);

        //Widgets

        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("BAJAS ALUMNOS");

        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

        txtTitulo.setBounds(20, 25, 200,30);

        panelSuperior.setBackground(Color.decode("#cf2008"));

        panelSuperior.add(txtTitulo);

        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        JLabel txtNumControl = new JLabel("Número de control");

        asignarPosicion(txtNumControl, 30, 90, 120, 20);

        cajaNumControl = new JTextField();

        asignarPosicion(cajaNumControl, 160, 90, 100, 20);

        ImageIcon icono = new ImageIcon("./img/search.png");

        Image imagenAjustada = icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

        ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);

        btnLogo = new JButton(iconoAjustado);

        asignarPosicion(btnLogo, 270, 80, 90, 35);

        btnBorrar = new JButton("Borrar");

        asignarPosicion(btnBorrar, 380, 90, 80, 20);

        JLabel txtNombre = new JLabel("Nombre");

        asignarPosicion(txtNombre, 30, 130, 120, 20);

        cajaNombre = new JTextField();

        cajaNombre.setEnabled(false);

        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        JLabel txtApePat = new JLabel("Apellido Paterno");

        asignarPosicion(txtApePat, 30, 160, 120, 20);

        cajaApePat = new JTextField();

        cajaApePat.setEnabled(false);

        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        JLabel txtApeMat = new JLabel("Apellido Materno");

        asignarPosicion(txtApeMat, 30, 190, 120, 20);

        cajApeMat = new JTextField();

        cajApeMat.setEnabled(false);

        asignarPosicion(cajApeMat, 160, 190, 200,20);

        JLabel txtSemestre = new JLabel("Semestre");

        asignarPosicion(txtSemestre, 30, 220,120, 20);

        comboSemestre = new JComboBox<>();


        for(int i = 0; i < 10; i++){

            comboSemestre.addItem(String.valueOf(i+1));

        }

        comboSemestre.setEnabled(false);

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");

        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();

        comboCarrera.addItem("ISC");

        comboCarrera.addItem("IM");

        comboCarrera.addItem("LA");

        comboCarrera.addItem("IIA");

        comboCarrera.addItem("LCP");

        comboCarrera.setEnabled(false);

        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnEliminar = new JButton("Eliminar");

        asignarPosicion(btnEliminar, 380, 175, 90, 20);

        btnCancelar = new JButton("Cancelar");

        asignarPosicion(btnCancelar, 380, 235, 90, 20);

        String[][] rowData = {{"1","1","1","1","1","1"}};

        String[] columnNames = {"NO DE CONTROL", "NOMBRE", "AP. PATERNO", "AP. MATERNO", "SEMESTRE", "CARRERA"};

        JTable tabla = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(tabla);

        scrollPane.setBackground(Color.decode("#d2e2f1"));

        scrollPane.setBounds(10, 280, 580, 200);

        add(scrollPane);

    }

    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

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
