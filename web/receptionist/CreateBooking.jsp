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
    </head>
    <body>
        <div class="container">
            <div class="row my-2">
                <h2>Đặt phòng</h2>
            </div>
            <div class="row">
                <div class="col">
                    <form action="<c:url value="/CreateBookingServlet" />" method="post">
                        <input type="hidden" name="action"  value ="searchFreeRoom">
                        <div class="row">
                            <div class="col-2">
                                <div class="form-group">
                                    <label for="dt1">Check-in:</label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <input type="datetime-local" id="dt1" name="checkin" step="1" value="${checkinStr}">
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-2">
                                <div class="form-group">
                                    <label for="dt2">Check-out:</label>
                                </div>
                            </div>

                            <div class="col">
                                <div class="form-group">
                                    <input type="datetime-local" id="dt2" name="checkout" step="1" value="${checkoutStr}">
                                </div>
                            </div>
                        </div>
                        <input class="btn btn-primary"  type="submit" value="Tìm kiếm">
                    </form>
                </div>

            </div>

            <div class="row mt-4">
                <div class="col">
                    <form action="<c:url value="/CreateBookingServlet" />" method="post">
                        <input type="hidden" name="action"  value ="bookRoom">
                        <table  class="table table-striped"  cellspacing="5" cellpadding="5" border="1">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Cỡ</th>
                                    <th>Hạng</th>
                                    <th>Giá / h</th>
                                    <th>Mô tả</th>
                                    <th>Chọn</th>
                                </tr>
                            </thead>

                            </tr>
                            <c:forEach var="i" items="${listRoom}" varStatus="status">
                                <tr valign="top">
                                    <td><c:out value="${i.ID}"/></td>
                                    <td>${i.size}</td>
                                    <td>${i.type}</td>
                                    <td>${i.pricePerHour}</td>
                                    <td>${i.description}</td>

                                    <td>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="selectedItems" value="<c:out value="${status.index}"/>">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <input type="hidden" name="checkin" value="${checkin}">
                        <input type="hidden" name="checkout" value="${checkout}">
                        <input class="btn btn-primary" type="submit" value="Đặt phòng" name="update" />
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>

