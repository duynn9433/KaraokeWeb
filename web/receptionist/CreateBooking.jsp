<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo booking</title>

        <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
              crossorigin="anonymous">
        <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
        <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>

        <!-- <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.css"> -->
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css"> 
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker-standalone.css"> 
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>

    </head>
    <body>
        <div class="container">

        </div>

        <h1>Dat phong</h1>
        <form action="<c:url value="/CreateBookingServlet" />" method="post">
            <input type="datetime-local" id="dt1" name="bdaytime" step="1" value="2021-12-30 22:26:00">

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
        <form action="<c:url value="/CreateBookingServlet" />" method="post">
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

        <script type="text/javascript">
         </script>
    </body>
</html>

