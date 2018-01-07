/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class ShowCategory extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:ShopDsn");
        String qr="select distinct pcat from products";
        PreparedStatement ps=con.prepareStatement(qr);
        ResultSet rs=ps.executeQuery();
        out.println("<html>");
        out.println("<body>");
        HttpSession session=request.getSession();
        
        int items=0;
        
        ArrayList list=(ArrayList)session.getAttribute("cart");
        if(list!=null){
            items=list.size();
        }
        
        
        String name=(String)session.getAttribute("uname");
        if(name==null){
            response.sendRedirect("index.jsp");
        }
        out.println("<h3>Welcome "+name+"</h3>");
        out.println("<h4>Total Items In Cart : "+items+"</h4>");
        out.println("<h2>Select Desired Category</h2>");
        out.println("<hr>");
        while(rs.next())
        {
            String s=rs.getString(1);
            out.println("<a href=ShowList?cat="+s+">");
            out.println(s);
            out.println("</a>");
            out.println("<br>");
        }
        out.println("<hr>");
        out.println("<a href=buyerhome.jsp>home</a><br>");
        
        String choice="All";
        
        Cookie ck[]=request.getCookies();
        for(int i=0;i<ck.length;i++)
        {
            if(ck[i].getName().equals("userchoice")){
                choice=ck[i].getValue();
                break;
            }
        }
        
        out.println("<h3><marquee>Attractive Offers On "+choice+" products</marquee></h3>");
        
        
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
