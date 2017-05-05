<%-- 
    Document   : categorias
    Created on : 15/03/2016, 11:44:47 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrapValidator.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <title>Categorias</title>
    </head>
    <body onload="showMessage();">
        <div class="container">
            <h1>Administración de Categorias</h1>

            <div class="alert alert-success alert-dismissible text-center" id="exito" style="display: none">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                ${mensaje}
            </div>
            <div class="row">
                <a href="./PublicacionesServlet?accion=guardar"><b>Volver</b></a>
                <form method="post" action="./CategoriasServlet" id="categorias" name="cat" class="form-horizontal col-md-offset-3">

                    <div class="form-group">
                        <%

                            String accion = request.getParameter("accion");
                        %>  
                        <input type="hidden" name="message" id="message" value="${mensaje}">
                        <input type="hidden" name="accion" value="<%=accion%>">

                        <%
                            if (accion.equalsIgnoreCase("guardar") || accion.equalsIgnoreCase("eliminar")) {
                        %>
                        <div class="col-sm-12 col-md-6">
                            <label for="id_categoria" class="control-label">Identificador Categoria:</label>
                            <input type="text" name="id_categoria" id="id_categoria" class="form-control col-sm-12 col-md-6 input-sm" value="<c:out value='${id}'/>" readonly="true">
                        </div>
                        <br>
                        <%} else {%>
                        <div class="col-sm-12 col-md-6">
                            <label for="id_categoria" class="control-label">Identificador Categoria:</label>
                            <input type="text" name="id_categoria" id="id_categoria" class="form-control col-sm-12 col-md-6 input-sm" value="<c:out value='${a_Categoria.getId_Categoria()}'/>" readonly="true">
                        </div>
                        <%}%>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 col-md-6">
                            <label for="descripcion" class="control-label">Descripcion Categoria</label>
                            <input type="text" name="descripcion" id="descripcion" class="form-control input-sm col-sm-12 " value="<c:out value='${a_Categoria.getDescripcion()}'/>">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 col-md-6">
                            <label for="estado" class="control-label">Estado Categoria</label>

                            <select id="estado" name="estado" class="form-control col-sm-12 col-md-6 input-sm">
                                <%
                                    if (accion.equalsIgnoreCase("actualizar")) {
                                %>
                                <option value="<c:out value='${a_Categoria.getEstado()}'/>" selected="">${a_Categoria.getEstado()}</option>
                                <c:choose>
                                    <c:when test="${a_Categoria.getEstado() == 'Activo'}">
                                        <option value="Inactivo">Inactivo</option>
                                    </c:when>
                                    <c:when test="${a_Categoria.getEstado() == 'Inactivo'}">
                                        <option value="Activo">Activo</option>
                                    </c:when>
                                </c:choose><%} else {%>
                                <option value="">-- Seleccione --</option>
                                <option value="Activo">Activo</option>
                                <option value="Inactivo">Inactivo</option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-md" id="guardar" value="guardar">Guardar</button>
                </form> 
            </div>
            <hr>
            <div class="row">
                <table class="table table-striped " >
                    <thead>
                    <th>ID</th>
                    <th>Descripcion</th>
                    <th>Estado</th>
                    <th>Aciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${categorias}" var="cat">
                            <tr>
                                <td><c:out value="${cat.getId_Categoria()}"></c:out></td>
                                <td><c:out value="${cat.getDescripcion()}"></c:out></td>
                                <td><c:out value="${cat.getEstado()}"></c:out></td>
                                    <td>
                                        <a href="./CategoriasServlet?accion=actualizar&idcat=<c:out value='${cat.getId_Categoria()}'/>"><span class="glyphicon glyphicon-pencil left"/></a>
                                    <a href="./CategoriasServlet?accion=eliminar&idcat=<c:out value='${cat.getId_Categoria()}'/>"><span class="glyphicon glyphicon-trash left"/></a>
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
