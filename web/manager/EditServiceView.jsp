<%-- 
    Document   : EditServiceView
    Created on : Nov 13, 2021, 9:08:19 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa dịch vụ</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    
    <style>
        .btn-primary{
            width: 100px;
        }
    </style>
    
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("editServiceMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${editServiceMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("editServiceMsg");
            }
        %>
        <h1>Sửa dịch vụ</h1> 
        <form action="<c:url value="/EditServiceServlet"/>" method="post">
            <table>
                <tr>
                    <td>Tên:</td>
                    <td><input class = "form-control" type="text" name="name" value="tên" required></td>
                </tr>
                <tr>
                    <td>Loại:</td>
                    <td><input class = "form-control" type="text" name="unity" value="loại" required></td>
                </tr>
                <tr>
                    <td>Giá:</td>
                    <td><input class = "form-control" type="text" name="price" value="giá" required></td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td><input class = "form-control" type="text" name="des" value="mô tả" required></td>
                </tr>

            </table>
            <input type="hidden" name="action" value="Sửa"><br>
            <input class = "btn btn-primary" type="submit" value="Sua">
        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
            <input class = "btn btn-primary" type="submit" value="HOME">
        </form>
    </body>
</html>
