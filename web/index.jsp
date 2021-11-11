<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Dang nhap</h1>
        <form action="<c:url value="/CheckLoginServlet" />" method ="post">
            <input type="hidden" name="action" value="checkLogin">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="seller"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="seller"></td>
                </tr>
            </table>
        <input type="submit" value ='Dang nhap'>
        
<!--        <form action="<c:url value="/sellerHomeServlet" />" method ="post">
        <input type="submit" value ='Dang nhap Seller'>-->
    </form>
</body>
</html>
