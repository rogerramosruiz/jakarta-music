
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
    <form action="editartist" method="post">
        <input hidden type = "text" name = "id" value="${artist.id}">
        <div>
            <label>Nombre</label>
            <input type = "text" name = "nombre" value="${artist.nombre}">
        </div>

        <div>
            <label>Imagen</label>
            <input type = "text" name = "imageurl" value="${artist.imageurl}">
        </div>

        <div>
            <label>Spotify</label>
            <input type = "text" name = "spotify" value="${artist.spotify}">
        </div>
        
        <div>
            <label>Youtube</label>
            <input type = "text" name = "youtube" value="${artist.youtube}">
        </div>
        <input type="submit" value="Guardar">
    </form>
    </div>

</body>
</html>