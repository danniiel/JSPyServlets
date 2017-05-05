<%-- 
    Document   : Publicaciones
    Created on : 20/03/2016, 12:17:27 AM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrapValidator.min.css" rel="stylesheet">
        <title>Publicaciones</title>
    </head>
    <body onload="showMessage();">
        <div class="container">
            <h1 class="">Crear Nueva Publicación</h1>
            <div class="alert alert-success alert-dismissible text-center" id="exito" style="display: none">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                ${mensaje}
            </div>
            <div class="row">
                <a href="index.html"><b>Inicio</b></a>
                <form method="post" action="./PublicacionesServlet" id="publicaciones" class="form-horizontal col-md-offset-3">
                    <%
                        String accion = request.getParameter("accion");
                    %>

                    <input type="hidden" name="message" id="message" value="${mensaje}">
                    <input type="hidden" name="accion" value="<%=accion%>">

                    <%
                        if (accion.equalsIgnoreCase("guardar")) {
                    %>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="id_publicacion">Id de la Publicación</label>
                            <input type="text" class="form-control input-sm" id="id_publicacion" name="id_publicacion" value="<c:out value="${id}"/>" readonly="true">
                        </div>
                    </div>
                    <%} else {%>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="id_publicacion">Id de la Publicación</label>
                            <input type="text" class="form-control input-sm" id="id_publicacion" name="id_publicacion" value="<c:out value="${a_publicacion.getIndentificador()}"/>" readonly="true">
                        </div>
                    </div>
                    <%}%>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="titulo">Titulo de la Publicación</label>
                            <input type="text" class="form-control input-sm" id="titulo" name="titulo" value="<c:out value="${a_publicacion.getTitulo()}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="categoria">Categoria</label>
                            <select class="form-control input-sm" id="categoria" name="categoria">
                                <%
                                    if (accion.equalsIgnoreCase("actualizar")) {
                                %>
                                <option value="${a_publicacion.getCategoria()}"  selected>${a_publicacion.getCategoria()}</option>                                
                                <c:forEach items="${categorias}" var="c">
                                    <c:if test="${c.getDescripcion() != a_publicacion.getCategoria()}">                                   
                                        <option value="${c.getId_Categoria()}">${c.getDescripcion()}</option>
                                    </c:if>
                                </c:forEach>
                                <%
                                } else {
                                %>
                                <c:forEach var="c" items="${categorias}">
                                    <option value="<c:out value="${c.getId_Categoria()}"/>">
                                        <c:out value="${c.getDescripcion()}"/>
                                    </option>
                                </c:forEach>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="contenido">Contenido</label>
                            <textarea class="form-control" rows="3" id="contenido" name="contenido" ><c:out value="${a_publicacion.getContenido()}"/></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="estado">Estado de la Publicación</label>
                            <select id="estado" name="estado" class="form-control col-sm-12 col-md-6 input-sm">
                                <%
                                    if (accion.equalsIgnoreCase("actualizar")) {
                                %>
                                <option value="<c:out value='${a_publicacion.getEstado()}'/>" selected="">${a_publicacion.getEstado()}</option>
                                <c:choose>
                                    <c:when test="${a_publicacion.getEstado() == 'Activo'}">
                                        <option value="Inactivo">Inactivo</option>
                                    </c:when>
                                    <c:when test="${a_publicacion.getEstado() == 'Inactivo'}">
                                        <option value="Activo">Activo</option>
                                    </c:when>
                                </c:choose>
                                <%} else {%>
                                <option value="">-- Seleccione --</option>
                                <option value="Activo">Activo</option>
                                <option value="Inactivo">Inactivo</option>
                                <% }%>
                            </select>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary btn-md" value="guardar">Guardar</button>

                </form>
                <h3 style="text-align: center"><a href="./CategoriasServlet?accion=guardar">Administrar Categorias</a></h3>       
            </div>
            <hr>
            <div class="row">
                <table class="table table-striped">
                    <thead>
                    <th>ID</th>
                    <th>TITULO</th>
                    <th>CATEGORIA</th>
                    <th>CONTENIDO</th>
                    <th>ESTADO</th>
                    <th>ACCIONES</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${pub}" var="p">
                            <tr>
                                <td><c:out value="${p.getIndentificador()}"/></td>
                                <td><c:out value="${p.getTitulo()}"/></td>
                                <td><c:out value="${p.getCategoria()}"/></td>
                                <td><c:out value="${p.getContenido()}"/></td>
                                <td><c:out value="${p.getEstado()}"/></td>
                                <td>
                                    <a href="./PublicacionesServlet?accion=actualizar&idpub=<c:out value="${p.getIndentificador()}"/>"><span class="glyphicon glyphicon-pencil left"/></a>
                                    <a href="./PublicacionesServlet?accion=eliminar&idpub=<c:out value="${p.getIndentificador()}"/>"><span class="glyphicon glyphicon-trash left"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/jquery-1.12.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrapValidator.min.js"></script>
        <script src="js/validator.js"></script>
        <script src="js/js.js"></script>
    </body>
</html>
