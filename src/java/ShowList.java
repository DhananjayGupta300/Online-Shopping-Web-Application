import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ShowList extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String s=request.getParameter("cat");
        Cookie c=new Cookie("userchoice",s);
        c.setMaxAge(60*60*24*7);
        response.addCookie(c);
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:ShopDsn");
        String qr="select pid,pname from products where pcat=?";
        PreparedStatement ps=con.prepareStatement(qr);
        ps.setString(1, s);
        ResultSet rs=ps.executeQuery();
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Select Desired Product</h2>");
        out.println("<hr>");
        while(rs.next())
        {
            String s1=rs.getString(1);
            String s2=rs.getString(2);
            out.println("<a href=ShowDetails?id="+s1+">");
            out.println(s2);
            out.println("</a>");
            out.println("<br>");
        }
        out.println("<hr>");
        out.println("<a href=buyerhome.jsp>home</a><br>");
        out.println("<a href=ShowCategory>category-page</a><br>");
        out.println("</body>");
        out.println("</html>");
            
        con.close();
            
            
            
            
            
        }
        catch(Exception ex){out.println(ex);}
        
        
        
        
        
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
