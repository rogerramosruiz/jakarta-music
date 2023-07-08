package servlets.Album;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import controllers.AlbumController;


@WebServlet("deletealbum")
public class deleteAlbumServlet extends HttpServlet{
    private AlbumController albumController;

    deleteAlbumServlet(){
        super();
        albumController = new AlbumController();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));  
        int id_artist = Integer.parseInt(request.getParameter("id_artist"));
      
        albumController.deleteAlbum(id);
        response.sendRedirect("/music/album?id_artist="+id_artist);        
    }

    @Override
    public void destroy(){
        super.destroy();
        albumController.close();
    }
}
