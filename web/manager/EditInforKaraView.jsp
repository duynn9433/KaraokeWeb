

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
    </head>
    <body>
         <h1>Edit Infor Kara Bar</h1>
        <form action="<c:url value="/EditInforKara"/>" method="POST">
            Name:<br><input type="text" name="name" value="${kara.name}"><br>
            Address:<br><input type="text" name="address" value="${kara.address}"><br>
            Desciption:<br><input type="text" name="des" value="${kara.description}"><br>
            <input type="submit" name="save" value="save">
        </form>
    </body>
</html>
