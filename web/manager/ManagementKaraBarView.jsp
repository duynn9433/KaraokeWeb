<%-- 
    Document   : ManagementKaraBarView
    Created on : Nov 13, 2021, 2:37:42 PM
    Author     : Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            .mg5{
               margin-top: 50px; 
            }
        </style>
    </head>
    <body>
         <div class="text-center">
        <h1>Quản lí thông tin Karaoke Bar</h1>
        <button onclick="location.href ='AddInforKaraView.jsp'" class="btn-primary mg5"> Thêm mới thông tin</button><br>
        <button onclick="location.href ='<c:url value="/GetInforKara"/>'" class="btn-primary mg5"> Sửa thông tin Karaoke Bar</button><br>
         </div>
    </body>
</html>
