<%-- 
    Document   : ServiceManagement
    Created on : Nov 13, 2021, 8:36:34 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý dịch vụ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    
    <style>
        .btn{
            width: 150px;
        }
    </style>
    
    <body>
        <h1>Quản lý dịch vụ</h1>
        <div>
            <form action ="AddServiceView.jsp">
                <input class = "btn btn-primary" type ="submit" value ="Thêm dịch vụ">
            </form>
        </div><br>
        <div>
            <form action ="SearchServiceView.jsp">
                <input class = "btn btn-primary" type ="submit" value ="Sửa dịch vụ">
            </form>
        </div><br>
        <div>
            <form action ="DeleteServiceView.jsp">
                <input class = "btn btn-primary" type ="submit" value ="Xóa dịch vụ">
            </form>
        </div><br>
    </body>
</html>
