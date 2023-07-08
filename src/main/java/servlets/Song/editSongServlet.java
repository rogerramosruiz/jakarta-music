package servlets.Song;


import java.io.IOException;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controllers.SongController;
import models.Song;


@WebServlet("editsong")
public class editSongServlet extends HttpServlet{
    private SongController songController;

    editSongServlet(){
        super();
        songController = new SongController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Song song = songController.getSong(id);
        request.setAttribute("song", song);
        getServletContext().getRequestDispatcher("/song/editSong.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_album= Integer.parseInt(request.getParameter("id_album"));
        String nombre = request.getParameter("nombre");        
        int estrellas = Integer.parseInt(request.getParameter("estrellas"));
     
        songController.updateSong(id, nombre, estrellas);
        response.sendRedirect("/music/song?id_album="+id_album);        
    }

    @Override
    public void destroy(){
        super.destroy();
        songController.close();
    }

}
