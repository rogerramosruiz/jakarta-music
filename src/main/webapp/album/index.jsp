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
    
    <img src="${artist.imageurl}" width="200">
    <h2>
        Albums de ${artist.nombre}
    </h2>
    
    <form action="album" method="post">
        <input hidden type="text" name="id_artist" value="${artist.id}">

        <label>Nombre</label>
        <input type="text" name="nombre">
        <input type="submit" value="Añadir">

    </form>
    <table>
        <c:forEach items="${albums}" var="album">
        <tr>
            <td>${album.nombre}</td>
            <td>
                <form action="songs">
                    <input hidden type="text" name="id_album" value="${album.id}">
                    <input type="submit" value="Canciones">
                </form>
            </td>
            <td>
                <form action="editalbum">
                    <input hidden type="text" name="id" value="${album.id}">
                    <input type="submit" value="Editar">
                </form>
            </td>
            <td>
                <form action="deletealbum">
                    <input hidden type="text" name="id" value="${album.id}">
                    <input hidden type="text" name="id_artist" value="${artist.id}">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>    
</body>
</html>