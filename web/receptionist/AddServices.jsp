<%-- 
    Document   : AddServices
    Created on : Nov 13, 2021, 10:53:18 PM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm dịch vụ</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>

        <style>
            .big-checkbox {
                width: 20px;
                height: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="row mt-3">
                <h3>Thêm dịch vụ</h3>
            </div>
            <div class="row">
                <div class="col">
                    <form class="my-1" action="<c:url value="/AddServicesServlet"/>" method="POST">

                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-2">
                                                <label for="service_name">Tìm dịch vụ: </label>
                                            </div>
                                            <div class="col-5">
                                                <input class="form-control" type="text" name="service_name" id="service_name"/>
                                            </div>
                                            <div class="col">
                                                <input class="btn btn-primary" type="submit" value="Tìm dịch vụ" name="SEARCH_SERVICE" />
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <h4>Danh sách phòng</h4>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">

                                        <div class="container">
                                            <div class="row my-2">
                                                <div class="col-2" style="display: flex; align-items: center;">
                                                    <div class="row">
                                                        <td><input class="big-checkbox" type="checkbox" name="selectedBookedRoom" value="<c:out value="${statusBookedRoom.index}"/>"></td>
                                                        <h6 class="ml-3">Phòng ${bookedRoom.room.ID}</h6>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <table class="table" cellspacing="5" cellpadding="5" border="1">
                                                        <thead class="thead-dark">
                                                            <tr>
                                                                <th>Tên dịch vụ</th>
                                                                <th>Đơn vị</th>
                                                                <th>Số lượng</th>
                                                                <th>Đơn giá</th>
                                                                <th>Ghi chú</th>
                                                            </tr>
                                                        </thead>

                                                        <c:forEach var="usedService" items="${bookedRoom.listUsedService}" varStatus="statusService">
                                                            <tr valign="top">
                                                                <td>${usedService.service.name}</td>
                                                                <td>${usedService.service.unity}</td>   
                                                                <td><input type="text" name="amountServices" value="${usedService.amount}"></td>
                                                                <td>${usedService.service.pricePerUnit}</td>
                                                                <td><input type="text" name="noteServices" value="${usedService.note}"></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>
                            </div>

                            <div class="row">
                                <h4 class="my-2">Danh sách dịch vụ</h4>
                            </div>

                            <div class="row">
                                <div class="col-8">
                                    <table class="table my-3" cellspacing="5" cellpadding="5" border="1">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Tên dịch vụ</th>
                                                <th>Đơn vị</th>
                                                <th>Đơn giá</th>
                                                <th>Chọn</th>
                                            </tr>
                                        </thead>


                                        <c:forEach var="i" items="${listServices}" varStatus="status">
                                            <tr valign="top">
                                                <td>${i.name}</td>
                                                <td>${i.unity}</td>
                                                <td>${i.pricePerUnit}</td>
                                                <td><input class="big-checkbox" type="checkbox" name="selectedService" value="<c:out value="${status.index}"/>"></td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                </div>

                            </div>
                        </div>
                        <input class="btn btn-primary" type="submit" value="Chọn" name="ADD_SERVICE" />
                        <input class="btn btn-primary" type="submit" value="Xong" name="DONE" />
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>
