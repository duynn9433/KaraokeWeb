<%-- 
    Document   : ManagerHomeView
    Created on : Nov 5, 2021, 8:43:00 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Home View</title>
    </head>
    <body>
        <table>
            <tr>
                <td>
                    <form action="<c:url value="/ManagerHomeServlet"/>" method="post">
                        <input type="submit" value="Log out">
                    </form>
                </td>
                <td>Logged in as: ${user.name}</td>
            </tr>
        </table>
        <h1>Manager's Home</h1>
        <form action ="<c:url value="/manager/ManagerKaraoke.jsp"/>" method="post">
            <input type="submit" value="Quan li thong tin nha hang">
        </form>
        <form action ="<c:url value="/manager/.jsp"/>" method="post">
            <input type="submit" value="Quan li thong tin phong">
        </form>
        <form action ="<c:url value="/manager/ServiceManagementView.jsp"/>" method="post">
            <input type="submit" value="Quan li dich vu">
        </form>
        <form action ="<c:url value="/manager/SelectStatView.jsp"/>" method="post">
            <input type="submit" value="Xem bao cao thong ke">
        </form>
    </body>
</html>
