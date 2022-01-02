<%-- 
    Document   : AddInforKaraView
    Created on : Nov 13, 2021, 2:43:35 PM
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
    </head>
    <body>
        <% String msg = (String)request.getSession().getAttribute("addKaraMsg");
            if(msg!=null){%>
        <script> alert("${addKaraMsg}"); </script>
        <% request.getSession().removeAttribute("addKaraMsg");
            }%>
        
        <h1 class="text-center">Thêm thông tin Karaoke Bar</h1>
        <div class="thumbnail">
        <form action="<c:url value="/AddInforKara"/>" method="POST" class="text-center">
            Tên:<br><input type="text" name="name"><br>
            Địa chỉ:<br><input type="text" name="address"><br>
            Mô tả:<br><input type="text" name="des"><br><br>
            <input type="submit" name="save" value="luu" class ="btn-primary">
        </form>
        </div>
    </body>
</html>
