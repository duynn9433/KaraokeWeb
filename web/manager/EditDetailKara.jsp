<%-- 
    Document   : EditDetailKara
    Created on : Nov 12, 2021, 10:08:00 PM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Kara</title>
    </head>
    <body>
        <%
            String msg = (String) request.getSession().getAttribute("editKaraMsg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${editKaraMsg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("editKaraMsg");
            }
        %>
        <h1>Sua thong tin nha hang Karaoke</h1>
        <form action="<c:url value="/EditDetailKaraServlet" />" method="post">
            <table>
                <tr>
                    <td>ID: </td>
                    <td><input type="text" name="id" value="${karaokeBar.ID}" readonly></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name" value="${karaokeBar.name}"></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" value="${karaokeBar.address}"></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><input type="text" name="des" value="${karaokeBar.description}"></td>
                </tr>

            </table>
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Sua">
        </form>

        <form action ="<c:url value="/manager/ManagerHome.jsp"/>" method="post">
            <input type="submit" value="Home">
        </form>
    </body>
</html>
