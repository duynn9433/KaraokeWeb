<%-- 
    Document   : IncomeStatView
    Created on : Nov 5, 2021, 8:44:39 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Income Statistic View</title>
    </head>
    <body>
        <form action="<c:url value="/IncomeStatServlet"/>" method="post">
            <label>Ngay bat dau: </label>
            <input type="text" name="startDate" value="01/01/2020">
            <label>Ngay ket thuc: </label>
            <input type="text" name="endDate" value="30/04/2020">
            <input type="hidden" name="action" value="thongKe">
            <input type="submit" value="Thong ke">
        </form>


        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>Ten thang</th>
                <th>Doanh thu</th>
            </tr>
        </tr>
        <c:forEach var="i" items="${listIncomeStat}" varStatus="status">
            <tr valign="top">
                <td>${i.thang}</td>
                <td>${i.income}</td>
                <td>
                    <form action="<c:url value="/IncomeStatServlet"/>" method="post"> 
                        <input type="hidden" name="income" value="${i.income}">
                        <input type="hidden" name="thang" value="${i.thang}">
                        <input type="hidden" name="action" value="chiTiet">
                        <input type="submit" value="Xem chi tiet">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
