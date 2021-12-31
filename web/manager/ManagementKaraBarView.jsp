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
    </head>
    <body>
        <h1>Managemet Karaoke Bar</h1>
        <button onclick="location.href ='AddInforKaraView.jsp'"> Add Infor</button><br>
        <button onclick="location.href ='<c:url value="/GetInforKara"/>'"> Edit Infor</button><br>
    </body>
</html>
