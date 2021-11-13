<%-- 
    Document   : Checkout
    Created on : Nov 12, 2021, 10:26:04 AM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cehckout</title>
    </head>
    <body>
         <form action="<c:url value="/CheckoutServlet"/>" method="POST">
            <span>
                <label for="customer_name">Tìm khách hàng: </label>
                <input type="text" name="customer_name" id="customer_name" value="Nguyen A"/>
                <input type="text" name="customer_phone" id="customer_phone" value="2000001"/>
                <input type="submit" name="SEARCH_CUSTOMER" value="Tìm kiếm" />
            </span>
        </form>

        <br>

        <h2>Danh sách Booking: </h2>

        <form action="<c:url value="/CheckoutServlet"/>" method="POST">
            <c:forEach var="booking" items="${listBookings}" varStatus="statusBooking">
                <h3>Booking ${booking.ID}</h3>
                <input type="checkbox" name="selectedBooking" value="<c:out value="${statusBooking.index}"/>">

                <table cellspacing="5" cellpadding="5" border="1">
                    <tr>
                        <th>Mã phòng</th>
                        <th>Thời gian đặt trước</th>
                    </tr>

                    <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBooked">
                        <tr valign="top">
                            <td>${bookedRoom.room.ID}</td>
                            <td><tags:ldt date="${booking.bookDate}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>

                </table>
            </c:forEach>

            <input type="submit" name="ADD_SERVICES" value="Thêm dịch vụ">
            <input type="submit" name="PAYMENT" value="Thanh toán">
        </form>
    </body>
</html>
