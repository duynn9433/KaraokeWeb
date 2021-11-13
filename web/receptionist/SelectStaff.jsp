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
        <title>Select Staff</title>
    </head>
    <body>
        <form action="<c:url value="/SelectStaffServlet" />" method="post">
            
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Id Phòng</th>
                    <th>Nhân viên 1</th>
                    <th>Nhân viên 2</th>
                </tr>
                    <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">
                        <tr valign="top">
                            <td>${bookedRoom.room.ID}</td>
                            <td>${bookedRoom.listHiredStaff[0].user.name}</td>
                            <td>${bookedRoom.listHiredStaff[1].user.name}</td>

                            <td><input type="checkbox" name="selectedBooked" value="<c:out value = "${statusBookedRoom.index}"/>"></td>
                        </tr>
                    </c:forEach>
            </table>
            
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Tên nhân viên</th>
                    <th>Số giờ phục vụ</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listStaffs}" varStatus="status">
                    <tr valign="top">
                        <td>${i.name}</td>
                        <td>0</td>
                        <td><input type="checkbox" name="selectedStaff" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
            
            
            <input type="submit" value="Chọn" name="select_staff" />
            <input type="submit" value="Xong" name="select_done" />
        </form>
    </body>
</html>
