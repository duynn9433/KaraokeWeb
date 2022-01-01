<%-- 
    Document   : SellerBookRoomInfo
    Created on : Nov 3, 2021, 2:51:50 PM
    Author     : duynn
--%>

<%@page import="java.util.List"%>
<%@page import="model.Client"%>
<%@page import="model.Room"%>
<%@page import="model.BookedRoom"%>
<%@page import="model.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Book Room Information (Search Client)</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row my-2">
                <h1>Thông tin đặt phòng</h1>
            </div>

            <div class="row mt-4">
                <h2>Thông tin phòng:</h2>
                <table class="table table-striped" cellspacing="5" cellpadding="5" border="1">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Tên</th>
                            <th>Cỡ</th>
                            <th>Hạng</th>
                            <th>Giá</th>
                            <th>Mô tả</th>
                        </tr>
                    </thead>

                    <c:forEach var="i" items="${booking.listBookedRoom}" varStatus="status">
                        <tr valign="top">
                            <td>${i.room.ID}</td>
                            <td>${i.room.name}</td>
                            <td>${i.room.size}</td>
                            <td>${i.room.type}</td>
                            <td>${i.room.pricePerHour}</td>
                            <td>${i.room.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="row mt-4">
                <form action="<c:url value="/SellerBookRoomInfo"/>" method="POST">
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
                                <input type="hidden" name="action" value="Tim">
                                <input type="submit" class="btn btn-primary" name="Tim" value="Tìm kiếm" />
                            </div>
                        </div>


                    </div>
                </form>
            </div>

            <div class="row">
                <form action="<c:url value="/SellerBookRoomInfo" />" method="post">

                    <div class="col" style="display: flex; align-items:flex-end;">
                        <div class="form-group">
                            <input type="hidden" name="action" value="Them">
                            <input type="hidden" name="name" value="${name}">
                            <input type="hidden" name="phoneNumber" value="${phoneNumber}">
                            <input type="submit" class="btn btn-primary" name="Them" value="Thêm khách hàng" />
                        </div>
                    </div>
                </form>
            </div>

            <div class="row mt-4">
                <form action="<c:url value="/SellerBookRoomInfo" />" method="post">
                    <table class="table table-striped" cellspacing="5" cellpadding="5" border="1">
                        <tr>
                            <th>ID</th>
                            <th>Tên</th>
                            <th>Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th>Mail</th>
                            <th>Note</th>
                            <th>Chọn</th>
                        </tr>
                        <c:forEach var="i" items="${listClient}" varStatus="status">
                            <tr valign="top">
                                <td>${i.ID}</td>
                                <td>${i.name}</td>
                                <td>${i.phoneNumber}</td>
                                <td>${i.address}</td>
                                <td>${i.mail}</td>
                                <td>${i.note}</td>
                                <td>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>">
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>    
                    </table>
                    <input type="hidden" name="action" value="Luu">
                    <input class="btn btn-primary" type="submit" value="Lưu">
                </form>
            </div>
        </div>
    </body>
</html>
