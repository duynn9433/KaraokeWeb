<%-- 
    Document   : IncomeStatView
    Created on : Nov 5, 2021, 8:44:39 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%
        String msg = (String) request.getSession().getAttribute("msg");
        System.out.println("View" + msg);
        //msg="luu that bai";
        if (msg == null) {

        } else {
    %>
    <script type="text/javascript">
        var msg = "${msg}";
        alert(msg);
    </script>
    <%
            request.getSession().removeAttribute("msg");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Income Statistic View</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Thông tin thống kê</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <form action="<c:url value="/SelectStatServlet"/>" method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-6">
                                    <label for="obj">Đối tượng thống kê</label>
                                </div>
                                <div class="col-6">
                                    <select class="form-control" id="obj" name="object">
                                        <option value="doanhThu">Doanh Thu</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-6">
                                    <label for="type">Loại thống kê</label>
                                </div>
                                <div class="col-6">
                                    <select class="form-control" id="type" name="type">
                                        <option value="thoiGian">Thời gian</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-6">
                                    <label for="mode">Chế độ thống kê</label>
                                </div>
                                <div class="col-6">
                                    <select class="form-control" id="mode" name="mode">
                                        <option value="thang">Tháng</option>
                                        <option value="quy">Quý</option>
                                        <option value="nam">Năm</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="action" value="thongKe">
                        <input class="btn btn-primary" type="submit" value="Thống kê">
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>
