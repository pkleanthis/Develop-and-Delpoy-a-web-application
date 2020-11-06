import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InsertProduct extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int barcode;
        char name, color, description;
        
        barcod = Integer.valueOf(request.getParameter("barcode"));
        name = request.getParameter("name");
        color = request.getParameter("color");
        description = request.getParameter("description");
        boolean exists = false;
        PrintWriter out = response.getWriter();
        
        try{
            
            Connection conn = DbConnection.initiallizeDatabase();
            
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT barcode FROM product";
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<html><body><b>" + rs.getInt("barcode"));
            while(rs.next()){
                int barcode  = rs.getInt("barcode");
                if(barcode == barcode){
                   
                    exists = true;
                    out.println("<html><body><b>PRODUCT ALREADY EXISTS");    
                }
            }
                
            if(!exists){
                
                PreparedStatement st = conn.prepareStatement("insert into product values(?, ?, ?, ?)");

                st.setInt(1, barcode); 
                st.setString(2, name); 
                st.setString(3, color); 
                st.setString(4, description); 

            
                st.executeUpdate();

                st.close();
                conn.close();

                out = response.getWriter();
                out.println("<html><body><b>Successfully Inserted" + "</b><h4>Barcode: "+barcode+"</h4><br/> <h4>Name: "+name+"</h4><br/>");
                out.println("<h4>colour: "+color+"</h4><br/> <h4>description: "+desc+"</h4><br/></body>  </html>");    
            }
        } 
        catch (Exception e) { 
            throw new ServletException("Data Insertion failed", e);
        } 
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
