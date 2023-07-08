package servlets.Album;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Album;

import java.io.IOException;

import controllers.AlbumController;

@WebServlet("editalbum")
public class editAlbumServlet extends HttpServlet{
    private AlbumController albumController;


    editAlbumServlet(){
        super();        
        albumController = new AlbumController();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));        
        Album album = albumController.getAlbum(id);

        request.setAttribute("album", album);

        getServletContext().getRequestDispatcher("/album/editAlbum.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));        
        String nombre = request.getParameter("nombre");
        int id_artist = Integer.parseInt(request.getParameter("id_artist"));

        albumController.updateAlbum(id, nombre);

        response.sendRedirect("/music/album?id_artist="+id_artist);        
    }

    @Override
    public void destroy(){
        super.destroy();
        albumController.close();
    }

}
