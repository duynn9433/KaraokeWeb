<%-- 
    Document   : SellerCancelRoomView
    Created on : Nov 5, 2021, 8:40:49 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Cancel Room</title>
    </head>
    <body>
        <h1>Huy phong</h1>
        <form action="<c:url value="/ReceptionistCancelRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="searchRoom">
            <!--<input type="hidden" name="user" value="${user}">-->
            <table>
                <tr>
                    <td align="right">Ten:</td>
                    <td><input type="text" name="name" value="Nguyen A"></td>
                </tr>
                <tr>
                    <td align="right">So dien thoai:</td>
                    <td><input type="text" name="phoneNumber" value="2000001"></td>
                </tr>
                <tr>
                <input type="submit" value="Tim">
                </tr>
            </table>
        </form>
        <form action="<c:url value="/ReceptionistCancelRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="deleteRoom">
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>ID Khach hang</th>
                    <th>Ten</th>
                    <th>So dien thoai</th>
                    <th>ID Booking</th>
                    <th>Ngay dat</th>
                    <th>ID Phong</th>
                    <th>Ten </th>
                    <th>Checkin</th>
                    <th>Checkout</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listBooking}" >
                    <c:forEach var="j" items="${i.listBookedRoom}" varStatus="status">
                        <tr valign="top">
                            <td>${i.client.ID}</td>
                            <td>${i.client.name}</td>
                            <td>${i.client.phoneNumber}</td>
                            <td>${i.ID}</td>
                            <td>${i.bookDate}</td>

                            <td>${j.room.ID}</td>
                            <td>${j.room.name}</td>
                            <td>${j.checkin}</td>
                            <td>${j.checkout}</td>

                            <td><input type="checkbox" name="selectedItems" value="<c:out value="${j.ID}"/>"></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            <input type="hidden" name="name" value="${name}">
            <input type="hidden" name="phoneNumber" value="${phoneNumber}">
            <input type="submit" value="Xoa phong"  />
        </form>
    </body>
</html>
