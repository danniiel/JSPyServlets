/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.dao;

import co.com.ces3.model.Categorias;
import co.com.ces3.model.Publicacion;
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
public class PublicacionesDao {

    private Connection conexion;

    public List<Categorias> ListarCategorias() {
        List<Categorias> categorias = new ArrayList<>();
        try {

            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM CATEGORIAS WHERE ESTADO <> 'Inactivo' ");
            ResultSet rs = ps.executeQuery();
            Categorias categoria;
            categoria = new Categorias();
            categoria.setId_Categoria(0);
            categoria.setDescripcion("-- SELECCIONE --");
            categorias.add(categoria);
            
            while (rs.next()) {
                categoria = new Categorias();
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
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categorias;
    }

    public List<Publicacion> ListarPublicaciones() {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT P.IDENTIFICADOR,P.TITULO,C.DESCRIPCION,P.CONTENIDO,P.ESTADO FROM PUBLICACIONES P JOIN CATEGORIAS C ON P.CATEGORIA = C.ID_CATEGORIAS ORDER BY P.IDENTIFICADOR");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setIndentificador(rs.getString("IDENTIFICADOR"));
                publicacion.setTitulo(rs.getString("TITULO"));
                publicacion.setCategoria(rs.getString("DESCRIPCION"));
                publicacion.setContenido(rs.getString("CONTENIDO"));
                publicacion.setEstado(rs.getString("ESTADO"));
                publicaciones.add(publicacion);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return publicaciones;
    }

    public void GuardarPublicacion(Publicacion publicacion) {
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO PUBLICACIONES(TITULO,CATEGORIA,CONTENIDO,ESTADO)VALUES(?,?,?,?)");
            ps.setString(1, publicacion.getTitulo());
            ps.setInt(2, Integer.parseInt(publicacion.getCategoria()));
            ps.setString(3, publicacion.getContenido());
            ps.setString(4, publicacion.getEstado());
            ps.execute();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Publicacion BuscarPublicacion(String id) {
        Publicacion publicacion = new Publicacion();
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT P.IDENTIFICADOR,P.TITULO,C.DESCRIPCION,P.CONTENIDO,P.ESTADO FROM PUBLICACIONES P JOIN CATEGORIAS C ON P.CATEGORIA = C.ID_CATEGORIAS WHERE IDENTIFICADOR = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                publicacion.setIndentificador(rs.getString("IDENTIFICADOR"));
                publicacion.setTitulo(rs.getString("TITULO"));
                publicacion.setCategoria(rs.getString("DESCRIPCION"));
                publicacion.setContenido(rs.getString("CONTENIDO"));
                publicacion.setEstado(rs.getString("ESTADO"));
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return publicacion;
    }

    public int Buscarid() {
        int id = 0;
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT MAX(IDENTIFICADOR)FROM PUBLICACIONES");
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
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id + 1;

    }

    public void ActualizarPublicacion(Publicacion publicacion) {
        try {
            conexion = Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE PUBLICACIONES SET TITULO=?,CATEGORIA=?,CONTENIDO=?,ESTADO=? WHERE IDENTIFICADOR = ?");
            ps.setString(1, publicacion.getTitulo());
            ps.setInt(2, Integer.parseInt(publicacion.getCategoria()));
            ps.setString(3, publicacion.getContenido());
            ps.setString(4, publicacion.getEstado());
            ps.setString(5, publicacion.getIndentificador());
            ps.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void CambiarEstado(String id,String estado){
        try {
            conexion=Conexion.getConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE PUBLICACIONES SET ESTADO=? WHERE IDENTIFICADOR = ?");
            ps.setString(1, estado);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                Conexion.cerrarConexion(conexion);
            } catch (SQLException ex) {
                Logger.getLogger(PublicacionesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
