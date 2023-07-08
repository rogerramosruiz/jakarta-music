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
        
    <form action="editalbum" method="post">
        <input hidden type="text" name="id" value="${album.id}">
        <input hidden type="text" name="id_artist" value="${album.getArtist().getId()}">
        <label>Nombre</label>
        <input type="text" name="nombre" value="${album.nombre}">
        <input type="submit" value="Guardar">

    </form>

</body>
</html>