<%-- 
    Document   : IncomeDetailView
    Created on : Nov 5, 2021, 8:44:56 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Income Detail View</title>
    </head>
    <body>

        <label>Thang: </label>
        <input type="text" name="startDate" value="${incomeStat.thang}" readonly>
        <br>
        <label>Tong doanh thu: </label>
        <input type="text" name="endDate" value="${incomeStat.income}" readonly>
        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>ID</th>
                <th>Payment Date</th>
                <th>Payment Type</th>
                <th>Note</th>
            </tr>
            <c:forEach var="i" items="${listBill}" varStatus="status">
                <tr valign="top">
                    <td>${i.ID}</td>
                    <td>${i.paymentDate}</td>
                    <td>${i.paymentType}</td>
                    <td>${i.note}</td>
                </tr>
            </c:forEach>
            <%request.getSession().removeAttribute("listBill");%>
        </table>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
            <input type="submit" value="Return">
        </form>
    </body>
</html>
