<%-- 
    Document   : CheckinFrm
    Created on : Nov 8, 2021, 9:11:10 PM
    Author     : xxxx9
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%
    int bookedIndex = 0;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <form action="<c:url value="/CheckinServlet"/>" method="POST">
   
            <span>
                <label for="customer_name">Tìm phòng: </label>
                <input type="text" name="customer_name" id="customer_name" value = "Nguyen A"/>
                <input type="text" name="customer_phone" id="customer_phone" value = "2000001"/>
                <input type="submit" name="SEARCH_CUSTOMER" value="Tìm kiếm" />
            </span>
        </form>

        <br>

        <h3>Thông tin khách hàng</h3>
        <h2>Tên: <c:out value="${sessionClient.name}" /></h2>


        <h2>Danh sách Booking: </h2>

        <form action="<c:url value="/CheckinServlet"/>" method="POST">
            <c:forEach var="booking" items="${requestBookings}" varStatus="statusBooking">
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

            <input type="submit" name="CREATE_BOOKING" value="Tạo Booking">
            <input type="submit" name="SELECT_STAFF" value="Chọn nhân viên">
            <input type="submit" name="CHECKIN" value="Checkin">
        </form>
    </body>
</html>
