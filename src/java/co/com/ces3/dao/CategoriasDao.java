/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.dao;

import co.com.ces3.model.Categorias;
import co.com.ces3.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class CategoriasDao {

    private Connection conexion;

    public List<Categorias> ListarCategorias() {
        List<Categorias> categorias = new ArrayList<>();
        try {

            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM CATEGORIAS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setId_Categoria(rs.getInt("ID_CATEGORIAS"));
                categoria.setDescripcion(rs.getString("DESCRIPCION"));
                categoria.setEstado(rs.getString("ESTADO"));
                categorias.add(categoria);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categorias;
    }

    public int Buscarid() {
        int id = 0;
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT MAX(ID_CATEGORIAS)FROM CATEGORIAS");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id + 1;

    }

    public void GuardarCategoria(Categorias cat) {

        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO CATEGORIAS (DESCRIPCION,ESTADO)VALUES (?,?)");
            ps.setString(1, cat.getDescripcion());
            ps.setString(2, cat.getEstado());
            ps.execute();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void ActualizarCategoria(Categorias categorias) {
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE CATEGORIAS SET DESCRIPCION=?,ESTADO=? WHERE ID_CATEGORIAS=?");
            ps.setString(1, categorias.getDescripcion());
            ps.setString(2, categorias.getEstado());
            ps.setInt(3, categorias.getId_Categoria());
            ps.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void CambiarEstado(String id,String categoria){
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE CATEGORIAS SET ESTADO=? WHERE ID_CATEGORIAS=?");
            ps.setString(1, categoria);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Categorias BuscarCategoria(String id) {
        Categorias categorias = new Categorias();
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM CATEGORIAS WHERE ID_CATEGORIAS=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categorias.setId_Categoria(rs.getInt("ID_CATEGORIAS"));
                categorias.setDescripcion(rs.getString("DESCRIPCION"));
                categorias.setEstado(rs.getString("ESTADO"));
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categorias;
    }
}
