<%-- 
    Document   : SellerConfirmView
    Created on : Nov 3, 2021, 8:43:57 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Confirm Booking</title>

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
        <%
            String msg = (String) request.getSession().getAttribute("confirmBookingMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${confirmBookingMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("confirmBookingMsg");
            }
        %>

        <div class="container">
            <div class="row my-2">
                <div class="col">
                    <h1>Thông tin phòng và khách hàng</h1>
                </div>
                <div class="col">
                    <form action ="<c:url value="/seller/SellerHome.jsp"/>" method="post">
                        <input class="btn btn-primary" type="submit" value="Home">
                    </form>
                </div>
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

            <div>
                <div class="row mt-4">
                    <h2>Thông tin khách hàng:</h2>
                </div>
                <div class="row mt-4">
                    <table class="table table">
                        <tr>
                            <td>Tên: </td>
                            <td>${booking.client.name}</td>
                        </tr>
                        <tr>
                            <td>Số điện thoại: </td>
                            <td>${booking.client.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Địa chỉ: </td>
                            <td>${booking.client.address}</td>
                        </tr>
                        <tr>
                            <td>Mail: </td>
                            <td>${booking.client.mail}</td>
                        </tr>
                        <tr>
                            <td>Note: </td>
                            <td>${booking.client.note}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row mt-4">
                <form action ="<c:url value="/SellerConfirmBookingServlet"/>" method="post">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="note"> Ghi chú:</label>
                                <input type="text" name="note" id="note" 
                                       class="form-control" 
                                       placeholder="Nhập ghi chú" value="">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="saleOff"> Khuyến mãi:</label>
                                <input type="text" name="saleOff" id="saleOff" 
                                       class="form-control" 
                                       placeholder="Nhập khuyến mãi" value="0.0">
                            </div>
                        </div>
                        <div class="col" style="display: flex; align-items:flex-end;">
                            <div class="form-group">
                                <input type="hidden" name="action" value="Xacnhan">
                                <input type="submit" class="btn btn-primary" name="XacNhan" value="Xác nhận" />
                            </div>
                        </div>
                    </div>
                    <!--
                                        <table>
                                            <tr>
                                                <td>Ghi chu: </td>
                    
                                                <td>Khuyen mai: </td>
                                                <td><input type="text" name="saleOff" value="0.0"></td>
                                            </tr>
                                        </table>
                                        <input type="hidden" name="action" value="Xacnhan">
                                        <input class="btn btn-primary" type="submit" value="Xac nhan">-->
                </form>
            </div>
<!--            <div class="row mt-4">
                <div class="col">
                    <form action ="<c:url value="/seller/SellerHome.jsp"/>" method="post">
                        <input class="btn btn-primary" type="submit" value="Home">
                    </form>
                </div>
            </div>-->
        </div>
    </body>
</html>
