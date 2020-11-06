import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class AllProducts extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Eshop</title>");
        out.println("<style>");
        out.println("td { padding 2% 3%; border: 1px solid rebeccapurple;}");
        out.println("th { color: gray;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Eshop",
            "admin", "admin_pass");
            Statement stmt = conn.createStatement();) 
        {

            String sqlStr = "select * from products order by FIELD('barcode', 'Name', 'Color') ASC" ;
            out.println("<h3>The database contents so far.</h3>");
            ResultSet rset = stmt.executeQuery(sqlStr);

            int count = 0;
            out.println("<table>");
            out.println("<tr>"
                        +   "<th scope='col'>Barcode</th>"
                        +   "<th scope='col'>Name</th>"
                        +   "<th scope='col'>Color</th>"
                        +   "<th scope='col'>Description</th>"
                        +   "</tr>");
            while(rset.next()) {
                out.println("<tr>");
                out.println("<td style='text-align:center;'>" + rset.getString("barcode") + "</td>"
                    + "<td style='text-align:center;'>" + rset.getString("name")  + "</td>"
                    + "<td style='text-align:center;'>" + rset.getString("color")  + "</td>"
                    + "<td style='text-align:center;'>" + rset.getString("description") + "</td>");
                out.println("</tr>");
                count++;
            }
            out.println("</table>");
            out.println("<h4>" + count + " records found.</h4>");
            out.println("<button type='submit'>"
                        +"<a style='text-decoration:none;color:rebeccapurple;'"
                        +"href='/Eshop/index.html'>Insert a new product</a</button>");
            
        } catch(Exception ex) {
            out.println("Oups.Try again later.");
              out.println("<p>Error: " + ex.getMessage() + "</p>");
             ex.printStackTrace();
        }
        out.println("</body></html>");
    out.close();
   }

}