<%-- 
    Document   : Checkout
    Created on : Nov 12, 2021, 10:26:04 AM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cehckout</title>
    </head>
    <body>
         <form action="<c:url value="/CheckinFrmServlet"/>" method="POST">
            <input type="hidden" name="action" value="SEARCH_CUSTOMER"/>
            <span>
                <label for="customer_name">Tìm khách hàng: </label>
                <input type="text" name="customer_name" id="customer_name"/>
                <input type="text" name="customer_phone" id="customer_phone"/>
                <input type="submit" value="Tìm kiếm" />
            </span>
        </form>

        <br>

        <h2>Danh sách phòng: </h2>




        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>Tên khách</th>
                <th>Mã phòng</th>
                <th>Thời gian checkin</th>
            </tr>



            <c:forEach var="booking" items="${bookings}" varStatus="statusBooking">
                <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBooked">
                    <tr valign="top">
                        <td>${booking.client.name}</td>
                        <td>${bookedRoom.room.ID}</td>
                        <td><tags:ldt date="${booking.bookDate}" pattern="dd/MM/yyyy"/></td>
                        <td><input type="checkbox" name="selectedBookedRoom" value="<c:out value="${9}"/>"></td>
                    </tr>
                </c:forEach>
            </c:forEach>

        </table>
    </body>
</html>
