<%-- 
    Document   : SellerBookRoomInfo
    Created on : Nov 3, 2021, 2:51:50 PM
    Author     : duynn
--%>

<%@page import="java.util.List"%>
<%@page import="model.Client"%>
<%@page import="model.Room"%>
<%@page import="model.BookedRoom"%>
<%@page import="model.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Book Room Information (Search Client)</title>
    </head>
    <body>
        <h1>Thong tin dat phong</h1>
        <h2>Thong tin phong:</h2>
        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>ID</th>
                <th>Co</th>
                <th>Hang</th>
                <th>Gia</th>
                <th>Mo ta</th>
            </tr>
            <!--          <%
                String bookingID = request.getParameter("booking");
                Booking booking = (Booking) request.getSession().getAttribute("booking");
                for (BookedRoom br : booking.getListBookedRoom()) {
                    Room r = br.getRoom();
                    System.out.println(r.toString());
            %>
            <tr>
                <td> <%=r.getID()%> </td>
                <td> <%=r.getSize()%> </td>
                <td> <%=r.getType()%> </td>
                <td> <%=r.getPricePerHour()%> </td>
                <td> <%=r.getDescription()%> </td>
            </tr>    
            <%
                }
            %> -->
            <c:forEach var="i" items="${booking.listBookedRoom}" varStatus="status">
                <tr valign="top">
                    <td>${i.room.ID}</td>
                    <td>${i.room.size}</td>
                    <td>${i.room.type}</td>
                    <td>${i.room.pricePerHour}</td>
                    <td>${i.room.description}</td>
                </tr>
            </c:forEach>
        </table>

        <h2> Thong tin khach hang:</h2>

        <form action="<c:url value="/SellerBookRoomInfo" />" method="post">
           <table>
                <tr>
                    <td>Ten: </td>
                    <td><input type="text" name="name" value="Nguyen A"></td>
                </tr>
                <tr>
                    <td>So dien thoai: </td>
                    <td><input type="text"  name="phoneNumber" value="2000001"></td>
                </tr>
                    
            </table>
            <input type="submit" value="Tim">
            <input type="hidden" name="action" value="Tim">
        </form>

        <form action="<c:url value="/SellerBookRoomInfo" />" method="post">
            <input type="hidden" name="action" value="Them">
            <input type="hidden" name="name" value="${name}">
            <input type="hidden" name="phoneNumber" value="${phoneNumber}">
            
            <input type="submit" value="Them">
        </form>

        <form action="<c:url value="/SellerBookRoomInfo" />" method="post">
            <table cellspacing="5" cellpadding="5" border="1">
                <tr>
                    <th>ID</th>
                    <th>Ten</th>
                    <th>So dien thoai</th>
                    <th>Dia chi</th>
                    <th>Mail</th>
                    <th>Note</th>
                </tr>
                <c:forEach var="i" items="${listClient}" varStatus="status">
                    <tr valign="top">
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.phoneNumber}</td>
                        <td>${i.address}</td>
                        <td>${i.mail}</td>
                        <td>${i.note}</td>
                        <td><input type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>"></td>
                    </tr>
                </c:forEach>    
            </table>
            <input type="hidden" name="action" value="Luu">
            <input type="submit" value="Luu">
        </form>

    </body>
</html>
