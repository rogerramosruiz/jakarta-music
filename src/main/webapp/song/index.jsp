<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="song" method="post">
        <input hidden type="text" name="id_album" value="${album.id}">
        <div>
            <label>Nombre</label>
            <input type="text" name="nombre">
        </div>

        <div>
            <label>Estrellas</label>
            <input type="number" name="estrellas">
        </div>

        <input type="submit" value="Añadir">

    </form>
    <h2>Canciones del album <b>${album.nombre}</b></h2>
    <table>
        <tr>
            <th>Nombre</th>
            <th>Estrellas</th>
        </tr>
        <c:forEach items="${songs}" var="song">
            <tr>
                <td>${song.nombre}</td>
                <td>${song.estrellas}</td>
                <td>
                    <form action="editsong">
                        <input hidden type="text" name="id" value="${song.id}">
                        <input hidden type="text" name="id_album" value="${song.album.id}">
                        <input type="submit" value="Editar">                    
                    </form>
                </td>
                <td>
                    <form action="deletesong">
                        <input hidden type="text" name="id" value="${song.id}">
                        <input hidden type="text" name="id_album" value="${song.album.id}">
                        <input type="submit" value="Delete">                    
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
    </html>