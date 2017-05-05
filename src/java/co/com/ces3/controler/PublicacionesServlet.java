    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.controler;

import co.com.ces3.dao.PublicacionesDao;
import co.com.ces3.model.Publicacion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
@WebServlet(name = "PublicacionesServlet", urlPatterns = {"/PublicacionesServlet"})
public class PublicacionesServlet extends HttpServlet {

    private static final String PUBLICACIONES = "Publicaciones.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PublicacionesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicacionesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("guardar")) {
            PublicacionesDao dao = new PublicacionesDao();
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("pub", dao.ListarPublicaciones());
            request.setAttribute("id", dao.Buscarid());
            forward = PUBLICACIONES;
        } else if (accion.equalsIgnoreCase("actualizar")) {
            PublicacionesDao dao = new PublicacionesDao();
            String id = request.getParameter("idpub");
            Publicacion publicacionRetorno = dao.BuscarPublicacion(id);
            request.setAttribute("a_publicacion", publicacionRetorno);
            request.setAttribute("pub", dao.ListarPublicaciones());
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("id", dao.Buscarid());
            request.setAttribute("mensaje", "Los datos se han cargado en el formulario para su edicion");
            forward = PUBLICACIONES;
        }else if(accion.equalsIgnoreCase("eliminar")){
            PublicacionesDao dao = new PublicacionesDao();
            String id = request.getParameter("idpub");
            String estado ="Inactivo";
            dao.CambiarEstado(id,estado);
            request.setAttribute("pub", dao.ListarPublicaciones());
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("id", dao.Buscarid());
            request.setAttribute("mensaje", "El estado de la categoria ha cambiado a "+estado+" ");
            forward = PUBLICACIONES;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String forward = "";
        String accion = request.getParameter("accion");

        String id_publicacion = request.getParameter("id_publicacion");
        String categoria = request.getParameter("categoria");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        String estado = request.getParameter("estado");

        if (accion.equalsIgnoreCase("guardar")) {

            Publicacion publicacion = new Publicacion(id_publicacion, titulo, categoria, contenido, estado);
            PublicacionesDao dao = new PublicacionesDao();
            dao.GuardarPublicacion(publicacion);
            request.setAttribute("pub", dao.ListarPublicaciones());
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("id", dao.Buscarid());
            request.setAttribute("mensaje", "Datos Almacenados Correctamente");

        } else if (accion.equalsIgnoreCase("actualizar")) {
            Publicacion publicacion = new Publicacion(id_publicacion, titulo, categoria, contenido, estado);
            PublicacionesDao dao = new PublicacionesDao();
            dao.ActualizarPublicacion(publicacion);

            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("pub", dao.ListarPublicaciones());
            publicacion=new Publicacion();
            publicacion.setIndentificador(String.valueOf(dao.Buscarid()));
            request.setAttribute("a_publicacion", publicacion);
            request.setAttribute("mensaje", "Datos Actualizados Correctamente");
        }
        RequestDispatcher view = request.getRequestDispatcher(PUBLICACIONES);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
