<%-- 
    Document   : ServiceManagement
    Created on : Nov 13, 2021, 8:36:34 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Service Management</h1>
        <div>
            <form action ="AddServiceView.jsp">
                <input type ="submit" value ="Add Service">
            </form>
        </div>
        <div>
            <form action ="SearchServiceView.jsp">
                <input type ="submit" value ="Edit Service">
            </form>
        </div>
        <div>
            <form action ="DeleteServiceView.jsp">
                <input type ="submit" value ="Delete Service">
            </form>
        </div>
    </body>
</html>
