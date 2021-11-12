<%-- 
    Document   : EditInforKara
    Created on : Nov 12, 2021, 10:07:31 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Danh sach thong tin nha hang Karaoke</h1>
        <form action="<c:url value="/EditInforKaraServlet" />" method="post">
            <input type="hidden" name="action"  value ="edit">
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>ID</th>
                    <th>Ten</th>
                    <th>Dia chi</th>
                    <th>Mo ta</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listKara}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.address}</td>
                        <td>${i.description}</td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Sua" name="update" />
        </form>
    </body>
</html>
