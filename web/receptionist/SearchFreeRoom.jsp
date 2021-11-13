<%-- 
    Document   : SearchFreeRoomFrm
    Created on : Nov 9, 2021, 3:38:33 PM
    Author     : xxxx9
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<c:url value="/SearchFreeRoomFrmServlet"/>" method="POST">
            <input type="hidden" name="action" value="SEARCH_ROOM"/>
            <span>
                <label for="hours">Số giờ: </label>
                <input type="text" name="num_hour" id="hours"/>
                <input type="submit" value="Tìm phòng trống" />
            </span>
        </form>
        <br>

        <h2>Danh sách phòng trống</h2>
        <br>



        <form action="<c:url value="/SearchFreeRoomFrmServlet"/>" method="POST">
            <input type="hidden" name="action" value="SELECT_ROOM"/>

            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>Mã phòng</th>
                    <th>Cỡ phòng</th>
                    <th>Loại phòng</th>
                    <th>Mô tả</th>
                    <th>Giá</th>
                </tr>

                <c:forEach var="room" items="${rooms}" varStatus="status">
                    <tr>
                        <td>${room.ID}</td>
                        <td>${room.size}</td>
                        <td>${room.type}</td>
                        <td>${room.description}</td>
                        <td>${room.pricePerHour}</td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>

            </table>
            <br>

            <input type="submit" value="Chọn phòng" />
        </form>
    </body>
</html>

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Book Room</title>
    </head>
    <body>
        <h1>Dat phong</h1>
        <form action="<c:url value="/SearchFreeRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="searchFreeRoom">
            <!--<input type="hidden" name="user" value="${user}">-->
            <table>
                <tr>
                    <td align="right">Check-in:</td>
                    <td><input type="text" name="checkin" value="2021-11-10 00:00:00"></td>
                </tr>
                <tr>
                    <td align="right">Check-out:</td>
                    <td><input type="text" name="checkout" value="2021-11-10 01:00:00"></td>
                </tr>
                <tr>
                <input type="submit" value="Tim">
                </tr>
            </table>
        </form>
        <form action="<c:url value="/SearchFreeRoomServlet" />" method="post">
            <input type="hidden" name="action"  value ="bookRoom">
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>ID</th>
                    <th>Co</th>
                    <th>Hang</th>
                    <th>Gia</th>
                    <th>Mo ta</th>
                </tr>
                </tr>
                <c:forEach var="i" items="${listRoom}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.size}</td>
                        <td>${i.type}</td>
                        <td>${i.pricePerHour}</td>
                        <td>${i.description}</td>
                        <td><c:out value="${i.ID}"/></td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" name="checkin" value="${checkin}">
            <input type="hidden" name="checkout" value="${checkout}">
            <input type="submit" value="Dat phong" name="update" />
        </form>


    </body>
</html>

