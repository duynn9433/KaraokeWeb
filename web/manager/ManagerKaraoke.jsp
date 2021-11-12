<%-- 
    Document   : ManagerKaraoke
    Created on : Nov 12, 2021, 9:23:50 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Karaoke</title>
    </head>
    <body>
        <h1>Quan li thong tin nha hang Karaoke</h1>
        <form action ="<c:url value="/manager/AddInforKara.jsp"/>" method="post">
            <input type="submit" value="Them thong tin nha hang">
        </form>
        <form action ="<c:url value="/EditInforKaraServlet"/>" method="post">
            <input type="submit" value="Sua thong tin nha hang">
        </form>
    </body>
</html>
