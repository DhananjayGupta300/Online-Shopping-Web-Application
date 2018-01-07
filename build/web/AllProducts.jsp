<%!
    int x=10;
    String getFormatted(String s){
        return s.toUpperCase().trim();
    }
%>
<%
    int x=20;
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:odbc:ShopDsn");
    java.sql.Statement stmt=con.createStatement();
    String qr="select * from products";
    java.sql.ResultSet rs=stmt.executeQuery(qr);
%>
<html>
    <body>
        <h1>Product-List</h1>
        <table bgcolor="pink" border="5" width="5" cellspacing="5" cellpadding="5">
            <thead>
                <tr>
                    <th>PCode</th>
                    <th>Name</th>
                    <th>Desc</th>
                    <th>Cat</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
<%
    while(rs.next())
    {
        String s1=rs.getString(1);
        String s2=rs.getString(2);
        String s3=rs.getString(3);
        String s4=rs.getString(4);
        String s5=rs.getString(5);
%>                
                <tr>
                    <td><% out.println(s1); %></td>
                    <td><% out.println(s2); %></td>
                    <td><% out.println(s3); %></td>
                    <td><% out.println(s4); %></td>
                    <td><% out.println(s5); %></td>
                </tr>
                <%
               }
               con.close();
               %>
            </tbody>
        </table>
    </body>
</html>
