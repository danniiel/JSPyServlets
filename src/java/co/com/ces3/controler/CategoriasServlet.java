/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.controler;

import co.com.ces3.dao.CategoriasDao;
import co.com.ces3.model.Categorias;
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
@WebServlet(name = "CategoriasServlet", urlPatterns = {"/CategoriasServlet"})
public class CategoriasServlet extends HttpServlet {

    private static final String CATEGORIAS = "categorias.jsp";
    private static final String listar = "Listar.jsp";

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
            out.println("<title>Servlet CategoriasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriasServlet at " + request.getContextPath() + "</h1>");
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
            CategoriasDao dao = new CategoriasDao();
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("id", dao.Buscarid());
            forward = CATEGORIAS;
        } else if (accion.equalsIgnoreCase("actualizar")) {
            CategoriasDao dao = new CategoriasDao();
            String id_categoria = request.getParameter("idcat");
            Categorias categoriaRetorno = dao.BuscarCategoria(id_categoria);
            request.setAttribute("a_Categoria", categoriaRetorno);
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("mensaje", "Los datos se han cargado en el formulario para su edicion");
            forward = CATEGORIAS;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            CategoriasDao dao = new CategoriasDao();
            String id_categoria = request.getParameter("idcat");
            String estado = "Inactivo";
            dao.CambiarEstado(id_categoria,estado);
            request.setAttribute("categorias", dao.ListarCategorias());
            request.setAttribute("id", dao.Buscarid());
            request.setAttribute("mensaje", "El estado de la categoria ha cambiado a "+estado+" ");
            forward = CATEGORIAS;
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

        String accion = request.getParameter("accion");

        String id_Categoria = request.getParameter("id_categoria");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        if (accion.equalsIgnoreCase("guardar")) {

            Categorias categorias = new Categorias(descripcion, estado);
            CategoriasDao categoriasDao = new CategoriasDao();

            categoriasDao.GuardarCategoria(categorias);
            request.setAttribute("categorias", categoriasDao.ListarCategorias());
            request.setAttribute("id", categoriasDao.Buscarid());
            request.setAttribute("mensaje", "Datos Almacenados Correctamente");
        } else if (accion.equalsIgnoreCase("actualizar")) {
            Categorias categorias = new Categorias(Integer.parseInt(id_Categoria), descripcion, estado);
            CategoriasDao categoriasDao = new CategoriasDao();

            categoriasDao.ActualizarCategoria(categorias);
            request.setAttribute("categorias", categoriasDao.ListarCategorias());

            categorias = new Categorias();
            categorias.setId_Categoria(categoriasDao.Buscarid());
            request.setAttribute("a_Categoria",categorias);
            request.setAttribute("mensaje", "Datos Actualizados Correctamente");
            
        }

        RequestDispatcher view = request.getRequestDispatcher(CATEGORIAS);
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
