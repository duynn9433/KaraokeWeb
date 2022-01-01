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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <br>
            <div class="row">
                <label>Tháng: ${incomeStat.thang}</label>
            </div>
            <div class="row">
                <label>Tổng doanh thu: ${incomeStat.income}</label>
            </div>
            <div class="row">
                <table class="table table-striped" cellspacing="5" cellpadding="5" border="1">
                    <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Payment Amount</th>
                        <th>Payment Date</th>
                        <th>Payment Type</th>
                        <th>Note</th>
                    </tr>
                    </thead>
                    <c:forEach var="i" items="${listBill}" varStatus="status">
                        <tr valign="top">
                            <td>${i.ID}</td>
                            <td>${i.paymentAmount}</td>
                            <td>${i.paymentDate}</td>
                            <td>${i.paymentType}</td>
                            <td>${i.note}</td>
                        </tr>
                    </c:forEach>
                    <%request.getSession().removeAttribute("listBill");%>
                </table>
                <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
                    <input class="btn btn-primary" type="submit" value="Return">
                </form>
            </div>
        </div>


    </body>
</html>
