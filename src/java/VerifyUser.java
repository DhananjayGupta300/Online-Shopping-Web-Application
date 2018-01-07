import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUser extends HttpServlet {
    Connection con; PreparedStatement ps; ResultSet rs;
    @Override
    public void init() {
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:ShopDsn");
        String qr="select uname from users where userid=? and password=?";
        ps=con.prepareCall(qr);
        }
        catch(Exception ex){}
    }

    @Override
    public void destroy() {
        try
        {
            con.close();
        }
        catch(Exception ex){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String s1=request.getParameter("t1");
        String s2=request.getParameter("t2");
        String s3=request.getParameter("utype");
        String s4=request.getParameter("save");
        
        try
        {
            ps.setString(1, s1);
            ps.setString(2, s2);
            rs=ps.executeQuery();
            boolean found=rs.next();        
            if(found){
                String userName=rs.getString(1);
                HttpSession session=request.getSession();
                session.setAttribute("uname", userName);
                
                
                if(s4!=null){
                    Cookie c1=new Cookie("uid",s1);
                    Cookie c2=new Cookie("pw",s2);
                    c1.setMaxAge(60*60*24*7);
                    c2.setMaxAge(60*60*24*7);
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                response.sendRedirect("buyerhome.jsp");
            }
            else
            {
                response.sendRedirect("index.jsp");
                //out.println("Invalid User<br>");
                //out.println("<a href=index.jsp>Try-Again</a><br>");
            }
            
        }
        catch(Exception ex)
        {out.println(ex);}
        
        
        
        
        
        
        
        
        
        
        
        
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
