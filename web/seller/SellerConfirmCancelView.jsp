<%-- 
    Document   : SellerConfirmCancelView
    Created on : Nov 5, 2021, 8:41:01 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Confirm Cancel View</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("sellerConfirmCancelMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${sellerConfirmCancelMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("sellerConfirmCancelMsg");
            }
        %>
        <h1>Thong tin huy phong</h1>


        <table cellspacing="5" cellpadding="5" border="1">
            <tr>
                <th>ID Phong</th>
                <th>Ten</th>
                <th>Co</th>
                <th>Hang</th>
                <th>Gia</th>
                <th>Checkin</th>
                <th>Checkout</th>
            </tr>

            <c:forEach var="j" items="${listDeleteBookedRoom}" >
                <tr valign="top">
                    <td>${j.room.ID}</td>
                    <td>${j.room.name}</td>
                    <td>${j.room.size}</td>
                    <td>${j.room.type}</td>
                    <td>${j.room.pricePerHour}</td>
                    <td>${j.checkin}</td>
                    <td>${j.checkout}</td>
                </tr>
            </c:forEach>
        </table>
        <form action="<c:url value="/SellerConfirmCancelServlet" />" method="post">
            <input type="hidden" name="action"  value ="xacNhan">
            <input type="submit" value="Xac nhan"  />
        </form>
        <form action ="<c:url value="/seller/SellerHome.jsp"/>" method="post">

            <input type="submit" value="Home">
        </form>
    </body>
</html>
