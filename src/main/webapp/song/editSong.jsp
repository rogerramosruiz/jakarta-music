<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=s, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="editsong" method="post">
        <input hidden type="text" name="id" value="${song.id}">
        <input hidden type="text" name="id_album" value="${song.album.id}">
        <div>
            <label>Nombre</label>
            <input type="text" name="nombre" value="${song.nombre}">
        </div>

        <div>
            <label>Estrellas</label>
            <input type="number" name="estrellas" value="${song.estrellas}">
        </div>

        <input type="submit" value="Guardar">

    </form>
</body>
</html>