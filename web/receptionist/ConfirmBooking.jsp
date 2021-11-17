<%-- 
    Document   : SellerConfirmView
    Created on : Nov 3, 2021, 8:43:57 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Confirm Booking</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("confirmBookingMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${confirmBookingMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("confirmBookingMsg");
            }
        %>
        <h2>Thong tin phong dat</h2>
        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>ID</th>
                <th>Ten</th>
                <th>Co</th>
                <th>Hang</th>
                <th>Gia</th>
                <th>Mo ta</th>
            </tr>
        </tr>
        <c:forEach var="i" items="${booking.listBookedRoom}" varStatus="status">
            <tr valign="top">
                <td>${i.room.ID}</td>
                <td>${i.room.name}</td>
                <td>${i.room.size}</td>
                <td>${i.room.type}</td>
                <td>${i.room.pricePerHour}</td>
                <td>${i.room.description}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Thong tin khach hang</h2>
    <table>
        <tr>
            <td>Ten: </td>
            <td>${booking.client.name}</td>
        </tr>
        <tr>
            <td>So dien thoai: </td>
            <td>${booking.client.phoneNumber}</td>
        </tr>
        <tr>
            <td>Dia chi: </td>
            <td>${booking.client.address}</td>
        </tr>
        <tr>
            <td>Mail: </td>
            <td>${booking.client.mail}</td>
        </tr>
        <tr>
            <td>Note: </td>
            <td>${booking.client.note}</td>
        </tr>
    </table>

    <form action ="<c:url value="/ConfirmBookingServlet"/>" method="post">
        <table>
            <tr>
                <td>Ghi chu: </td>
                <td><input type="text" name="note"></td>
                <td>Khuyen mai: </td>
                <td><input type="text" name="saleOff" value="0.0"></td>
            </tr>
        </table>
        <input type="hidden" name="action" value="Xacnhan">
        <input type="submit" value="Xac nhan">
    </form>
    <form action ="<c:url value="/receptionist/ReceptionistHome.jsp"/>" method="post">
        
        <input type="submit" value="Home">
    </form>

</body>
</html>
