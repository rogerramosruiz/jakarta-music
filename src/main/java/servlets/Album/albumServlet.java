package servlets.Album;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import models.Album;
import models.Artist;
import controllers.AlbumController;
import controllers.ArtistController;



@WebServlet("album")
public class albumServlet extends HttpServlet{
    private AlbumController albumController;
    private ArtistController artistController;

    albumServlet(){
        super();
        albumController = new AlbumController();
        artistController = new ArtistController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_artist = Integer.parseInt(request.getParameter("id_artist"));

        Artist artist = artistController.getArtist(id_artist);
        List<Album> albums = albumController.getAlbums(artist);

        request.setAttribute("albums", albums);
        request.setAttribute("artist", artist);
        getServletContext().getRequestDispatcher("/album/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        int id_artist = Integer.parseInt(request.getParameter("id_artist"));
        Artist artist = artistController.getArtist(id_artist);

        albumController.addAlbum(new Album(nombre, artist));

        doGet(request, response);
    }

    @Override
    public void destroy(){
        super.destroy();
        albumController.close();
        artistController.close();
    }


}
