package servlets.Artist;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import controllers.ArtistController;

@WebServlet("/deleteartist")
public class deleteArtistServlet extends HttpServlet{
    private ArtistController artistController;
    
    deleteArtistServlet(){
        super();
        artistController = new ArtistController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("--------------------------------------");
        System.out.println(id);
        System.out.println("--------------------------------------");
        artistController.deleteArtist(id);
        response.sendRedirect("/music/artist");

    }

    @Override
    public void destroy(){
        super.destroy();
        artistController.close();
    }

    
}
