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
            <input type="hidden" name="action" value="SEARCH_CUSTOMER"/>
            <span>
                <label for="customer_name">Tìm phòng: </label>
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
                <th>Thời gian đặt trước</th>
                <th>Id</th>
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

        <form action="<c:url value="/CheckinServlet"/>" method="POST">
            <input type="hidden" name="action" value="ADD_BOOKING"/>
            <input type="submit" value="Tạo booking" />
        </form>



        <form action="<c:url value="/CheckinServlet"/>" method="POST">

            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Id Phòng</th>
                    <th>Nhân viên 1</th>
                    <th>Nhân viên 2</th>
                </tr>

                <c:set var="indexBooked" value="${0}" /> 
                <c:forEach var="booking" items="${bookings}" varStatus="statusBooking">
                    <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="status">

                        <tr valign="top">

                            <td>${bookedRoom.room.ID}</td>
                            <td>${bookedRoom.listHiredStaff[0].user.name}</td>
                            <td>${bookedRoom.listHiredStaff[1].user.name}</td>

                            <td><c:out value="${bookedRoom.ID}"/></td>
                            <td><input type="checkbox" name="selectedBooked" value="<c:out value = "${indexBooked}"/>"></td>
                        </tr>

                        <c:set var="indexBooked" value="${indexBooked+1}" /> 

                    </c:forEach>
                </c:forEach>
            </table>

            <input type="hidden" name="action" value="SELECT_STAFF"/>
            <input type="submit" value="Chọn nhân viên" />
        </form>

        <form action="<c:url value="/CheckinServlet"/>">
            <input type="hidden" name="action" value="SAVE_BOOKING"/>
            <input type="submit" value="Checkin" />
        </form>

    </body>
</html>
