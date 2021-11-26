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
    </head>
    <body>
        <h1>Add Infor Kara Bar</h1>
        <form action="<c:url value="/AddInforKara"/>" method="POST">
            Name:<br><input type="text" name="name"><br>
            Address:<br><input type="text" name="address"><br>
            Desciption:<br><input type="text" name="des"><br>
            <input type="submit" name="save" value="save">
        </form>
    </body>
</html>
