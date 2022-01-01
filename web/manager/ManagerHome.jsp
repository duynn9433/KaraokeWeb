<%-- 
    Document   : ManagerHomeView
    Created on : Nov 5, 2021, 8:43:00 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manager Home View</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    
    <style>
        .btn-primary{
            width: 200px;
        }
    </style>
    
    <body>
        <table>
            <tr>
                <td>
                    <form action="<c:url value="/ManagerHomeServlet"/>" method="post">
                        <input class = "btn btn-danger" type="submit" value="Log out">
                    </form>
                </td>
                <td>Logged in as: ${user.name}</td>
            </tr>
        </table>
        <h1>Manager's Home</h1>
        <form action ="<c:url value="/manager/ManagementKaraBarView.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Quan li thong tin nha hang">
        </form><br>
        <form action ="<c:url value="/manager/.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Quan li thong tin phong">
        </form><br>
        <form action ="<c:url value="/manager/ServiceManagementView.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Quan li dich vu">
        </form><br>
        <form action ="<c:url value="/manager/SelectStatView.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="Xem bao cao thong ke">
        </form>
    </body>
</html>
