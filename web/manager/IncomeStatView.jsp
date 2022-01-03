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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <br>
            <div class="row">
                <div class="col">
                    <form action="<c:url value="/IncomeStatServlet"/>" method="post">
                        <div class="row">
                            <div class="col-2">
                                <div class="form-group">
                                    <label for="dt1">Ngày bắt đầu:</label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <input type="date" id="dt1" name="startDate" step="1" value="2020-01-01">
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-2">
                                <div class="form-group">
                                    <label for="dt2">Ngày kết thúc:</label>
                                </div>
                            </div>

                            <div class="col">
                                <div class="form-group">
                                    <input type="date" id="dt2" name="endDate" step="1" value="2020-04-30">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="action" value="thongKe">
                        <input class="btn btn-primary"  type="submit" value="Thống kê">
                    </form>
                </div>
            </div>
            <br>
            <div class="row">
                <table class="table table-striped" cellspacing="5" cellpadding="5" border="1">
                    <thead class="thead-dark">
                        <tr>
                            <th>Tên tháng</th>
                            <th>Doanh thu</th>
                            <th>Xem chi tiết</th>
                        </tr>
                    </thead>

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
            </div>


    </body>
</html>
