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
        <title>Receptionist Add New Client</title>
    </head>
    <body>
        <h1>Them khach hang moi</h1>
        <form action="<c:url value="/AddClientServlet" />" method="post">
            <table>
                <tr>
                    <td>Name: </td>
                    <td> <input type="text" name="name" value="${name}"></td>
                </tr>
                <tr>
                    <td>Phone number: </td>
                    <td> <input type="text" name="phoneNumber" value="${phoneNumber}"></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td> <input type="text" name="address" value=""></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td> <input type="text" name="email" value=""></td>
                </tr>
                <tr>
                    <td>Note: </td>
                    <td> <input type="text" name="note" value=""></td>
                </tr>
            </table>
            <input type="hidden" name="action"  value="them">
            <input type="submit" value="Them">

        </form>
    </body>
</html>
