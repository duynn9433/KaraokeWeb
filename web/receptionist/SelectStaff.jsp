<%-- 
    Document   : SelectStaffFrm
    Created on : Nov 10, 2021, 9:20:14 PM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chọn nhân viên</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row my-3">
                <h3>Chọn nhân viên phục vụ: </h3>
            </div>
            <div class="row">
                <form action="<c:url value="/SelectStaffServlet" />" method="post">

                <div class="container">
                    <div class="row mb-3">
                        <h4>Danh sách nhân viên đã chọn</h4>
                    </div>
                    <div class="row mb-3">
                        <table class="table" cellspacing="5" cellpadding="5" border="1">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Id Phòng</th>
                                    <th>Nhân viên 1</th>
                                    <th>Nhân viên 2</th>
                                    <th>Chọn</th>
                                </tr>
                            </thead>
                           
                            <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">
                                <tr valign="top">
                                    <td>${bookedRoom.room.ID}</td>
                                    <td>${bookedRoom.listHiredStaff[0].user.name}</td>
                                    <td>${bookedRoom.listHiredStaff[1].user.name}</td>
            
                                    <td><input type="checkbox" name="selectedBooked" value="<c:out value = "${statusBookedRoom.index}"/>"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="row mb-3">
                        <h4>Danh sách nhân viên còn trống:</h4>
                    </div>
                    <div class="row">
                        <table class="table" cellspacing="5" cellpadding="5" border="1">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Tên nhân viên</th>
                                    <th>Số giờ phục vụ</th>
                                    <th>Chọn</th>
                                </tr>
                            </thead>
                            
                            </tr>
                            <c:forEach var="i" items="${listStaffs}" varStatus="status">
                                <tr valign="top">
                                    <td>${i.name}</td>
                                    <td>0</td>
                                    <td><input type="checkbox" name="selectedStaff" value="<c:out value="${status.index}"/>"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="row mb-5">
                    <div class="col-2">
                        <input class="btn btn-primary" type="submit" value="Chọn" name="select_staff" />
                    </div>
                    <div class="col-2">
                        <input class="btn btn-primary" type="submit" value="Xong" name="select_done" />
                    </div>
                </div>
            </form>
            </div>
        </div>    
    </body>
</html>
