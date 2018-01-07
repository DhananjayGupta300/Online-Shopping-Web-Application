<%
    //session.setMaxInactiveInterval(100);
    String s1="",s2="";
    Cookie ck[]=request.getCookies();
    if(ck!=null)
    {    
    for(int i=0; i<ck.length;i++){
        String name=ck[i].getName();
        if(name.equals("uid")){
            s1=ck[i].getValue();
        }
        if(name.equals("pw")){
            s2=ck[i].getValue();
        }
    }
    }
 %>
<html>
    <body>
        <h1>Online Shopping</h1>
        <hr>
        
        <form action="VerifyUser">
            <pre>
            Userid          <input type="text" name="t1" value="<%=s1%>" />
            Password        <input type="password" name="t2" value="<%=s2%>" />
            UserType        <select name="utype">
                            <option>buyer</option>
                            <option>admin</option>
                            </select>
            SavePassword    <input type="checkbox" name="save" value="yes" />
                            <input type="submit" value="Submit" />
            </pre>
        </form>
        <hr>
        <a href="registration.jsp">new-user-registration</a><br>
    </body>
</html>
