package servlets.Artist;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

import models.Artist;
import controllers.ArtistController;

@WebServlet("artist")
public class ArtistServlet extends HttpServlet {
    private ArtistController artistController;
    ArtistServlet(){
        super();
        artistController = new ArtistController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Artist> artists = artistController.getArtists();
        request.setAttribute("artists", artists);
        getServletContext().getRequestDispatcher("/artist/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String imageurl = request.getParameter("imageurl");
        String spotify = request.getParameter("spotify");
        String youtube = request.getParameter("youtube");

        artistController.addArtist(new Artist(nombre, imageurl, spotify, youtube));
        doGet(request, response);
    }

    @Override
    public void destroy(){
        super.destroy();
        artistController.close();
    }
}
