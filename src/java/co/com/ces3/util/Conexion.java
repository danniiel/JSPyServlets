/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daniel
 */
public class Conexion {

    public static Connection getConexion() throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Practica_Ces_3", "ces3", "ces3");
            return con;
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    public static void cerrarConexion(Connection con) throws SQLException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
