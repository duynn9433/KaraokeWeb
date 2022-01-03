<%-- 
    Document   : AddInforRoomView
    Created on : Nov 17, 2021, 1:51:25 PM
    Author     : Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("addRoomMsg");
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${addRoomMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("addRoomMsg");
            }
        %>
        <h1>Add Infor Room </h1>
        <form action="<c:url value="/AddInforRoomServlet"/>" method="post">
            Name:<br><input type="text" name="name"><br>
            Size : <br><select name="selectsize">
                <option>Small</option>
            </select><br>
            Type: <br><select name="selecttype">
                <option>Narmall</option>
            </select><br>
            Price:<br><input type="text" name="price"><br>
            Description:<br><input type="text" name="des"><br>
            <input type="submit" name="save" value="save">
        </form>
    </body>
</html>
