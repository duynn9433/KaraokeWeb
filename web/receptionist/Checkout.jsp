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
        <title>Checkout</title>
        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>

        <style>
            .big-checkbox {
                width: 20px;
                height: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="row my-3">
                <h3>Tìm danh sách booking theo khách hàng: </h3>
            </div>
            <div class="row mb-3">
                <form action="<c:url value="/CheckoutServlet"/>" method="POST">
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

            <div class="row mb-3">
                <h3>Danh sách Booking: </h3>
            </div>

            <div class="row my-3">
                <div class="col">
                    <form action="<c:url value="/CheckoutServlet"/>" method="POST">
                        <c:forEach var="booking" items="${listBookings}" varStatus="statusBooking">
                            <div class="container my-2">
                                <div class="row">
                                    <div class="col-2" style="display: flex; align-items: center;">
                                        <div class="row">
                                            <input class="big-checkbox" type="checkbox" name="selectedBooking" value="<c:out value="${statusBooking.index}"/>">
                                            <p class="ml-2">Booking ${booking.ID}</p>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <table class="table" cellspacing="5" cellpadding="5" border="1">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th>Mã phòng</th>
                                                    <th>Thời gian đặt trước</th>
                                                    <th>Giá / giờ</th>
                                                    <th>Số nhân viên</th>
                                                </tr>
                                            </thead>

                                            <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBooked">
                                                <tr valign="top">
                                                    <td>${bookedRoom.room.ID}</td>
                                                    <td><tags:ldt date="${booking.bookDate}" pattern="dd/MM/yyyy"/></td>
                                                    <td>${bookedRoom.pricePerHour}</td>
                                                    <td>${bookedRoom.listHiredStaff.size()}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <input class="btn btn-primary my-3" type="submit" name="ADD_SERVICES" value="Thêm dịch vụ">
                        <input class="btn btn-primary my-3" type="submit" name="PAYMENT" value="Thanh toán">
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>
