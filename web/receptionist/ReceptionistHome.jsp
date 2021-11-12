<%-- 
    Document   : ReceptionistHoneFrm
    Created on : Nov 11, 2021, 8:02:48 AM
    Author     : xxxx9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhan vien le tan</title>
    </head>
    <body>
        <form action="<c:url value="/ReceptionistHomeServlet"/>" method="POST">
            <input type="hidden" name="action" value="ACTION_CHECKIN"/>
            <input type="submit" value="Checkin" />
        </form>

        <form action="<c:url value="/ReceptionistHomeServlet"/>" method="POST">
            <input type="hidden" name="action" value="ACTION_CHECKOUT"/>
            <input type="submit" value="Checkout" />
        </form>
    </body>
</html>
