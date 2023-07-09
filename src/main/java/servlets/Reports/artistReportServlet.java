package servlets.Reports;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import models.Artist;
import controllers.ArtistController;

@WebServlet("artistreport")
public class artistReportServlet extends HttpServlet{
    private ArtistController artistController;
    artistReportServlet(){
        super();
        artistController = new ArtistController();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                List<Artist> artists = artistController.getArtists();
    
                ServletContext context = getServletContext();
                String jrxmlPath = context.getRealPath("reports/Artist.jrxml");
                
                System.out.println(jrxmlPath);
                // InputStream reportTemplate = getServletContext().getResourceAsStream(jrxmlPath);
    
                // InputStream reportTemplate = getServletContext().getResourceAsStream("/reports/Address_01.jrxml");
                // Compile the JasperReport template and generate a JasperReport object
                
                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
    
                // Convert the list of Person objects to a JRBeanCollectionDataSource
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(artists);
    
                // Set any report parameters, if needed
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("name", "Bill Board");
                parameters.put("artistDataSet", dataSource);
    
                // Generate the report using JasperReports
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
    
                // Set the response headers
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=artist.pdf");
    
                // Export the report to PDF and write it to the response output stream
                OutputStream outputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            }
            catch(JRException e){
                e.printStackTrace();
            }
    }

  
    @Override
    public void destroy(){
        super.destroy();
        artistController.close();
    }
}
