package servlets.Artist;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Artist;

import java.io.IOException;

import controllers.ArtistController;

@WebServlet("/editartist")
public class editArtistServlet extends HttpServlet {
    private ArtistController artistController;
    
    editArtistServlet(){
        artistController = new ArtistController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Artist artist = artistController.getArtist(id);
        request.setAttribute("artist", artist);
        getServletContext().getRequestDispatcher("/artist/editArtist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String imageurl = request.getParameter("imageurl");
        String spotify = request.getParameter("spotify");
        String youtube = request.getParameter("youtube");
        artistController.updateArtist(id, nombre, imageurl, spotify, youtube);

        response.sendRedirect("/music/artist");
    }

    @Override
    public void destroy(){
        super.destroy();
        artistController.close();
    }

}
