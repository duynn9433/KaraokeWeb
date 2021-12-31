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

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
        crossorigin="anonymous">
  <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
  <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
  <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <form action="<c:url value="/PaymentServlet"/>" method="POST">

                <h2>Hóa đơn</h2>

                <div class="container">
                    <div class="row my-1">
                        <div class="col">
                            <h5 class="text-secondary mr-1">Tên khách hàng: </h5>
                        </div>
                        <div class="col">
                            <h5>${booking.client.name}</h5>
                        </div>
                    </div>
                    <div class="row my-1">
                        <div class="col">
                            <h5 class="text-secondary mr-1">Khuyến mãi: </h5>
                            
                        </div>
                        <div class="col">
                            <h5>${booking.saleOff}</h5>
                        </div>
                    </div>
                    <div class="row my-1">
                        <div class="col">
                            <h5 class="text-secondary mr-1">Tổng: </h5>
                            
                        </div>
                        <div class="col">
                            <h5>${totalMoney}</h4>
                        </div>
                    </div>

                    <div class="row my-1">
                        <div class="col">
                            <h5 class="mr-1">Phương thức thanh toán: </h5>
                        </div>
                        <div class="col">
                            <input class="form-control" type="text" name="paymentMethod">
                        </div> 
                    </div>

                    <div class="row my-1">
                        <div class="col">
                            <h5 class="mr-1">Ghi chú: </h5>
                        </div>
                        <div class="col">
                            <input class="form-control" type="text" name="note">
                        </div> 
                    </div>

                    <div class="row my-2" >
                        <h3>Danh sách phòng:</h3>
                    </div>

                    <div class="row my-2">
                        <table class="table" cellspacing="5" cellpadding="5" border="1">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Mã phòng</th>
                                    <th>Checkin</th>
                                    <th>Checkout</th>
                                    <th>Đơn giá</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            
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
                    </div>

                    <div class="row mt-4 mb-1">
                        <h3>Danh sách dịch vụ: </h3>
                    </div>

                    <div class="row my-1">
                        <table class="table" cellspacing="5" cellpadding="5" border="1">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Mã phòng</th>
                                    <th>Dịch vụ</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Thành tiền</th>
                                    <th>Ghi chú</th>
                                </tr>
                            </thead>
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
                    </div>
                </div>
                <input class="btn btn-primary mt-4" type="submit" value="Hoàn thành" name="DONE" />
            </form>
            </div>
        </div>
    </body>
</html>
