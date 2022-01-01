<%-- 
    Document   : SellerCancelRoomView
    Created on : Nov 5, 2021, 8:40:49 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Cancel Room</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row my-2">
                <h1>Huỷ phòng cho khách qua điện thoại</h1>
            </div>
            <div class="row">
                <form action="<c:url value="/SellerCancelRoomServlet" />" method="post">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="name">Tên khách hàng:</label>
                                <input type="text" name="name" id="name"
                                       class="form-control" placeholder="Nhập tên khách hàng"
                                       value="Nguyen A" />
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="phoneNumber">Số điện thoại:</label>
                                <input type="text" class="form-control" name="phoneNumber"
                                       id="phoneNumber" placeholder="Nhập số điện thoại" value="2000001" />
                            </div>
                        </div>
                        <div class="col" style="display: flex; align-items:flex-end;">
                            <div class="form-group">
                                <input type="hidden" name="action"  value ="searchRoom">
                                <input type="submit" class="btn btn-primary" name="SEARCH_CUSTOMER" value="Tìm kiếm" />
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <form action="<c:url value="/SellerCancelRoomServlet" />" method="post">
                    <input type="hidden" name="action"  value ="deleteRoom">
                    <table cellspacing="5" cellpadding="5" border="1">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID Khách hàng</th>
                                <th>Tên</th>
                                <th>Số điện thoại</th>
                                <th>ID Booking</th>
                                <th>Ngày đặt</th>
                                <th>ID Phòng</th>
                                <th>Tên </th>
                                <th>Checkin</th>
                                <th>Checkout</th>
                                <th>Chọn </th>
                            </tr>
                        </thead>

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

                                    <td>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="selectedItems" value="<c:out value="${j.ID}"/>">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                    <input type="hidden" name="name" value="${name}">
                    <input type="hidden" name="phoneNumber" value="${phoneNumber}">
                    <br>
                    <input class="btn btn-primary" type="submit" value="Xoá phòng"  />
                </form>
            </div>
        </div>


    </body>
</html>
