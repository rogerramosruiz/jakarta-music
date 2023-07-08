package servlets.Song;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Album;
import models.Song;


import java.io.IOException;
import java.util.List;

import controllers.AlbumController;
import controllers.SongController;

@WebServlet("song")
public class songServlet extends HttpServlet{
    private SongController songController;
    private AlbumController albumController;
    songServlet(){
        super();
        songController = new SongController();
        albumController = new AlbumController();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_album = Integer.parseInt(request.getParameter("id_album"));

        Album album = albumController.getAlbum(id_album);
        List<Song> songs = songController.getSongs(album);
        
        request.setAttribute("songs", songs);
        request.setAttribute("album", album);
    
        getServletContext().getRequestDispatcher("/song/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");        
        int estrellas = Integer.parseInt(request.getParameter("estrellas"));
        int id_album = Integer.parseInt(request.getParameter("id_album"));
        Album album = albumController.getAlbum(id_album);

        songController.addSong(new Song(nombre, estrellas, album));
        doGet(request, response);
    }

    @Override
    public void destroy(){
        super.destroy();
        songController.close();
        albumController.close();
    }
}
