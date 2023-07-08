
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div>
    <form action="artist" method="post">
        <div>
            <label>Nombre</label>
            <input type = "text" name = "nombre">
        </div>

        <div>
            <label>Imagen</label>
            <input type = "text" name = "imageurl">
        </div>

        <div>
            <label>Spotify</label>
            <input type = "text" name = "spotify">
        </div>
        
        <div>
            <label>Youtube</label>
            <input type = "text" name = "youtube">
        </div>
        <input type="submit" value="Añadir">
    </form>
</div>

<table>
    <c:forEach items="${artists}" var="artist">
        <tr>
            <td><img src="${artist.imageurl}" width="100"></td>
            <td>${artist.nombre}</td>
            <td><a href="${artist.spotify}">Spotify</a></td>
            <td><a href="${artist.youtube}">Youtube</a> </td>
            
            <form action="album" method="get">
                <td><input hidden type="text" name="id_artist" value="${artist.id}"></td>
                <td><input type="submit" value="Albums"></td>
            </form>

            <form action="editartist" method="get">
                <td><input hidden type="text" name="id" value="${artist.id}"></td>
                <td><input type="submit" value="Edit"></td>
            </form>
            
            <form action="deleteartist" method="get">
                <td><input hidden type="text" name="id" value="${artist.id}"></td>
                <td><input type="submit" value="Delete"></td>
            </form>
        </tr>
    </c:forEach>
   </table> 
</body>
</html>