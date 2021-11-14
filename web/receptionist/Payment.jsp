<%-- 
    Document   : Payment
    Created on : Nov 14, 2021, 9:40:14 AM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hóa đơn</title>
    </head>
    <body>
        <form action="<c:url value="/PaymentServlet"/>" method="POST">
            <h2>Hóa đơn</h2>
            <h3>Tên khách hàng: ${booking.client.name}</h3>
            <h3>Khuyến mãi: ${booking.saleOff}</h3>
            <h3>Tổng: ${totalMoney}</h3>
            
            <span>
                <h3>Phương thức thanh toán: </h3>
                <input type="text" name="paymentMethod">
            </span>
            
            
            <span>
                <h3>Ghi chú: </h3>
                <input type="text" name="note">
            </span>
            

            <h3>Danh sách phòng</h3>
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Mã phòng</th>
                    <th>Checkin</th>
                    <th>Checkout</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                </tr>

                <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">
                    <tr valign="top">
                        <td>${bookedRoom.room.ID}</td>
                        <td><tags:ldt date="${bookedRoom.checkin}" pattern="HH:mm:ss dd/MM/yyyy"/></td>   
                        <td><tags:ldt date="${bookedRoom.checkout}" pattern="HH:mm:ss dd/MM/yyyy"/></td>
                        <td>${bookedRoom.pricePerHour}</td>
                        
                        
                        
                        <td><c:out value="${bookedRoom.pricePerHour * Duration.between(bookedRoom.checkin, bookedRoom.checkout).toHours()}"/></td>
                    </tr>
                </c:forEach>
            </table>

            <h3>Danh sách dịch vụ: </h3>
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Mã phòng</th>
                    <th>Dịch vụ</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                    <th>Ghi chú</th>
                </tr>

                <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusService">
                    <c:forEach var="usedService" items="${bookedRoom.listUsedService}" varStatus="statusService">
                        <tr valign="top">
                            <td>${bookedRoom.room.ID}</td>
                            <td>${usedService.service.name}</td>   
                            <td>${usedService.service.unity}</td>   
                            <td>${usedService.service.pricePerUnit}</td>
                            <td>${usedService.totalPrice}</td>
                            <td>${usedService.note}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            
            <input type="submit" value="Hoàn thành" name="DONE" />
        </form>
    </body>
</html>
