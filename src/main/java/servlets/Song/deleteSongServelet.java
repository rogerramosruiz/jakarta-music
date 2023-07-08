package servlets.Song;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import controllers.SongController;

@WebServlet("deletesong")
public class deleteSongServelet extends HttpServlet{
    private SongController songController;

    deleteSongServelet(){
        super();
        songController = new SongController();
    }   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_album= Integer.parseInt(request.getParameter("id_album"));
        songController.deleteSong(id);
        response.sendRedirect("/music/song?id_album="+id_album);        
    }


    @Override
    public void destroy(){
        super.destroy();
        songController.close();
    }
}
