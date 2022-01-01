<%-- 
    Document   : SellerHome
    Created on : Nov 2, 2021, 9:19:22 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Home</title>
        
        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <table>
            <tr>
<!--                <td><button onclick="location.href = 'index.html'">Logout</button></td>-->
                <td>
                    <form action="<c:url value="/sellerHomeServlet"/>" method="post">
                        <input type="submit" value="Log out">
                    </form>
                </td>
                <td>Logged in as: ${user.name}</td>
            </tr>
        </table>
        <h1>Seller's Home</h1>
        <!--        <form action="SellerBookingServlet" method="post">
                    <input type ="hidden" name="user" value=${user}>
                    <input type="submit" value="Dat phong cho khach hang qua dien thoai">
                </form>
                <form action="sellerCancel" method="post">
                    <input type ="hidden" name="user" value=${user}>
                    <input type="submit" value="Huy phong cho khach hang qua dien thoai">
                </form>-->
        <form action ="<c:url value="/seller/SellerBookRoom.jsp"/>" method="post">
            <input type="submit" value="Dat phong cho khach qua dien thoai">
        </form>
        <form action ="<c:url value="/seller/SellerCancelRoomView.jsp"/>" method="post">
            <input type="submit" value="Huy phong cho khach qua dien thoai">
        </form>
    </body>
</html>
