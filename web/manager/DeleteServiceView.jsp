<%-- 
    Document   : DeleteServiceView
    Created on : Nov 13, 2021, 9:08:30 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delete Service</h1>
        <div>
            <form action="<c:url value="/DeleteServiceServlet"/>" method="post">
                <input type ="hidden" name ="action" value ="search">
                <input type ="text" name = "key">
                <input type ="submit" name ="search" value ="Tim kiem">
                <input type="hidden" name="action"  value ="search">
            </form>
        </div><br><br>

        <form action="<c:url value="/DeleteServiceServlet"/>" method="post">
            <input type="hidden" name="action" value ="delete">
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
            <input type="submit" value="Xoa" name="delete" />

        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">

            <input type="submit" value="Home">
        </form>
    </body>
</html>
