<%-- Document : CheckinFrm Created on : Nov 8, 2021, 9:11:10 PM Author : xxxx9 --%>

<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<% int bookedIndex = 0;%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
        <title>Checkin</title>
    </head>

    <body>

        <div class="container">
            <div class="row my-2">
                <h3>Tìm danh sách booking theo khách hàng: </h3>
            </div>
            <div class="row">

                <form action="<c:url value="/CheckinServlet"/>" method="POST">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="customer_name">Tên khách hàng:</label>
                                <input type="text" name="customer_name" id="customer_name"
                                       class="form-control" placeholder="Nhập tên khách hàng"
                                       value="Nguyen A" />
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="customer_name">Số điện thoại:</label>
                                <input type="text" class="form-control" name="customer_phone"
                                       id="customer_phone" placeholder="Nhập số điện thoại" value="2000001" />
                            </div>
                        </div>
                        <div class="col" style="display: flex; align-items:flex-end;">
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" name="SEARCH_CUSTOMER" value="Tìm kiếm" />
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <br>
            <div class="row">
                <h3 class="text-secondary">Thông tin khách hàng</h3>
            </div>
            <div class="row">
                <h4>Tên:
                    <c:out value="${sessionClient.name}" />
                </h4>
            </div>
            <br>
            <div class="row mb-3">
                <h3 class="text-secondary">Danh sách Booking: </h3>
                
            </div>

            <div class="row">
                <form action="<c:url value="/CheckinServlet"/>" method="POST">
                    <c:forEach var="booking" items="${requestBookings}" varStatus="statusBooking">
                        <div class="container">
                            <div class="row">
                                <div class="col-1" style="display: flex; align-items: baseline;">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="selectedBooking"
                                        value="<c:out value="${statusBooking.index}" />">
                                    </div>
                                </div>
                                <div class="col">
                                    <table class="table" border="1">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Mã phòng</th>
                                                <th>Thời gian đặt trước</th>
                                                <th>Giá / h</th>
                                            </tr>
                                        </thead>
                                       
            
                                        <c:forEach var="bookedRoom" items="${booking.listBookedRoom}"
                                                   varStatus="statusBooked">
                                            <tr valign="top">
                                                <td>${bookedRoom.room.ID}</td>
                                                <td>
                                                    <tags:ldt date="${booking.bookDate}" pattern="dd/MM/yyyy" />
                                                </td>
                                                <td>${bookedRoom.pricePerHour}</td>
                                            </tr>
                                        </c:forEach>
            
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="btn-group" role="group" aria-label="Basic example">
                        <input class="btn btn-primary" type="submit" name="CREATE_BOOKING" value="Tạo Booking">
                        <input class="btn btn-primary" type="submit" name="SELECT_STAFF" value="Chọn nhân viên">
                        <input class="btn btn-primary" type="submit" name="CHECKIN" value="Checkin">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>