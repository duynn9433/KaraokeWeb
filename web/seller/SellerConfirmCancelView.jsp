<%-- 
    Document   : SellerConfirmCancelView
    Created on : Nov 5, 2021, 8:41:01 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Confirm Cancel View</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("sellerConfirmCancelMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${sellerConfirmCancelMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("sellerConfirmCancelMsg");
            }
        %>
        <div class="container">
            <div class="row my-2">
                <div class="col">
                    <h1>Thông tin huỷ phòng</h1>
                </div>
                <div class="col">
                    <form action ="<c:url value="/seller/SellerHome.jsp"/>" method="post">
                        <input class="btn btn-primary" type="submit" value="Home">
                    </form>
                </div>
            </div>
            <div class="row">
                <table class="table table-striped" cellspacing="5" cellpadding="5" border="1">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID Phòng</th>
                            <th>Tên</th>
                            <th>Cỡ</th>
                            <th>Hạng</th>
                            <th>Giá</th>
                            <th>Checkin</th>
                            <th>Checkout</th>
                        </tr>
                    </thead>

                    <c:forEach var="j" items="${listDeleteBookedRoom}" >
                        <tr valign="top">
                            <td>${j.room.ID}</td>
                            <td>${j.room.name}</td>
                            <td>${j.room.size}</td>
                            <td>${j.room.type}</td>
                            <td>${j.room.pricePerHour}</td>
                            <td>${j.checkin}</td>
                            <td>${j.checkout}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="row">
                <form action="<c:url value="/SellerConfirmCancelServlet" />" method="post">
                    <input type="hidden" name="action"  value ="xacNhan">
                    <input class="btn btn-primary" type="submit" value="Xác nhận"  />
                </form>
            </div>
        </div>
<!--        <form action ="<c:url value="/seller/SellerHome.jsp"/>" method="post">

            <input type="submit" value="Home">
        </form>-->
    </body>
</html>
