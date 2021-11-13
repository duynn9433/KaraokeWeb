<%-- 
    Document   : SellerBooking
    Created on : Nov 2, 2021, 9:53:16 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Book Room</title>
    </head>
    <body>
        <h1>Dat phong</h1>
        <form action="<c:url value="/SellerBookRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="searchFreeRoom">
            <!--<input type="hidden" name="user" value="${user}">-->
            <table>
                <tr>
                    <td align="right">Check-in:</td>
                    <td><input type="text" name="checkin" value="2021-10-10 00:00:00"></td>
                </tr>
                <tr>
                    <td align="right">Check-out:</td>
                    <td><input type="text" name="checkout" value="2021-10-10 01:00:00"></td>
                </tr>
                <tr>
                <input type="submit" value="Tim">
                </tr>
            </table>
        </form>
        <form action="<c:url value="/SellerBookRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="bookRoom">
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>ID</th>
                    <th>Ten</th>
                    <th>Co</th>
                    <th>Hang</th>
                    <th>Gia</th>
                    <th>Mo ta</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listRoom}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.size}</td>
                        <td>${i.type}</td>
                        <td>${i.pricePerHour}</td>
                        <td>${i.description}</td>
                        <td><c:out value="${i.ID}"/></td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" name="checkin" value="${checkin}">
            <input type="hidden" name="checkout" value="${checkout}">
            <input type="submit" value="Dat phong" name="update" />
        </form>

    </body>
</html>
