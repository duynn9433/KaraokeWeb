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
    </head>
    <body>
        <form action="<c:url value="/AddServicesServlet"/>" method="POST">
            <label for="service_name">Tìm dịch vụ: </label>
            <input type="text" name="service_name" id="service_name"/>
            <input type="submit" value="Tìm dịch vụ" name="SEARCH_SERVICE" />
            
            
            <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">
                <h3>Phòng ${bookedRoom.room.ID}</h3>

                <c:forEach var="usedService" items="${bookedRoom.listUsedService}" varStatus="statusBookedRoom">

                    <table cellspacing="5" cellpadding="5" border="1">
                        <tr>
                            <th>Tên dịch vụ</th>
                            <th>Đơn vị</th>
                            <th>Số lượng</th>
                            <th>Đơn giá</th>
                        </tr>
                        <c:forEach var="bookedRoom" items="${booking.listBookedRoom}" varStatus="statusBookedRoom">
                            <tr valign="top">
                                <td>${usedService.service.name}</td>
                                <td>${usedService.service.unity}</td>   
                                <td><input type="text" name="amountServices" value="0"></td>
                                <td>${usedService.service.pricePerUnit}</td>

                            </tr>
                        </c:forEach>
                    </table>

                </c:forEach>
            </c:forEach>

            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Tên dịch vụ</th>
                    <th>Đơn vị</th>
                    <th>Đơn giá</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listServices}" varStatus="status">
                    <tr valign="top">
                        <td>${i.name}</td>
                        <td>${i.unity}</td>
                        <td>${i.pricePerUnit}</td>
                        <td><input type="checkbox" name="selectedService" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
                
            <input type="submit" value="Chọn" name="ADD_SERVICE" />
            <input type="submit" value="Xong" name="DONE" />
        </form>


    </body>
</html>
