package Elementos;

import controlador.AlumnoDAO;
import modelo.ResultSetTableModel;

import  javax.swing.*;
import java.sql.SQLException;

public class Elementos extends JFrame{

    public AlumnoDAO alumnoDAO = new AlumnoDAO();



    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

    }

    public void actualizarTabla(JTable tabla) {

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



    public void restablecer(JComponent... componentes) {

        //En java

        for(JComponent c : componentes) {

            System.out.println(c);

            if(c instanceof JTextField) {

                ((JTextField) c).setText("");

            }
            else if(c instanceof JComboBox){

                ((JComboBox) c).setSelectedIndex(0);


            }else if(c instanceof JRadioButton){

                ((JRadioButton) c).setSelected(true);

            }

        }

    }

    public boolean validacion(JComponent... componente){

        boolean cajas = false, combos = true;

        for(JComponent c: componente){

            if(c instanceof JTextField){


                if(((JTextField) c).getText() == ""){

                    cajas = false;

                }else{

                    cajas = true;

                }

            }

            if( c instanceof JComboBox){

                if(((JComboBox<?>) c).getSelectedIndex() == 0){

                    combos = false;

                }else{

                    combos =  true;

                }

            }

        }

        if((cajas && combos)){

            return true;

        }else{

            return false;

        }

    }

}
