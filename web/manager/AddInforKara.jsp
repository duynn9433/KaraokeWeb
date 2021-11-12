<%-- 
    Document   : AddInforKara
    Created on : Nov 12, 2021, 9:31:36 PM
    Author     : duynn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Information Karaoke Bar</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("addKaraMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${addKaraMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("addKaraMsg");
            }
        %>
        <h1>Them thong tin nha hang</h1>
        <form action="<c:url value="/AddInfoKaraServlet"/>" method="post">
            <table>
                <tr>
                    <td>Ten nha hang:</td>
                    <td><input type="text" name="name" value="name"></td>
                </tr>
                <tr>
                    <td>Dia chi:</td>
                    <td><input type="text" name="address" value="address"></td>
                </tr>
                <tr>
                    <td>Mo ta:</td>
                    <td><input type="text" name="des" value="des"></td>
                </tr>
                
            </table>
            <input type="hidden" name="action" value="them">
            <input type="submit" value="Them">
        </form>
        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
       
        <input type="submit" value="Home">
    </form>
    </body>
</html>
