<%-- 
    Document   : SellerAddClientView
    Created on : Nov 5, 2021, 8:39:50 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Add New Client</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid">
            <!--<div class ="row col-12 text-center" style = "margin-top:180px;">-->
            <div class="row  col-12 text-center">
                <h1>Thêm khách hàng mới</h1> <br>
            </div>
            <div class="row col-12">
                <form action="<c:url value="/SellerAddClientServlet" />" method="post">
                    <table>
                        <tr>
                            <td>Tên: </td>
                            <td> <input class="form-control" type="text" name="name" value="${name}"></td>
                        </tr>
                        <tr>
                            <td>Số điện thoại: </td>
                            <td> <input class="form-control" type="text" name="phoneNumber" value="${phoneNumber}"></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ: </td>
                            <td> <input class="form-control" type="text" name="address" value=""></td>
                        </tr>
                        <tr>
                            <td>Email: </td>
                            <td> <input class="form-control" type="text" name="email" value=""></td>
                        </tr>
                        <tr>
                            <td>Ghi chú: </td>
                            <td> <input class="form-control" type="text" name="note" value=""></td>
                        </tr>
                    </table>
                    <br>
                    <input type="hidden" name="action"  value="them">
                    <input class="btn btn-primary" type="submit" value="Thêm khách hàng">
                </form>
            </div>

            <!--</div>-->
        </div>
    </body>
</html>
