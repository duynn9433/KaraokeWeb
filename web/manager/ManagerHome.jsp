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
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <form action="<c:url value="/ManagerHomeServlet"/>" method="post">
                        <input class = "btn btn-danger" type="submit" value="Log out">
                    </form>
                </div>
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <h3>Logged in as: ${user.name}</h3>
                </div>
            </div>

            <div class ="row" style = "margin-top:20px;">
                <div class="col-12 text-center">
                    <h1>Manager's Home</h1>
                </div>
            </div>

            <div class="row ">
                <div class="col-12 text-center">
                    <form action ="<c:url value="/manager/ManagementKaraBarView.jsp"/>" method="post">
                        <input class = "btn btn-primary" type="submit" value="Quản lí thông tin nhà hàng">
                    </form><br>
                    <form action ="<c:url value="/manager/.jsp"/>" method="post">
                        <input class = "btn btn-primary" type="submit" value="Quản lí thông tin phòng">
                    </form><br>
                    <form action ="<c:url value="/manager/ServiceManagementView.jsp"/>" method="post">
                        <input class = "btn btn-primary" type="submit" value="Quản lí dịch vụ">
                    </form><br>
                    <form action ="<c:url value="/manager/SelectStatView.jsp"/>" method="post">
                        <input class = "btn btn-primary" type="submit" value="Xem báo cáo thống kê">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
