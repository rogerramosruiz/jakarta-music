package servlets.Reports;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import controllers.ArtistController;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Artist;
import jakarta.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@WebServlet("/bestsongreport")
public class BestSongsServlet extends HttpServlet{
    private ArtistController artistController;

    BestSongsServlet(){
        super();
        artistController = new ArtistController();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                int id_artist = Integer.parseInt(request.getParameter("id"));
                
                String dbhost = System.getenv("DB_HOST");
                String dbname = System.getenv("DB_NAME");
                String username = System.getenv("DB_USER");
                String password = System.getenv("DB_PASSWORD");
                String url = "jdbc:mysql://"+dbhost+"/"+dbname;
                
                Class.forName("com.mysql.cj.jdbc.Driver");

                Artist artist = artistController.getArtist(id_artist);

                Connection connection = DriverManager.getConnection(url, username, password);

                ServletContext context = getServletContext();
                String jrxmlPath = context.getRealPath("reports/bestsongs.jrxml");
                
                
                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
    
    
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id_artist", id_artist);
    
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                
                connection.close();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename="+artist.getNombre()+"canciones con mas estrellas.pdf");
    
                OutputStream outputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            }
            catch(JRException e){
                e.printStackTrace();
            }
            catch(Exception e ){
                e.printStackTrace();
            }
    }

    @Override
    public void destroy(){
        super.destroy();
        artistController.close();
    }
}
