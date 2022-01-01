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
        <title>Add Service</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
                <tr class = "tbl-row">
                    <td>Ten:</td>
                    <td><input class = "form-control" type="text" name="name" value="name" required></td>
                </tr>
                <tr class = "tbl-row">
                    <td>Loai:</td>
                    <td><input class = "form-control" type="text" name="unity" value="unity" required></td>
                </tr>
                <tr class = "tbl-row">
                    <td>Gia:</td>
                    <td><input class = "form-control" type="text" name="price" value="price" required></td>
                </tr>
                <tr class = "tbl-row">
                    <td>Mo ta:</td>
                    <td><input class = "form-control" type="text" name="des" value="des" required></td>
                </tr>

            </table>
            <input type="hidden" name="action" value="them"><br>
            <input class = "btn btn-primary" type="submit" value="Them">
        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Home">
        </form>
    </body>
</html>
