<%-- 
    Document   : AddServiceView
    Created on : Nov 13, 2021, 8:46:18 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("addServiceMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${addKaraMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("addServiceMsg");
            }
        %>
        <h1>Them dich vu</h1> 
        <form action="<c:url value="/AddServiceServlet"/>" method="post">
            <table>
                <tr>
                    <td>Ten:</td>
                    <td><input type="text" name="name" value="name"></td>
                </tr>
                <tr>
                    <td>Loai:</td>
                    <td><input type="text" name="unity" value="unity"></td>
                </tr>
                <tr>
                    <td>Gia:</td>
                    <td><input type="text" name="price" value="price"></td>
                </tr>
                <tr>
                    <td>Mo ta:</td>
                    <td><input type="text" name="des" value="des"></td>
                </tr>

            </table>
            <input type="hidden" name="action" value="them">
            <input type="submit" value="Them">
        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">

            <input type="submit" value="Home">
        </form>
    </body>
</html>
