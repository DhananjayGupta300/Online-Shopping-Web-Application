<%
    String name=(String)session.getAttribute("uname");
    long v=session.getCreationTime();
    java.util.Date dt=new java.util.Date(v);
%>
<html>
    <body>
        <h1>Welcome <%=name%></h1>
        <h2>You Are With Us <%=dt%></h2>
        <hr>
        <a href="ShowCategory">Start-Shopping</a><br>
        <a href="AllProducts.jsp">View-All-Products</a><br>
        <a href="DisplayCart">View-Cart</a><br>
        <a href="">Pay-Bills</a><br>
        <a href="">Track-Order</a><br>
        <a href="trial.jsp">Remove Name</a><br>
        <a href="EndSession">Logout</a><br>
        
        <hr>
    </body>
</html>
