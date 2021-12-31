<%-- 
    Document   : EditServiceView
    Created on : Nov 13, 2021, 9:08:19 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Service</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    
    <style>
        .btn-primary{
            width: 100px;
        }
    </style>
    
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("editServiceMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${editServiceMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("editServiceMsg");
            }
        %>
        <h1>Sua dich vu</h1> 
        <form action="<c:url value="/EditServiceServlet"/>" method="post">
            <table>
                <tr>
                    <td>Ten:</td>
                    <td><input class = "form-control" type="text" name="name" value="name" required></td>
                </tr>
                <tr>
                    <td>Loai:</td>
                    <td><input class = "form-control" type="text" name="unity" value="unity" required></td>
                </tr>
                <tr>
                    <td>Gia:</td>
                    <td><input class = "form-control" type="text" name="price" value="price" required></td>
                </tr>
                <tr>
                    <td>Mo ta:</td>
                    <td><input class = "form-control" type="text" name="des" value="des" required></td>
                </tr>

            </table>
            <input type="hidden" name="action" value="sua"><br>
            <input class = "btn btn-primary" type="submit" value="Sua">
        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Home">
        </form>
    </body>
</html>
