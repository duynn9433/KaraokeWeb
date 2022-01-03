

<%-- 
    Document   : Add
    Created on : Nov 14, 2021, 8:40:56 AM
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
        <% String msg = (String)request.getSession().getAttribute("editKaraMsg");
            if(msg!=null){%>
        <script> alert("${editKaraMsg}"); </script>
        <% request.getSession().removeAttribute("editKaraMsg");
            }%>
        <div class="text-center">
         <h1>Sửa thông tin Kara Bar</h1>
        <form action="<c:url value="/EditInforKara"/>" method="POST">
            Tên:<br><input type="text" name="name" value="${kara.name}"><br>
            Địa chỉ:<br><input type="text" name="address" value="${kara.address}"><br>
            Mô tả:<br><input type="text" name="des" value="${kara.description}"><br><br>
            <input type="submit" name="save" value="luu" class="btn-primary">
        </form>
        </div>
    </body>
</html>
