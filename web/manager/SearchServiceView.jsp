<%-- 
    Document   : SearchServiceView
    Created on : Nov 13, 2021, 9:07:37 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Service</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Search Service</h1>
        <div>
            <form class = "form-inline" action="<c:url value="/SearchServiceServlet"/>" method="post">
                <input type ="hidden" name ="action" value ="search">
                <input class = "form-control" type ="text" name = "key" required>
                <input class = "btn btn-primary" type ="submit" name ="search" value ="Tim kiem">
                <input type="hidden" name="action"  value ="search">
            </form>
        </div><br><br>
        
        <form action="<c:url value="/SearchServiceServlet"/>" method="post">
            <input type="hidden" name="action" value ="edit">
            <table cellspacing="6" cellpadding="6" border="1">
                <tr>
                    <th>ID</th>
                    <th>Ten</th>
                    <th>Don vi</th>
                    <th>Gia</th>
                    <th>Mo ta</th>
                </tr>
                
                <c:forEach var="i" items="${listService}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.unity}</td>
                        <td>${i.pricePerUnit}</td>
                        <td>${i.description}</td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
            <input class = "btn btn-primary" type="submit" value="Sua" name="edit" />
        </form>
    </body>
</html>
