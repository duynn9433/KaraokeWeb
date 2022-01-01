<%-- 
    Document   : ReceptionistHoneFrm
    Created on : Nov 11, 2021, 8:02:48 AM
    Author     : xxxx9
--%>

<%@page import="model.User"%>
<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhan vien le tan</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css" crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <style>
        .btn-primary{
            width: 300px;
        }
    </style>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <form action="<c:url value="/ReceptionistHomeServlet"/>" method="post">
                        <input type="hidden" name="action" value="logOut">
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
                    <h1>Receptionist's Home</h1>
                </div>
            </div>

            <div class="row ">
                <div class="col-12 text-center">
                    <form action="<c:url value="/ReceptionistHomeServlet"/>" method="POST">
                        <input type="hidden" name="action" value="ACTION_CHECKIN"/>
                        <input class="btn btn-primary" type="submit" value="Checkin" />
                    </form>
                    <br>
                    <form action="<c:url value="/ReceptionistHomeServlet"/>" method="POST">
                        <input type="hidden" name="action" value="ACTION_CHECKOUT"/>
                        <input class="btn btn-primary" type="submit" value="Checkout" />
                    </form>
                    <br>
                    <form action ="<c:url value="/receptionist/SearchFreeRoom.jsp"/>" method="post">
                        <input class="btn btn-primary" type="submit" value="Đặt phòng cho khách hàng tại quầy">
                    </form><br>
                    <form action ="<c:url value="/receptionist/ReceptionistCancelRoom.jsp"/>" method="post">
                        <input class="btn btn-primary" type="submit" value="Huỷ phòng cho khách hàng tại quầy">
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>
